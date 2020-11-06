package com.example.ej5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Terciaria extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terciaria);
        text = findViewById(R.id.textView9);
        Intent recivido = getIntent();
        text.setText((recivido.getBooleanExtra("check1",false) == true ? "CheckBox1 Activado":"CheckBox1 Desactivado")+"\n");
        text.setText(text.getText().toString()+(recivido.getBooleanExtra("check2",false) == true ? "CheckBox2 Activado":"CheckBox2 Desactivado")+"\n");
        text.setText(text.getText().toString()+(recivido.getBooleanExtra("check3",false) == true ? "CheckBox3 Activado":"CheckBox3 Desactivado")+"\n");

        Log.i("msg",recivido.getBooleanExtra("check",false) == true ? "CheckBox Activado":"CheckBox Desactivado");
        Log.i("msg2",recivido.getBooleanExtra("check2",false) == true ? "CheckBox2 Activado":"CheckBox2 Desactivado");
        Log.i("msg3",recivido.getBooleanExtra("check3",false) == true ? "CheckBox3Activado":"CheckBox3 Desactivado");
    }
}