<%-- 
    Document   : eliminar
    Created on : 15 nov 2024, 13:56:58
    Author     : padil
--%>

<%@page import="com.artistsagency.campusjalpa.Exposicion"%>
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
        <title></title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/misestilos.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
        <style>
            body {
                background-image: url(img/gal.png);
                background-size: cover;
                background-repeat: no-repeat;
            }
        </style>
    </head>
    <%
        //obtenemos el id de la exposicion
        Exposicion miExposicion = (Exposicion)request.getAttribute("MIEXPO");
     %>
    <body>
        <div class="fondo" style="color: white">
            <h1 class="pt-5">¿Deseas Eliminar la Exposicion <% out.print(miExposicion.getEXP_ID());%>?</h1>
        
            <a class='btn btn-secondary' href='ServletExposicion?&instruccion=listar'>Cancelar</a>

            <a class='btn btn-danger' href='ServletExposicion?EXP_ID=<%= miExposicion.getEXP_ID() %>&instruccion=eliminarBD'>Eliminar</a>
        </div>
    </body>
</html>
