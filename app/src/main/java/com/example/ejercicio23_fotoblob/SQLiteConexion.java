package com.example.ejercicio23_fotoblob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.ejercicio23_fotoblob.Transacciones.transacciones;



public class SQLiteConexion extends SQLiteOpenHelper
{
    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(transacciones.CrearTablaImagenes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(transacciones.DropeTableImagenes);
        onCreate(db);
    }

    //Cursos para retornar los valores
    public Cursor getAll() {
        return(getReadableDatabase().rawQuery("SELECT * FROM Imagenes",null));
    }
    public void insert(byte[] bytes, String descripcion)
    {
        ContentValues cv = new ContentValues();

        cv.put(transacciones.blobImagen,bytes);
        cv.put(transacciones.descripcion,descripcion);
        Log.e("insertado", "insertado");
        getWritableDatabase().insert(transacciones.tablaImagenes,transacciones.idImagen,cv);

    }
    public byte[] getImage(Cursor c)
    {
        return(c.getBlob(1));
    }
}
