package com.example.ej5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Secundaria extends AppCompatActivity {

    private TextView textView;
    private RatingBar ratingBar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);
        textView = findViewById(R.id.textView8);
        ratingBar = findViewById(R.id.ratingBar2);
        button = findViewById(R.id.button);
        Intent it = getIntent();
        textView.setText(it.getStringExtra("nombre"));
        ratingBar.setRating(it.getFloatExtra("rating",0f));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSec = new Intent();
                intentSec.putExtra("rating",ratingBar.getRating());
                setResult(RESULT_OK,intentSec);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        /*Intent intentSec = new Intent();
        intentSec.putExtra("rating",ratingBar.getRating());
        setResult(RESULT_OK,intentSec);
        finish();*/
        Secundaria.this.button.callOnClick();
    }
}