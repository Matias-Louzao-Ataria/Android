package com.example.ejentregar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class CrearPelicula extends AppCompatActivity {
    private int dia = 0,mes = 0,anho = 0,pegi = 0;
    private RadioGroup rg;
    private EditText titulo,director,duracion;
    private Button calbutton;
    private ImageView portada;
    private Spinner sala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearpelicula);
        titulo = findViewById(R.id.editTextTextPersonName2);
        director = findViewById(R.id.editTextTextPersonName);
        duracion = findViewById(R.id.editTextTextPersonName3);
        sala = findViewById(R.id.spinner);
        rg = findViewById(R.id.radiogroup);
        portada = findViewById(R.id.portadaCrear);
        portada.setImageResource(R.drawable.sincara);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton:
                        pegi = R.drawable.pg;
                        break;
                    case R.id.radioButton2:
                        pegi = R.drawable.pg13;
                        break;
                    case R.id.radioButton3:
                        pegi = R.drawable.r;
                        break;
                    case R.id.radioButton4:
                        pegi = R.drawable.g;
                        break;
                }
            }
        });

        calbutton = findViewById(R.id.button2);
        calbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcal = new Intent(CrearPelicula.this,Calendario.class);
                startActivityForResult(intentcal,1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menucrear,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.aceptar:
                if(titulo.getText().toString().trim().replace(" ","").replace("\t","").length() > 0){
                    if(director.getText().toString().trim().replace(" ","").replace("\t","").length() > 0){
                        if(duracion.getText().toString().trim().replace(" ","").replace("\t","").length() > 0){
                            if(sala.getSelectedItem().toString().length() > 0){
                                if(pegi > 0 && dia > 0 && mes > 0 && anho > 0){
                                    Intent peliculaCreada = new Intent();
                                    Calendar c = Calendar.getInstance();
                                    c.set(anho,mes,dia);
                                    peliculaCreada.putExtra("pelicula",new Pelicula(titulo.getText().toString(),director.getText().toString(),Integer.parseInt(duracion.getText().toString()),c.getTime(),sala.getSelectedItem().toString(),pegi,R.drawable.sincara));
                                    setResult(RESULT_OK,peliculaCreada);
                                    finish();
                                    break;
                                }
                            }
                        }
                    }
                }
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancelar:
                Toast.makeText(this, "Estreno no agregado", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Estreno no agregado", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            dia = data.getIntExtra("dia",1);
            mes = data.getIntExtra("mes",1);
            anho = data.getIntExtra("anho",2000);
        }
    }
}