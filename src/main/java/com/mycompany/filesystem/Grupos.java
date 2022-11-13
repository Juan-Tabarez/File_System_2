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
        if(buscarGrupo(nombre) != null){
            throw new Exception("Grupo existente");
        }
        Grupo grupoNuevo = new Grupo(nombre);
        grupos.add(grupoNuevo);
    }
    
    public static void EliminarGrupo(String nombre) throws Exception{
        Grupo grupo = buscarGrupo(nombre);
        if(grupo == null){
            throw new Exception("Grupo no existente");
        }
        grupos.remove(grupo);  
    }
    
    public static Grupo buscarGrupo(String nombre){
        for(Grupo grupo: grupos){
            if(grupo.getNombre().equals(nombre)){
                return grupo;
            }
        }
        return null;
    }
    
     public void agregarUsuarioAGrupo(String nombreUsuario, String nombreGrupo) throws Exception{
        Grupo grupo = Grupos.buscarGrupo(nombreGrupo);
        Usuario usuario = Usuarios.buscarUsuario(nombreUsuario);
        if(grupo == null){
            throw new Exception("Grupo no existente");
        }
        if(usuario == null){
            throw new Exception("Usuario no existente");        
        }
        if(grupo.ConieneUsuario(nombreUsuario)){
            throw new Exception("El usuario "+nombreUsuario+" ya pertenece al grupo "+nombreGrupo);
        }
        grupo.agregarUusario(usuario);
        usuario.agregarGrupo(grupo);
    }
}
