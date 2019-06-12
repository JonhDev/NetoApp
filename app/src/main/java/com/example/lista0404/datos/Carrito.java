package com.example.lista0404.datos;

public class Carrito {

    private int idCarrito;
    private int carritoCliente;

    public Carrito(int idCarrito, int carritoCliente) {
        setIdCarrito(idCarrito);
        setCarritoCliente(carritoCliente);
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
}
