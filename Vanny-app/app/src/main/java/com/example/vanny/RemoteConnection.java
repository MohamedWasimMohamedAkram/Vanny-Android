package com.example.vanny;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.util.Arrays;

public class RemoteConnection {
    public static void connectToPi() {
    }
}





//        try {
//            JSch jsch = new JSch();
//            Session session = jsch.getSession("pi", "<YOUR_RASPBERRY_PI_IP_ADDRESS>", 22);
//            session.setPassword("<YOUR_RASPBERRY_PI_PASSWORD>");
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.connect();
//            // Do something with the session here
//            session.disconnect();
//        } catch (JSchException e) {
//            e.printStackTrace();
//        }
