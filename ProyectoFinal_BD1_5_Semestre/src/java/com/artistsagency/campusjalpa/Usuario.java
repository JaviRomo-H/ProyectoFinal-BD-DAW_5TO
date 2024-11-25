/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.artistsagency.campusjalpa;

/**
 *
 * @author music
 */
public class Usuario {
    //declaracion de campos
    String user;
    String password;

    //constructor del usuario
    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }
    //constructor vacio

    public Usuario() {
    }
    
    //getters y setters

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        if(user.equals("")){
            throw new IllegalArgumentException("usuario incorrecto");
        }
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.equals("")){
            throw new IllegalArgumentException("contraseña incorrecta");
        }
        this.password = password;
    }
    
    
}
