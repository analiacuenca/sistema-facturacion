package com.escueladigital.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.escueladigital.dao.UsuarioDAO;
import com.escueladigital.excepciones.ExcepcionGeneral;
import com.escueladigital.modelos.Usuario;

/**
 * Servlet implementation class Autenticar
 */
@WebServlet("/Autenticar")
public class Autenticar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Autenticar() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // para que l a persona se logue y opere
        HttpSession sesion = request.getSession(true);
        
        // capturo info que enviaron con usuario y clave, en los campos del formulario
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        
        // creo un objeto dao para logica de autenticacion con bd
        UsuarioDAO dao = new UsuarioDAO();
        
        try{
            // Llamamos al método autenticar del objeto dao de tipo UsuarioDAO, devuelve el usuario, lo guarda en user.
            // Si hay error lanza la excepcion creda ExcepcionGeneral y lo cachamos acá.
            Usuario user = dao.autenticar(usuario, clave);
            // y guarda ese usuario en la sesion
            sesion.setAttribute("usuario", user);
            response.sendRedirect("compras.jsp"); // luego de ingresar con user y contra lo dirije a la vista de compras
        }
        catch (ExcepcionGeneral eg){
            request.setAttribute("mensaje", eg.getMessage());
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            
        }
        
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
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    

}
