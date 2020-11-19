package com.example.ejentregar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements Adaptador.ItemClickListener {

    private RecyclerView rw;
    private Button button;
    private Fragment f;
    private int seleccionadaPos;
    private TextView titulo;
    private TextView director;
    private TextView sala;
    private TextView fecha;
    private TextView duracion;
    private ImageView portada;
    private ImageView pegi;
    private CheckBox fav;
    private static datos d = new datos();
    public static ArrayList<Pelicula> peliculas = d.rellenaPeliculas();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RecyclerView Stuff
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        Adaptador adaptador = new Adaptador(peliculas,this);
        rw = findViewById(R.id.RecyclerView);
        rw.setAdapter(adaptador);
        rw.setLayoutManager(manager);

        //Fragment Stuff
        director = findViewById(R.id.director);
        titulo = findViewById(R.id.titulo);
        sala = findViewById(R.id.sala);
        fecha = findViewById(R.id.fecha);
        duracion = findViewById(R.id.duracion);
        portada = findViewById(R.id.portada3);
        pegi = findViewById(R.id.pegi);
        fav = findViewById(R.id.checkBox);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pelicula p = peliculas.get(seleccionadaPos);
                p.setFavorita(!p.isFavorita());
            }
        });

        //Button Stuff
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
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
        setDatos(peliculas.get(0));
    }

    //Assings data to fragment stuff
    public void setDatos(Pelicula pelicula){
        titulo.setText(pelicula.getTitulo());
        director.setText(pelicula.getDirector());
        sala.setText(pelicula.getSala());
        fecha.setText("representativo");
        duracion.setText(String.valueOf(pelicula.getDuracion())+" minutos");
        portada.setImageResource(pelicula.getPortada());
        pegi.setImageResource(pelicula.getPegi());
        fav.setChecked(pelicula.isFavorita());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public static String parseFecha(String str){
        /*Scanner sc = new Scanner(str);
        String res = "";
        for(int i = 0;sc.hasNext();i++){
            if(i == 2 || i == 3){
                res+=sc.next()+"-";
            }
        }
        for(int i = 0;i < str.length();i++){
            if(i+4 >= str.length()){
                res+=str.charAt(i);
            }
        }*/
        return "WIP";
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
                Intent intent = new Intent(MainActivity.this,ListaCompleta.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void OnItemClicked(int pos) {
        this.seleccionadaPos = pos;
        setDatos(peliculas.get(pos));
    }
}