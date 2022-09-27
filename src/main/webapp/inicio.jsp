<%@page import="com.escueladigital.modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	// Cargar el Driver
	Class.forName("org.postgresql.Driver");

    Usuario usuario = null;
    if (session.getAttribute("usuario") != null) {
        usuario = (Usuario) session.getAttribute("usuario");
    } else {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8"/>
        <title>Bienvenido</title>
    </head>
    <body>
        <h1>Bienvenido
        <%
            if (usuario != null) { 
                out.print(usuario.nombre); 
            } 
        %>
        </h1>
    </body>
</html>