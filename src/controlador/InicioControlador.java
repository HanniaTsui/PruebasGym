package controlador;

import Modelo.BaseDatos;
import Modelo.Usuario;
import Vista.FramePrincipal;
import Vista.InicioVista;

import javax.swing.*;

public class InicioControlador {
    private InicioVista vista;

    public InicioControlador() {
        this.vista = new InicioVista(this);
    } 

    public void inicio() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.iniciar());
    }

    public void login() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.login());
    }

    public void registro() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.registro());
    }


    public boolean validarLogin(String usuario, String contraseña) {
        // Realizar la validación con la base de datos
        for (Usuario usuario2 : BaseDatos.getUser()) {
            if (usuario2.getNombre().equals(usuario) && usuario2.getContraseña().equals(contraseña)) {
                return true;
            }
        }
        return false;
    }

    public static void registrar(String usuario,String contraseña,String correo) {
        BaseDatos.optenerIstancia().subirDatos(new Usuario(usuario,contraseña,correo));
    }

}
