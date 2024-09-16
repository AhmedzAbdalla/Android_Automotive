package com.example.webserviceandjsonparser;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.*;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView editTitle;
    TextView editPrice;
    TextView editBrand;
    TextView editDescription;
    Button btnPrev;
    Button btnNext;
    ImageView img;
    //img = findViewById(R.id.imageView);

    Handler handler = new Handler(Looper.getMainLooper());
    private int counter;

    static JSONArray contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTitle = findViewById(R.id.editTitle);
        editPrice = findViewById(R.id.editPrice);
        editBrand = findViewById(R.id.editBrand);
        editDescription = findViewById(R.id.editDescription);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        img = findViewById(R.id.imageView);


        Handler handler_title = new Handler(Looper.getMainLooper())
        {
            @Override
            public void handleMessage(Message msg)
            {
                JSONObject testobj = (JSONObject)msg.obj;
                try {
                    editTitle.setText( testobj.getString("title"));
                    editPrice.setText( testobj.getString("price"));
                    editBrand.setText( testobj.getString("brand"));
                    editDescription.setText( testobj.getString("description"));
                    String url = testobj.getString("thumbnail").toString();
                    Download myDownload = new Download();
                    myDownload.execute(url);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Handler handler_2 = new Handler(Looper.getMainLooper())
        {
            @Override
            public void handleMessage(Message msg)
            {
                JSONObject testobj = (JSONObject)msg.obj;
                try {
                    editTitle.setText( testobj.getString("title"));
                    editPrice.setText( testobj.getString("price"));
                    editBrand.setText( testobj.getString("brand"));
                    editDescription.setText( testobj.getString("description"));
                    String url = testobj.getString("thumbnail").toString();
                    Download myDownload = new Download();
                    myDownload.execute(url);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        //==============================
        // Start a new thread to perform the network operation
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpHandler sh = new HttpHandler();
                // Making a request to url and getting response
                String url = "https://dummyjson.com/products";
                String jsonStr = sh.makeServiceCall(url);
                JSONObject jsonObj = null;
                JSONObject testobj;
                try {

                    jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("products");
                    testobj = contacts.getJSONObject(0);
                    //Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                Message msg = handler_title.obtainMessage();
                msg.obj=testobj;
                handler_title.sendMessage(msg);


            }
        }).start();

        //==============================

        //==============================
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start a new thread to perform the network operation
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpHandler sh = new HttpHandler();
                        // Making a request to url and getting response
                        String url = "https://dummyjson.com/products";
                        String jsonStr = sh.makeServiceCall(url);
                        JSONObject jsonObj = null;
                        JSONObject testobj;
                        try {

                            jsonObj = new JSONObject(jsonStr);
                            // Getting JSON Array node
                            JSONArray contacts = jsonObj.getJSONArray("products");
                            int arrayLength = contacts.length();
                            // Check if counter is within a valid range
                            if (counter < arrayLength)
                            {
                                testobj = contacts.getJSONObject(++counter);
                                Message msg = handler_2.obtainMessage();
                                msg.obj=testobj;
                                handler_2.sendMessage(msg);
                            }

                            //Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }




                    }
                }).start();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start a new thread to perform the network operation
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpHandler sh = new HttpHandler();
                        // Making a request to url and getting response
                        String url = "https://dummyjson.com/products";
                        String jsonStr = sh.makeServiceCall(url);
                        JSONObject jsonObj = null;
                        JSONObject testobj = null;
                        try {

                            jsonObj = new JSONObject(jsonStr);
                            // Getting JSON Array node
                            JSONArray contacts = jsonObj.getJSONArray("products");
                            int arrayLength = contacts.length();
                            // Check if counter is within a valid range
                            if (counter > 0)
                            {
                                testobj = contacts.getJSONObject(--counter);
                                Message msg = handler_2.obtainMessage();
                                msg.obj=testobj;
                                handler_2.sendMessage(msg);
                            }
                                 // Decrement and get the previous item


                            //Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }




                    }
                }).start();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }




    //========================================

    // ========================================
    public class HttpHandler {

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

    private Bitmap Download(String l_URL)
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
        }
    }
}