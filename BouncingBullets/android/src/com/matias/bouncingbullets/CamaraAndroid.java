package com.matias.bouncingbullets;

/*import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.camera2.*;
import android.hardware.camera2.CameraDevice;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.RequiersApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CamaraAndroid extends Activity implements PlatformSpecific {

    private Activity context;
    CameraDevice c;
    CameraDevice.StateCallback stateCallback;

    public CamaraAndroid(Activity context) {
        this.context = context;
    }

    public void hacerFoto() {
        this.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "Hey platform specific code!", Toast.LENGTH_SHORT).show();
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
                    }
                }
            }
        });
    }


    public void abrirCamara(){
        CameraManager manager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            manager = (CameraManager) getSystemService(context.CAMERA_SERVICE);
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                manager.openCamera("camera", stateCallback, null);
            } catch (CameraAccessException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        ImageView iv = (ImageView) findViewById(R.id.imageView);
        if (requestCode == 1 && resultCode == RESULT_OK) { // Thumbnail
            Bitmap imagen = (Bitmap) data.getExtras().get("data");
//            iv.setImageBitmap(imagen);
            try { // Guardo la imagen
                FileOutputStream fout = openFileOutput("imagen.jpg", MODE_PRIVATE);
                imagen.compress(Bitmap.CompressFormat.JPEG, 80, fout);
                fout.close();
                String[] files = getApplicationContext().fileList(); // Visualizo el nombre de las imagenes guardadas
                for (String file : files) {
                    Log.i("imagen -> ", file);
                }
            } catch (IOException e) {
                Log.e("Error imagen", e.getLocalizedMessage());
            }
        }
    }
}*/
