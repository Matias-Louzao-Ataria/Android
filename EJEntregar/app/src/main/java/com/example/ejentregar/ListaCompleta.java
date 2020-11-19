package com.example.ejentregar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class ListaCompleta extends AppCompatActivity implements AdaptadorListaCompleta.ItemClickListener {

    private RecyclerView rv;
    public static int pelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_completa);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        rv = findViewById(R.id.rw);
        AdaptadorListaCompleta adaptadorListaCompleta = new AdaptadorListaCompleta(MainActivity.peliculas,this);
        rv.setAdapter(adaptadorListaCompleta);
        rv.setLayoutManager(manager);

    }

    @Override
    public void OnItemClicked(int pos) {
        Intent intent = new Intent(this,PeliculaSinopsis.class);
        this.pelicula = pos;
        startActivity(intent);
    }
}