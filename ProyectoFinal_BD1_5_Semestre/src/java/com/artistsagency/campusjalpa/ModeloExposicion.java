/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.artistsagency.campusjalpa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author music
 */
public class ModeloExposicion {
    //creacion de la variable de origen de datos
    private DataSource origenDatos;
    
    //constructor que inicia el origen de datos
    public ModeloExposicion(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }
    
    /**
     * metodo que retorna todas las exposiciones
     * @return
     * @throws SQLException 
     */
    public ArrayList<Exposicion> buscarExposicion()throws SQLException{
        ArrayList<Exposicion> misExposiciones = new ArrayList<>();
        //variables para la conexion SQL
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            miConexion = origenDatos.getConnection();
            
            String query = "SELECT * FROM EXPOSICION, GALERIA" 
                    + " WHERE EXPOSICION.GAL_ID = GALERIA.GAL_ID";
            
            stmt = miConexion.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Exposicion expo = new Exposicion();
                
                expo.setEXP_ID(rs.getInt("EXP_ID"));
                expo.setEXP_NAME(rs.getString("EXP_NAME"));
                expo.setEXP_START_DATE(rs.getDate("EXP_START_DATE"));
                expo.setEXP_END_DATE(rs.getDate("EXP_END_DATE"));
                
                Galeria gal = new Galeria();
                gal.setGAL_ID(rs.getInt("GAL_ID"));
                gal.setGAL_NAME("GAL_NAME");
                gal.setGAL_ADDRESS("GAL_ADDRESS");
                gal.setGAL_EMAIL("GAL_EMAIL");
                gal.setGAL_PHONE("GAL_PHONE");
                gal.setGAL_WEBSITE("GAL_WEBSITE");
                
                expo.setGAL_ID(gal);
                
                misExposiciones.add(expo);
            }//fin del while
        }
        finally{
            try{if(rs!=null) rs.close();
            }catch(SQLException ex){}//ignore
            
            try{if(stmt!=null) stmt.close();
            }catch(SQLException ex){}//ignore
            
            try{if(miConexion!=null) miConexion.close();
            }catch(SQLException ex){}//ignore
        }
        return misExposiciones;
    }
    /***
     * Agrega una nueva exposicion a la base de datos
     * @param miExposicion
     * @throws SQLException 
     */
     public void agregarNuevaExposicion(Exposicion miExposicion) throws SQLException {
        Connection miConexion = null;
        PreparedStatement stmt = null;
       
        try{
            miConexion = origenDatos.getConnection();

            String query = "INSERT INTO EXPOSICION (EXP_NAME, EXP_START_DATE, EXP_END_DATE, GAL_ID) VALUES "
                    + " (?,?,?,?)";

            stmt = miConexion.prepareStatement(query);
            stmt.setString(1, miExposicion.getEXP_NAME());
            stmt.setDate(2, (Date) miExposicion.getEXP_START_DATE());
            stmt.setDate(3, (Date) miExposicion.getEXP_END_DATE());
            stmt.setInt(4, miExposicion.getGAL_ID().getGAL_ID());

            stmt.executeUpdate();
        }finally{            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){}//ignore
        }       
    }//fin del método agregarNuevaExposicion
     
    /**
     *Metodo que busca la exposicion por el id
     * @param id
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("null")
    Exposicion buscarExposicionPorID(int id) throws SQLException {
        Exposicion miExposicion = new Exposicion();
        
        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            miConexion = origenDatos.getConnection();

            //escribimos la consulta
            String query = "SELECT * FROM EXPOSICION, GALERIA WHERE EXP_ID=? AND"
                    + " EXPOSICION.GAL_ID = GALERIA.GAL_ID";

            stmt = miConexion.prepareStatement(query);
            stmt.setInt(1, id);

            //guardamos lo que retorne la consutla
            rs = stmt.executeQuery();
            //obtenemos los datos
            if (rs.next()){
                miExposicion.setEXP_ID(rs.getInt("EXP_ID"));
                miExposicion.setEXP_NAME(rs.getString("EXP_NAME"));
                miExposicion.setEXP_START_DATE(rs.getDate("EXP_START_DATE"));
                miExposicion.setEXP_END_DATE(rs.getDate("EXP_END_DATE"));
                
                Galeria gal = new Galeria();
                gal.setGAL_ID(rs.getInt("GAL_ID"));
                gal.setGAL_NAME(rs.getString("GAL_NAME"));
            
                miExposicion.setGAL_ID(gal);
                
            }
        }finally{
            try{ if (rs!=null) rs.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){}//ignore
        } 
        
        return miExposicion;
    }//fin del metodo para buscar exposicion por id
    
    //metodo actualizar exposicion
    public void actualizarExposicion(Exposicion miExposicion) throws SQLException {
        Connection miConexion = null;
        PreparedStatement stmt = null;

        
        try{
            miConexion = origenDatos.getConnection();

            String query = "UPDATE EXPOSICION SET EXP_NAME=?, EXP_START_DATE=?, EXP_END_DATE=?, GAL_ID=?"
                    + " WHERE EXP_ID=?";

            stmt = miConexion.prepareStatement(query);
            stmt.setString(1, miExposicion.getEXP_NAME());
            stmt.setDate(2, (Date) miExposicion.getEXP_START_DATE());
            stmt.setDate(3, (Date) miExposicion.getEXP_END_DATE());
            stmt.setInt(4, miExposicion.getGAL_ID().getGAL_ID());
            stmt.setInt(5, miExposicion.getEXP_ID());

            stmt.executeUpdate();
        }finally{            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){}//ignore
        }     
    }
    
    public void eliminarExposicion(int id) throws SQLException{
        Connection miConexion = null;
        PreparedStatement stmt = null;
       
        try{
            miConexion = origenDatos.getConnection();

            String query = "DELETE FROM EXPOSICION WHERE EXP_ID=?";

            stmt = miConexion.prepareStatement(query);
            stmt.setInt(1, id);          

            stmt.executeUpdate();
        }finally{            
            try{ if (stmt!=null) stmt.close();
            }catch(SQLException ex){}//ignore
            
            try{ if (miConexion!=null) miConexion.close();
            }catch(SQLException ex){}//ignore
        }
    }//fin del método eliminarExposicion
}//fin de la clase