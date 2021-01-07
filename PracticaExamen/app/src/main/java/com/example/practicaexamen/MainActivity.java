package com.example.practicaexamen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ActionBar bar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.button);
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        personajes.add(new Personaje(0,0,"Test","Test",R.drawable.sinimagen,"asfa"));
        personajes.add(new Personaje(0,3,"Test","Test",R.drawable.barry,"fasdf"));
        personajes.add(new Personaje(0,6,"Test","Test",R.drawable.ben,"faadfgdf"));
        personajes.add(new Personaje(0,5,"Test","Test",R.drawable.betty,"sdfgsdfg"));
        personajes.add(new Personaje(0,8,"Test","Test",R.drawable.viejosabio,"sdfg"));
        personajes.add(new Personaje(0,56,"Test","Test",R.drawable.tarquino,"sdfg"));
        MainActivityAdaptador adaptador = new MainActivityAdaptador(personajes);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                adaptador.setSelectedPos(pos);
                //Toast.makeText(MainActivity.this, ""+ pos, Toast.LENGTH_SHORT).show();
                if(adaptador.getSelectedPos() >= 0){
                    bar.setTitle(personajes.get(pos).getNombre());
                }else{
                    bar.setTitle("Ben y Holly");
                }
                bar.setSubtitle(String.valueOf(personajes.size()));
            }
        };
        adaptador.setOnClickListener(listener);
        recyclerView.setAdapter(adaptador);
        adaptador.notifyItemChanged(0);
        bar = getSupportActionBar();
        bar.setSubtitle(String.valueOf(personajes.size()));
        RecyclerView.LayoutManager lm = new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new SpacesItemDecoration(3));
        recyclerView.setLayoutManager(lm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adaptador.getSelectedPos() >= 0){
                    personajes.remove(adaptador.getSelectedPos());
                    adaptador.notifyItemRemoved(adaptador.getSelectedPos());
                }else{
                    Toast.makeText(MainActivity.this, "No hay elemento seleccionado!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(adaptador.getSelectedPos() >= 0){
                    Personaje personajeActual = personajes.get(adaptador.getSelectedPos());
                    Toast.makeText(MainActivity.this, personajeActual.getNombre()+"\n"+personajeActual.getTipo()+"\n"+personajeActual.getValoracion()+"\n"+personajeActual.getApariciones(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "No hay elemento seleccionado!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    public void opcionOK(String str){
        Log.d("Dialogo",str);
        super.onBackPressed();
    }

    public void opcionNO(String str){
        Log.d("Dialog",str);
    }

    public void opcionnifunifa(String str){
        Log.d("Dialog",str);
    }

    @Override
    public void onBackPressed() {
        DialogAlert d = new DialogAlert();
        d.show(getSupportFragmentManager(),"Algo");
    }
}