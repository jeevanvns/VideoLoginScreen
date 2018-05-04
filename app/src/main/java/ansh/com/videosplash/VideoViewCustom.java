package ansh.com.videosplash;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.widget.VideoView;

public class VideoViewCustom extends VideoView implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    private int mForceHeight = 0;
    private int mForceWidth = 0;
    private MediaPlayer mediaPlayer;

    public VideoViewCustom(Context context) {
        super(context);
        this.setOnPreparedListener(this);
      //  this.setOnCompletionListener(this);
        this.setOnErrorListener(this);
    }

    public VideoViewCustom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.setOnPreparedListener(this);
        //  this.setOnCompletionListener(this);
        this.setOnErrorListener(this);
    }

    public VideoViewCustom(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOnPreparedListener(this);
        //  this.setOnCompletionListener(this);
        this.setOnErrorListener(this);
    }

    public void setDimensions(int w, int h) {
        this.mForceHeight = h;
        this.mForceWidth = w;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mForceWidth, mForceHeight);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        mute();
    }


    public void mute() {
        if (mediaPlayer != null) {
            this.setVolume(0);
        }

    }

    public void unMute() {
        if (mediaPlayer != null) {
            this.setVolume(100);
        }
    }

    private void setVolume(int amount) {
        final int max = 100;
        final double numerator = max - amount > 0 ? Math.log(max - amount) : 0;
        final float volume = (float) (1 - (numerator / Math.log(max)));
        this.mediaPlayer.setVolume(volume, volume);
    }
}
