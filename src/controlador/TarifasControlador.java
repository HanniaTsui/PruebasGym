package controlador;

import Vista.FramePrincipal;
import Vista.Tarifas;

import javax.swing.*;

public class TarifasControlador {
    private Tarifas vista;

    public TarifasControlador() {
        this.vista = new Tarifas(this);
    }

    public void tarifas() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.tarifas());
    }

    public void nuevaTarifa() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.nuevaTarifa());
    }

    public void editarTarifa() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.editarTarifa());
    }

   
}
