package com.example.ejercicio23_fotoblob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.widget.ListView;
import java.util.ArrayList;

import com.example.ejercicio23_fotoblob.Transacciones.transacciones;
import com.example.ejercicio23_fotoblob.tablas.Fotos;



public class ActivityListFotos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fotos);

        ListView Presentacion = (ListView) findViewById(R.id.lista_fotos);
        SQLiteConexion help = new SQLiteConexion(this, transacciones.NameDataBase, null, 1);
        ArrayList<Fotos> lista_Imagenes = new ArrayList<>();
        Cursor c= help.getAll();

        //Inicia proceso de recoleccion de datos, desde el registro #0 para mostrar en pantalla

        int i=0;
        if(c.getCount()>0)
        {
            c.moveToFirst();
            while(c.isAfterLast()==false)
            {
                byte[] bytes=c.getBlob(c.getColumnIndexOrThrow(transacciones.blobImagen));
                String descripcion = c.getString(c.getColumnIndexOrThrow(transacciones.descripcion));
                Fotos fotografia = new Fotos(BitmapFactory.decodeByteArray(bytes, 0, bytes.length), descripcion);
                lista_Imagenes.add(fotografia);
                c.moveToNext();
                i++;
            }
            Adapter myAdapter=new Adapter(this,R.layout.plantilla,lista_Imagenes);
            Presentacion.setAdapter(myAdapter);
        }
    }
}