package com.example.ej4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button  b = findViewById(R.id.button);
        final EditText nombre = findViewById(R.id.editTextTextPersonName);
        final TextView mostrar = findViewById(R.id.textView2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b(nombre,mostrar);
            }
        });
    }

    public void b(EditText nombre,TextView mostrar){
        mostrar.setText(nombre.getText());
    }
}