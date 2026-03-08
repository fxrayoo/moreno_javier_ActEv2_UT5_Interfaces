package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.usuario;
import persistencia.gestor_archivos;
import utils.seguridad;

public class login extends JFrame {
    private JTextField campo_usuario;
    private JPasswordField campo_contrasenia;
    private JButton boton_entrar, boton_registrar;
    private JLabel etiqueta_mensaje;

    public login() {
        setTitle("Login");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JPanel p1 = new JPanel(); p1.add(new JLabel("Usuario:")); campo_usuario = new JTextField(15); p1.add(campo_usuario);
        JPanel p2 = new JPanel(); p2.add(new JLabel("Contraseña:")); campo_contrasenia = new JPasswordField(15); p2.add(campo_contrasenia);
        
        JPanel p3 = new JPanel();
        boton_entrar = new JButton("Entrar");
        boton_registrar = new JButton("Registrarse");
        p3.add(boton_entrar); p3.add(boton_registrar);

        etiqueta_mensaje = new JLabel("Por favor, introduzca sus datos", SwingConstants.CENTER);

        add(p1); add(p2); add(p3); add(etiqueta_mensaje);

        asignar_eventos();
    }

    private void asignar_eventos() {
        boton_registrar.addActionListener(e -> {
            String nom = campo_usuario.getText();
            String pass = new String(campo_contrasenia.getPassword());
            
            if(nom.isEmpty() || pass.isEmpty()) {
                etiqueta_mensaje.setText("Porfavor, complete todos los campos");
                return;
            }

            List<usuario> lista = gestor_archivos.cargar_usuarios();
            for(usuario u : lista) {
                if(u.get_nombre().equals(nom)) {
                    etiqueta_mensaje.setText("El usuario ya existe");
                    return;
                }
            }

            String pass_hash = seguridad.encriptar(pass);
            lista.add(new usuario(nom, pass_hash));
            gestor_archivos.guardar_usuarios(lista);
            etiqueta_mensaje.setText("Se ha registrado");
        });

        boton_entrar.addActionListener(e -> {
            String nom = campo_usuario.getText();
            String pass = new String(campo_contrasenia.getPassword());
            String pass_hash = seguridad.encriptar(pass);

            List<usuario> lista = gestor_archivos.cargar_usuarios();
            for(usuario u : lista) {
                if(u.get_nombre().equals(nom) && u.get_contrasenia_hash().equals(pass_hash)) {
                    new menu().setVisible(true);
                    this.dispose(); 
                    return;
                }
            }
            etiqueta_mensaje.setText("Algo está mal.");
        });
    }
}