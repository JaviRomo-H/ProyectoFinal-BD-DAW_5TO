<%-- 
    Document   : insertar
    Created on : 15 nov 2024, 14:12:13
    Author     : padil
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.artistsagency.campusjalpa.Galeria"%>
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
    if (uid == null) {
        response.sendRedirect("login.jsp");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Exposicion</title>
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
            }
        </style>
    </head>
    <body>
        <div class="fondo" style="color: white">
            <div class="container mt-5 mb-5">
            <h1 style="text-align: center">Alta de Exposiciones</h1>

            <form method="get" action="ServletExposicion">
                <input type="hidden" name="instruccion" value="agregarexpoBD">
                <div class="form-group">
                    <label for="exp-name">Nombre de Exposicion</label>
                    <input type="text" class="form-control" name="exp-name" id="exp-name" maxlength="100" required>
                </div>
                <div class="form-group">
                    <label for="startdate">Fecha de Inicio</label>
                    <input type="date" class="form-control" name="startdate" id="startdate" required>
                </div>
                <div class="form-group">
                    <label for="enddate">Fecha de Conclusión</label>
                    <input type="date" class="form-control" name="enddate" id="enddate" min="" required>
                </div>

                <!-- mostramos la lista de galerias obtenidas desde la base de datos -->
                <div class="form-group">
                    <label for="gal-id">Galeria:</label>
                    <SELECT name="gal-id" class="custom-select" required="">
                        <option value="" selected>Selecciona una Galeria</option>
                        <%
                            ArrayList<Galeria> lstGalerias
                                    = (ArrayList<Galeria>) request.getAttribute("TODASLASGALERIAS");

                            for (Galeria tempGaleria : lstGalerias) {
                                out.print("<option value='"
                                        + tempGaleria.getGAL_ID() + "'>" + tempGaleria.getGAL_NAME()
                                        + "</option>");
                            }
                        %>
                    </SELECT>
                </div>               

                <button type="submit" class="btn btn-info">Submit</button>
                <button type="reset" class="btn btn-secondary">Reset</button>
                <a class='btn btn-danger' href='ServletExposicion?&instruccion=listar'>Cancelar</a>
            </form>
        </div>
        </div>
        
        <script>
            const startdate = document.getElementById("startdate");
            const enddate = document.getElementById("enddate");

            // Deshabilitar enddate al cargar la página
            enddate.disabled = true;

            // Listener para habilitar enddate y establecer su mínimo
            startdate.addEventListener("change", () => {
                if (startdate.value) {
                    // Habilitar enddate
                    enddate.disabled = false;
                    // Establecer la fecha mínima en enddate
                    enddate.min = startdate.value;
                } else {
                    // Si no se selecciona una fecha válida, volver a deshabilitar enddate
                    enddate.disabled = true;
                    enddate.value = ""; // Limpiar cualquier valor anterior
                }
            });
        </script>
        <div>
            <footer class="footer">© 2024. Derechos Protegidos.</footer>
        </div>
    </body>
</html>
