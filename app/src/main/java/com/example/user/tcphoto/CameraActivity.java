package com.example.user.tcphoto;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class CameraActivity extends Activity {
    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Create a folder named photoFolder to store
        // photos taken by the camera by clicking capture button.

        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/photoFolder/";
        File newDir = new File(dir);
        newDir.mkdirs();

        Button capture = (Button) findViewById(R.id.btnCapture);
        capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               // The counter will be incremented each time, and the
               // Photo taken by camera will be stored as 1.jpg, 2.jpg and so on.

                count++;
                String file = dir+count+".jpg";
                File newFile = new File(file);
                try {
                    newFile.createNewFile();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    Log.e("IO","IO"+e);
                }


                //
                Uri outputFileUri = Uri.fromFile(newFile);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {

            Toast.makeText(this,"Photo has been saved", Toast.LENGTH_LONG ).show();

        }
    }
}