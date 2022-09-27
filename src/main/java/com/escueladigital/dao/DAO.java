package com.escueladigital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// DAO es un patron en el cual podemos conectarnos a la base de datos de una manera sencilla 
// y mapear la info en nuestros modelos
// Data Acces Object

public class DAO {
    private static final Logger LOG = Logger.getLogger(DAO.class.getName());
    
    // nos permite crear una conexión cada vez que necesitemos hacer una acción en la BD
    // quieres consultar, insertar, tienes que conectarte. Esto no es la mejor opcion porque 
    // se utilizan un pull (piscinas) de conexiones. Para el curso lo hacemos asi sencillo
    public static Connection conectar(){

		// Cargar el Driver
		// Class.forName("org.postgresql.Driver");
		
        Connection conexion = null;
        String url = "jdbc:postgresql://localhost/escueladigital";
        String user = "postgres";
        String pass = "H1sql22";
        try{
            // para conectarme a este moto. Obtiene una conexion a esta url, use, y pass.
            // si hay un error captura esa exception
            conexion = DriverManager.getConnection(url, user, pass);
        }
        catch(SQLException sqle){
            // lo envio al log de errores
            LOG.log(Level.SEVERE, "Ocurrió un error en: {0}", sqle.getMessage());
        }
        return conexion;
    }
    
    public static void cerrar(Connection c, PreparedStatement p, ResultSet r){
        try{
            if(r!=null){
                r.close();
            }
            if(p!=null){
                p.close();
            }
            if(c!=null){
                c.close();
            }
        }
        catch(SQLException sqle){}
    }
}

