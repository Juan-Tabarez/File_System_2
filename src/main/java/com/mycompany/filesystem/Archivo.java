/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filesystem;

import java.io.File;

/**
 *
 * @author Juan
 */
public class Archivo {
    
    protected String nombre;
    
    protected File contenido;
    
    protected String extension;

    protected boolean esDirectorio;
  
    protected Usuario propietario;
    
    protected Permisos permisosPropietario;
    
    protected Permisos permisosGrupo;
    
    protected Permisos permisosUsuario;
    
    public void Abrir(){}
    
}
