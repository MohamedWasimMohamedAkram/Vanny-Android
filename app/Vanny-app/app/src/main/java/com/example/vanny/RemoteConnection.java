package com.example.vanny;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class RemoteConnection {
    public static void connectToPi() {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession("pi", "<YOUR_RASPBERRY_PI_IP_ADDRESS>", 22);
            session.setPassword("<YOUR_RASPBERRY_PI_PASSWORD>");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            // Do something with the session here
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
}
