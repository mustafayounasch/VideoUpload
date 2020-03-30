package com.example.videoupload;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class UploadVideoActivity extends AppCompatActivity {
private static final int PICK_VIDEO_REQUEST=3;
    private VideoView vv;
    private Uri videouri;
    private MediaController mc;
    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        vv= findViewById(R.id.videoabc);
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mc= new MediaController(UploadVideoActivity.this);
                        vv.setMediaController(mc);
                        mc.setAnchorView(vv);
                    }
                });
            }
        });


        vv.start();
    }

    public void videoupload(View view) {
        Intent i= new Intent();
        i.setType("video/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
 startActivityForResult(Intent.createChooser(i,"Select a video"), PICK_VIDEO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_VIDEO_REQUEST && requestCode == RESULT_OK && data != null)
        {
            videouri=data.getData();
            vv.setVideoURI(videouri);
        }
    }

    public void videouploadtoserver(View view) {
    }
}

