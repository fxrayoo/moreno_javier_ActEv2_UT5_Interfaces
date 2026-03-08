package model;

public class usuario {
    private String nombre;
    private String contrasenia;

    public usuario(String nombre, String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public String get_nombre() { return nombre; }
    public void set_nombre(String nombre) { this.nombre = nombre; }

    public String get_contrasenia() { return contrasenia; }
    public void set_contrasenia(String contrasenia) { this.contrasenia = contrasenia; }
}