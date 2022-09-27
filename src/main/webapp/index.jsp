<%-- 
    Document   : index.jsp
    Created on : 15 sep. 2022, 10:09:27
    Author     : Analía
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
	//Cargar el Driver en tiempo de ejecución
	Class.forName("org.postgresql.Driver");
    String mensaje = "";
    if (request.getAttribute("mensaje") != null) { // mensaje que viene de Autenticar.java
        mensaje = (String) request.getAttribute("mensaje");
    }
%>


<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content =" "width="device-width, user-scalable=no, initial-scale=1.0, minumun-scale=1,0"/>
        <link rel="stylesheet" href="css/estilos.css"/>
        <link rel="stylesheet" href="https://file.myfontastic.com/gVhWFfTbY55yVaY7oxJaF3/icons.css"/>
        <title>Sistema de facturación - Escuela Digital</title>
    </head>
   
    <body class = "inicio">
        
        <header class="main-header">
            <div class="ed-container">
                <div class="ed-item"><img src="img/logo.png" class="logo"/></div>
            </div>
        </header>
        
        <main class="ed-container">
          <div class="ed-item">
            <h1>Ingresar</h1>
            <form class="login-form ed-container web-40" action="Autenticar" method="post">
              <div class="ed-item form-item">
                <label for="username">Usuario</label>
                <input type="text" name="usuario" id="usuario" class="input" autofocus autocomplete="off"/>
              </div>
              <div class="ed-item form-item">
                <label for="password">Contraseña</label>
                <input type="password" name="clave" id="clave" class="input"/>
              </div>
              <div class="ed-item form-item">
                <input type="submit" value="Ingresar"/>
              </div>
            </form>
            <div class="web-40"><%=mensaje%></div>
            <%--
            <%
            	if(mensaje !=null){
            		out.println("<div class=\"error messaje icon-error\">"+mensaje+"</div>");
            	}
            %>
            --%>
          </div>
        </main>
          
        <footer class="main-footer">
          <div class="ed-container">
            <div class="ed-item">
              <p>Curso Bases de Datos Desde Cero - Alexys Lozada</p>
            </div>
          </div>
        </footer>
          <script src="js/efectos.js"></script>
    </body>
</html>