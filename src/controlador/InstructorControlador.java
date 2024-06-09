package controlador;

import Vista.FramePrincipal;
import Vista.InstructorVista;
import Vista.TarifasVista;

import java.awt.image.BufferedImage;

import javax.swing.*;

import Modelo.ClienteModelo;
import Modelo.ClienteObj;
import Modelo.InstructorModelo;
import Modelo.InstructorObj;

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