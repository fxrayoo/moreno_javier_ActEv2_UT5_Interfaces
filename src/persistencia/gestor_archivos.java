package persistencia;

import java.io.*;
import java.util.*;
import model.usuario;
import model.nota;

public class gestor_archivos {
    private static final String FICHERO_USUARIOS = "usuarios.data";
    private static final String CARPETA_NOTAS = "notas_usuarios/";

    public static void guardar_usuarios(List<usuario> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_USUARIOS))) {
            oos.writeObject(lista);
        } catch (IOException e) { e.printStackTrace(); }
    }

    @SuppressWarnings("sin revissar")
    public static List<usuario> cargar_usuarios() {
        File f = new File(FICHERO_USUARIOS);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<usuario>) ois.readObject();
        } catch (Exception e) { return new ArrayList<>(); }
    }
    
    public static void guardar_notas_usuario(String nombre, List<nota> notas) {
        new File(CARPETA_NOTAS).mkdirs();
    }
}