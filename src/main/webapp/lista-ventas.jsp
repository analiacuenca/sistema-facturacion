<%@page import="com.escueladigital.modelos.Venta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.escueladigital.dao.VentaDAO"%>
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
    
    short sumaCantidad = 0;
    int sumaTotal = 0;
    short limite = 5;
    short pagina = 1;
    if (request.getParameter("limite") != null) {
        limite = Short.parseShort((String) request.getParameter("limite"));
    }
    if (request.getParameter("pagina") != null) {
        pagina = Short.parseShort((String) request.getParameter("pagina"));
    }
    
    VentaDAO ventaDAO = new VentaDAO();
    List<Venta> ventas = new ArrayList<Venta>();
    ventas = ventaDAO.listar(limite, pagina);
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
        <h1> Listado de ventas</h1>
        <table class="listado">
          <thead>
            <tr>
              <th>ID</th>
              <th>Fecha</th>
              <th>Cliente</th> <!-- Acá cambié proveedor por cliente -->
              <th>Producto</th>
              <th>Cantidad</th>
              <th>Valor</th>
            </tr>
          </thead>
          <tbody>
              <%
                  for (Venta venta : ventas) {
                      out.println("<tr>");
                      out.println("<td>");
                      out.println(venta.id_venta);
                      out.println("</td>");
                      out.println("<td>");
                      out.println(venta.getFecha());
                      out.println("</td>");
                      out.println("<td>");
                      out.println(venta.cliente);
                      out.println("</td>");
                      out.println("<td>");
                      out.println(venta.producto);
                      out.println("</td>");
                      out.println("<td>");
                      out.println(venta.cantidad);
                      out.println("</td>");
                      out.println("<td>");
                      out.println(venta.valor);
                      out.println("</td>");
                      out.println("</tr>");
                      sumaCantidad += venta.cantidad;
                      sumaTotal += venta.valor;
                  }
              %>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="4" class="suma-label">Total</td>
              <td class="suma"><%=sumaCantidad%></td>
              <td class="suma"><%=sumaTotal%></td>
            </tr>
          </tfoot>
        </table>
            <form action="lista-ventas.jsp" method="post">
            <div class="ed-container">
                <div class="ed-item web-20">
                    <label for="registros">Registros:</label>
                </div>
                <div class="ed-item web-20">
                    <select id="registros" name="limite">
                        <option value="1">1</option>
                        <option value="3">3</option>
                        <option value="5">5</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <div class="ed-item web-20">
                    <label for="pagina">Página:</label>
                </div>
                <div class="ed-item web-20">
                    <input type="number" id="pagina" name="pagina" min="1" value="1"/>
                </div>
                <div class="ed-item web-15">
                    <input type="submit" value="Listar"/>
                </div>
            </div>
            </form>
      </main>
    </div>
  </body>
</html>