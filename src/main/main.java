package main;

import view.menu;

public class main {
    public static void Main (String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            menu ventana = new menu();
            ventana.setVisible(true);
        });
    }
}