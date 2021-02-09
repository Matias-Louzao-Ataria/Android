package com.example.pruebacamara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getThumbnail(View v) {
        Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (fotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(fotoIntent, 1); // requestCode 1 para Thumbnail
        }
    }
// Comprobamos que existe algún programa asociado al intent implícito, ya que si no saltaría una excepción.

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        if (requestCode == 1 && resultCode == RESULT_OK) { // Thumbnail
            Bitmap imagen = (Bitmap) data.getExtras().get("data");
            iv.setImageBitmap(imagen);
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
                iv.setImageBitmap(imagen);
            } else Log.e("Error", "No hay ruta a la imagen");
        }
    }

    String pathActualFotos = null;

    private File creaNombreUnicoArchivoImagen() throws IOException {
        String formatoFecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreImagen = "jpg_" + formatoFecha + "_";
        File directorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);
        pathActualFotos = imagen.getAbsolutePath(); // Path para el recoger luego el archivo
        Log.i("Path fotos", pathActualFotos);
        return imagen;
    }

    public void getFoto(View v) {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

// En caso de no tenerlos se solicita al usuario su concesión
            String[] permisos = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(MainActivity.this, permisos, 3);
        } else { // Si ya se tienen los permisos se ejecuta de forma normal
            Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
// Aseguramos que se puede lanzar el intent implícito
            if (fotoIntent.resolveActivity(getPackageManager()) != null) {
                File archivoImagen = null; // Creamos el fichero donde se guardará la foto
                try {
                    archivoImagen = creaNombreUnicoArchivoImagen();
                    if (archivoImagen != null) { // Si se ha creado el archivo preparamos el intent con el archivo
                        Uri photoURI = FileProvider.getUriForFile(this, "com.example.camara.fileprovider", archivoImagen);
                        fotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(fotoIntent, 2); // requestCode 2 para foto normal
                    } else {
                        Log.e("ERROR", "No creó el archivo: ");
                    }
                } catch (Exception ex) {
                    Log.e("ERROR", ex.getLocalizedMessage());
                    ex.printStackTrace();
                }
            }
        }
    }
}