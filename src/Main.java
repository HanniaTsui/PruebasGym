import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controlador.InicioControlador;
import modelo.BaseDatos;
import modelo.ClasesModelo;

public class Main {

    public static void main(String[] args) {
        setLookAndFeel();

        BaseDatos.optenerIstancia().cargarBase();
        EventQueue.invokeLater(() -> {
            try {
                InicioControlador inicio = new InicioControlador();
                inicio.inicio();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //System.out.println("Java version: " + System.getProperty("java.version"));
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}