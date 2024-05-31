package controlador;

import Vista.ChecadorVista;
import Vista.FramePrincipal;
import Vista.MenuPrincipalVista;

import javax.swing.*;

public class MenuControlador {
	
    private MenuPrincipalVista vista;
    
    public MenuControlador() {
        this.vista = new MenuPrincipalVista(this);
        
    }

    public void menu() {
        FramePrincipal.obtenerInstancia().agregarPanel(vista.menuPrincipal());
    }

    public void checador() {
        ChecadorControlador c = new ChecadorControlador();
        c.checador();
    }

    public void clientes() {
       ClientesControlador clientesControlador = new ClientesControlador();
        clientesControlador.clientes();
    }

    public void clases() {
        ClasesControlador clasesControlador = new ClasesControlador();
        clasesControlador.clases();
    }

    public void tarifas() {
        TarifasControlador tarifasControl = new TarifasControlador();
        tarifasControl.tarifas();
    }
    public void instructor() {
        InstructorControlador instructorControl = new InstructorControlador();
        instructorControl.instructor();
    }

    public JPanel getPanelMenu() {
        return vista.getMenu();
    }

  

}
