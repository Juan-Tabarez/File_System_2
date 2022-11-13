/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filesystem;

import java.util.LinkedList;

/**
 *
 * @author Juan
 */
public class Usuario {
    
    private String nombre;
    
    private boolean permisosRoot;
    
    private LinkedList<Grupo> grupos;
    
    public Usuario(String nombre){
        this.nombre = nombre;
        grupos = new LinkedList<>();
        if(nombre.equals("Root")){
            permisosRoot = true;
        }
        else{
            permisosRoot = false;
        }
    }
}
