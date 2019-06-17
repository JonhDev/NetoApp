package com.example.lista0404.datos;

public class Carrito {

    private int idCarrito;
    private int carritoCliente;
    private int carritoRefaccion;
    private String fecha;
    private int fueComprado;

    public Carrito(int idCarrito, int carritoCliente, int carritoRefaccion, String fecha, int fueComprado) {
        setIdCarrito(idCarrito);
        setCarritoCliente(carritoCliente);
        setCarritoRefaccion(carritoRefaccion);
        setFecha(fecha);
        setFueComprado(fueComprado);
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getCarritoCliente() {
        return carritoCliente;
    }

    public void setCarritoCliente(int carritoCliente) {
        this.carritoCliente = carritoCliente;
    }

    public int getCarritoRefaccion() {
        return carritoRefaccion;
    }

    public void setCarritoRefaccion(int carritoRefaccion) {
        this.carritoRefaccion = carritoRefaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getFueComprado() {
        return fueComprado;
    }

    public void setFueComprado(int fueComprado) {
        this.fueComprado = fueComprado;
    }
}
