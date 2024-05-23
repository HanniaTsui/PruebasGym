package controlador;

import Vista.Clientes;
import Vista.FramePrincipal;

import javax.swing.*;

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


}
