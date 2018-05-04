package ansh.com.videosplash;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoViewCustom vvSplash;
    int screenHeight = 1280;
    int screenWidth = 800;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenHeight = ScreenUtils.getScreenHeight(MainActivity.this);
        screenWidth = ScreenUtils.getScreenWidth(MainActivity.this);

        vvSplash = findViewById(R.id.vv_splash);
        vvSplash.setDimensions(screenWidth, screenHeight);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
        vvSplash.setVideoURI(video);
        vvSplash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                vvSplash.start();
            }
        });

        vvSplash.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (vvSplash != null && !vvSplash.isPlaying()) {
            vvSplash.start();
        }
    }
}
