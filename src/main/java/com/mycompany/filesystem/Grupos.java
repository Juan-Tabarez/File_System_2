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
public class Grupos {
    
    public static LinkedList<Grupo> grupos = new LinkedList<>();
    
    public static void CrearGrupo(String nombre) throws Exception{
        if(existeGrupo(nombre)){
            throw new Exception("Grupo existente");
        }
        Grupo grupoNuevo = new Grupo(nombre);
        grupos.add(grupoNuevo);
    }
    
    public static void EliminarGrupo(String nombre) throws Exception{
        if(!existeGrupo(nombre)){
            throw new Exception("Grupo no existente");
        }
        for(Grupo grupo: grupos){
            if(grupo.getNombre().equals(nombre)){
                grupos.remove(grupo);
                break;
            }
        }
    }
    
    public static boolean existeGrupo(String nombre){
        for(Grupo grupo: grupos){
            if(grupo.getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }
}
