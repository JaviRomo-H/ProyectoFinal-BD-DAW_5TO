<%-- 
    Document   : exposicion
    Created on : 14 nov 2024, 07:35:27
    Author     : padil
--%>

<%@page import="java.util.List"%>
<%@page import="com.artistsagency.campusjalpa.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // No almacenar en caché
    response.setHeader("Pragma", "no-cache"); // No almacenar en caché (para navegadores antiguos)
    response.setDateHeader("Expires", 0); // Expira inmediatamente
    
    //verificamos si el usuario ya proporciono sus credenciales 
    String uid = (String) session.getAttribute("username");
    
    //si la variable uid es nula quiere decir que el usuario
    //no se ha logrado logear
    if(uid == null){
        response.sendRedirect("login.jsp");     
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exposiciones</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/estilos.css"/>
        
        <style>
            body {
                background-image: url(img/expo.png);
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center;
                height: 100vh;
                margin: 0;
                background-attachment: fixed;
            }
            h4 {
                color: white;
            }
        </style>
        
    </head>
    <%
        List<Exposicion> lasExpo = (ArrayList<Exposicion>) 
                request.getAttribute("TODASLASEXPOSICIONES");
    %>
    <body>
        <nav class="navbar navbar-expand-lg navbar-black bg-light ml-auto">
            <a class="navbar-brand" href="index.jsp">Inicio</a>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav me-auto">
                <a class="nav-item nav-link active" href="ServletExposicion">EXPOSICION</a>
                <a class="nav-item nav-link" href="#">GALERIA</a>
                <a class="nav-item nav-link" href="#">PINTOR</a>
                <a class="nav-item nav-link" href="#">PINTURA</a>
              </div>
            </div>
            <div>
                <form action="ServletUsuario" method="POST" style="display: inline;">
                    <input type="hidden" name="instruccion" value="cerrarsesion">
                    <button type="submit" class="btn btn-outline-danger">Cerrar sesión</button>
                </form>
            </div>
        </nav>
        <!-- Boton para agregar una nueva exposicion a la base de datos -->
        <div class="fondo">
            <div class="container mt-5">
            <div class="row">
                <div class="col">
                    <!-- se debe de cambiar ServletLibros -->
                    <a href="ServletExposicion?instruccion=nuevaexpo" 
                       class="btn btn-info float-left mb-3">Nueva Exposicion</a>
                </div>
            </div>
        </div>
        <%
            //verificamos que existan las exposiciones en la base de datos 
            if(lasExpo.size()!=0){//si existen las exposiciones entramos a la tabla
        %>
            <div class="container">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Id de Exposicion</th>
                            <th>Nombre de Exposicion</th>
                            <th>Fecha de Inicio</th>
                            <th>Fecha de Terminacion</th>
                            <th>Modificar</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Exposicion tempExpo : lasExpo) {
                                    out.print("<tr>");
                                    out.print("<td>"+tempExpo.getEXP_ID()+"</td>");
                                    out.print("<td>"+tempExpo.getEXP_NAME()+"</td>");
                                    out.print("<td>"+tempExpo.getEXP_START_DATE()+"</td>");
                                    out.print("<td>"+tempExpo.getEXP_END_DATE()+"</td>");
                                    //boton Modificar
                                    out.print("<td><a class='btn btn-info' href='ServletExposicion?EXP_ID="
                                            +tempExpo.getEXP_ID()+"&instruccion=editarexpo'>Modificar</a></td>");
                                    //boton eliminar
                                    out.print("<td><a class='btn btn-danger' href='ServletExposicion?EXP_ID="
                                            +tempExpo.getEXP_ID()+"&instruccion=intentoeliminar'>Eliminar</a></td>");
                                out.print("</tr>");
                                }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        
    <%
        }else{
            out.print("<div class='container'>");
            out.println("<div class='row'>");
            out.println("<div class='col'>");
            out.print("<h4>No hay Exposiciones en la base de datos</h4>");
        }
    %>
    
    <script src="js/jquery.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/script.js"></script>
    <div>
        <footer class="footer">© 2024. Derechos Protegidos.</footer>
    </div>
    </body>
</html>
