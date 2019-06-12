package com.example.lista0404.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lista0404.datos.Modelo;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class RefaccionariaHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "refaccionaria_db";
    private static final int DB_VERSION = 2;

    //Tablas
    private static final String TABLA_MARCA = "create table if not exists Marca(" +
            "idMarca integer primary key autoincrement," +
            "nombreMarca varchar (50))";

    private static final String TABLA_ADMINISTRADOR = "create table if not exists Administrador(" +
            "idAdministrador integer primary key autoincrement," +
            "nombre varchar(50)," +
            "contrasena varchar(50))";

    private static final String TABLA_CLIENTE = "create table if not exists Cliente(" +
            "idCliente integer primary key autoincrement," +
            "nombre varchar(50)," +
            "contrasena varchar(50))";

    private static final String TABLA_TIPO_REFACCION = "create table if not exists TipoDeRefaccion(" +
            "idTipoRefaccion integer primary key autoincrement," +
            "nombreTipoRefaccion varchar(50))";

    private static final String TABLA_MODELO = "create table if not exists Modelo(" +
            "idModelo integer primary key autoincrement," +
            "nombreModelo varchar(50)," +
            "marcaModelo integer," +
            "FOREIGN KEY(marcaModelo) REFERENCES Marca(idMarca) on delete cascade)";

    private static final String TABLA_REFACCIONES = "create table if not exists Refacciones(" +
            "idRefaccion integer primary key autoincrement," +
            "nombreRefaccion varchar(50)," +
            "refaccionTipo integer," +
            "descripcion varchar(50)," +
            "precio real," +
            "refaccionModelo integer," +
            "FOREIGN KEY(refaccionModelo) REFERENCES Modelo(idModelo) on delete cascade," +
            "FOREIGN KEY(refaccionTipo) REFERENCES TipoDeRefaccion(idTipoRefaccion) on delete cascade)";

    private static final String TABLA_CARRITO = "create table if not exists Carrito(" +
            "idCarrito integer primary key autoincrement," +
            "carritoCliente integer," +
            "carritoRefaccion integer," +
            "fecha varchar(50)," +
            "FOREIGN KEY(carritoCliente) REFERENCES Cliente(idCliente) on delete cascade," +
            "FOREIGN KEY(carritoRefaccion) REFERENCES Refacciones(idRefaccion) on delete cascade)";

    private static final String OBTENER_ADMINITRADORES = "select * from Administrador" ;
    private static final String OBTENER_USUARIOS = "select * from Cliente";
    private static final String OBTENER_MARCAS = "select * from Marca";
    private static final String OBTENER_MODELO = "select * from Modelo";

    public RefaccionariaHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_MARCA);
        db.execSQL(TABLA_ADMINISTRADOR);
        db.execSQL(TABLA_CLIENTE);
        db.execSQL(TABLA_TIPO_REFACCION);
        db.execSQL(TABLA_MODELO);
        db.execSQL(TABLA_REFACCIONES);
        db.execSQL(TABLA_CARRITO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertarMarca(String marca) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombreMarca", marca);
        db.insert("Marca", null, values);
    }

    public Hashtable<Integer, String> obtenerMarcas() {
        Hashtable<Integer, String> marcas = new Hashtable<Integer, String>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(OBTENER_MARCAS, null);
        while (cursor.moveToNext()) {
            marcas.put(cursor.getInt(0), cursor.getString(1));
        }
        return marcas;
    }

    public void eliminarMarca(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Marca WHERE idMarca = " + id);
    }

    public void insertarAdministrador(String nombre, String contrasena) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("contrasena", contrasena);
        db.insert("Administrador", null, values);
    }

    public boolean loginAdministrator(String nombre, String contrasena) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery(OBTENER_ADMINITRADORES + " where nombre = '" + nombre + "'", null);
            cursor.moveToFirst();
            String usuario = cursor.getString(1);
            String contrasenaDb = cursor.getString(2);
            return usuario.equals(nombre) && contrasena.equals(contrasenaDb);
        } catch (Exception e) {
            return false;
        }
    }

    public void insertarCliente(String nombre, String contrasena) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("contrasena", contrasena);
        db.insert("Cliente", null, values);
    }

    public boolean loginUsuario(String nombre, String contrasena) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery(OBTENER_USUARIOS + " where nombre = '" + nombre + "'", null);
            cursor.moveToFirst();
            String usuario = cursor.getString(1);
            String contrasenaDb = cursor.getString(2);
            return usuario.equals(nombre) && contrasena.equals(contrasenaDb);
        } catch (Exception e) {
            return false;
        }
    }

    public void insertarTipoRefaccion(String tipoDeRefaccion) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombreTipoRefaccion", tipoDeRefaccion);
        db.insert("TipoDeRefaccion", null, values);
    }

    public void insertarModelo(String nombreModelo, int marcaModelo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombreModelo", nombreModelo);
        values.put("marcaModelo", marcaModelo);
        db.insert("Modelo", null, values);
    }

    public ArrayList<Modelo> obtenerModelos() {
        ArrayList<Modelo> modelos = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(OBTENER_MODELO, null);
        while (cursor.moveToNext()) {
            modelos.add(new Modelo(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
        }
        return modelos;
    }

    public void eliminarModelo(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Modelo WHERE idModelo = " + id);
    }

    public void insertarRefaccion(String nombre, int tipo, String descripcion, double precio, int modelo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombreRefaccion", nombre);
        values.put("refaccionTipo", tipo);
        values.put("descripcion", descripcion);
        values.put("precio", precio);
        values.put("refaccionModelo", modelo);
        db.insert("Refacciones", null, values);
    }
}
