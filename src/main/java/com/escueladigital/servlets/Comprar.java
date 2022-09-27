package com.escueladigital.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.escueladigital.dao.CompraDAO;
import com.escueladigital.excepciones.ExcepcionGeneral;
import com.escueladigital.modelos.Usuario;

/**
 * Servlet implementation class Comprar
 */
@WebServlet("/Comprar")
public class Comprar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comprar() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // para que la persona se logue y opere
        HttpSession sesion = request.getSession();
        
        // La info del usuario logueado la tenemos en la sesion. La tomamos y asignamos a un objeto usuario
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        
        // creo un objeto dao para logica de compra con bd
        CompraDAO compraDao = new CompraDAO();
        
        // Todas las solicitudes del cliente (request) viene en tipo String.
        String proveedor = request.getParameter("proveedor");
        String producto = request.getParameter("producto");
        String cantidad = request.getParameter("cantidad");
        String valor = request.getParameter("valor");
        
        short iProveedor = 0, iProducto = 0, iCantidad = 0, iValor = 0;
        
        try{
            iProveedor = Short.parseShort(proveedor);
            iProducto = Short.parseShort(producto);
            iCantidad = Short.parseShort(cantidad);
            iValor = Short.parseShort(valor);
        } catch (NumberFormatException nfe){
            // Nos toca hacerlo acá porque java no puede capturar la información numérica desde el cliente al servlet.
            // No la puede capturar numéricamente tiene que convertirla
            request.setAttribute("mensaje__error", "No se puede convertir el dato solicitado");
        }
        
        try{
            short idCompra = compraDao.comprar(iProveedor, iProducto, iCantidad, iValor, usuario.id_usuario); // el usuario que estaba en la sesion
            request.setAttribute("mensaje__exito", "La compra fue realizada con el id " + idCompra);
            
        } catch (ExcepcionGeneral eg){
            request.setAttribute("mensaje__error", eg.getMessage());  
        }
        getServletContext().getRequestDispatcher("/ventas.jsp").forward(request, response);
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

   @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
