package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.InicioControlador;
import controlador.MenuControlador;


public class MenuPrincipalVista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelSup;
	private final JPanel panelNegro = new JPanel();
	
	JLabel lblTitulo, lblGym;
	File fontFile;
	Font forte;
	private MenuControlador controlador;

	public MenuPrincipalVista(MenuControlador controlador) {
		this.controlador = controlador;
	}
	

	public JPanel menuPrincipal() { // Con botones
		JPanel panel = getMenu();
		
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
		    botones[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));    
		    botones[i].addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            switch (index) {
		                case 0:
		                	controlador.clientes();
		                    break;
		                case 1:
							controlador.tarifas();
		                    break;
		                case 2:
		                	controlador.instructor();
		                    break;
		                case 3:
		                	controlador.clases();
		                    break;
		                case 4:
		                	controlador.checador();
		                    break;
		                default:
		                    break;
		            }
		        }
		    });
		    panel.add(botones[i]);
		}

		JLabel lblTitulo_1 = new JLabel("Larrys");
		lblTitulo_1.setForeground(new Color(0, 0, 0));
		lblTitulo_1.setFont(new Font("Forte", Font.BOLD, 38));
		lblTitulo_1.setBounds(120, 323, 165, 50);
		panel.add(lblTitulo_1);
		
		JLabel lblGym_1 = new JLabel("Gym");
		lblGym_1.setForeground(new Color(0, 124, 163));
		lblGym_1.setFont(new Font("Forte", Font.BOLD, 38));
		lblGym_1.setBackground(Color.WHITE);
		lblGym_1.setBounds(254, 323, 86, 50);
		panel.add(lblGym_1);
		
		JLabel lblLogo = new JLabel("",0);
		lblLogo.setIcon(new ImageIcon(MenuPrincipalVista.class.getResource("/img/logoMenu.png")));
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
		    lblColores[i].setIcon(new ImageIcon(MenuPrincipalVista.class.getResource(imagenes[i])));
		    panel.add(lblColores[i]);		
		    int index = i; // Índice de la etiqueta actual
		    lblColores[i].addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            switch (index) {
		                case 0:
		                    controlador.clientes();
		                    break;
		                case 1:
		                    controlador.tarifas();
		                    break;
		                case 2:
		                    controlador.instructor();
		                    break;
		                case 3:
		                    controlador.clases();
		                    break;
		                case 4:
		                    controlador.checador();
		                    break;
		                default:
		                    break;
		            }
		        }
		    });
		    
		    panel.add(lblColores[i]);
		}

		return panel;
	}

	public JPanel getMenu() {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            InputStream is = getClass().getResourceAsStream("/Fuente/Forte.ttf");
            if (is == null) {
                throw new IOException("Font file not found");
            }
            forte = Font.createFont(Font.TRUETYPE_FONT, is);
            ge.registerFont(forte);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar el archivo de la fuente: " + e.getMessage());
        } catch (FontFormatException e) {
            e.printStackTrace();
            System.err.println("El archivo de fuente tiene un formato incorrecto: " + e.getMessage());
        }
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 720);
		panel.setBackground(Color.white);
		panel.setLayout(null);

		panelSup = new JPanel();
		panelSup.setBounds(0, 0, 1200, 70);
		panel.add(panelSup);
		panelSup.setLayout(new BorderLayout(0, 0));
		panelSup.add(panelNegro, BorderLayout.CENTER);
		panelNegro.setOpaque(true);
		panelNegro.setBackground(new Color(0, 0, 0));
		panelNegro.setLayout(null);

		lblTitulo = new JLabel("Larrys");
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setFont(forte.deriveFont(34f));
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(20, 3, 131, 40);
		panelNegro.add(lblTitulo);

		lblGym = new JLabel("Gym");
		lblGym.setVerticalAlignment(SwingConstants.TOP);
		lblGym.setBackground(new Color(255, 255, 255));
		lblGym.setForeground(new Color(0, 124, 163));
		lblGym.setFont(forte.deriveFont(34f));
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
				controlador.menu();
			}
		});
		configurarBotones(btnInicio);
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.clientes();
			}
		});
		configurarBotones(btnClientes);
		JButton btnTarifas = new JButton("Tarifas");
		btnTarifas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.tarifas();
			}
		});
		configurarBotones(btnTarifas);
		JButton btnInstructor = new JButton("Instructores");
		btnInstructor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.instructor();
			}
		});
		configurarBotones(btnInstructor);
		JButton btnClases = new JButton("Clases");
		btnClases.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.clases();
			}
		});
		configurarBotones(btnClases);
		JButton btnChecador = new JButton("Checador");
		btnChecador.addActionListener(e -> controlador.checador());
		configurarBotones(btnChecador);
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InicioControlador controlador=new InicioControlador();
				controlador.inicio();
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

		return panel;
	}

	public void configurarBotones(JButton btn) {
    	btn.setForeground(Color.black);
    	btn.setFont(new Font("Arial Black", Font.BOLD, 12));
    	btn.setFocusable(false);
    	btn.setBackground(new Color(217, 217, 217)); 
    }


}
