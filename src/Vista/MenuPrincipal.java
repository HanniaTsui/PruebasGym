package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelSup;
	private final JPanel panelNegro = new JPanel();
	
	JLabel lblTitulo, lblGym;
	Checador check = new Checador();
	Tarifas tf = new Tarifas();
	Clientes cl = new Clientes();
	Clases clase = new Clases();
	Instructor ins = new Instructor();
	Inicio i1 = new Inicio();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,720);
		setTitle("Larry's Gym");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		menuPrincipal();
		
	}
	

	public void menuPrincipal() { // Con botones
		panel();

		// Crear un arreglo de JButton
		JButton[] botones = new JButton[5];
		// Crear y configurar los botones
		String[] nombres = {"Clientes", "Tarifas", "Instructores", "Clases", "Checador"};
		int[] bounds_X = {466, 832, 100, 466, 832};
		int[] bounds_Y = {302, 302, 589, 589, 589};

		for (int i = 0; i < botones.length; i++) {
		    final int index = i; 
		    botones[i] = new JButton(nombres[i]);
		    botones[i].setForeground(Color.WHITE);
		    botones[i].setFont(new Font("Arial Black", Font.BOLD, 20));
		    botones[i].setFocusable(false);
		    botones[i].setBackground(Color.BLACK);
		    botones[i].setBounds(bounds_X[i], bounds_Y[i], 266, 40);
		    
		    botones[i].addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            quitarComponentes();
		            dispose();
		            switch (index) {
		                case 0:
		                	cl.setVisible(true);
		                    break;
		                case 1:
		                	tf.setVisible(true);
		                    break;
		                case 2:
		                	ins.setVisible(true);
		                    break;
		                case 3:
		                	clase.setVisible(true);
		                    break;
		                case 4:
		                	check.setVisible(true);
		                    break;
		                default:
		                    break;
		            }
		        }
		    });
		    panel.add(botones[i]);
		}

		JLabel lblTitulo_1 = new JLabel("Larry's");
		lblTitulo_1.setForeground(new Color(0, 0, 0));
		lblTitulo_1.setFont(new Font("Forte", Font.BOLD, 38));
		lblTitulo_1.setBounds(128, 323, 125, 50);
		panel.add(lblTitulo_1);
		
		JLabel lblGym_1 = new JLabel("Gym");
		lblGym_1.setForeground(new Color(0, 124, 163));
		lblGym_1.setFont(new Font("Forte", Font.BOLD, 38));
		lblGym_1.setBackground(Color.WHITE);
		lblGym_1.setBounds(254, 323, 86, 50);
		panel.add(lblGym_1);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/img/logoMenu.png")));
		lblLogo.setBounds(116, 106, 233, 208);
		panel.add(lblLogo);
		
		JLabel[] lblColores = new JLabel[5];
		String[] imagenes = {"/img/clientesLogoMenu.png", "/img/tarifasMenu.png", "/img/InstructorMenu.png", "/img/clasesMenu.png", "/img/checadorMenu.png"};
		int[] boundsX = {466, 832, 100, 466, 832};
		int[] boundsY = {112, 112, 399, 399, 399};

		for (int i = 0; i < lblColores.length; i++) {
		    lblColores[i] = new JLabel("");
		    lblColores[i].setOpaque(true);
		    lblColores[i].setBackground(new Color(119, 182, 255));
		    lblColores[i].setHorizontalAlignment(SwingConstants.CENTER);
		    lblColores[i].setBounds(boundsX[i], boundsY[i], 266, 230);
		    lblColores[i].setIcon(new ImageIcon(MenuPrincipal.class.getResource(imagenes[i])));
		    panel.add(lblColores[i]);
		}
	}
	
	
	public void panel() {
		panel = new JPanel();
		panel.setBounds(0, 0, 1200, 720);
		panel.setBackground(Color.white);
		contentPane.add(panel);
		panel.setLayout(null);
		menuB();
	}
	
	public void quitarComponentes() {
		contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
	}
	
	public void menuB() { // Menu bar 
		panelSup = new JPanel();
		panelSup.setBounds(0, 0, 1200, 70);
		panel.add(panelSup);
		panelSup.setLayout(new BorderLayout(0, 0));
		panelSup.add(panelNegro, BorderLayout.CENTER);
		panelNegro.setOpaque(true);
		panelNegro.setBackground(new Color(0, 0, 0));
		panelNegro.setLayout(null);
		
		lblTitulo = new JLabel("Larry's");
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setFont(new Font("Forte", Font.PLAIN, 35));
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(20, 3, 131, 40);
		panelNegro.add(lblTitulo);
		
		lblGym = new JLabel("Gym");
		lblGym.setVerticalAlignment(SwingConstants.TOP);
		lblGym.setBackground(new Color(255, 255, 255));
		lblGym.setForeground(new Color(0, 124, 163));
		lblGym.setFont(new Font("Forte", Font.PLAIN, 35));
		lblGym.setBounds(155, 3, 97, 40);
		panelNegro.add(lblGym);
		
		JPanel panelBar = new JPanel();
		panelSup.add(panelBar, BorderLayout.SOUTH);
		panelBar.setLayout(new GridLayout(1, 0, 0, 0));
		
		 JButton btnInicio = new JButton("Inicio");
		 btnInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuPrincipal();
			}
		 });
	     configurarBotones(btnInicio);
	     JButton btnClientes = new JButton("Clientes");
	     btnClientes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					cl.setVisible(true);
				}
			 });
	     configurarBotones(btnClientes);
	     JButton btnTarifas = new JButton("Tarifas");
	     btnTarifas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					tf.setVisible(true);
				}
			 });
	     configurarBotones(btnTarifas);
	     JButton btnInstructor = new JButton("Instructores");
	     btnInstructor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					ins.setVisible(true);
				}
			 });
	     configurarBotones(btnInstructor);
	     JButton btnClases = new JButton("Clases");
	     btnClases.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					clase.setVisible(true);
				}
			 });
	     configurarBotones(btnClases);
	     JButton btnChecador = new JButton("Checador");
	     btnChecador.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					check.setVisible(true);
				}
			 });
	     configurarBotones(btnChecador);
	     JButton btnSalir = new JButton("Salir");
	     btnSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					i1.setVisible(true);
				}
			 });
	     configurarBotones(btnSalir);
	        
	        panelBar.add(btnInicio);
	        panelBar.add(btnClientes);
	        panelBar.add(btnTarifas);
	        panelBar.add(btnInstructor);
	        panelBar.add(btnClases);
	        panelBar.add(btnChecador);
	        panelBar.add(btnSalir);
		}

	public void configurarBotones(JButton btn) {
    	btn.setForeground(Color.black);
    	btn.setFont(new Font("Arial Black", Font.BOLD, 12));
    	btn.setFocusable(false);
    	btn.setBackground(new Color(217, 217, 217)); 
    }


}
