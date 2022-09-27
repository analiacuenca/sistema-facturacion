<%@page import="com.escueladigital.modelos.Producto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.escueladigital.dao.ProductoDAO"%>
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
    
    ProductoDAO productoDAO = new ProductoDAO();
    List<Producto> productos = new ArrayList<Producto>();
    productos = productoDAO.listar();
%>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimun-scale=1.0"/>
    <link rel="stylesheet" href="css/estilos.css"/>
    <link href="https://file.myfontastic.com/gVhWFfTbY55yVaY7oxJaF3/icons.css" rel="stylesheet"/>
    <title>Lista de compras</title>
  </head>
  <body class="Listado de compras">
    <div class="main-container">
      <aside class="main-aside"><a href="/"><img src="img/logo.png" class="logo"/></a>
        <ul class="menu ed-menu">
          <li class="menu__item"><a href="compras.jsp" class="menu__link icon-compras">Compras</a></li>
          <li class="menu__item"><a href="ventas.jsp" class="menu__link icon-ventas">Ventas</a></li>
          <li class="menu__item"><a href="lista-compras.jsp" class="menu__link icon-lista-compras">Lista de compras</a></li>
          <li class="menu__item"><a href="lista-ventas.jsp" class="menu__link icon-lista-ventas">Lista de ventas</a></li>
          <li class="menu__item"><a href="lista-productos.jsp" class="menu__link icon-lista-productos">Lista de productos</a></li>
        </ul>
      </aside>
      <main class="main-content">
        <h1> Listado de productos</h1>
        <table class="listado">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Cantidad</th>
              <th>Precio</th>
            </tr>
          </thead>
          <tbody>
              <%
                  for (Producto producto : productos) {
                      out.println("<tr>");
                      out.println("<td>");
                      out.println(producto.id_producto);
                      out.println("</td>");
                      out.println("<td>");
                      out.println(producto.nombre);
                      out.println("</td>");
                      out.println("<td>");
                      out.println(producto.cantidad);
                      out.println("</td>");
                      out.println("<td>");
                      out.println(producto.precio);
                      out.println("</td>");
                      out.println("</tr>");
                  }
              %>
          </tbody>
        </table>
      </main>
    </div>
  </body>
</html>