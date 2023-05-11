package com.example.vanny;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.jcodec.api.android.AndroidSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VideoEncoder {

    ArrayList<String> imagePaths;
    public VideoEncoder(ArrayList<String> imagePaths) {
        this.imagePaths = imagePaths;
    }



    private void encodeVideo() {
        SeekableByteChannel out = null;
        try {

            out = NIOUtils.writableFileChannel("/tmp/output.mp4");

            AndroidSequenceEncoder enc = new AndroidSequenceEncoder(out, Rational.R(25, 1));
            for (String filePath:this.imagePaths){
                Bitmap bMap = BitmapFactory.decodeFile(filePath);
                enc.encodeImage(bMap);
            }
            enc.finish();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            NIOUtils.closeQuietly(out);
        }
    }
}
