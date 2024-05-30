import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Modelo.BaseDatos;
import controlador.InicioControlador;

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
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}