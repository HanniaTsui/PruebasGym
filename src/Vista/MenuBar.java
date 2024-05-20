package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuBar extends JPanel {
    private static final long serialVersionUID = 1L;
    private MenuPrincipal mainFrame;
    Checador check;
	Tarifas tf  ;
	Clientes cl;
	Clases clase;
	Instructor ins ;
	Inicio i1;
    private JPanel panelNegro;
    JLabel lblTitulo, lblGym;
    

   /* public MenuBar(MenuPrincipal mainFrame) {
        this.mainFrame = mainFrame;
        menuB();
    }

    public MenuBar(Checador checador) {
        this.check = checador;
        menuB();
        
    }
    
    public MenuBar(Tarifas tarifa) {
        this.tf = tarifa;
        menuB();
        
    }
    
    public MenuBar(Clientes cli) {
        this.cl = cli;
        menuB();
        
    }
    public MenuBar(Clases clase) {
        this.clase = clase;
        menuB();
        
    }
    
    public MenuBar(Instructor ins) {
        this.ins = ins;
        menuB();
        
    }*/
    public MenuBar(MenuPrincipal mainFrame, Checador check, Tarifas tf, Clientes cl, Clases clase, Instructor ins) {
        this.mainFrame = mainFrame;
        this.check = check;
        this.tf = tf;
        this.cl = cl;
        this.clase = clase;
        this.ins = ins;
        
        menuB();
    }
    private void menuB() {//VERIFICAR CAMBIOS ENTRE VENTANAS
        setLayout(new BorderLayout(0, 0));
        panelNegro = new JPanel();
        panelNegro.setOpaque(true);
        panelNegro.setBackground(new Color(0, 0, 0));
        panelNegro.setLayout(null);

        lblTitulo = new JLabel("Larry's");
        lblTitulo.setFont(new Font("Forte", Font.PLAIN, 38));
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setBounds(20, 3, 131, 50);
        panelNegro.add(lblTitulo);

        lblGym = new JLabel("Gym");
        lblGym.setBackground(new Color(255, 255, 255));
        lblGym.setForeground(new Color(0, 124, 163));
        lblGym.setFont(new Font("Forte", Font.PLAIN, 38));
        lblGym.setBounds(155, 3, 97, 50);
        panelNegro.add(lblGym);

        add(panelNegro, BorderLayout.CENTER);

        JPanel panelBar = new JPanel();
        add(panelBar, BorderLayout.SOUTH);
        panelBar.setLayout(new GridLayout(1, 0, 0, 0));
        
        JButton[] botones = new JButton[7];
        String[] nombres = {"Inicio", "Clientes", "Tarifas", "Instructores", "Clases", "Checador", "Salir"};
        for (int i = 0; i < botones.length; i++) {
            final int index = i;
            botones[i] = new JButton(nombres[i]);
            botones[i].setForeground(Color.black);
            botones[i].setFont(new Font("Arial Black", Font.BOLD, 12));
            botones[i].setFocusable(false);
            botones[i].setBackground(new Color(217, 217, 217));
            botones[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mainFrame.quitarComponentes();
                    switch (index) {
                        case 0:
                            mainFrame.menuPrincipal();
                            break;
                        case 1:
                        	mainFrame.dispose();
                            mainFrame.cl.setVisible(true);
                            break;
                        case 2:
                        	mainFrame.dispose();
                            mainFrame.tf.setVisible(true);
                            break;
                        case 3:
                        	mainFrame.dispose();
                            mainFrame.ins.setVisible(true);
                            break;
                        case 4:
                        	mainFrame.dispose();
                            mainFrame.clase.setVisible(true);
                            break;
                        case 5:
                        	mainFrame.dispose();
                            mainFrame.check.setVisible(true);
                            break;
                        case 6:
                        	mainFrame.dispose();
                        	Inicio i1 = new Inicio();
                            i1.setVisible(true);
                            break;
                        default:
                            break;
                    }
                }
            });
            panelBar.add(botones[i]);
        }
    }
}
