/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.artistsagency.campusjalpa;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "ServletUsuario", urlPatterns = {"/ServletUsuario"})
public class ServletUsuario extends HttpServlet {

    private ModeloUsuario modeloUsuario;

    @Resource(name = "jdbc/ProyectoFinalBD_DAW")
    private DataSource miPool;

    @Override
    public void init() throws ServletException {
        super.init();

        try {

            modeloUsuario = new ModeloUsuario(miPool);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //leer el parametro que le llega desde el formulario
        String bandera = request.getParameter("instruccion");

        //si no se envia el parametro verificar usuario
        if (bandera == null) {
            bandera = "verificarusuario";
        }

        //redirigir a el metodo adecuado
        switch (bandera) {
            case "verificarusuario":
                verificarUsuario(request, response);
                break;
            case "cerrarsesion":
                cerrarSesion(request, response);
                break;
            default:
                verificarUsuario(request, response);
        }

    }

    private void verificarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //recolectamos lo que escribio el usuario en el formulario de username and password
        String user = request.getParameter("USER_NAME");
        String password = request.getParameter("USER_PASSWORD");
        //valida posibles nulos en el usuario y contraseña
        if (user == null || user.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("ERRORMENSAJE", "El usuario y la contraseña son obligatorios");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            //se sale del metodo con el return
            return;
        }
        //encriptamos la contraseña con formato md5 para poder compararla con la contraseña encriptada de la base de datos
        String encryptedPassword = generarMD5(password);
        try {
            //realizamos la consulta a la base de datos para obtener la respuesta de si existe o no 
            //un usuario con esa contraseña
            boolean miUsuario = modeloUsuario.validarUsuario(user, encryptedPassword);

            //si miUsuario = true entonces se redirige a la pagina index
            if (miUsuario) {
                HttpSession session = request.getSession();
                session.setAttribute("username", user);
                response.sendRedirect("index.jsp");
            } else {//si es falso se manda un error mediante un request
                request.setAttribute("ERRORMENSAJE", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }//fin del metodo verificar usuario y crear session

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidar la sesión actual
        HttpSession session = request.getSession(false); // Evita crear una nueva sesión si no existe
        if (session != null) {
            session.invalidate();
        }
        // Redirigir a la página de inicio de sesión
        response.sendRedirect("login.jsp");
    }//fin del metodo que destruye la session

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

    // Método para generar un hash MD5 a partir de una cadena de texto
    private String generarMD5(String input) {
        try {
            // Crear una instancia de MessageDigest para el algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Calcular el hash de la entrada en formato de bytes
            byte[] messageDigest = md.digest(input.getBytes());

            // Convertir los bytes del hash a una representación en formato hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                // Convertir cada byte a un valor hexadecimal sin signo
                String hex = Integer.toHexString(0xff & b);

                // Si el valor hexadecimal tiene una sola cifra, añadir un '0' al inicio para mantener el formato
                if (hex.length() == 1) {
                    hexString.append('0');
                }

                // Añadir el valor hexadecimal al StringBuilder
                hexString.append(hex);
            }

            // Devolver la cadena hexadecimal resultante (el hash MD5)
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            // Manejar la excepción si el algoritmo MD5 no está disponible en la plataforma
            throw new RuntimeException(e);
        }
    }//fin del metodo para generar md5
}//fin del ServletUsuario
