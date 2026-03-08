package logica;

import model.nota;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class gestion_nota {
    private List<nota> lista_notas;

    public gestion_nota() {
        this.lista_notas = new ArrayList<>();
    }

    public void agregar_nota(nota nueva_nota) {
        lista_notas.add(nueva_nota);
    }

    public void eliminar_nota(nota eliminar_nota) {
        lista_notas.remove(eliminar_nota);
    }

    public void actualizar_nota(int index, String nuevo_titulo, String nuevo_contenido) {
        if (index >= 0 && index < lista_notas.size()) {
            nota n = lista_notas.get(index);
            n.set_titulo(nuevo_titulo);
            n.set_contenido(nuevo_contenido);
        }
    }

    public void limpiar_notas() {
        lista_notas.clear();
    }

    public List<nota> filtrar_titulo(String texto) {
        return lista_notas.stream()
            .filter(n -> n.get_titulo().toLowerCase().contains(texto.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<nota> get_lista_notas() {
        return lista_notas;
    }
}