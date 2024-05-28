package controlador;

import Vista.Clientes;
import Vista.FramePrincipal;

import java.awt.image.BufferedImage;

import javax.swing.*;

import Modelo.Cliente;
import Modelo.ClienteModelo;

public class ClientesControlador {

    private Clientes vista;

    public ClientesControlador() {
        this.vista = new Clientes(this);

    }

    public void clientes() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.clientes());
    }

    public void crearClientes() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.crearCliente());
    }

    public void editarCliente() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.editarClientes());
    }

    public void detallesCliente() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.detallesClientes());
    }

    public void eliminarCliente() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.eliminarCliente());
    }
    public static void registrarCliente(int iD, String nombre, String apellido, String correo, int telefono, String fechaInicial,
			String fechaFinal, String tipoMembresia, String planMembresia, String fechaNacimiento, BufferedImage imagen,
			String metodoPago) {
    	ClienteModelo.obtenerInstancia().subirDatosCliente(new Cliente(iD,nombre,apellido,correo,telefono,fechaInicial,fechaFinal,tipoMembresia,planMembresia
    			,fechaNacimiento,imagen,metodoPago));
    	
    }


}