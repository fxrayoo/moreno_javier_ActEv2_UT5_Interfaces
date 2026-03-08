package main;

import view.login;

public class main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            login ventana = new login();
            ventana.setVisible(true);
        });
    }
}