package controlador;

import objetos.TarifaObj;
import vista.FramePrincipal;
import vista.TarifasVista;

import javax.swing.*;

public class TarifasControlador {
    private TarifasVista vista;

    public TarifasControlador() {
        this.vista = new TarifasVista(this);
    }

    public void tarifas() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.tarifas());
    }

    public void nuevaTarifa() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.nuevaTarifa());
    }

    public void editarTarifa(TarifaObj tarifa) {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.editarTarifa(tarifa));
    }

   
}
