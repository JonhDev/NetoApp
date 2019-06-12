package com.example.lista0404.datos;

public class TipoRefaccion {

    private int idTipoRefaccion;
    private String nombreTipoRefaccion;

    public TipoRefaccion(int idTipoRefaccion, String nombreTipoRefaccion) {
        setIdTipoRefaccion(idTipoRefaccion);
        setNombreTipoRefaccion(nombreTipoRefaccion);
    }

    public int getIdTipoRefaccion() {
        return idTipoRefaccion;
    }

    public void setIdTipoRefaccion(int idTipoRefaccion) {
        this.idTipoRefaccion = idTipoRefaccion;
    }

    public String getNombreTipoRefaccion() {
        return nombreTipoRefaccion;
    }

    public void setNombreTipoRefaccion(String nombreTipoRefaccion) {
        this.nombreTipoRefaccion = nombreTipoRefaccion;
    }
}
