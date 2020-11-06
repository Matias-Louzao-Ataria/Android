package com.example.ej5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private SeekBar seek;
    private TextView seekView;
    private CheckBox check;
    private CheckBox check2;
    private CheckBox check3;
    private ToggleButton toggle;
    private Switch sw;
    private TextView switchtextView;
    private Button boton;
    private Button botonMostrar;
    private RatingBar rating;
    private EditText edit;
    private Button boton3;
    private int cont = 0;
    private ImageButton imgButton;
    private TextView buttonView;
    private TextView button2View;
    private TextView toggleButtonView;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seek = findViewById(R.id.seekBar);
        seekView = findViewById(R.id.textView7);
        check2 = findViewById(R.id.checkBox2);
        toggle = findViewById(R.id.toggleButton);
        sw = findViewById(R.id.switch1);
        switchtextView = findViewById(R.id.textView5);
        boton = findViewById(R.id.button2);
        check = findViewById(R.id.checkBox);
        check3 = findViewById(R.id.checkBox3);
        rating = findViewById(R.id.ratingBar);
        edit = findViewById(R.id.editTextTextPersonName);
        boton3 = findViewById(R.id.button3);
        imgButton = findViewById(R.id.imageButton);
        buttonView = findViewById(R.id.textView3);
        toggleButtonView = findViewById(R.id.textView4);
        button2View = findViewById(R.id.textView2);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rg = findViewById(R.id.grupo);
        botonMostrar = findViewById(R.id.button5);

        botonMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar action = MainActivity.this.getSupportActionBar();
                action.show();
            }
        });

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.this.seekView.setText(String.valueOf(MainActivity.this.seek.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.this.check2.setChecked(MainActivity.this.toggle.isChecked());
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.this.switchtextView.setText(MainActivity.this.sw.isChecked() ? "Activado":"Desactivado");
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle.setChecked(false);
                check.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(false);
                sw.setChecked(false);
                seek.setProgress(0);
                rating.setRating(0);
                edit.setText("");
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check3.isChecked()){
                    cont--;
                }else{
                    cont++;
                }
                MainActivity.this.boton3.setText(String.valueOf(cont));
            }
        });

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,Secundaria.class);
                it.putExtra("rating",rating.getRating());
                it.putExtra("nombre",edit.getText().toString());
                startActivityForResult(it,1);
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == rb1.getId()){
                    Toast.makeText(MainActivity.this, "id: "+rb1.getId()+", Texto:"+rb1.getText().toString(), Toast.LENGTH_SHORT).show();
                }else if(checkedId == rb2.getId()){
                    Toast.makeText(MainActivity.this, "id: "+rb2.getId()+", Texto:"+rb2.getText().toString(), Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });

    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nuevo:
                Intent it = new Intent(MainActivity.this,Terciaria.class);
                it.putExtra("check1",check.isChecked());
                it.putExtra("check2",check2.isChecked());
                it.putExtra("check3",check3.isChecked());
                startActivity(it);
                break;
            case R.id.borrar:
                seek.setProgress(0);
                buttonView.setText("");
                switchtextView.setText("");
                seekView.setText("");
                button2View.setText("");
                toggleButtonView.setText("");
                break;

            case R.id.editar:
                edit.setText("");
                break;

            case R.id.opc1:
                Toast.makeText(this, "Opc1 pulsado!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.opc2:
                Toast.makeText(this, "Opc2 pulsado!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ocultar:
                ActionBar action = MainActivity.this.getSupportActionBar();

                action.hide();


                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        buttonView.setText(String.valueOf(data.getFloatExtra("rating",0f)));
        rating.setRating(data.getFloatExtra("rating",0f));
        Log.i("MainActivity",String.valueOf(data.getFloatExtra("rating",0f))+" Rating devuelto por la actividad secundaria!");
    }
}