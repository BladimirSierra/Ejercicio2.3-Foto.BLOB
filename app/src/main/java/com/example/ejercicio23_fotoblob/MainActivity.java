package com.example.ejercicio23_fotoblob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ejercicio23_fotoblob.Transacciones.transacciones;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {


    static final int REQUEST_IMAGE_CAPTURE = 101;
    static final int PETICION_ACCESO_CAM = 100;
    ImageView objImagen;
    SQLiteConexion db;
    byte[] byteArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objImagen = (ImageView) findViewById(R.id.foto);
        db = new SQLiteConexion(getApplicationContext(), transacciones.NameDataBase, null, 1);
        byteArray = new byte[0];

        objImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Otorgar_permisos();
            }
        });


        EditText descripcion = (EditText) findViewById(R.id.txtDescripcion);
        Button btnGuardar = (Button) findViewById(R.id.btn_guardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(byteArray.length != 0) {
                    db.insert(byteArray, descripcion.getText().toString());
                    Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_LONG).show();
                    byteArray = new byte[0];
                    objImagen.setImageResource(R.drawable.descarga);
                    descripcion.setText("");
                }else{
                    Toast.makeText(getApplicationContext(), "No se ha tomado ninguna fotografia", Toast.LENGTH_LONG).show();
                }
            }
        });


        Button btn_fotos = (Button) findViewById(R.id.btn_galeria);

        btn_fotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityListFotos.class);
                startActivity(intent);
            }
        });
    }


    private void Otorgar_permisos() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, PETICION_ACCESO_CAM);
        }else{
            Accion_tomarfoto();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PETICION_ACCESO_CAM)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Accion_tomarfoto();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Se necesitan permisos de acceso", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            getBytes(data);
        }
    }


    private void getBytes(Intent data){
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        objImagen.setImageBitmap(photo);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byteArray = stream.toByteArray();
    }


    private void Accion_tomarfoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }
}