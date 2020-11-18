package com.example.ejentregar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rw;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        datos d = new datos();
        Adaptador adaptador = new Adaptador(d.rellenaPeliculas());
        rw = findViewById(R.id.RecyclerView);
        rw.setLayoutManager(manager);
        rw.setAdapter(adaptador);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionBar ac = MainActivity.this.getSupportActionBar();
                if(ac.isShowing()){
                    ac.hide();
                }else{
                    ac.show();
                }
                Intent i = new Intent(MainActivity.this,favoritos.class);//Prueba de lanzamiento actividad de favoritos.
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}