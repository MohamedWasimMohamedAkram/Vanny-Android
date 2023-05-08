package com.example.vanny;

import org.zeromq.ZMQ;
import java.io.ByteArrayInputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;



public class HomeActivity extends AppCompatActivity {

    private ImageView imageView;
    private Handler handler;
    private ZMQ.Socket subscriber;
    private Thread subscriberThread;
    private volatile boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageView = findViewById(R.id.streamContainer);
        handler = new Handler(Looper.getMainLooper());
        running = true;
        subscriberThread = new Thread(() -> {
            ZMQ.Context context = ZMQ.context(1);
            subscriber = context.socket(ZMQ.SUB);
            subscriber.connect("tcp://<RASPBERRY_PI_IP_ADDRESS>:5555");
            subscriber.subscribe("".getBytes());
            while (running) {
                byte[] frameBytes = subscriber.recv();
                if (frameBytes != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(frameBytes));
                    handler.post(() -> imageView.setImageBitmap(bitmap));
                }
            }
            subscriber.close();
            context.term();
        });
        subscriberThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        running = false;
        try {
            subscriberThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}