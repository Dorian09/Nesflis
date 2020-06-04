package com.example.nesflis;

import android.os.Parcel;
import android.os.Parcelable;

public class Pelicula implements Parcelable {
    private String nombre;
    private String img;

    public Pelicula(String nombre, String img) {
        this.nombre = nombre;
        this.img = img;
    }

    protected Pelicula(Parcel in) {
        nombre = in.readString();
        img = in.readString();
    }

    public static final Creator<Pelicula> CREATOR = new Creator<Pelicula>() {
        @Override
        public Pelicula createFromParcel(Parcel in) {
            return new Pelicula(in);
        }

        @Override
        public Pelicula[] newArray(int size) {
            return new Pelicula[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(img);
    }
}
