/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.artistsagency.campusjalpa;

import java.util.Date;

/**
 *
 * @author music
 */
public class Exposicion {
    //declaracion de campos
    int EXP_ID;
    String EXP_NAME;
    Date EXP_START_DATE;
    Date EXP_END_DATE;
    Galeria GAL_ID;
    
    //constructor con 6 parametros
    public Exposicion(int EXP_ID, String EXP_NAME, Date EXP_START_DATE, Date EXP_END_DATE, Galeria GAL_ID) {
        this.EXP_ID = EXP_ID;
        this.EXP_NAME = EXP_NAME;
        this.EXP_START_DATE = EXP_START_DATE;
        this.EXP_END_DATE = EXP_END_DATE;
        this.GAL_ID = GAL_ID;
    }
    //constructor vacio

    public Exposicion() {
    }
    //getter and setters

    public int getEXP_ID() {
        return EXP_ID;
    }

    public void setEXP_ID(int EXP_ID) {
        this.EXP_ID = EXP_ID;
    }

    public String getEXP_NAME() {
        return EXP_NAME;
    }

    public void setEXP_NAME(String EXP_NAME) {
        if(EXP_NAME.trim().equals("")){
            throw new IllegalArgumentException("Escribe una nombre correcto");
        }
        this.EXP_NAME = EXP_NAME;
    }

    public Date getEXP_START_DATE() {
        return EXP_START_DATE;
    }

    public void setEXP_START_DATE(Date EXP_START_DATE) {
        if(EXP_START_DATE.toString().trim().equals("")){
            throw new IllegalArgumentException("La fecha debe estar correcta");
        }
        this.EXP_START_DATE = EXP_START_DATE;
    }

    public Date getEXP_END_DATE() {
        return EXP_END_DATE;
    }

    public void setEXP_END_DATE(Date EXP_END_DATE) {
        if(EXP_START_DATE.toString().trim().equals("")){
            throw new IllegalArgumentException("La fecha debe estar correcta");
        }
        this.EXP_END_DATE = EXP_END_DATE;
    }

    public Galeria getGAL_ID() {
        if(GAL_ID.toString().equals("")){
            throw new IllegalArgumentException("el id debe de ser valido");
        }
        return GAL_ID;
    }

    public void setGAL_ID(Galeria GAL_ID) {
        this.GAL_ID = GAL_ID;
    }
    
    //sobreescribir el metodo toString;

    @Override
    public String toString() {
        return "Exposicion{" + "EXP_ID=" + EXP_ID + ", EXP_NAME=" + EXP_NAME + ", EXP_START_DATE=" + EXP_START_DATE + ", EXP_END_DATE=" + EXP_END_DATE + ", GAL_ID=" + GAL_ID + '}';
    }
    
    
    
}
