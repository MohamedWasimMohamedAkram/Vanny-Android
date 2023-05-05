package com.example.vanny;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
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

    static String USERNAME = "admin";
    static String PASSWORD = "admin";
    String topicStr = "pleasework";

    private ImageView imageView;
    private MqttAndroidClient mqttAndroidClient;
    private Handler handler;
    private final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageView = findViewById(R.id.streamContainer);
        handler = new Handler();

        String brokerUrl = "ws://broker.emqx.io:8083";
        String clientId = "mqttx_c5bad67b";
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);
        connectOptions.setUserName(USERNAME);
        connectOptions.setPassword(PASSWORD.toCharArray());
        MemoryPersistence persistence = new MemoryPersistence();
        mqttAndroidClient = new MqttAndroidClient(this, brokerUrl, clientId, persistence);


        Button pairButton = findViewById(R.id.pairButton);
        pairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    IMqttToken token = mqttAndroidClient.connect(connectOptions);
                    token.setActionCallback(new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i(TAG, "Connected to MQTT broker");
                            try {
                                mqttAndroidClient.subscribe("my/topic", 0, new IMqttMessageListener() {
                                    @Override
                                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                byte[] payload = message.getPayload();
                                                Bitmap bitmap = BitmapFactory.decodeByteArray(payload, 0, payload.length);
                                                imageView.setImageBitmap(bitmap);
                                            }
                                        });
                                    }
                                });
                            } catch (MqttException ex) {
                                Log.e(TAG, "Failed to subscribe to MQTT topic", ex);
                            }
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.e(TAG, "Failed to connect to MQTT broker", exception);
                        }
                    });
                } catch (MqttException ex) {
                    Log.e(TAG, "Failed to connect to MQTT broker", ex);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mqttAndroidClient.disconnect();
        } catch (MqttException ex) {
            Log.e(TAG, "Failed to disconnect from MQTT broker", ex);
        }
    }

}