package com.escueladigital.dao;

import com.escueladigital.excepciones.ExcepcionGeneral;
import com.escueladigital.modelos.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompraDAO {
    private static final Logger LOG = Logger.getLogger(CompraDAO.class.getName());
    private static final String INSERTAR = "SELECT comprar(?,?,?,?,?)"; // no se coloca proyeccion porque esta fx devuelve un solo valor, un campo, con un registro
    private static final String CONSULTA = "SELECT id_compra, fecha, cliente, producto, cantidad, valor FROM consulta_compras(?,?)";
    
    public short comprar(short proveedor,
                         short producto,
                         short cantidad,
                         short valor,
                         short usuario) throws ExcepcionGeneral{
        short id_compra = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con=DAO.conectar();
            ps=con.prepareStatement(INSERTAR);
            ps.setShort(1, proveedor);
            ps.setShort(2, producto);
            ps.setShort(3, cantidad);
            ps.setShort(4, valor);
            ps.setShort(5, usuario);
            rs=ps.executeQuery();// executeUpdate es para insertar y para borrar y modificar. executeQuery para consultar. En este caso el insert lo hace una funcion en la BD, acá solo consulta, porque devuelve un dato.
            if(rs.next()){ // si hay un dato, guarda lo que hay en la primera columna.
                id_compra = rs.getShort(1);
            }
        }
        catch (SQLException sqle){
            LOG.log(Level.SEVERE, "Error en CompraDAO.comprar: {0}", sqle.getMessage());
            throw new ExcepcionGeneral(sqle.getMessage()); // lanzarlo y entregarselo al cliente
        }
        finally{
            DAO.cerrar(con, ps, rs);
        }
        return id_compra;   
    }
    
    public List<Compra> listar (short limite, short pagina) throws ExcepcionGeneral{
        List<Compra> listado = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con=DAO.conectar();
            ps = con.prepareStatement(CONSULTA);
            ps.setShort(1, limite);
            ps.setShort(2, pagina);
            rs=ps.executeQuery();
            while(rs.next()){
                listado.add(convertir(rs));
            }
        }
        catch (SQLException sqle){
            LOG.log(Level.SEVERE, "Error en CompraDAO.listar: {0}", sqle.getMessage());
            throw new ExcepcionGeneral(sqle.getMessage()); // lanzarlo y entregarselo al cliente
        }
        finally{
            DAO.cerrar(con, ps, rs);
        }
        return listado;
    }
    
    public Compra convertir(ResultSet rs) throws SQLException{
        Compra compra = new Compra();
        compra.id_compra = rs.getShort("id_compra");
        compra.fecha = Calendar.getInstance();
        compra.fecha.setTime(rs.getDate("fecha"));
        compra.cliente = rs.getString("cliente");
        compra.producto = rs.getString("producto");
        compra.cantidad= rs.getShort("cantidad");
        compra.valor = rs.getShort("valor");
        return compra;
    }
}
