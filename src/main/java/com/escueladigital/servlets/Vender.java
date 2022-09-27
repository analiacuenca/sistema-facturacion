package com.escueladigital.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.escueladigital.dao.VentaDAO;
import com.escueladigital.excepciones.ExcepcionGeneral;
import com.escueladigital.modelos.Usuario;

/**
 * Servlet implementation class Vender
 */
@WebServlet("/Vender")
public class Vender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Vender() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // La persona se loguea y opera
        HttpSession sesion = request.getSession();
        
        // La info del usuario logueado la tenemos en la sesion. La tomamos y asignamos a un objeto usuario
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        
        // creo un objeto dao para logica de compra con bd
        VentaDAO ventaDao = new VentaDAO();
        
        // Todas las solicitudes del cliente (request) viene en tipo String.
        String cliente = request.getParameter("cliente");
        String producto = request.getParameter("producto");
        String cantidad = request.getParameter("cantidad");
        
        short iCliente = 0, iProducto = 0, iCantidad = 0;
        
        try{
            iCliente = Short.parseShort(cliente);
            iProducto = Short.parseShort(producto);
            iCantidad = Short.parseShort(cantidad);
        }
        catch (NumberFormatException nfe){
            // Nos toca hacerlo acá porque java no puede capturar la información numérica desde el cliente al servlet.
            // No la puede capturar numéricamente tiene que convertirla
            request.setAttribute("mensaje__error", "No se puede convertir el dato solicitado");
        }
        
        try{
            short idVenta = ventaDao.vender(iCliente, iProducto, iCantidad, usuario.id_usuario);
            request.setAttribute("mensaje__exito", "La compra fue realizada con el id " + idVenta);
            
        }
        catch (ExcepcionGeneral eg){
            request.setAttribute("mensaje__erorr", eg.getMessage()); // estaba mensaje solo antes
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
