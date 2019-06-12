package com.example.lista0404;

public class EnlistarMarca {
    String Marca;
    public  EnlistarMarca (String Marca){
        this.Marca = Marca;

    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    @Override
    public String toString() {
        return Marca;
    }
}
