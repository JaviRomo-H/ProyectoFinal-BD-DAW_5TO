<%-- 
    Document   : login
    Created on : 12 nov 2024, 18:18:24
    Author     : padil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // No almacenar en caché
    response.setHeader("Pragma", "no-cache"); // No almacenar en caché (para navegadores antiguos)
    response.setDateHeader("Expires", 0); // Expira inmediatamente
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/misestilos.css"/>
        <link rel="stylesheet" href="css/estilos.css"/>
        <style>
            body {
                background-image: url(img/expo.png);
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center;
                height: 100vh;
                margin: 0;
            }
        </style>
    </head>
    <body class="body_in">
            <div class="container mt-5 fondo" style="color: white">
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <form class="form-signin" action="ServletUsuario" method="Post">
                            <input type="hidden" name="instruccion" value="verificarusuario">
                            <h1 class="h3 mb-3 font-weight-normal">Introduce tus credenciales</h1>
                            <label for="inputUser" class="sr-only">Nombre de Usuario</label>
                            <input type="text" id="USER_NAME" name="USER_NAME" class="form-control mt-3" placeholder="Nombre de Usuario" required autofocus>
                            <label for="inputPassword" class="sr-only">Password</label>
                            <input type="password" id="USER_PASSWORD" name="USER_PASSWORD" class="form-control mt-3" placeholder="Password" required>
                            <button class="btn btn-lg btn-primary btn-block mt-3" type="submit">Ingresar</button>
                            <%
                                String mensajeError = (String) request.getAttribute("ERRORMENSAJE");
                                if(mensajeError != null){
                            %>
                            <label class="mensajeDeError" class="mensajeDeError"><% out.print(mensajeError); %></label>
                            <%
                                }
                            %>
                        </form>
                        
                    </div>
                </div>  
            </div>
            <div>
                <footer class="footer">© 2024. Derechos Protegidos.</footer>
            </div>
    </body>
    
</html>

