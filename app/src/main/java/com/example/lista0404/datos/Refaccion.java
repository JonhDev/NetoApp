package com.example.lista0404.datos;

public class Refaccion {

    private int idRefaccion;
    private String nombreModelo;
    private int tipo;
    private String descripcion;
    private double precio;
    private int marcaModelo;

    public Refaccion(
            int idRefaccion,
            String nombreModelo,
            int tipo,
            String descripcion,
            double precio,
            int marcaModelo)
    {
        setIdRefaccion(idRefaccion);
        setMarcaModelo(marcaModelo);
        setTipo(tipo);
        setDescripcion(descripcion);
        setPrecio(precio);
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
