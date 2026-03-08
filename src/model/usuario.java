package model;

import java.io.Serializable;

public class usuario implements Serializable {
    private String nombre;
    private String contrasenia_hash;

    public usuario(String nombre, String contrasenia_hash) {
        this.nombre = nombre;
        this.contrasenia_hash = contrasenia_hash;
    }

    public String get_nombre() { return nombre; }
    public String get_contrasenia_hash() { return contrasenia_hash; }
}