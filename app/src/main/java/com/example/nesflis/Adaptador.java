package com.example.nesflis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Pelicula> {

    public  Adaptador(@NonNull Context context, @NonNull ArrayList<Pelicula> competitions) {
        super(context, 0, competitions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Pelicula pelicula = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_peliculas, parent, false);
        }
        // Lookup view for data population
        TextView nombre = (TextView) convertView.findViewById(R.id.idTxt);
        ImageView imagen = (ImageView) convertView.findViewById(R.id.idImageView);
        // Populate the data into the template view using the data object
        nombre.setText(pelicula.getNombre());
        Glide.with(convertView)
                .load(pelicula.getImg())
                .centerCrop()
                //.crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imagen);
        // Return the completed view to render on screen
        return convertView;
    }
}
