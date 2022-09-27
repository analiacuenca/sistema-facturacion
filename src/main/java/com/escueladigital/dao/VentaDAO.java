
package com.escueladigital.dao;

import com.escueladigital.excepciones.ExcepcionGeneral;
import com.escueladigital.modelos.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentaDAO {
    private static final Logger LOG = Logger.getLogger(VentaDAO.class.getName());
    private static final String INSERTAR = "SELECT vender(?,?,?,?)"; // no se coloca proyeccion porque esta fx devuelve un solo valor, un campo, con un registro
    private static final String CONSULTA = "SELECT id_venta, fecha, cliente, producto, cantidad, valor FROM consulta_ventas(?,?)";
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public short vender (short cliente,
                        short producto,
                        short cantidad,
                        short usuario) throws ExcepcionGeneral{
        short respuesta = 0;
        try{
            con = DAO.conectar();
            ps = con.prepareStatement(INSERTAR);
            ps.setShort(1, cliente);
            ps.setShort(2, producto);
            ps.setShort(3, cantidad);
            ps.setShort(4, usuario);
            rs = ps.executeQuery();
            if(rs.next()){ // si hay datos
                respuesta = rs.getShort(1); // guardo el dato que envia en una variable respuesta
            }
        }
        catch (SQLException sqle){
            LOG.log(Level.SEVERE, "Error en VentaDAO.vender {0}", sqle.getMessage());
            throw new ExcepcionGeneral(sqle.getMessage());
        }
        finally{
            DAO.cerrar(con, ps, rs);
        }
        return respuesta;
    }
    
    public List<Venta> listar(short limite, short pagina) throws ExcepcionGeneral {
        List<Venta> listado = new ArrayList<>();
        try {
            con = DAO.conectar();
            ps = con.prepareStatement(CONSULTA);
            ps.setShort(1, limite);
            ps.setShort(2, pagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(convertir(rs));
            }
        } catch (SQLException sqle) {
            LOG.log(Level.SEVERE, "Error en VentaDAO.listar: {0}", sqle.getMessage());
            throw new ExcepcionGeneral(sqle.getMessage());
        }
        return listado;
    }
    
    private Venta convertir (ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.id_venta = rs.getShort("id_venta");
        venta.fecha = Calendar.getInstance();
        venta.fecha.setTime(rs.getDate("fecha"));
        venta.cliente = rs.getString("cliente");
        venta.producto = rs.getString("producto");
        venta.cantidad = rs.getShort("cantidad");
        venta.valor = rs.getShort("valor");
        return venta;
    }
    
}
