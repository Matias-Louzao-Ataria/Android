package com.example.t3ej1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView textView;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b = findViewById(R.id.button2);
        textView = findViewById(R.id.textViewSec);
        textView.setText(getIntent().getStringExtra("texto"));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent it = new Intent();
                it.putExtra("rating",rb.getRating());
                setResult(RESULT_OK,it);
                finish();*/
            }
        });
    }
}