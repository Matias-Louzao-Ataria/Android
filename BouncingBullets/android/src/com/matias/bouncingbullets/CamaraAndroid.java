package com.matias.bouncingbullets;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CamaraAndroid extends AndroidApplication implements PlatformSpecific {

    private Activity context;

    public CamaraAndroid(Activity context) {
        this.context = context;
    }

    public CamaraAndroid() {
    }

    public void hacerFoto() {

//        Intent i = new Intent().setAction(Intent.ACTION_GET_CONTENT);
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(i,1);
        this.context.startActivityForResult(Intent.createChooser(i,"select"),1);
        /*this.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                /*Toast.makeText(context, "Hey platform specific code!", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                        stateCallback = new CameraDevice.StateCallback() {
                            @Override
                            public void onOpened(@NonNull CameraDevice camera) {
                                camera = camera;

                            }

                            @Override
                            public void onDisconnected(@NonNull CameraDevice camera) {
                                camera.close();
                                camera = null;
                            }

                            @Override
                            public void onError(@NonNull CameraDevice camera, int error) {
                                camera.close();
                                camera = null;
                            }
                        };
                        abrirCamara();
                    }
                }
            }
        });*/
    }
}
