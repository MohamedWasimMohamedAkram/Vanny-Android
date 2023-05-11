package com.example.vanny;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.jcodec.api.awt.SequenceEncoder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VideoEncoder {

    ArrayList<String> imagePaths;
    public VideoEncoder(ArrayList<String> imagePaths) {
        this.imagePaths = imagePaths;
    }


// GOP size will be supported in 0.2
// enc.getEncoder().setKeyInterval(25);
    private void encodeVideo() {
        try {
            SequenceEncoder enc = new SequenceEncoder(new File("filename.mp4"));
            for (String filePath:this.imagePaths){
                Bitmap bMap = BitmapFactory.decodeFile(filePath);
                enc.encodeImage(bMap);
            }
            enc.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
