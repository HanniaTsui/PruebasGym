package controlador;

import Modelo.TarifaObj;
import Vista.FramePrincipal;
import Vista.TarifasVista;

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
