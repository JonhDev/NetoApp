package com.example.lista0404.datos;

public class Modelo {

    private int idModelo;
    private String nombreModelo;
    private int marcaModelo;

    public Modelo(int idModelo, String nombreModelo, int marcaModelo) {
        setIdModelo(idModelo);
        setNombreModelo(nombreModelo);
        setMarcaModelo(marcaModelo);
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
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
