package controlador;

import Vista.ClasesVista;
import Vista.FramePrincipal;

import java.util.List;

import javax.swing.*;

import Modelo.ClasesModelo;
import Modelo.ClasesObj;
import Modelo.ClienteModelo;
import Modelo.ClienteObj;

public class ClasesControlador {
    private ClasesVista vista;
    private static List<ClasesObj> clase = ClasesModelo.obtenerInstancia().getCheck();
    

    public ClasesControlador() {
        this.vista = new ClasesVista(this);

    }

    public void clases() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.clases());
    }

    public void detallesClase(ClasesObj i) {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.detallesClase(i));
    }

    public void inscribirseClase(ClasesObj i) {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.inscribirseClase(i));
    }
    
    public void editarClase(ClasesObj i) {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.detallesClase(i));
    }

    public void registrosClase() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.registrosClase());
    }

    public void nuevaClase() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.nuevaClase());
    }
    
    
	public static  ClasesObj buscarClase(int id) {
	        for (ClasesObj clase : clase) {
	            if (clase.getID() == id) {
	                return clase;
	            }
	        }
	      return null;
    }

}