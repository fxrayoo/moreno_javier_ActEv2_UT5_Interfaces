package persistencia;

import java.io.*;
import java.util.*;
import model.usuario;
import model.nota;

public class gestor_archivos {
    private static final String FICHERO_USUARIOS = "usuarios.dat";
    private static final String CARPETA_NOTAS = "notas_usuarios/";

    public static void guardar_usuarios(List<usuario> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_USUARIOS))) {
            oos.writeObject(lista);
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
    }

    @SuppressWarnings("unchecked")
    public static List<usuario> cargar_usuarios() {
        File f = new File(FICHERO_USUARIOS);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<usuario>) ois.readObject();
        } catch (Exception e) { 
            return new ArrayList<>(); 
        }
    }
    
    public static void guardar_notas_usuario(String nombre, List<nota> notas) {
        File carpeta = new File(CARPETA_NOTAS);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        
        String rutaArchivo = CARPETA_NOTAS + nombre + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(notas);
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
    }

    @SuppressWarnings("unchecked")
    public static List<nota> cargar_notas_usuario(String nombre) {
        File f = new File(CARPETA_NOTAS + nombre + ".dat");
        if (!f.exists()) return new ArrayList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<nota>) ois.readObject();
        } catch (Exception e) { 
            return new ArrayList<>(); 
        }
    }
}