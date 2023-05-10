package com.example.vanny;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

import java.io.ByteArrayInputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;



public class HomeActivity extends AppCompatActivity {

    private VideoView videoView;
    private String streamUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MediaController mediaController = new MediaController(this);
        Button pairButton = findViewById(R.id.pairButton);

        pairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get a reference to the VideoView
                videoView = findViewById(R.id.streamContainer);

                // Create a MediaController to control the playback
                mediaController.setAnchorView(videoView);

                // Set the MediaController to the VideoView
                videoView.setMediaController(mediaController);

                // Set the video URI to the MJPEG-Streamer URL
                Uri videoUri = Uri.parse(streamUrl);
                videoView.setVideoURI(videoUri);

                // Start the video playback
                videoView.start();
            }
        });
    }
}