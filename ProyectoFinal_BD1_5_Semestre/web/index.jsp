<%-- 
    Document   : index
    Created on : 12 nov 2024, 18:17:27
    Author     : padil
--%>

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
        <title>Bienvenido</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

        <div class="container" style="display: flex; justify-content: center; align-items: center; margin-top: 100px;">
            <img src="img/logazo2.png" alt="alt" width="500" height="500"/>
        </div> 

        <footer class="footer text-center mt-5">© 2024. Derechos Protegidos.</footer>
    </body>
</html>
