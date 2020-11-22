package com.example.ejentregar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class Calendario extends AppCompatActivity {

    private DatePicker date;
    private int dia,mes,anho;
    private Button aceptar,cancelar;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        date = findViewById(R.id.calendario);
        aceptar = findViewById(R.id.button3);
        cancelar = findViewById(R.id.button4);
        date.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dia = dayOfMonth;
                mes = monthOfYear;
                anho = year;
            }
        });

        dia = date.getDayOfMonth();
        mes = date.getMonth();
        anho = date.getYear();

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("dia",dia);
                intent.putExtra("mes",mes);
                intent.putExtra("anho",anho);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}