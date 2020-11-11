package com.example.ej5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class Terciaria extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terciaria);
        actionBar = Terciaria.this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent recivido = getIntent();

        Log.i("msg",recivido.getBooleanExtra("check",false) == true ? "CheckBox Activado":"CheckBox Desactivado");
        Log.i("msg2",recivido.getBooleanExtra("check2",false) == true ? "CheckBox2 Activado":"CheckBox2 Desactivado");
        Log.i("msg3",recivido.getBooleanExtra("check3",false) == true ? "CheckBox3Activado":"CheckBox3 Desactivado");

        //finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}