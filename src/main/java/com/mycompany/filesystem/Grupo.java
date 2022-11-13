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
public class Grupo {
    
    private String nombre;
    
    private LinkedList<Usuario> usuarios;
    
    public Grupo(String nombre){
        this.nombre = nombre;
        usuarios = new LinkedList<>();
    }
    
    public String getNombre(){
        return this.nombre;
    }
}
