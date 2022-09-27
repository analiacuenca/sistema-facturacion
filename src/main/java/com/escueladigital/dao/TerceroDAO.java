
package com.escueladigital.dao;

import com.escueladigital.excepciones.ExcepcionGeneral;
import com.escueladigital.modelos.Tercero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TerceroDAO {
        private static final Logger LOG = Logger.getLogger(TerceroDAO.class.getName());
        private static final String CONSULTA = "SELECT id_tercero, nombre FROM consulta_terceros()";
        
        public List<Tercero> listar() throws ExcepcionGeneral{
            List<Tercero> listado = new ArrayList<>();
            Connection conexion = null;
            PreparedStatement sentencia = null;
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
        
        private Tercero convertir(ResultSet rs) throws SQLException{
            Tercero tercero = new Tercero();
            tercero.id_tercero = rs.getShort("id_tercero");
            tercero.nombre = rs.getString("nombre");
            return tercero;
        }     
}
