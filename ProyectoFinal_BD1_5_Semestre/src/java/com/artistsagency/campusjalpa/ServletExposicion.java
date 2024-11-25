/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.artistsagency.campusjalpa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author music
 */
@WebServlet(name = "ServletExposicion", urlPatterns = {"/ServletExposicion"})
public class ServletExposicion extends HttpServlet {

    // Declaración de un objeto para manejar la lógica de negocio relacionada con exposiciones
    private ModeloExposicion modeloExposicion;

    // Declaración de un objeto para manejar la lógica de negocio relacionada con galerías
    private ModeloGaleria modeloGaleria;

    // Inyección del recurso DataSource configurado en el servidor de aplicaciones
    @Resource(name = "jdbc/ProyectoFinalBD_DAW")
    private DataSource miPool;

    // Método de inicialización que se ejecuta al iniciar el servlet
    @Override
    public void init() throws ServletException {
        super.init(); // Llama al método init de la clase base para garantizar la inicialización estándar

        try {
            // Inicializa el modelo de exposiciones con la conexión al pool de la base de datos
            modeloExposicion = new ModeloExposicion(miPool);
            // Inicializa el modelo de galerías con la conexión al pool de la base de datos
            modeloGaleria = new ModeloGaleria(miPool);
        } catch (Exception ex) {
            // Lanza una excepción ServletException si ocurre algún error durante la inicialización
            throw new ServletException(ex);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //leer el parametro que le llega desde el formulario
        String bandera = request.getParameter("instruccion");

        //si no se envia el parametro listar los libros
        if (bandera == null) {
            bandera = "listar";
        }

        //redirigir el flujo de ejecucion al metodo adecuado
        switch (bandera) {
            case "listar":
                obtenerExposiciones(request, response);
                break;
            case "nuevaexpo":
                nuevaExposicion(request, response);
                break;
            case "agregarexpoBD":
                agregarExposicion(request, response);
                response.sendRedirect(request.getContextPath() + "/ServletExposicion?instruccion=listar");
                break;
            case "editarexpo":
                editarExposicion(request, response);
                break;
            case "guardaredicionBD":
                guardarCambiosExposicion(request, response);
                response.sendRedirect(request.getContextPath() + "/ServletExposicion?instruccion=listar");
                break;
            case "intentoeliminar":
                intentoEliminar(request, response);
                break;
            case "eliminarBD":
                eliminarExposicion(request, response);
                response.sendRedirect(request.getContextPath() + "/ServletExposicion?instruccion=listar");
                break;
            default:
                obtenerExposiciones(request, response);
        }
    }//fin del metodo doGet

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void obtenerExposiciones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //obtener la lista de las exposiciones desde el modelo
        ArrayList<Exposicion> misExposiciones;

        try {
            misExposiciones = modeloExposicion.buscarExposicion();

            //agregamos la lista de productos al request
            request.setAttribute("TODASLASEXPOSICIONES", misExposiciones);

            //enviar ese request a la pagina JSP
            RequestDispatcher miDispatcher = request.getRequestDispatcher("exposicion.jsp");

            miDispatcher.forward(request, response);

        } catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }//fin del metodo obtener exposiciones

    /**
     * Agrega las galerias a un combobox para ser elegidas y abre la pagina de
     * agregar nueva exposicion
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void nuevaExposicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //obtenemos las galerias
        ArrayList<Galeria> misGalerias;

        try {
            //obtenemos la lista de galerias 
            misGalerias = modeloGaleria.getGalerias();
            // Verificar si la lista de galerías es nula o está vacía
            if (misGalerias == null || misGalerias.isEmpty()) {
                // Enviar mensaje de error si no hay galerías disponibles
                request.setAttribute("mensajeError", "No hay galerías registradas. Por favor, agrega una galería primero.");
                RequestDispatcher miDispatcher = request.getRequestDispatcher("error.jsp");
                miDispatcher.forward(request, response);
                return;
            }
            //agregamos la lista de Galerias al request
            request.setAttribute("TODASLASGALERIAS", misGalerias);

            //enviar ese request a la página JSP
            RequestDispatcher miDispatcher = request.getRequestDispatcher("insertar_expo.jsp");

            miDispatcher.forward(request, response);

        } catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }//fin del método nuevaExposicion

    /**
     * agrega una exposicion
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void agregarExposicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // leer la información del producto que viene del formulario
            String nombre = request.getParameter("exp-name");
            int gal_id = Integer.parseInt(request.getParameter("gal-id"));
            //convertir las fechas a java.sql.Date
            String startDateS = request.getParameter("startdate");
            String endDateS = request.getParameter("enddate");
            //formatea los datos a algo que puede leer SQL: YYY-MM-DD
            java.sql.Date startDate = java.sql.Date.valueOf(startDateS);
            java.sql.Date endDate = java.sql.Date.valueOf(endDateS);
            // Validar que la fecha de fin no sea anterior a la de inicio
            if (endDate.before(startDate)) {
                throw new IllegalArgumentException("La fecha de conclusión no puede ser anterior a la fecha de inicio.");
            }

            //Validar que el nombre de la exposicion no sea nula
            if (nombre == null) {
                throw new IllegalArgumentException("Nombre de la Exposición es null.");
            }
            //busca la galeria asignada por su id 
            Galeria miGaleria = modeloGaleria.buscarGaleriaPorId(gal_id);
            //crea un nuevo objeto exposicion
            Exposicion miExposicion = new Exposicion();
            //añade los datos al nuevo objeto
            miExposicion.setEXP_NAME(nombre);
            miExposicion.setEXP_START_DATE(startDate);
            miExposicion.setEXP_END_DATE(endDate);
            miExposicion.setGAL_ID(miGaleria);

            // Enviar el objeto al modelo y después insertar el objeto exposicion a la bd
            modeloExposicion.agregarNuevaExposicion(miExposicion);

        } catch (IllegalArgumentException ex) {
            mensajeDeError(ex.getMessage(), "error.jps", request, response);
        } catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jps", request, response);
        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jps", request, response);
        }
    }//fin del método agregarExposicion

    //-----------------------------------------------------------------------------------------------
    /**
     * metodo para editar las exposiciones
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void editarExposicion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //obtenemos el id de la exposicion seleccionada
        int id = Integer.parseInt(request.getParameter("EXP_ID"));
        try {
            //realizamos la consulta a la base de datos para obtener todos los datos del id seleccionado
            Exposicion miExposicion = modeloExposicion.buscarExposicionPorID(id);

            ArrayList<Galeria> misGalerias = new ArrayList<>();

            misGalerias = modeloGaleria.getGalerias();
            // Verificar si la lista de galerías es nula o está vacía
            if (misGalerias == null || misGalerias.isEmpty()) {
                // Enviar mensaje de error si no hay galerías disponibles
                request.setAttribute("mensajeError", "No hay galerías registradas. Por favor, agrega una galería primero.");
                RequestDispatcher miDispatcher = request.getRequestDispatcher("error.jsp");
                miDispatcher.forward(request, response);
                return;
            }
            //agregamos los datos al request
            request.setAttribute("MIEXPOSICION", miExposicion);

            request.setAttribute("TODASLASGALERIAS", misGalerias);

            //enviamos el request a la página JSP
            RequestDispatcher miDispatcher
                    = request.getRequestDispatcher("editar.jsp");

            miDispatcher.forward(request, response);
        } catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jps", request, response);
        }
    }//fin del metodo editar Exposiciones 

    /**
     * guarda la exposicion
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void guardarCambiosExposicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // leer la información del producto que viene del formulario
            int id = Integer.parseInt(request.getParameter("exp-id"));
            String nombre = request.getParameter("exp-name");
            int gal_id = Integer.parseInt(request.getParameter("gal-id"));

            //convertir y recibir las fechas a java.sql.Date desde el formulario
            String startDateS = request.getParameter("startdate");
            String endDateS = request.getParameter("enddate");

            //formatea los datos a algo que puede leer SQL: YYY-MM-DD
            java.sql.Date startDate = java.sql.Date.valueOf(startDateS);
            java.sql.Date endDate = java.sql.Date.valueOf(endDateS);

            // Validar que la fecha de fin no sea anterior a la de inicio
            if (endDate.before(startDate)) {
                throw new IllegalArgumentException("La fecha de conclusión no puede ser anterior a la fecha de inicio.");
            }

            //Validar que el nombre de la exposicion no sea nula
            if (nombre == null) {
                throw new IllegalArgumentException("Nombre de la Exposición es nula.");
            }

            Galeria miGaleria = modeloGaleria.buscarGaleriaPorId(gal_id);

            //creamos un objeto de tipo exposicion
            Exposicion miExposicion = new Exposicion();

            miExposicion.setEXP_NAME(nombre);
            miExposicion.setEXP_START_DATE(startDate);
            miExposicion.setEXP_END_DATE(endDate);
            miExposicion.setGAL_ID(miGaleria);
            miExposicion.setEXP_ID(id);

            // enviar el objeto al modelo y despues realizar los cambios en la bd
            modeloExposicion.actualizarExposicion(miExposicion);

        } catch (IllegalArgumentException ex) {
            String mensaje = ex.getMessage();

            request.setAttribute("mensaje", mensaje);

            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (SQLException ex) {
            String mensaje = ex.getMessage();

            request.setAttribute("mensaje", mensaje);

            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception ex) {
            String mensaje = ex.getMessage();

            request.setAttribute("mensaje", mensaje);

            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }//fin del método guardarCambiosExposicion

    //----------------------------------------------------------------------------------------------
    /**
     * Metodo para eliminar una exposicion
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void eliminarExposicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("EXP_ID"));

        try {
            //llamamos al método para eliminar la exposicion
            modeloExposicion.eliminarExposicion(id);
        } catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }//fin del metodo eliminar 

    /**
     * Ingresa a la pagina para confirmar la eliminacion de una fila
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void intentoEliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //obtenemos el id de la exposicion seleccionada
        int id = Integer.parseInt(request.getParameter("EXP_ID"));

        try {
            //realizamos la consulta a la base de datos para obtener todos los datos del id seleccionado
            Exposicion miExposicion = modeloExposicion.buscarExposicionPorID(id);

            //agregamos los datos al request
            request.setAttribute("MIEXPO", miExposicion);

            //enviar ese request a la página JSP
            RequestDispatcher miDispatcher = request.getRequestDispatcher("eliminar.jsp");

            miDispatcher.forward(request, response);

        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }//fin del método intento eliminar

    /**
     * manda un mensaje de error mostrandolo en la pagina error.jsp
     *
     * @param ex
     * @param errorjsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void mensajeDeError(String ex, String errorjsp, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mensaje", ex);

        request.getRequestDispatcher("error.jsp").forward(request, response);
    }//fin del metodo mensaje error
}
