package com.example.ejercicio23_fotoblob.tablas;

import android.graphics.Bitmap;

public class Fotos {
    Bitmap imagen;
    String descripcion;

    public Fotos(Bitmap imagen, String descripcion)
    {
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public Bitmap getImagen()
    {
        return imagen;
    }
    public String getDescripcion()
    {
        return descripcion;
    }
}
