package controlador;

import modelo.ChecadorModelo;
import objetos.ChecadorObj;
import objetos.ClienteObj;
import vista.ChecadorVista;
import vista.FramePrincipal;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class ChecadorControlador {

    private ChecadorVista vista;
    private ChecadorModelo modelo;
    private ClientesControlador clientesControlador; // Agrega una referencia al controlador de clientes

    public ChecadorControlador() {
        this.modelo = ChecadorModelo.instance;
        this.vista = new ChecadorVista(this);
        this.clientesControlador = new ClientesControlador(); // Inicializa el controlador de clientes
    }

    public void checador() {
        FramePrincipal.obtenerInstancia().agregarPanel(vista.checador());
    }

    public ChecadorObj registrarChecada(int idCliente, String nombre, String horaActual,String fecha) {
        ClienteObj cliente = clientesControlador.buscarClientePorID(idCliente); // Usa el método buscarClientePorID del controlador de clientes
        if (cliente != null) {
            ChecadorObj checador = modelo.registrarChecada(idCliente, nombre, horaActual,fecha);
            vista.actualizarTabla(checador);
            return checador;
        } else {
            JOptionPane.showMessageDialog(vista, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public String obtenerHoraActual() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
    
}