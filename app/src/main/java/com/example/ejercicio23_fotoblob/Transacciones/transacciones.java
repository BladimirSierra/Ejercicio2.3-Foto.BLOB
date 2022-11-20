package com.example.ejercicio23_fotoblob.Transacciones;

public class transacciones {

    public static final String tablaImagenes = "Imagenes";
    public static final String idImagen = "id_Imagen";
    public static final String blobImagen = "Imagen_blob";
    public static final String descripcion = "Imagen_descripcion";

    public static final String CrearTablaImagenes =
            "CREATE TABLE imagenes(id_Imagen INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Imagen_blob BLOB, Imagen_descripcion TEXT)";
    public static final String DropeTableImagenes =
            "DROPE TABLE IF EXISTS Imagenes";

    /* Base de Datos */
    public static final String NameDataBase = "Tarea3ImagenBlob";

}
