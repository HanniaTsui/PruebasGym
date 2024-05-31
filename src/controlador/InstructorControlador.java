package controlador;

import Vista.FramePrincipal;
import Vista.InstructorVista;
import Vista.TarifasVista;

import javax.swing.*;

public class InstructorControlador {
    private InstructorVista instructor;

    public InstructorControlador() {
        this.instructor = new InstructorVista(this);
    }

    public void instructor() {
    	FramePrincipal.obtenerInstancia().agregarPanel(instructor.instructor());
    }

    public void detallesInstructor() {
    	FramePrincipal.obtenerInstancia().agregarPanel(instructor.detallesInstructor());
    }

    public void historialClases() {
    	FramePrincipal.obtenerInstancia().agregarPanel(instructor.historialClases());
    }
    public void nuevoInstructor() {
    	FramePrincipal.obtenerInstancia().agregarPanel(instructor.nuevoInstructor());
    }
    public void editarInstructor() {
    	FramePrincipal.obtenerInstancia().agregarPanel(instructor.editarInstructor());
    }

}
