package com.example.examen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static ArrayList<Personaje> personajes = generaPersonajes.generarPersonajes();
    private ActionBar bar;
    private int span = 1;
    private MiAdaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        bar = getSupportActionBar();
        adaptador = new MiAdaptador(personajes);
        bar.setTitle(R.string.hotel);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this,span,GridLayoutManager.VERTICAL,false);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                if(adaptador.getSelectedPosition() == pos){
                    adaptador.setSelectedPosition(RecyclerView.NO_POSITION);
                    adaptador.notifyItemChanged(pos);

                }else{
                    adaptador.setSelectedPosition(pos);
                    if(adaptador.getSelectedPosition() >= 0){
                        bar.setTitle(personajes.get(pos).getNombre());
                        adaptador.notifyItemChanged(pos);
                        Personaje personaje = MainActivity.personajes.get(pos);
                        Toast.makeText(v.getContext(), personaje.getNombre()+"\n"+personaje.getPelicula()+"\n"+personaje.getValoracion(), Toast.LENGTH_SHORT).show();
                        adaptador.notifyItemChanged(pos);
                    }
                }
                if(adaptador.getSelectedPosition() == -1){
                    bar.setTitle(R.string.hotel);
                }
                bar.setSubtitle(String.valueOf(personajes.size()));
            }
        };
        adaptador.setOnClickListener(listener);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(1));
        recyclerView.setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onResume() {
        bar.setSubtitle(String.valueOf(personajes.size()));
        if(adaptador.getSelectedPosition() < 0){
            bar.setTitle(R.string.hotel);
        }
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Personaje personaje = personajes.get(adaptador.getSelectedPosition());
            personajes.set(adaptador.getSelectedPosition(),new Personaje(data.getStringExtra("nombre"),data.getFloatExtra("valoracion",0),personaje.getImagen(),personaje.isFavorito(),personaje.getActorOriginal(),personaje.getImagenActor(),personaje.getPelicula()));
            adaptador.notifyItemChanged(adaptador.getSelectedPosition());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.modifica:
                if(adaptador.getSelectedPosition() >= 0){
                    Intent intent = new Intent(MainActivity.this,ModificarPersonaje.class);
                    intent.putExtra("pos",adaptador.getSelectedPosition());
                    startActivityForResult(intent,1);
                }
                break;

            case R.id.actores:
                break;

            case R.id.borrar:
                if(adaptador.getSelectedPosition() >= 0){
                    personajes.remove(personajes.get(adaptador.getSelectedPosition()));
                    adaptador.setSelectedPosition(RecyclerView.NO_POSITION);
                    adaptador.notifyItemRemoved(adaptador.getSelectedPosition());
                }else{
                    Toast.makeText(this, "Ningún personaje seleccionado", Toast.LENGTH_SHORT).show();
                }
                bar.setSubtitle(String.valueOf(personajes.size()));
                adaptador.notifyDataSetChanged();
                bar.setTitle(R.string.hotel);
                break;

            case R.id.grid:
                if(span >= 3){
                    span = 1;
                }else{
                    span++;
                }
                recyclerView.setLayoutManager(new GridLayoutManager(this,span,GridLayoutManager.VERTICAL,false));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void aceptar(String str){
        Log.d("Dialog",str);
        super.onBackPressed();
    }


    public void rechazar(String str){
        Log.d("Dialog",str);
    }

    public void neutral(String str){
        Log.d("Dialog",str);
    }

    @Override
    public void onBackPressed() {
        DialogAlert d = new DialogAlert();
        d.show(getSupportFragmentManager(),"");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}