public class Video extends AppCompatActivity {
    private String videoPath;
    public Video (String videoPath) {
        this.videoPath = videoPath;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }

    public void onButtonClick(View v) {
        VideoView videoview = (VideoView) findViewById(R.id.videoview);
        videoView.setVideoPath(videoPath);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoview);
        videoview.setMediaController(mediaController);
        videoview.start();
    }
}