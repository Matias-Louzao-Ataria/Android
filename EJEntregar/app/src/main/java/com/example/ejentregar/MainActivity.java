package com.example.ejentregar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements Adaptador.ItemClickListener {

    private RecyclerView rw;
    private Button button;
    private Fragment f;
    private int seleccionadaPos;
    private TextView titulo,director,sala,fecha,duracion;
    private ImageView portada,pegi;
    private CheckBox fav;
    private static Datos d = new Datos();
    public static ArrayList<Pelicula> peliculas = d.rellenaPeliculas();

    public Adaptador getAdaptador() {
        return adaptador;
    }

    private Adaptador adaptador;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        adaptador = new Adaptador(peliculas,this);
        rw = findViewById(R.id.RecyclerView);
        rw.setAdapter(adaptador);
        rw.setLayoutManager(manager);
        rw.addItemDecoration(new DividerItemDecoration(rw.getContext(),DividerItemDecoration.VERTICAL));

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDatos(Pelicula pelicula){
        titulo.setText(pelicula.getTitulo());
        director.setText(pelicula.getDirector());
        sala.setText(pelicula.getSala());
        Calendar cal = Calendar.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate d = LocalDateTime.ofInstant(pelicula.getFecha().toInstant(), ZoneId.systemDefault()).toLocalDate();
        cal.setTime(pelicula.getFecha());
        fecha.setText(d.format(formatter));
        duracion.setText(pelicula.getDuracion()+" minutos");
        portada.setImageResource(pelicula.getPortada());
        pegi.setImageResource(pelicula.getPegi());
        fav.setChecked(pelicula.isFavorita());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
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
                Intent intentfav = new Intent(MainActivity.this, Favoritos.class);
                startActivity(intentfav);
                break;
            case R.id.estreno:
                Intent intentcrear = new Intent(MainActivity.this, CrearPelicula.class);
                startActivityForResult(intentcrear,1);
                break;
            case R.id.listado:
                Intent intentlista = new Intent(MainActivity.this,ListaCompleta.class);
                startActivity(intentlista);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            peliculas.add((Pelicula) data.getExtras().get("pelicula"));
            adaptador.notifyItemInserted(peliculas.size()-1);
            rw.scrollToPosition(peliculas.size()-1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void OnItemClicked(int pos) {
        this.seleccionadaPos = pos;
        setDatos(peliculas.get(pos));
    }
}