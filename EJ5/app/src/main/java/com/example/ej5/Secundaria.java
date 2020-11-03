package com.example.ej5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Locale;

public class Secundaria extends AppCompatActivity {

    private TextView textView;
    private RatingBar ratingBar;
    private Button button;
    private Button llamar;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);
        textView = findViewById(R.id.textView8);
        ratingBar = findViewById(R.id.ratingBar2);
        button = findViewById(R.id.button);
        phone = findViewById(R.id.editTextPhone);
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

        llamar = findViewById(R.id.button4);
        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_DIAL);
                String uri = String.format("tel:%s",phone.getText().toString());
                it.setData(Uri.parse(uri));
                if(it.resolveActivity(getPackageManager()) != null){// Si existe una Activity que es capaz de gestionar los datos
                    startActivity(it);
                    // Se lanza
                }
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