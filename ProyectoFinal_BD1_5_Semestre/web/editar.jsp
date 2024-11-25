<%-- 
    Document   : editar
    Created on : 15 nov 2024, 14:30:01
    Author     : padil
--%>

<%@page import="java.util.*"%>
<%@page import="com.artistsagency.campusjalpa.*"%>
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
        <title>Editar Exposicion</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
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
        //obtenemos los datos de la exposicion
        Exposicion miExposicion = (Exposicion) request.getAttribute("MIEXPOSICION");
    %>
    <body>
        <div class="container mt-5 mb-5 fondo" style="color: white">
            <h1>Actualizar Exposicion</h1>
            <form method="get" action="ServletExposicion">
                <input type="hidden" name="instruccion" value="guardaredicionBD">
                <div class="form-group">
                    <label for="exp-id">ID:</label>
                    <input type="number" class="form-control" 
                           name="exp-id" id="exp-id" maxlength="8" 
                           value="<% out.print(miExposicion.getEXP_ID()); %>" readonly>
                </div>

                <div class="form-group">
                    <label for="exp-name">Nombre de exposicion</label>
                    <input type="text" class="form-control" 
                           name="exp-name" id="exp-name" maxlength="100"
                           value="<% out.print(miExposicion.getEXP_NAME()); %>" required>
                </div>

                <div class="form-group">
                    <label for="startdate">Fecha de inicio</label>
                    <input type="date" class="form-control" 
                           name="startdate" id="startdate" 
                           value="<% out.print(miExposicion.getEXP_START_DATE()); %>" required>
                </div>

                <div class="form-group">
                    <label for="enddate">Fecha de terminacion</label>
                    <input type="date" class="form-control"  
                           name="enddate" id="enddate" 
                           value="<% out.print(miExposicion.getEXP_END_DATE()); %>" min=""
                           required>
                </div>

                <!-- mostramos la lista de galerias obtenidas desde la base de datos -->
                <div class="form-group">
                    <label for="gal-id">Galeria:</label>
                    <SELECT name="gal-id" class="custom-select" required>
                        <%-- galeria preseleccionada --%>
                        <option value="<% out.print(miExposicion.getGAL_ID().getGAL_ID()); %>"
                                ><% out.print(miExposicion.getGAL_ID().getGAL_NAME()); %></option>
                        <%
                            ArrayList<Galeria> lstGalerias
                                    = (ArrayList<Galeria>) request.getAttribute("TODASLASGALERIAS");

                            for (Galeria tempGal : lstGalerias) {
                                //si es diferente a la galeria ya preseleccionada se muestra
                                if(!miExposicion.getGAL_ID().getGAL_NAME().equals(tempGal.getGAL_NAME())){
                                    out.print("<option value='"
                                        + tempGal.getGAL_ID() + "'>" + tempGal.getGAL_NAME()
                                        + "</option>");
                                }
                                
                            }
                        %>
                    </SELECT>
                </div>         
                <button type="submit" class="btn btn-info">Actualizar Exposicion</button>
                <a class='btn btn-danger' href='ServletExposicion?&instruccion=listar'>Cancelar</a>
            </form>
        </div>
        <script>
            const startdate = document.getElementById("startdate");
            const enddate = document.getElementById("enddate");
            
            //establece la fecha que ya tiene, como la minima en enddate
            if (startdate.value) {
                    // Establecer la fecha mínima en enddate
                    enddate.min = startdate.value;
                }
            // Listener para habilitar enddate y establecer su mínimo
            startdate.addEventListener("change", () => {
                if (startdate.value) {
                    // Establecer la fecha mínima en enddate
                    enddate.min = startdate.value;
                    enddate.value = startdate.value;
                } else {
                    enddate.value = ""; // Limpiar cualquier valor anterior
                }
            });
        </script>
    </body>
</html>
