package com.example.lista0404.datos;

public class Refaccion {

    private int idRefaccion;
    private String nombreModelo;
    private int marcaModelo;

    public Refaccion(int idRefaccion, String nombreModelo, int marcaModelo) {
        setIdRefaccion(idRefaccion);
        setMarcaModelo(marcaModelo);
        setNombreModelo(nombreModelo);
    }


    public int getIdRefaccion() {
        return idRefaccion;
    }

    public void setIdRefaccion(int idRefaccion) {
        this.idRefaccion = idRefaccion;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public int getMarcaModelo() {
        return marcaModelo;
    }

    public void setMarcaModelo(int marcaModelo) {
        this.marcaModelo = marcaModelo;
    }
}
