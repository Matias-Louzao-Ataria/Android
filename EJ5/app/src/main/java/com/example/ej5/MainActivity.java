package com.example.ej5;

import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private SeekBar seek;
    private TextView SeekView;
    private CheckBox check;
    private CheckBox check2;
    private CheckBox check3;
    private ToggleButton toggle;
    private Switch sw;
    private TextView SwitchtextView;
    private Button boton;
    private RatingBar rating;
    private EditText edit;
    private Button boton3;
    private int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seek = findViewById(R.id.seekBar);
        SeekView = findViewById(R.id.textView7);
        check2 = findViewById(R.id.checkBox2);
        toggle = findViewById(R.id.toggleButton);
        sw = findViewById(R.id.switch1);
        SwitchtextView = findViewById(R.id.textView5);
        boton = findViewById(R.id.button2);
        check = findViewById(R.id.checkBox);
        check3 = findViewById(R.id.checkBox3);
        rating = findViewById(R.id.ratingBar);
        edit = findViewById(R.id.editTextTextPersonName);
        boton3 = findViewById(R.id.button3);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.this.SeekView.setText(String.valueOf(MainActivity.this.seek.getProgress()));
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
                MainActivity.this.SwitchtextView.setText(MainActivity.this.sw.isChecked() ? "Activado":"Desactivado");
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
    }
}