package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo.BaseDatos;
import objetos.Usuario;
import vista.InicioVista;
import vista.MenuPrincipalVista;

public class Controlador implements ActionListener {
	private  InicioVista inicio1;
	
	
	
	public Controlador(InicioVista inicio1) {
		this.inicio1=inicio1;
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	//ya se esta ocupando en inicio controlador
	public boolean validacion(String usuario,String contraseña) {
	    // Realizar la validación con la base de datos
	    for (Usuario usuario2 : BaseDatos.getUser()) {
	        if (usuario2.getNombre().equals(usuario) && usuario2.getContraseña().equals(contraseña)) {
	            return true; 
	        }   
	    }
	    return false;
	 
	}
	//ya se esta ocupando en inicio controlador
	public void registrar(String usuario,String contraseña,String correo) {
	
		 BaseDatos.optenerIstancia().subirDatos(new Usuario(usuario,contraseña,correo));
		 
	}
	
}
