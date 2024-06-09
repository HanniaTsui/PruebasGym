package controlador;

import modelo.ClasesModelo;
import modelo.ClienteModelo;
import modelo.InstructorModelo;
import objetos.ClasesObj;
import objetos.ClienteObj;
import objetos.InstructorObj;
import vista.ClasesVista;
import vista.FramePrincipal;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.*;

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

    public void registrosClase(ClasesObj i) {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.registrosClase(i));
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
	
	public static void registrarClaseNueva(int iD, String nombre, int idDia, int idHorario) {
            boolean subirIns=ClasesModelo.obtenerInstancia().subirDatosClases(new ClasesObj(iD,nombre, idDia, idHorario));
            if(subirIns) {
                ClasesModelo.cargarClases();
            }
		
	}
	
	public static int obtenerIdHorario(String horario) {
        return ClasesModelo.obtenerHorarioPorId(horario);
    }


}
