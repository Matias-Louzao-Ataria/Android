package com.example.examen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class ModificarPersonaje extends AppCompatActivity {
    private EditText nombre;
    private RatingBar valoracion;
    private ImageView img;
    private Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent in = getIntent();
        setContentView(R.layout.activity_modificar_personaje);
        nombre = findViewById(R.id.editnombre);
        valoracion = findViewById(R.id.ratingBar);
        guardar = findViewById(R.id.buttonguardar);
        Personaje personaje = MainActivity.personajes.get(in.getIntExtra("pos",0));
        img = findViewById(R.id.editfoto);
        nombre.setText(personaje.getNombre());
        valoracion.setRating(personaje.getValoracion());
        img.setImageResource(personaje.getImagen());
        guardar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                nombre.setText("");
                valoracion.setRating(0f);
                return true;
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((""+nombre.getText()).length() > 0){
                    Intent intentSec=new Intent();
                    intentSec.putExtra("nombre",nombre.getText().toString());
                    intentSec.putExtra("valoracion", valoracion.getRating());
                    setResult(RESULT_OK, intentSec);
                    finish();
                }else{
                    Toast.makeText(ModificarPersonaje.this, "Rellene los datos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}