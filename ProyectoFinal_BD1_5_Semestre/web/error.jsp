<%-- 
    Document   : error
    Created on : 17 nov. 2022, 21:51:43
    Author     : alopezorozco
--%>

<%@page import="com.artistsagency.campusjalpa.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title></title>
            <link rel="stylesheet" href="css/bootstrap.min.css">
            <link rel="stylesheet" href="css/misestilos.css"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col">
                    <h1>Error</h1>
                    <p>
                    <% 
                        String mensaje = (String) request.getAttribute("mensaje"); 
                        out.print("<span class='error'>"+ mensaje+"</span>"); 
                    %>                    
                    </p>
                    <input type="button" class="btn btn-info" onclick="history.back()" value="Regresar">
                </div>
            </div>
        </div>
    <script src="js/jquery.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/script.js"></script>            
    </body>    
</html>
