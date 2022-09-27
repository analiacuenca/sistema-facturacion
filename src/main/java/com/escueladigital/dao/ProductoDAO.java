
package com.escueladigital.dao;

import com.escueladigital.excepciones.ExcepcionGeneral;
import com.escueladigital.modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAO {
        private static final Logger LOG = Logger.getLogger(ProductoDAO.class.getName());
        private static final String CONSULTA = "SELECT id_producto, nombre, cantidad, precio FROM consulta_productos()";
        
        public List<Producto> listar() throws ExcepcionGeneral{
            List<Producto> listado = new ArrayList<>();
            Connection conexion = null;
            PreparedStatement sentencia = null; // no hay necesidad de prepararla si no hay paramteros
            ResultSet resultados = null;
            try{
                conexion = DAO.conectar();
                sentencia = conexion.prepareStatement(CONSULTA);
                resultados = sentencia.executeQuery();
                while(resultados.next()){
                    listado.add(convertir(resultados));
                }
            }
            catch(SQLException sqle){
                LOG.log(Level.SEVERE, "Error en TerceroDAO: {0}", sqle.getMessage());
                throw new ExcepcionGeneral(sqle.getMessage()); // lanzarlo y entregarselo al cliente
            }
            finally{
                DAO.cerrar(conexion, sentencia, resultados);
            }
            return listado;
        }
        
        private Producto convertir(ResultSet rs) throws SQLException{
            Producto producto = new Producto();
            producto.id_producto = rs.getShort("id_producto");
            producto.nombre = rs.getString("nombre");
            producto.cantidad = rs.getShort("cantidad");
            producto.precio = rs.getShort("precio");
            return producto;
        } 
        
        
}
