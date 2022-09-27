package com.escueladigital.modelos;

public class Producto {
    public short id_producto;
    public String nombre;
    public short cantidad;
    public short precio;
    
    // si necesitara el usuario que modificó el producto
    public Usuario usuario;
}
