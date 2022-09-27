package com.escueladigital.modelos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Venta{
    public short id_venta;
    public Calendar fecha;
    public String cliente; // porque no smallint?
    public String producto; // porque no smallint?
    public short cantidad;
    public short valor;
    
    public String usuario; // porque String?
    
    // convierte una fecha larga que viene la bd con Date y la convierte en simple
    public String getFecha(){
        String fechaFormat;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        fechaFormat = sdf.format(fecha.getTime());
        return fechaFormat;
    }
}