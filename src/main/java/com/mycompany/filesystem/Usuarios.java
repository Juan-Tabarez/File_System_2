/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filesystem;

import java.util.LinkedList;

/**
 *
 * @author jtaba
 */
public class Usuarios {
    
    public static LinkedList<Usuario> usuarios = new LinkedList<>();
    
    public static void CrearUsuarios(String nombre) throws Exception{
        Usuario usuario = buscarUsuario(nombre);
        if(usuario != null){
            throw new Exception("Usuario ya existente");
        }
        Usuario nuevoUsuario = new Usuario(nombre);
        usuarios.add(nuevoUsuario);
    }
    
    public static void EliminarUsuarios(String nombre) throws Exception{
        Usuario usuario = buscarUsuario(nombre);
        if(usuario == null){
            throw new Exception("Usuario no existente");
        }
        usuarios.remove(usuario);
    }
    
    public static Usuario buscarUsuario(String nombre){
        for(Usuario usuario: usuarios){
            if(usuario.getNombre().equals(nombre)){
                return usuario;
            }
        }
        return null;
    }

}
