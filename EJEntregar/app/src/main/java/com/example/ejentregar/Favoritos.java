package com.example.ejentregar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Favoritos extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListViewAdaptador adaptador = new ListViewAdaptador(this,MainActivity.peliculas);
        setListAdapter(adaptador);
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    }
}