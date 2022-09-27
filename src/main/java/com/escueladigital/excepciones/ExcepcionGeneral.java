package com.escueladigital.excepciones;

public class ExcepcionGeneral extends RuntimeException {
    public ExcepcionGeneral(String msg){
        super(msg);
    }
    
    public ExcepcionGeneral(){
        this("Hubo un error y está sin mensaje");
    }
}