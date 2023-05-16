package com.example.vanny;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.io.IOException;
public class HomeActivity extends AppCompatActivity {

    private ImageView imageView;
    private Handler handler;
    private boolean running;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageView = findViewById(R.id.stream_container);
        handler = new Handler();
        running = true;

        // Start a separate thread for receiving the video stream
        Thread receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    try {
                        // Connect to the video stream URL
                        URL url = new URL("http://127.0.0.1:5000");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");

                        // Read the video stream as an InputStream
                        InputStream inputStream = new BufferedInputStream(connection.getInputStream());

                        // Convert the InputStream to a Bitmap
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        // Update the ImageView in the main UI thread
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });

                        // Close the connection and release resources
                        connection.disconnect();
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        receiveThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        running = false;
    }
}