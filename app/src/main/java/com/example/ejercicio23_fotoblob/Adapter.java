package com.example.ejercicio23_fotoblob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejercicio23_fotoblob.tablas.Fotos;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Fotos> {

    ArrayList<Fotos> listadoImagenes = new ArrayList<>();

    public Adapter(Context context, int textViewResourceId, ArrayList<Fotos> objects) {
        super(context, textViewResourceId, objects);
        listadoImagenes = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View plantilla = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        plantilla = inflater.inflate(R.layout.plantilla, null);
        ImageView imageView = (ImageView) plantilla.findViewById(R.id.img);
        imageView.setImageBitmap(listadoImagenes.get(position).getImagen());
        TextView textView = (TextView) plantilla.findViewById(R.id.txt);
        textView.setText(listadoImagenes.get(position).getDescripcion());
        return plantilla;
    }
}
