package com.example.vanny;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//import org.eclipse.paho.android.service.MqttAndroidClient;
import info.mqtt.android.service.Ack;
import info.mqtt.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.util.Arrays;


public class HomeActivity extends AppCompatActivity {

    private Button pairBtn;
    private static final String TAG = "HomeActivity";
    private String topic, clientID;
    private MqttAndroidClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {
            init();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }


    }

    private void init() throws MqttException {
        String topic = "vanny_test/mqtt";

        System.out.println( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());

        String broker = "tcp://139.179.185.15";
        String clientId = MqttClient.generateClientId();

        Button pairBtn = findViewById(R.id.pairButton);

        // persistence
        MemoryPersistence persistence = new MemoryPersistence(); // MQTT connect options
        MqttConnectOptions connOpts = new MqttConnectOptions();



        MqttClient client = new MqttClient(broker, clientId, persistence);
        // callback
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.d("tag","message>>" + new String(message.getPayload()));
                Log.d("tag","topic>>" + topic);

                // CODE below does not work but we can receive properly
                //byte[] decodedImageBytes = Base64.decode(message.getPayload(), Base64.DEFAULT);

                //Bitmap bitmap = BitmapFactory.decodeByteArray(decodedImageBytes, 0,
                //        message.getPayload().length);

                //ImageView img = (ImageView) findViewById(R.id.streamContainer);
                //Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap,img.getWidth(),img.getHeight(),true);
                //img.setImageBitmap(bitmap1);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
        System.out.println("Connecting to broker: " + broker);
        client.connect(connOpts);
        System.out.println("Connected to broker: " + broker);

        client.subscribe(topic);
        System.out.println("Subscribed to topic: " + topic);


        pairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectx();
            }
        });
    }

    public void connectx(){
        String clientId = MqttClient.generateClientId();
        MqttAndroidClient client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883", clientID, Ack.AUTO_ACK);

        IMqttToken token = client.connect();
        token.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                // We are connected
                client.subscribe(topic, 0);
                Log.d(TAG, "onSuccess");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                // Something went wrong e.g. connection timeout or firewall problems
                Log.d(TAG, "onFailure");

            }
        });
    }

}