package com.example.seanlin.unitravel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

public class CameraView extends AppCompatActivity {

    private ImageView myImage;
    private VideoView myVideo;
    private Bitmap myBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);

        myImage = (ImageView) findViewById(R.id.cam_image);
        myVideo = (VideoView) findViewById(R.id.cam_video);
        myVideo.setVisibility(View.VISIBLE);
        myImage.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            myImage.setImageBitmap(imageBitmap);
        }
    }

    private void takePicture(Intent intent) {
        Bundle extras = intent.getExtras();
        myBitmap = (Bitmap) extras.get("data");
        myImage.setImageBitmap(myBitmap);
        myImage.setVisibility(View.VISIBLE);
        myVideo.setVisibility(View.INVISIBLE);
    }


}
