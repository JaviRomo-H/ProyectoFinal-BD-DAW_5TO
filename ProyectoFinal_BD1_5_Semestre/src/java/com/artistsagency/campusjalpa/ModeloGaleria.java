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
public class ModeloGaleria {
    //Creamos la variable para obtener el origen de los datos
    private DataSource origenDatos;
    
    //constructor con un parámetro
    public ModeloGaleria(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }
    
    /**
     * retorna la lista de todas las galerias
     * @return
     * @throws SQLException 
     */
    public ArrayList<Galeria> getGalerias() throws SQLException{
        //creamos la lista a retornar
        ArrayList<Galeria> lstGalerias = new ArrayList<>();
        
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            //nos conectamos mediante el pool de conexiones
            miConexion = origenDatos.getConnection();

            //creamos la consulta
            String query = "SELECT * FROM GALERIA";

            //ejecutamos la consulta
            stmt = miConexion.prepareStatement(query);

            //guardamos la consulta en el resultset
            rs = stmt.executeQuery();

            //recorremos el resultado para guardarlo en el arraylist
            while(rs.next()){
                Galeria miGaleria = new Galeria();
                miGaleria.setGAL_ID(rs.getInt("GAL_ID"));
                miGaleria.setGAL_NAME(rs.getString("GAL_NAME"));
                miGaleria.setGAL_PHONE(rs.getString("GAL_PHONE"));
                miGaleria.setGAL_ADDRESS(rs.getString("GAL_ADDRESS"));
                miGaleria.setGAL_EMAIL(rs.getString("GAL_EMAIL"));
                miGaleria.setGAL_WEBSITE(rs.getString("GAL_WEBSITE"));
                //adicionamos el objeto al arraylist
                lstGalerias.add(miGaleria);       
            }
        }finally{
            try{ if (rs!=null) rs.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){}//ignore
        } 
        
        return lstGalerias;
    }
    
    /**
     * Retorna un objeto de tipo GALERIA según el id proporcionado
     * @param id
     * @return
     * @throws SQLException 
     */
    public Galeria buscarGaleriaPorId(int id) throws SQLException{
        Galeria miGaleria = new Galeria();
        
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            miConexion = origenDatos.getConnection();

            //escribimos la consulta
            String query = "SELECT * FROM GALERIA WHERE GAL_ID=?";

            stmt = miConexion.prepareStatement(query);
            stmt.setInt(1, id);

            //guardamos lo que retorne la consutla
            rs = stmt.executeQuery();

            //obtenemos los datos
            if (rs.next()){
                miGaleria.setGAL_ID(rs.getInt("GAL_ID"));
                miGaleria.setGAL_NAME(rs.getString("GAL_NAME"));
                miGaleria.setGAL_PHONE(rs.getString("GAL_PHONE"));
                miGaleria.setGAL_ADDRESS(rs.getString("GAL_ADDRESS"));
                miGaleria.setGAL_EMAIL(rs.getString("GAL_EMAIL"));
                miGaleria.setGAL_WEBSITE(rs.getString("GAL_WEBSITE"));
            }
        }finally{
            try{ if (rs!=null) rs.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){}//ignore
        }
        
        return miGaleria;
    }//fin del método
}
