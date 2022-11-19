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
public class Directorio extends Archivo {

    private LinkedList<Fichero> ficheros;

    private LinkedList<Directorio> directorios;

    public Directorio(String nombre) {
        this.propietario = Sistema.getUsuarioActual();
        esDirectorio = true;
        ficheros = new LinkedList<Fichero>();
        directorios = new LinkedList<Directorio>();
        this.nombre = nombre;
        permisosUsuario = new Permisos(false, false, false);
        permisosGrupo = new Permisos(false, false, false);
    }

    public void MostrarArchivos() {
        if (directorios != null) {
            for (Directorio directorio : directorios) {
                System.out.println(directorio.nombre);
            }
        }
        if (ficheros != null) {
            for (Fichero fichero : ficheros) {
                System.out.println(fichero.nombre + "." + fichero.getExtension());
            }
        }
    }

    public void NuevaCarpeta(String nombre) throws Exception {
        boolean existe = false;
        for (Directorio directorio : directorios) {
            if (directorio.nombre.equals(nombre)) {
                existe = true;
                break;
            }
        }
        if (existe) {
            System.out.println("Directorio existente");
        } else {
            Usuario usuarioActual = Sistema.getUsuarioActual();
            Directorio directorioActual = Sistema.getDirectorioActual();
            if (usuarioActual.getPermisos() || usuarioActual.getNombre().equals(directorioActual.getPropietario()) || directorioActual.getPermisosUsuario().getEscritura() || (directorioActual.getPermisosGrupo().getEscritura() && Sistema.getUsuarioActual().compartenGrupo(propietario))) {
                Directorio nuevoDirectorio = new Directorio(nombre);
                directorios.add(nuevoDirectorio);
                System.out.println("Directorio creado");
            } else {
                System.out.println("No tiene permisos para realizar esta acción");
            }
        }
    }

    public void CrearFichero(String nombre, String extension) throws Exception {
        boolean existe = false;
        for (Fichero fichero : ficheros) {
            if (fichero.nombre.equals(nombre) && fichero.getExtension().equals(extension)) {
                existe = true;
                break;
            }
        }
        if (existe) {
            System.out.println("Fichero existente");
        } else {
            Usuario usuarioActual = Sistema.getUsuarioActual();
            Directorio directorioActual = Sistema.getDirectorioActual();
            if (usuarioActual.getPermisos() || usuarioActual.getNombre().equals(directorioActual.getPropietario()) || directorioActual.getPermisosUsuario().getEscritura() || (directorioActual.getPermisosGrupo().getEscritura() && Sistema.getUsuarioActual().compartenGrupo(propietario))) {
                Fichero nuevoFichero = new Fichero(nombre, extension);
                ficheros.add(nuevoFichero);
                System.out.println("Fichero creado correctamente");
            } else {
                System.out.println("No tiene permisos para realizar esta acción");
            }
        }
    }

    public void AbrirDirectorio(String nombre) {
        Directorio directorioAAbrir = null;
        for (Directorio directorio : directorios) {
            if (directorio.nombre.equals(nombre)) {
                directorioAAbrir = directorio;
            }
        }
        if (directorioAAbrir != null) {
            if (Sistema.getUsuarioActual().getPermisos() || directorioAAbrir.getPropietario().equals(Sistema.getUsuarioActual().getNombre()) || directorioAAbrir.getPermisosUsuario().getLectura() || (directorioAAbrir.getPermisosGrupo().getLectura() && Sistema.getUsuarioActual().compartenGrupo(propietario))) {
                Sistema.setDirectorioAnterior(Sistema.getDirectorioActual());
                Sistema.setDirectorioActual(directorioAAbrir);
                System.out.println("Directorio Actual: " + directorioAAbrir.getNombre());
            } else {
                System.out.println("No tiene permisos para realizar esta acción");
            }
        } else {
            System.out.println("Directorio no encontrado");
        }
    }

    public void AbrirFichero(String nombre, String extension) throws Exception {
        boolean existe = false;
        for (Fichero fichero : ficheros) {
            if (fichero.nombre.equals(nombre) && fichero.getExtension().equals(extension)) {
                existe = true;
                fichero.Abrir();
                break;
            }
        }
        if (!existe) {
            throw new Exception("Fichero no encontrado");
        }
    }

    public Fichero BuscarFichero(String nombre, String extension) {
        for (Fichero fichero : ficheros) {
            if (fichero.getNombre().equals(nombre) && fichero.getExtension().equals(extension)) {
                return fichero;
            }
        }
        return null;
    }

    public Directorio BuscarDirectorio(String nombre) {
        for (Directorio directorio : directorios) {
            if (directorio.getNombre().equals(nombre)) {
                return directorio;
            }
        }
        return null;
    }

    public void SetPermisosUsuario(String nombreArchivo, String permisos) {
        Archivo archivoACambiar = null;
        if (nombreArchivo.contains(".")) {
            String[] archivoSplit = nombreArchivo.split("\\.");
            archivoACambiar = Sistema.getDirectorioActual().BuscarFichero(archivoSplit[0], archivoSplit[1]);
        } else {
            archivoACambiar = Sistema.getDirectorioActual().BuscarDirectorio(nombre);
        }

        if (archivoACambiar != null) {
            if (Sistema.getUsuarioActual().getPermisos() || Sistema.getUsuarioActual().getNombre().equals(propietario.getNombre())) {
                if (permisos.charAt(0) == 'l') {
                    archivoACambiar.permisosUsuario.setLectura(true);
                } else {
                    archivoACambiar.permisosUsuario.setLectura(false);
                }
               
                if (permisos.charAt(1) == 'e') {
                    archivoACambiar.permisosUsuario.setEscritura(true);
                } else {
                    archivoACambiar.permisosUsuario.setEscritura(false);
                }

                if (permisos.charAt(2) == 'x') {
                    archivoACambiar.permisosUsuario.setEjecucion(true);
                } else {
                    archivoACambiar.permisosUsuario.setEjecucion(false);
                }
                System.out.println("Permisos del archivo modificados");
            } else {
                System.out.println("No tiene permisos para realizar esta acción");
            }
        }else{
            System.out.println("Archivo no encontrado");
        }

    }

    public void SetPermisosGrupo(String nombreArchivo, String permisos) {
        Archivo archivoACambiar = null;
        if (nombreArchivo.contains(".")) {
            String[] archivoSplit = nombreArchivo.split("\\.");
            archivoACambiar = Sistema.getDirectorioActual().BuscarFichero(archivoSplit[0], archivoSplit[1]);
        } else {
            archivoACambiar = Sistema.getDirectorioActual().BuscarDirectorio(nombre);
        }
        
        if(archivoACambiar != null){
            if (Sistema.getUsuarioActual().getPermisos() || Sistema.getUsuarioActual().getNombre().equals(propietario.getNombre())) {
                if (permisos.charAt(0) == 'l') {
                    archivoACambiar.permisosGrupo.setLectura(true);
                } else {
                    archivoACambiar.permisosGrupo.setLectura(false);
                }

                if (permisos.charAt(1) == 'e') {
                    archivoACambiar.permisosGrupo.setEscritura(true);
                } else {
                    archivoACambiar.permisosGrupo.setEscritura(false);
                }

                if (permisos.charAt(2) == 'x') {
                    archivoACambiar.permisosGrupo.setEjecucion(true);
                } else {
                    archivoACambiar.permisosGrupo.setEjecucion(false);
                }
            } else {
                System.out.println("No tiene permisos para realizar esta acción");
            }    
        }else{
            System.out.println("Archivo no encontrado");
        }
    }
}
