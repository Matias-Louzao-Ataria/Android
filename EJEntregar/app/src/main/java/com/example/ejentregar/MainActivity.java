package com.example.ejentregar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private RecyclerView rw;
    private Button b;
    private Pelicula peliculaAMostrar;
    private TextView titulo;
    private TextView director;
    private TextView sala;
    private TextView fecha;
    private TextView duracion;
    private ImageView portada;
    private ImageView pegi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        datos d = new datos();
        ArrayList<Pelicula> datos = d.rellenaPeliculas();
        Adaptador adaptador = new Adaptador(datos);
        rw = findViewById(R.id.RecyclerView);
        director = findViewById(R.id.director);
        rw.setLayoutManager(manager);
        titulo = findViewById(R.id.titulo);
        sala = findViewById(R.id.sala);
        fecha = findViewById(R.id.fecha);
        duracion = findViewById(R.id.duracion);
        portada = findViewById(R.id.portada3);
        pegi = findViewById(R.id.pegi);
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
            }
        });
        setDatos(datos.get(0));

        View.OnClickListener listener =
                new
                        View.OnClickListener() {
                            @Override
                            public void
                            onClick(View view) {
                                Toast.makeText(MainActivity.this, "Pos: ", Toast.LENGTH_SHORT).show();
                            }
                        };
        adaptador.setOnClickListener(listener);

    }

    public void setDatos(Pelicula pelicula){
        titulo.setText(pelicula.getTitulo());
        director.setText(pelicula.getDirector());
        sala.setText(pelicula.getSala());
        fecha.setText("representativo");
        duracion.setText(String.valueOf(pelicula.getDuracion()));
        portada.setImageResource(pelicula.getPortada());
        pegi.setImageResource(pelicula.getPegi());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.fav:
                Intent i = new Intent(MainActivity.this,favoritos.class);//Prueba de lanzamiento actividad de favoritos.
                startActivity(i);
                break;
            case R.id.estreno:
                break;
            case R.id.listado:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}