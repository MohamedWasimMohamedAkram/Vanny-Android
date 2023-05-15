package com.example.vanny;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import java.io.IOException;


public class HomeActivity extends AppCompatActivity {

    private ImageView imageView;
    private Socket socket;
    private DataInputStream dataInputStream;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageView = findViewById(R.id.stream_container);
        running = true;

        // Start a separate thread for receiving the video stream
        Thread receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("10.0.2.2",5000);
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    Log.d("Socket", "Connected to server");

                    while (running) {
                        // Read the length of the incoming image
                        int length = dataInputStream.readInt();
                        if (length > 0) {
                            byte[] imageBytes = new byte[length];
                            dataInputStream.readFully(imageBytes, 0, imageBytes.length);

                            // Convert the image bytes to a Bitmap
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
                            final Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);

                            // Display the bitmap on the ImageView in the main UI thread
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("Socket", "Connection Failed");
                }
            }
        });
        receiveThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        running = false;

        try {
            if (dataInputStream != null)
                dataInputStream.close();

            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}