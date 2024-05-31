package controlador;

import Vista.ClasesVista;
import Vista.FramePrincipal;

import javax.swing.*;

public class ClasesControlador {
    private ClasesVista vista;

    public ClasesControlador() {
        this.vista = new ClasesVista(this);

    }

    public void clases() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.clases());
    }

    public void detallesCliente() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.detallesClase(vista.getMenu()));
    }

    public void inscribirseClase() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.inscribirseClase());
    }

    public void registrosClase() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.registrosClase());
    }

    public void nuevaClase() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.nuevaClase());
    }

}
