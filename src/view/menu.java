package view;

import javax.swing.*;
import java.awt.*;
import logica.gestion_nota;
import model.nota;

public class menu extends JFrame {
    private gestion_nota logica;
    private DefaultListModel<nota> modelo_lista;

    private JList<nota> lista_visual;
    private JTextField campo_titulo;
    private JTextArea area_contenido;
    private JTextField campo_buscar;
    private JLabel etiqueta_estado;
    private JButton boton_crear, boton_editar, boton_borrar, boton_limpiar, boton_vaciar;

    public menu() {
        logica = new gestion_nota();
        configurar_ventana();
        incializar_componentes();
        asignar_eventos();
    }

    private void configurar_ventana() {
        setTitle("Gestor de notas:");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void incializar_componentes() {
        modelo_lista = new DefaultListModel<>();
        lista_visual = new JList<>(modelo_lista);
        
        JPanel panel_izq = new JPanel(new BorderLayout(5, 5));
        campo_buscar = new JTextField();
        
        JPanel panel_busqueda = new JPanel(new BorderLayout());
        panel_busqueda.add(new JLabel(" Buscar por título:"), BorderLayout.NORTH);
        panel_busqueda.add(campo_buscar, BorderLayout.CENTER);

        panel_izq.add(panel_busqueda, BorderLayout.NORTH);
        panel_izq.add(new JScrollPane(lista_visual), BorderLayout.CENTER);
        panel_izq.setPreferredSize(new Dimension(250, 0));

        campo_titulo = new JTextField();
        area_contenido = new JTextArea();
        
        JPanel panel_centro = new JPanel(new BorderLayout(5, 5));
        JPanel panel_campos = new JPanel(new GridLayout(2, 1));
        panel_campos.add(new JLabel("Título:"));
        panel_campos.add(campo_titulo);
        
        panel_centro.add(panel_campos, BorderLayout.NORTH);
        panel_centro.add(new JScrollPane(area_contenido), BorderLayout.CENTER);

        JPanel panel_sur = new JPanel(new BorderLayout());
        JPanel panel_botones = new JPanel();
        
        boton_crear = new JButton("Crear");
        boton_editar = new JButton("Editar");
        boton_borrar = new JButton("Borrar");
        boton_limpiar = new JButton("Limpiar");
        boton_vaciar = new JButton("Vaciar");

        panel_botones.add(boton_crear);
        panel_botones.add(boton_editar);
        panel_botones.add(boton_borrar);
        panel_botones.add(boton_limpiar);
        panel_botones.add(boton_vaciar);

        etiqueta_estado = new JLabel(" Sistema listo.");
        panel_sur.add(panel_botones, BorderLayout.CENTER);
        panel_sur.add(etiqueta_estado, BorderLayout.SOUTH);

        add(panel_izq, BorderLayout.WEST);
        add(panel_centro, BorderLayout.CENTER);
        add(panel_sur, BorderLayout.SOUTH);
    }

    private void asignar_eventos() {
        boton_crear.addActionListener(e -> {
            if (campo_titulo.getText().isEmpty()) {
                etiqueta_estado.setText("Ingrese un título");
                return;
            }
            nota nueva = new nota(campo_titulo.getText(), area_contenido.getText(), "usuario");
            logica.agregar_nota(nueva);
            actualizar_lista();
            limpiar_campos();
            etiqueta_estado.setText("Se ha añadido una nueva nota");
        });

        lista_visual.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                nota n = lista_visual.getSelectedValue();
                if (n != null) {
                    campo_titulo.setText(n.get_titulo());
                    area_contenido.setText(n.get_contenido());
                    etiqueta_estado.setText(" Viendo: " + n.get_titulo());
                }
            }
        });

        boton_editar.addActionListener(e -> {
            int idx = lista_visual.getSelectedIndex();
            if (idx != -1) {
                logica.actualizar_nota(idx, campo_titulo.getText(), area_contenido.getText());
                actualizar_lista();
                etiqueta_estado.setText("Se ha actualizado la nota");
            } else {
                etiqueta_estado.setText("Por favor, elija una de las creadas");
            }
        });

        boton_borrar.addActionListener(e -> {
            nota n = lista_visual.getSelectedValue();
            if (n != null) {
                logica.eliminar_nota(n);
                actualizar_lista();
                limpiar_campos();
                etiqueta_estado.setText("Se ha borrado la nota");
            }
        });

        boton_limpiar.addActionListener(e -> {
            limpiar_campos();
            etiqueta_estado.setText("Campos vacíos.");
        });

        boton_vaciar.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this, "Borrar todas las notas?", "Seguro?", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                logica.limpiar_notas();
                actualizar_lista();
                limpiar_campos();
                etiqueta_estado.setText("Listado vaciado por completo.");
            }
        });

        campo_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String texto = campo_buscar.getText();
                modelo_lista.clear();
                for (nota n : logica.filtrar_titulo(texto)) {
                    modelo_lista.addElement(n);
                }
            }
        });
    }

    private void actualizar_lista() {
        modelo_lista.clear();
        for (nota n : logica.get_lista_notas()) {
            modelo_lista.addElement(n);
        }
    }

    private void limpiar_campos() {
        campo_titulo.setText("");
        area_contenido.setText("");
        campo_buscar.setText("");
        lista_visual.clearSelection();
    }
}