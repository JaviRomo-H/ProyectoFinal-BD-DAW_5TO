/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.artistsagency.campusjalpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author music
 */
public class ModeloUsuario {
    //Creamos la variable para obtener el origen de los datos
    private DataSource origenDatos;
    
    //constructor con un parámetro
    public ModeloUsuario(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }
    
    public boolean validarUsuario(String userName, String userPassword) throws SQLException{
        Usuario miUsuario = null;
        
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            miConexion = origenDatos.getConnection();

            //escribimos la consulta
            String query = "SELECT * FROM USUARIO WHERE USER_NAME=?";

            stmt = miConexion.prepareStatement(query);
            stmt.setString(1, userName);

            //guardamos lo que retorne la consutla
            rs = stmt.executeQuery();

            //obtenemos los datos
            if (rs.next()){
                miUsuario = new Usuario();
                miUsuario.setUser(rs.getString("USER_NAME"));
                miUsuario.setPassword(rs.getString("USER_PASSWORD"));
            }
        }finally{
            try{ if (rs!=null) rs.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){}//ignore
        }
        // Si no se encontró un usuario, devolvemos false
        if (miUsuario == null) {
            return false;
        }
        //validamos los datos
            if(miUsuario.getUser().equals(userName)){
                if(miUsuario.getPassword().equals(userPassword)){
                    return true;
                }
            }
        
        return false;
    }//fin del método

}
