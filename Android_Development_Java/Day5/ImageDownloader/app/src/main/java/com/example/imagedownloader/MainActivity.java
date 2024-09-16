package com.example.imagedownloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    TextView editurl;
    Button btnDownload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imageView);
        editurl = findViewById(R.id.editurl);
        btnDownload = findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @   Override
            public void onClick(View view) {
                String url = editurl.getText().toString();
                Download myDownload = new Download();
                //try me
                //https://img.freepik.com/premium-photo/manekineko-full-body-view-with-blue-white-orange-mockup_974726-452.jpg?w=826
                myDownload.execute(url);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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


