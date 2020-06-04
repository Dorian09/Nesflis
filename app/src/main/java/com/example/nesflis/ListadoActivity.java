package com.example.nesflis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private ListView lv1;
    private ArrayList<Pelicula> peliculas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        Intent i = getIntent();
        peliculas = i.getParcelableArrayListExtra("peliculas");
        lv1 = findViewById(R.id.lv_peliculas);
        if (peliculas!=null && peliculas.size()>0){
            Adaptador adapter = new Adaptador(this, peliculas);
            lv1.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this,"No hay datos" , Toast.LENGTH_LONG).show();
        }
    }
}
