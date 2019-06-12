package com.example.lista0404.datos;

public class Cliente {

    private int idCliente;
    private String nombreCliente;
    private String contrasenaCliente;

    public Cliente(int idCliente, String nombreCliente, String contrasenaCliente) {
        setIdCliente(idCliente);
        setNombreCliente(nombreCliente);
        setContrasenaCliente(contrasenaCliente);
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getContrasenaCliente() {
        return contrasenaCliente;
    }

    public void setContrasenaCliente(String contrasenaCliente) {
        this.contrasenaCliente = contrasenaCliente;
    }
}
