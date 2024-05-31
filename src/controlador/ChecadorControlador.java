package controlador;

import Vista.ChecadorVista;
import Vista.FramePrincipal;

import javax.swing.*;

public class ChecadorControlador {

    private ChecadorVista vista;

    public ChecadorControlador() {
        this.vista = new ChecadorVista(this);
    }

    public void checador() {
        FramePrincipal.obtenerInstancia().agregarPanel(vista.checador());
    }


}
