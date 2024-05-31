package controlador;

import Vista.Clientes;
import Vista.FramePrincipal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Modelo.ClienteObj;
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
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.panelBuscarEditar());
    }

    public void detallesCliente() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.detallesClientes());
    }

    public void eliminarCliente() {
    	FramePrincipal.obtenerInstancia().agregarPanel(vista.panelEliminarCliente());
    }

    public void actualizarCliente(ClienteObj cliente) {
        ClienteModelo.obtenerInstancia().editarCliente(cliente);
    }

    public ClienteObj buscarClientePorID(int id) {
        for (ClienteObj cliente : ClienteModelo.getClient()) {
            if (cliente.getID() == id) {
                return cliente;
            }
        }
        return null;
    }

    public static void registrarCliente(int iD, String nombre, String apellido, String correo, String telefono, String fechaInicial,
            String fechaFinal, String tipoMembresia, String planMembresia, String fechaNacimiento, BufferedImage imagen,
            String metodoPago,String estado) {
            boolean subirCliente=ClienteModelo.obtenerInstancia().subirDatosCliente(new ClienteObj(iD,nombre,apellido,correo,telefono,fechaInicial,fechaFinal,tipoMembresia,planMembresia
                    ,fechaNacimiento,imagen,metodoPago,estado));
            if(subirCliente) {
                ClienteModelo.cargarCliente();
            }
    }
    


}
