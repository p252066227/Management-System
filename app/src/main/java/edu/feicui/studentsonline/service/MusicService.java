package edu.feicui.studentsonline.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import edu.feicui.studentsonline.R;

/**
 * Created by Administrator on 2016/8/21.
 */
public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    public MusicService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action=intent.getStringExtra("action");
        if (action.equals("ok")){
            if (mediaPlayer == null){
                mediaPlayer = MediaPlayer.create(this, R.raw.olddriver);
            }
            if (!mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
        }

        if (action.equals("zant")){
            if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        }

        if (action.equals("stop")){
            if (mediaPlayer!=null){
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();

                mediaPlayer=null;
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();

        mediaPlayer=null;

        super.onDestroy();
    }
}
