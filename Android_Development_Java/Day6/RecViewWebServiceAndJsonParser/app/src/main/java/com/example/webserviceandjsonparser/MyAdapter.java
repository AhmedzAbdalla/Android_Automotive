package com.example.webserviceandjsonparser;




import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private final Context context;
    //private List<cake> values;
    private static JSONObject testobj;
    private static final String TAG = "RecyclerView";
    public MyAdapter(Context context, JSONObject mytestobj)
    {
        //values = tst;
        this.testobj = mytestobj;
        this.context = context;;
    }
    public ImageView img;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView editTitle;
        public TextView editPrice;
        public TextView editBrand;
        public TextView editDescription;
        //public Button btnPrev;
        //public Button btnNext;

        public RatingBar ratingBarr;


        public ConstraintLayout constraintLayout;
        public View layout;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View v) {
            super(v);
            layout = v;
            editTitle = v.findViewById(R.id.editTitle);
            editPrice = v.findViewById(R.id.editPrice);
            editBrand = v.findViewById(R.id.editBrand);
            editDescription = v.findViewById(R.id.editDescription);
            //btnPrev = v.findViewById(R.id.btnPrev);
            //btnNext = v.findViewById(R.id.btnNext);
            img = v.findViewById(R.id.imageView);
            ratingBarr = v.findViewById(R.id.ratingBarr);
            ratingBarr.setEnabled(false);
            constraintLayout = v.findViewById(R.id.row);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.recycle_layout, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "=== onCreateViewHolder ===");
        //return vh;
        return vh;
    }
    JSONObject testobj2;;

    Handler handler_title = new Handler(Looper.getMainLooper())
    {
        @Override
        public void handleMessage(Message msg)
        {
            Log.e(TAG, "MalformedURLExceptionPPP");
            JSONObject testobj = (JSONObject)msg.obj;
            testobj2 = testobj;
            Log.e(TAG, "MalformedURLExceptionLLL");
            try {
                //editTitle.setText( testobj.getString("title"));
                //editPrice.setText( testobj.getString("price"));
                //editBrand.setText( testobj.getString("brand"));
                //editDescription.setText( testobj.getString("description"));
                String url = testobj.getString("thumbnail").toString();
                double ratingString = testobj.getDouble("rating");
                float rating = (float) (ratingString /2);
                Log.e(TAG, "MalformedURLException: ");
                //ratingBar.setRating(rating);
                //ratingBar.setRating(Float.parseFloat(String.valueOf(testobj.getString("rating")) + "f"));
                //Download myDownload = new Download();
                //myDownload.execute(url);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    };

    //=444444444444444444444444444444444
    Bitmap l_bitmap;
    Handler handler_img = new Handler(Looper.getMainLooper())
    {
        @Override
        public void handleMessage(Message msg)
        {
            l_bitmap = (Bitmap) msg.obj;
        }
    };
    //44444444444444444444444444444444444444
static int counter = 0;
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpHandler sh = new HttpHandler();
                // Making a request to url and getting response
                String url = "https://dummyjson.com/products";
                String jsonStr = sh.makeServiceCall(url);
                JSONObject jsonObj = null;
                JSONObject testobj;
                Log.e(TAG, "Malform>>>edURLException");
                try {

                    jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("products");
                    testobj = contacts.getJSONObject(counter++);
                    Log.e(TAG, "Malform<<<edURLException");
                    //Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                Message msg = handler_title.obtainMessage();
                msg.obj=testobj;
                handler_title.sendMessage(msg);
                Log.e(TAG, "Malform>>>edURLException????");


            }
        }).start();


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if(null != testobj2)
                    {
                        try {
                            holder.editTitle.setText(testobj2.getString("title"));
                            holder.editPrice.setText(testobj2.getString("price"));
                            holder.editBrand.setText(testobj2.getString("brand"));
                            holder.editDescription.setText(testobj2.getString("description"));
                            String url = testobj2.getString("thumbnail").toString();
                            double ratingString = testobj2.getDouble("rating");
                            float rating = (float) (ratingString /2);
                            holder.ratingBarr.setRating(rating);
                            Download myDownload = new Download();
                            myDownload.execute(url);
                            //TimeUnit.SECONDS.sleep(1);

                            Log.e(TAG, "MalformedURLException: " + rating);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    }
                    else
                    {
                        Toast.makeText(context.getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                    }
                    // Code to execute after the delay
                }
            }, 900); // Delay in milliseconds (e.g., 2000ms = 2 seconds)

            Log.e(TAG, "Malform>>،،،>edURLException????");


    }
    @Override
    public int getItemCount() {
        return 30;
    }

    public static class HttpHandler {

        private final String TAG = HttpHandler.class.getSimpleName();

        public HttpHandler() {
        }

        public String makeServiceCall(String reqUrl) {
            String response = null;
            try {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                // read the response
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException: " + e.getMessage());
            } catch (ProtocolException e) {
                Log.e(TAG, "ProtocolException: " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
            return response;
        }

        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return sb.toString();
        }
    }

    private static Bitmap Download(String l_URL)
    {
        Bitmap myimg;
        try {
            URL imgurl = new URL(l_URL);
            HttpURLConnection myconn = (HttpURLConnection)imgurl.openConnection();
            myconn.setRequestMethod("GET");
            myconn.connect();
            int ResponseCode = myconn.getResponseCode();
            InputStream IS = myconn.getInputStream();
            myimg = BitmapFactory.decodeStream(IS);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return myimg;
    }

    class Download extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String ...urls) {
            Bitmap result = Download(urls[0]);
            return result;


        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
            //Message msg = handler_img.obtainMessage();
            //msg.obj=bitmap;
            //handler_img.sendMessage(msg);
        }
    }

}

