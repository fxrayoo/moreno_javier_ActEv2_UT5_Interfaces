package model;
import java.io.Serializable;

public class nota implements Serializable {
    private String titulo;
    private String contenido;
    private String propietario;

    public nota(String titulo, String contenido, String propietario) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.propietario = propietario;
    }

    public String get_titulo() { return titulo; }
    public void set_titulo(String titulo) { this.titulo = titulo; }
    
    public String get_contenido() { return contenido; }
    public void set_contenido(String contenido) { this.contenido = contenido; }

    public String get_propietario() { return propietario; }

    @Override
    public String toString() {
        return titulo;
    }
}