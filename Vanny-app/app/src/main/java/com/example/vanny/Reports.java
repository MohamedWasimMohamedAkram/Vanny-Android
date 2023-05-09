package com.example.vanny;

public class Reports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
    }


    private void saveVideoToInternalStorage (String filePath) {

        File newfile;

        try {

            File currentFile = new File(filePath);
            String fileName = currentFile.getName();

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("videoDir", Context.MODE_PRIVATE);


            newfile = new File(directory, fileName);

            if(currentFile.exists()){

                InputStream in = new FileInputStream(currentFile);
                OutputStream out = new FileOutputStream(newfile);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                in.close();
                out.close();

                Log.v("", "Video file saved successfully.");

            }else{
                Log.v("", "Video saving failed. Source file missing.");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadVideoFromInternalStorage(String filePath){

        Uri uri = Uri.parse(Environment.getExternalStorageDirectory()+filePath);
        myVideoView.setVideoURI(uri);

    }

}