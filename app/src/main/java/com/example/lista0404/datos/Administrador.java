package com.example.lista0404.datos;

public class Administrador {

    private int idAdministrador;
    private String nombreAdministrador;
    private String contrasenaAdministrador;

    public Administrador(int idAdministrador, String nombreAdministrador, String contrasenaAdministrador) {
        setIdAdministrador(idAdministrador);
        setNombreAdministrador(nombreAdministrador);
        setContrasenaAdministrador(contrasenaAdministrador);
    }


    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getContrasenaAdministrador() {
        return contrasenaAdministrador;
    }

    public void setContrasenaAdministrador(String contrasenaAdministrador) {
        this.contrasenaAdministrador = contrasenaAdministrador;
    }
}
