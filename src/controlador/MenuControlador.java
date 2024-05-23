package controlador;

import Vista.Checador;
import Vista.FramePrincipal;
import Vista.MenuPrincipal;

import javax.swing.*;

public class MenuControlador {
	
    private MenuPrincipal vista;
    
    public MenuControlador() {
        this.vista = new MenuPrincipal(this);
        
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
