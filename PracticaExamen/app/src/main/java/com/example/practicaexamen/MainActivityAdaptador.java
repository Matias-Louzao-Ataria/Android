package com.example.practicaexamen;

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

public class MainActivityAdaptador extends RecyclerView.Adapter<MainActivityAdaptador.Holder> implements View.OnClickListener {
    private ArrayList<Personaje> personajes;
    private View.OnClickListener listener;
    private int selectedPos = RecyclerView.NO_POSITION;

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        if (selectedPos == this.selectedPos) {
            notifyItemChanged(this.selectedPos);
            this.selectedPos = RecyclerView.NO_POSITION;
        } else {
            if (selectedPos >= 0) {
                this.selectedPos = selectedPos;
                notifyItemChanged(this.selectedPos);
            }
        }
    }

    public MainActivityAdaptador(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.personaje, parent, false);
        elemento.setOnClickListener(this.listener);
        Holder holder = new Holder(elemento);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tipo.setText(personajes.get(position).getTipo());
        holder.nombre.setText(personajes.get(position).getNombre());
        holder.foto.setImageResource(personajes.get(position).getImagen());
        int color = 0;
        if (selectedPos == position) {
            color = R.color.teal_200;
        } else {
            color = R.color.white;
        }
        holder.itemView.setBackgroundResource(color);
    }

    @Override
    public int getItemCount() {
        return personajes.size();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "A", Toast.LENGTH_SHORT).show();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private ImageView foto;
        private TextView nombre, tipo;

        public Holder(@NonNull View itemView) {
            super(itemView);
            this.foto = itemView.findViewById(R.id.foto);
            this.nombre = itemView.findViewById(R.id.nombre);
            this.tipo = itemView.findViewById(R.id.tipo);
        }
    }
}
