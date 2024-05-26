import java.awt.EventQueue;

import Modelo.BaseDatos;
import controlador.InicioControlador;

public class Main {

	public static void main(String[] args) {
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

}
