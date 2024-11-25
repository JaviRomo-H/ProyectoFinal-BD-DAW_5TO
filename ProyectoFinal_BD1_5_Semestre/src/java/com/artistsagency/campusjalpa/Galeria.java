/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.artistsagency.campusjalpa;

/**
 * Representa a la tabla Galeria
 *hacen falta validaciones importantes
 * @author music
 */
public class Galeria {

    //declaración de campos
    private int GAL_ID;
    private String GAL_NAME;
    private String GAL_ADDRESS;
    private String GAL_PHONE;
    private String GAL_EMAIL;
    private String GAL_WEBSITE;

    //constructor con 6 parametros 
    public Galeria(int GAL_ID, String GAL_NAME, String GAL_ADDRESS, String GAL_PHONE, String GAL_EMAIL, String GAL_WEBSITE) {
        this.GAL_ID = GAL_ID;
        this.GAL_NAME = GAL_NAME;
        this.GAL_ADDRESS = GAL_ADDRESS;
        this.GAL_PHONE = GAL_PHONE;
        this.GAL_EMAIL = GAL_EMAIL;
        this.GAL_WEBSITE = GAL_WEBSITE;
    }

    //constructor vacío
    public Galeria() {
    }

    //getters y setters
    public int getGAL_ID() {
        return GAL_ID;
    }

    public void setGAL_ID(int GAL_ID) {
        this.GAL_ID = GAL_ID;
    }

    public String getGAL_NAME() {
        return GAL_NAME;
    }

    public void setGAL_NAME(String GAL_NAME) {
        if(GAL_NAME.trim().equals("")){
            throw new IllegalArgumentException("Escribe una nombre correcto");
        }
        this.GAL_NAME = GAL_NAME;
    }

    public String getGAL_ADDRESS() {
        return GAL_ADDRESS;
    }

    public void setGAL_ADDRESS(String GAL_ADDRESS) {
        if(GAL_ADDRESS.trim().equals("")){
            throw new IllegalArgumentException("Escribe una direccion correcta");
        }
        this.GAL_ADDRESS = GAL_ADDRESS;
    }

    public String getGAL_PHONE() {
        return GAL_PHONE;
    }

    public void setGAL_PHONE(String GAL_PHONE) {
        if(GAL_PHONE.trim().equals("")){
            throw new IllegalArgumentException("Escribe un numero telefonico correcto");
        }
        this.GAL_PHONE = GAL_PHONE;
    }

    public String getGAL_EMAIL() {
        return GAL_EMAIL;
    }

    public void setGAL_EMAIL(String GAL_EMAIL) {
        if(GAL_EMAIL.trim().equals("")){
            throw new IllegalArgumentException("Escribe una correo correcto");
        }
        this.GAL_EMAIL = GAL_EMAIL;
    }

    public String getGAL_WEBSITE() {
        return GAL_WEBSITE;
    }

    public void setGAL_WEBSITE(String GAL_WEBSITE) {
        if(GAL_WEBSITE.trim().equals("")){
            throw new IllegalArgumentException("Escribe una sitio web correcto");
        }
        this.GAL_WEBSITE = GAL_WEBSITE;
    }
    //sobreescribir el metodo toString
    @Override
    public String toString() {
        return "Galeria{" + "GAL_ID=" + GAL_ID + ", GAL_NAME=" + GAL_NAME + ", GAL_ADDRESS=" + GAL_ADDRESS + ", GAL_PHONE=" + GAL_PHONE + ", GAL_EMAIL=" + GAL_EMAIL + ", GAL_WEBSITE=" + GAL_WEBSITE + '}';
    }
}
