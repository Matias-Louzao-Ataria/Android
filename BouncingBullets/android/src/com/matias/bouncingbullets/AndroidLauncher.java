package com.matias.bouncingbullets;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.matias.bouncingbullets.Main;

import java.io.FileOutputStream;
import java.io.IOException;

public class AndroidLauncher extends AndroidApplication {
	/**
	 *
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = true;
		config.useWakelock = true;
		/*Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);*/
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			initialize(new Main(new CamaraAndroid(this)));
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("BBBB","BBBB");
//                ImageView iv = (ImageView) findViewById(R.id.imageView);
		if (requestCode == 1 && resultCode == RESULT_OK) { // Thumbnail
			Bitmap imagen = (Bitmap) data.getExtras().get("data");
//            iv.setImageBitmap(imagen);
			try { // Guardo la imagen
				FileOutputStream fout = openFileOutput("imagen2.jpg", MODE_PRIVATE);
				imagen.compress(Bitmap.CompressFormat.JPEG, 80, fout);
				fout.close();
				String[] files = getApplicationContext().fileList(); // Visualizo el nombre de las imagenes guardadas
				for (String file : files) {
					Log.i("imagen -> ", file);
					BitmapFactory.decodeFile(file);
				}
			} catch (IOException e) {
				Log.e("Error imagen", e.getLocalizedMessage());
			}
		}
	}
}
