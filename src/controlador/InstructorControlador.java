package controlador;

import Vista.FramePrincipal;
import Vista.Instructor;
import Vista.Tarifas;

import javax.swing.*;

public class InstructorControlador {
    private Instructor instructor;

    public InstructorControlador() {
        this.instructor = new Instructor(this);
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
