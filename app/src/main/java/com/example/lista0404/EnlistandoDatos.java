package com.example.lista0404;

import android.content.Intent;

public class EnlistandoDatos {
    String Marca, Modelo,  Nombre, Cantidad, Tipo, Descripcion, PrecioA;

    public EnlistandoDatos ( String Marca, String Modelo, String Nombre, String Cantidad, String Tipo, String Descripcion, String Precio) {

        this.Marca = Marca;
        this.Modelo = Modelo;
        this.Nombre = Nombre;
        this.Cantidad = Cantidad;
        this.Tipo = Tipo;
        this.Descripcion = Descripcion;
        this.PrecioA = Precio;


    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
      this.Cantidad = Cantidad;
        return;
    }

    public java.lang.String setMarca(String Marca) {
        this.Marca = Marca;
        return Marca;
    }

    public java.lang.String setModelo(String Modelo) {
        this.Modelo = Modelo;
        return Modelo;
    }

    public java.lang.String setTipo(String string) {
        return Tipo;
    }

    public java.lang.String setNombre(String s) {
        return Nombre;
    }

    public java.lang.String setDescripcion(String string) {
        return Descripcion;
    }

    public java.lang.String setPrecioA(String string) {
        return PrecioA;
    }

    public String getPrecioA() {
        return PrecioA;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getModelo() {
        return Modelo;
    }

    public String getMarca() {
        return Marca;
    }

    @Override
    public String toString() {
        return  Marca +" - "+  Modelo +" - "+  Nombre +" - "+ Cantidad + " -"+   Tipo +" - "+  Descripcion +" - "+  PrecioA;
    }


}
