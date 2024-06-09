package controlador;

import modelo.ClienteModelo;
import modelo.InstructorModelo;
import objetos.ClienteObj;
import objetos.InstructorObj;
import vista.FramePrincipal;
import vista.InstructorVista;
import vista.TarifasVista;

import java.awt.image.BufferedImage;

import javax.swing.*;

public class InstructorControlador {
    private InstructorVista instructor;

    public InstructorControlador() {
        this.instructor = new InstructorVista(this);
    }

    public void instructor() {
    	FramePrincipal.obtenerInstancia().agregarPanel(instructor.instructor());
    }

    public void detallesInstructor(InstructorObj instructorObj) {
    	FramePrincipal.obtenerInstancia().agregarPanel(instructor.detallesInstructor(instructorObj));
    }

    public void historialClases(InstructorObj instructorObj) {
    	FramePrincipal.obtenerInstancia().agregarPanel(instructor.historialClases(instructorObj));
    }
    public void nuevoInstructor() {
    	FramePrincipal.obtenerInstancia().agregarPanel(instructor.nuevoInstructor());
    }
    public void editarInstructor(InstructorObj instructorObj) {
    	FramePrincipal.obtenerInstancia().agregarPanel(instructor.editarInstructor(instructorObj));
    }
    
    public static void registrarInstructor(int ID, String nombre, String apellido, String correo, String telefono, String fecha,
            String especialidad, BufferedImage imagen, int idClase) {
    	

            boolean subirIns=InstructorModelo.obtenerInstancia().subirDatosInstructor(new InstructorObj(ID,nombre,apellido,correo,telefono,fecha,especialidad,imagen,idClase));
            if(subirIns) {
                InstructorModelo.cargarInstructor();;
            }
    }

}