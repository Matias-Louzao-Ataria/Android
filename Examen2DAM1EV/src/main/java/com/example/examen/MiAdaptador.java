package com.example.examen;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiHolder> implements View.OnClickListener{

    private ArrayList<Personaje> personajes;
    private View.OnClickListener listener = this;
    private int selectedPos = RecyclerView.NO_POSITION;


    public int getSelectedPosition() {
        return selectedPos;
    }

    public void setSelectedPosition(int selectedPosition) {
       if(this.selectedPos != selectedPosition){
           notifyItemChanged(this.selectedPos);
           this.selectedPos = selectedPosition;
           notifyItemChanged(this.selectedPos);
       }else{
           notifyItemChanged(selectedPosition);
           this.selectedPos = RecyclerView.NO_POSITION;
       }
    }

    public MiAdaptador(ArrayList<Personaje> personajes){
        this.personajes = personajes;
    }

    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda,parent,false);
        elemento.setBackgroundColor(Color.CYAN);
        elemento.setOnClickListener(this.listener);
        MiHolder holder = new MiHolder(elemento);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MiHolder holder, int position) {
        Personaje personajeActual = personajes.get(position);
        holder.posicion.setText(String.valueOf(personajes.indexOf(personajeActual)));
        holder.personaje.setImageResource(personajeActual.getImagen());
        holder.nombre.setText(personajeActual.getNombre());

        if(position == this.getSelectedPosition()){
            holder.v.setBackgroundColor(Color.RED);
        }else{
            holder.v.setBackgroundColor(Color.CYAN);
        }
    }

    @Override
    public int getItemCount() {
        return personajes.size();
    }

    @Override
    public void onClick(View v) {

    }

    public void setOnClickListener(View.OnClickListener listener){
        if(listener != null){
            this.listener = listener;
        }
    }

    public class MiHolder extends RecyclerView.ViewHolder{
        ImageView personaje;
        TextView posicion,nombre;
        View v;
        public MiHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.nombre);
            this.personaje = itemView.findViewById(R.id.personaje);
            this.posicion = itemView.findViewById(R.id.posicion);
            this.v = itemView;
        }
    }
}
