package controlador;

import Vista.Checador;
import Vista.FramePrincipal;

import javax.swing.*;

public class ChecadorControlador {

    private Checador vista;

    public ChecadorControlador() {
        this.vista = new Checador(this);
    }

    public void checador() {
        FramePrincipal.obtenerInstancia().agregarPanel(vista.checador());
    }


}
