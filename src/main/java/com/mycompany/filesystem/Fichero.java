/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filesystem;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class Fichero extends Archivo{
    
    private String extension;
    
    private File contenido;


    public Fichero(String nombre, String extension) throws IOException{
        this.propietario = Sistema.getUsuarioActual();
        this.nombre = nombre;
        this.extension = extension;
        this.contenido = new File("./src/Contenidos/"+nombre+extension+".txt");
        contenido.createNewFile();
        this.esDirectorio = false;
        permisosUsuario = new Permisos(false, false, false);
        permisosGrupo = new Permisos(false, false, false);
    }
    
    public String getExtension(){
        return extension;
    } 
    
    @Override
    public void Abrir(){
        Usuario usuarioActual = Sistema.getUsuarioActual();
        if(usuarioActual.getPermisos() || this.getPropietario().equals(usuarioActual.getNombre()) || 
          (this.getPermisosUsuario().getEscritura() && this.getPermisosUsuario().getLectura()) || 
          (this.getPermisosGrupo().getEscritura() && this.getPermisosGrupo().getLectura() && 
          Sistema.getUsuarioActual().compartenGrupo(propietario))){
            Desktop dt= Desktop.getDesktop();
            try {
                dt.open(contenido);
            } catch (IOException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if((this.permisosUsuario.getLectura()) || (this.permisosGrupo.getLectura() && Sistema.getUsuarioActual().compartenGrupo(propietario))){
            String[] leerArchivo = ManejadorArchivosGenerico.leerArchivo("./src/Contenidos/"+nombre+extension+".txt");
            for(String linea: leerArchivo){
                System.out.println(linea);
            }
        }
        else if((this.permisosUsuario.getEscritura()) || (this.permisosGrupo.getEscritura() && Sistema.getUsuarioActual().compartenGrupo(propietario))){
            String input = System.console().readLine();
            String[] lineas = input.split("\n");
            ManejadorArchivosGenerico.escribirArchivo("./src/Contenidos/"+nombre+extension+".txt", lineas);
        }
        else{
            System.out.println("No tiene permisos para realizar esta accion");
        }
    }
}
