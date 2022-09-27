package com.escueladigital.modelos;

// atributos publicos porque no es un bean. Es un pollo: clase con atributos publicos sin getters y setters
public class Usuario{
    public short id_usuario; // analogo a smallint
    public String usuario; // analogo a String
    public String nombre;
    public String clave;
    public short id_perfil;
    
    public String perfil;
}
