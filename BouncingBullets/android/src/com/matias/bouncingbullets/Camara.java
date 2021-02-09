package com.matias.bouncingbullets;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Camara extends Activity {

    public void getThumbnail(View v) {
        Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (fotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(fotoIntent, 1); // requestCode 1 para Thumbnail
        }
    }
// Comprobamos que existe algún programa asociado al intent implícito, ya que si no saltaría una excepción.

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) { // Thumbnail
            Bitmap imagen = (Bitmap) data.getExtras().get("data");
            try { // Guardo la imagen
                FileOutputStream fout = openFileOutput("imagen.jpg", MODE_PRIVATE);
                imagen.compress(Bitmap.CompressFormat.JPEG, 80, fout);
                fout.close();
// Visualizo las imagenes guardadas
                String[] files = getApplicationContext().fileList();
                for (String file : files) {
                    Log.i("imagen -> ", file);
                }
            } catch (IOException e) {
                Log.e("Error imagen", e.getLocalizedMessage());
            }

        } else if (requestCode == 2 && resultCode == RESULT_OK) { // Foto normal
            if (pathActualFotos != null) {
                Bitmap imagen = BitmapFactory.decodeFile(pathActualFotos);
                Log.i("tamaño", imagen.getWidth() + ":" + imagen.getHeight());
            } else Log.e("Error", "No hay ruta a la imagen");
        }
    }

    String pathActualFotos = null;

    @TargetApi(Build.VERSION_CODES.N)
    private File creaNombreUnicoArchivoImagen() throws IOException {
        String formatoFecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreImagen = "jpg_" + formatoFecha + "_";
        File directorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);
        pathActualFotos = imagen.getAbsolutePath(); // Path para el recoger luego el archivo
        Log.i("Path fotos", pathActualFotos);
        return imagen;
    }
}
