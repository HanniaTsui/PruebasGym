import java.awt.EventQueue;

import Controlador.Controlador;
import Modelo.BaseDatos;
import Vista.Inicio;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio inicio1= new Inicio();
					BaseDatos.optenerIstancia().cargarBase();
					inicio1.setVisible(true);
					//Controlador controlador = new Controlador(inicio1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
