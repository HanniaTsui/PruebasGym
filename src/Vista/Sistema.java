package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Sistema extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelSup, panelTarifas, panelMenuVertical, panelAsistencias, panelHistorialPagos, panelCredencial;
	private final JPanel panelNegro = new JPanel();
	JMenuBar menuBar;
	JLabel lblTitulo, lblGym;
	private JButton btnDetalles, btnCrear;
	private JTextField textField;
	 private JComboBox<String> monthComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sistema frame = new Sistema();
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
	public Sistema() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,720);
		setTitle("Larry's Gym");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
	 //  	menuPrincipal();
	//	clientes();
		detallesClientes();
		//menuVerticalClientes();
	}
	
	public void panel() {
		panel = new JPanel();
		panel.setBounds(0, 0, 1200, 720);
		panel.setBackground(Color.white);
		contentPane.add(panel);
		panel.setLayout(null);
	}
	
	public void menuPrincipal() { // Con botones
		panel();
		menuB();
		JButton btnClases = new JButton("Clases");
		btnClases.setForeground(Color.WHITE);
		btnClases.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnClases.setFocusable(false);
		btnClases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				clases();
			}
		});
		btnClases.setBackground(Color.BLACK);
		btnClases.setBounds(466, 589, 266, 40);
		panel.add(btnClases);
		
		JButton btnChecador = new JButton("Checador");
		btnChecador.setForeground(Color.WHITE);
		btnChecador.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnChecador.setFocusable(false);
		btnChecador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				checador();
			}
		});
		btnChecador.setBackground(Color.BLACK);
		btnChecador.setBounds(832, 589, 266, 40);
		panel.add(btnChecador);
		
		JButton btnInstructores = new JButton("Instructores");
		btnInstructores.setForeground(Color.WHITE);
		btnInstructores.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnInstructores.setFocusable(false);
		btnInstructores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				instructor();
			}
		});
		btnInstructores.setBackground(Color.BLACK);
		btnInstructores.setBounds(100, 589, 266, 40);
		panel.add(btnInstructores);
		
		JButton btnTarifas = new JButton("Tarifas");
		btnTarifas.setForeground(Color.WHITE);
		btnTarifas.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnTarifas.setFocusable(false);
		btnTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
			}
		});
		btnTarifas.setBackground(Color.BLACK);
		btnTarifas.setBounds(832, 302, 266, 40);
		panel.add(btnTarifas);
		
		JButton btn = new JButton("Clientes");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				clientes();
			}
		});
		btn.setFocusable(false);
		btn.setForeground(new Color(255, 255, 255));
		btn.setFont(new Font("Arial Black", Font.BOLD, 20));
		btn.setBackground(new Color(0, 0, 0));
		btn.setBounds(466, 302, 266, 40);
		panel.add(btn);
	
		
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
		lblLogo.setIcon(new ImageIcon(Sistema.class.getResource("/img/logoMenu.png")));
		lblLogo.setBounds(116, 106, 233, 208);
		panel.add(lblLogo);
		
		JLabel lblColor = new JLabel("");
		lblColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor.setIcon(new ImageIcon(Sistema.class.getResource("/img/clientesLogoMenu.png")));
		lblColor.setBackground(new Color(119, 182, 255));
		lblColor.setOpaque(true);
		lblColor.setBounds(466, 112, 266, 230);
		panel.add(lblColor);
		
		JLabel lblColor_1 = new JLabel("");
		lblColor_1.setVerticalAlignment(SwingConstants.TOP);
		lblColor_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor_1.setIcon(new ImageIcon(Sistema.class.getResource("/img/tarifasMenu.png")));
		lblColor_1.setOpaque(true);
		lblColor_1.setBackground(new Color(119, 182, 255));
		lblColor_1.setBounds(832, 112, 266, 230);
		panel.add(lblColor_1);
		
		JLabel lblColor_2 = new JLabel("");
		lblColor_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor_2.setIcon(new ImageIcon(Sistema.class.getResource("/img/InstructorMenu.png")));
		lblColor_2.setOpaque(true);
		lblColor_2.setBackground(new Color(119, 182, 255));
		lblColor_2.setBounds(100, 399, 266, 230);
		panel.add(lblColor_2);
		
		JLabel lblColor_3 = new JLabel("");
		lblColor_3.setVerticalAlignment(SwingConstants.TOP);
		lblColor_3.setIcon(new ImageIcon(Sistema.class.getResource("/img/clasesMenu.png")));
		lblColor_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor_3.setOpaque(true);
		lblColor_3.setBackground(new Color(119, 182, 255));
		lblColor_3.setBounds(466, 399, 266, 230);
		panel.add(lblColor_3);
		
		JLabel lblColor_4 = new JLabel("");
		lblColor_4.setIcon(new ImageIcon(Sistema.class.getResource("/img/checadorMenu.png")));
		lblColor_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor_4.setOpaque(true);
		lblColor_4.setBackground(new Color(119, 182, 255));
		lblColor_4.setBounds(832, 399, 266, 230);
		panel.add(lblColor_4);
		
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
		
		menuBar = new JMenuBar();
		menuBar.setBounds(148, 42, 101, 22);
		panelSup.add(menuBar, BorderLayout.SOUTH);
		menuBar.add(Box.createHorizontalStrut(100)); //Separar cada menu
		JMenuItem menuInicio = new JMenuItem("Inicio");
		menuInicio.setOpaque(false);
		menuInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				menuPrincipal();
			}
		});
		menuBar.add(menuInicio);
		
		menuBar.add(Box.createHorizontalStrut(100)); //Separar cada menu
		
		JMenuItem menuClientes = new JMenuItem("Clientes");
		menuClientes.setOpaque(false);
		menuClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				clientes();
			}
		});
		menuBar.add(menuClientes);
		menuBar.add(Box.createHorizontalStrut(100)); //Separar cada menu
		JMenuItem menuTarifas = new JMenuItem("Tarifas");
		menuTarifas.setOpaque(false);
		menuTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				
			}
		});
		menuBar.add(menuTarifas);
		menuBar.add(Box.createHorizontalStrut(80)); //Separar cada menu
		JMenuItem menuInst = new JMenuItem("Instructores");
		menuInst.setOpaque(false);
		menuInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				instructor();
			}
		});
		menuBar.add(menuInst);
		menuBar.add(Box.createHorizontalStrut(80)); //Separar cada menu
		JMenuItem menuClases = new JMenuItem("Clases");
		menuClases.setOpaque(false);
		menuClases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				clases();
			}
		});
		menuBar.add(menuClases);
		menuBar.add(Box.createHorizontalStrut(100)); //Separar cada menu
		JMenuItem menuCheck = new JMenuItem("Checador");
		menuCheck.setOpaque(false);
		menuCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				checador();
			}
		});
		menuBar.add(menuCheck);
		menuBar.add(Box.createHorizontalStrut(100)); //Separar cada menu
		JMenuItem menuSalir = new JMenuItem("Salir");
		menuSalir.setOpaque(false);
		menuSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inicio i1 = new Inicio();
		        i1.setVisible(true);
			}
		});
		menuBar.add(menuSalir);
	
	}

	public void quitarComponentes() {
		contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
	}
	
	public void clientes() {
		menuVerticalClientes();
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Clientes registrados");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(542, 114, 276, 33);
		panel.add(lblTitutlo);
		
		String titles[]= {"ID", "Nombre", "Apellido", "Correo", "Telefono", "Fecha de ingreso", "Tipo de membresía", "Estado"};
		DefaultTableModel modelo = new DefaultTableModel(null, titles) {
            @Override
            public boolean isCellEditable(int row, int column) {	              
                return false; //La tabla no se edita
            }
	     };
		JTable datosTabla = new JTable(modelo);
		JScrollPane tablaScroll = new JScrollPane(datosTabla);
		tablaScroll.setBounds(230,220,900,350);
		panel.add(tablaScroll);
		
		JComboBox btnFiltro = new JComboBox();
		btnFiltro.setModel(new DefaultComboBoxModel(new String[] {"Filtrar", "Todos","Activos", "No activos"}));
		btnFiltro.setForeground(new Color(0, 0, 0));
		btnFiltro.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnFiltro.setBounds(943, 120, 155, 30);
		panel.add(btnFiltro);
	}

	public void botonesDetallesClientes(JButton btn) {
		btn.setFocusable(false);
	    btn.setForeground(new Color(255, 255, 255));
	    btn.setBackground(new Color(116, 116, 116));
	    btn.setFont(new Font("Arial Black", Font.PLAIN, 13));
	}
	public void detallesClientes() {
		menuVerticalClientes();
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Detalles del cliente");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(542, 114, 276, 33);
		panel.add(lblTitutlo);
		
		JTextField textID = new JTextField("Ingrese ID");
		textID.setBackground(new Color(217, 217, 217));
		textID.setFont(new Font("Arial Black", Font.PLAIN, 20));
	    textID.setColumns(10);
	    textID.setForeground(Color.GRAY);
	    textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textID.setBounds(533, 172, 251, 50);
	    textID.addFocusListener(new FocusListener() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (textID.getText().equals("Ingrese ID")) { 
	                textID.setText(""); 
	                textID.setForeground(Color.BLACK); 
	            }
	        }
	        @Override
	        public void focusLost(FocusEvent e) {
	            if (textID.getText().isEmpty()) { 
	                textID.setText("Ingrese ID"); 
	                textID.setForeground(Color.GRAY);
	            }
	        }
	    });
	    panel.add(textID);
	    
	    textField = new JTextField();
	    textField.setBounds(170, 80, 0, 0);
	    panel.add(textField);
	    
	    panelCredencial = new JPanel();
	    panelCredencial.setBackground(new Color(217, 217, 217));
	    panelCredencial.setBounds(222, 306, 915, 310);
	    panel.add(panelCredencial);
	    panelCredencial.setVisible(false);
	    panelCredencial.setLayout(null);
	    
	    panelHistorialPagos = new JPanel();
	    panelHistorialPagos.setBackground(new Color(217, 217, 217));
	    panelHistorialPagos.setBounds(222, 250, 915, 400);
	    panel.add(panelHistorialPagos);
	    panelHistorialPagos.setVisible(false);
	    panelHistorialPagos.setLayout(null);
	    
	    JButton btnBuscar = new JButton("");
	    btnBuscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		panelCredencial.setVisible(true);
	    	}
	    });
	    btnBuscar.setFocusable(false);
	    btnBuscar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnBuscar.setIcon(new ImageIcon(Sistema.class.getResource("/img/buscar.png")));
	    btnBuscar.setBackground(new Color(217, 217, 217)); 
	    btnBuscar.setBounds(783, 172, 50, 50);
	    panel.add(btnBuscar);
	    
	    
	    JButton btnHistorial = new JButton("Historial de pagos");
	    btnHistorial.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		panelCredencial.setVisible(false);
	    		panelHistorialPagos.setVisible(true);
	    	}
	    	
	    });
	    botonesDetallesClientes(btnHistorial);
	    btnHistorial.setBounds(690, 22, 215, 50);
	    panelCredencial.add(btnHistorial);
	    
	    JButton btnHistorialDeAsistencias = new JButton("Historial de asistencias");
	    btnHistorialDeAsistencias.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		panelCredencial.setVisible(false);
	    		panelAsistencias.setVisible(true);
	    	}
	    });
	    botonesDetallesClientes(btnHistorialDeAsistencias);
	    btnHistorialDeAsistencias.setBounds(690, 94, 215, 50);
	    panelCredencial.add(btnHistorialDeAsistencias);
	    
	    JButton btnDescargarCredencial = new JButton("Descargar credencial");
	    btnDescargarCredencial.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 JOptionPane.showMessageDialog(null, "Credencial descargada correctamente", "Descarga exitosa", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    });
	    botonesDetallesClientes(btnDescargarCredencial);
	    btnDescargarCredencial.setBounds(690, 166, 215, 50);
	    panelCredencial.add(btnDescargarCredencial);
	    
	    JButton btnDescargarReporte = new JButton("Descargar reporte");
	    btnDescargarReporte.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 JOptionPane.showMessageDialog(null, "Reporte descargado correctamente", "Descarga exitosa", JOptionPane.INFORMATION_MESSAGE);
	 	    	
	    	}
	    });
	    botonesDetallesClientes(btnDescargarReporte);
	    btnDescargarReporte.setBounds(690, 238, 215, 50);
	    panelCredencial.add(btnDescargarReporte);
	    
	    JLabel lblPersona = new JLabel();
	    lblPersona.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 1.png")));
	    lblPersona.setBounds(36, 23, 217, 218);
	    panelCredencial.add(lblPersona);
	    
	    JLabel lblCodigo = new JLabel();
	    lblCodigo.setIcon(new ImageIcon(Sistema.class.getResource("/img/codigoDeBarras.png")));
	    lblCodigo.setBounds(299, 229, 327, 59);
	    panelCredencial.add(lblCodigo);
	    
	    JLabel lblFecha = new JLabel("Fecha de nacimiento: " + "12/09/2004");
	    lblFecha.setHorizontalAlignment(SwingConstants.CENTER);    lblFecha.setForeground(new Color(0, 0, 0));	    lblFecha.setFont(new Font("Arial Black", Font.PLAIN, 14));
	    lblFecha.setBounds(299, 33, 327, 20);
	    panelCredencial.add(lblFecha);
	    
	    JLabel lblTlefono = new JLabel("Teléfono: "+"2346546734");
	    lblTlefono.setHorizontalAlignment(SwingConstants.CENTER);    lblTlefono.setForeground(Color.BLACK);  lblTlefono.setFont(new Font("Arial Black", Font.PLAIN, 14));
	    lblTlefono.setBounds(299, 73, 327, 20);
	    panelCredencial.add(lblTlefono);
	    
	    JLabel lblCorreoElectrnico = new JLabel("Correo electrónico: "+"peterBp1@gmail.com");
	    lblCorreoElectrnico.setHorizontalAlignment(SwingConstants.CENTER);    lblCorreoElectrnico.setForeground(Color.BLACK);    lblCorreoElectrnico.setFont(new Font("Arial Black", Font.PLAIN, 14));
	    lblCorreoElectrnico.setBounds(299, 113, 327, 20);
	    panelCredencial.add(lblCorreoElectrnico);
	    
	    JLabel lblFechaDeRegistro = new JLabel("Fecha de registro: "+"21/07/2023");
	    lblFechaDeRegistro.setHorizontalAlignment(SwingConstants.CENTER);  lblFechaDeRegistro.setForeground(Color.BLACK);  lblFechaDeRegistro.setFont(new Font("Arial Black", Font.PLAIN, 14));  lblFechaDeRegistro.setBounds(299, 153, 327, 20);
	    panelCredencial.add(lblFechaDeRegistro);
	    
	    JLabel lblMembresa = new JLabel("Membresía: " + "Individual");
	    lblMembresa.setHorizontalAlignment(SwingConstants.CENTER);	    lblMembresa.setForeground(Color.BLACK);	    lblMembresa.setFont(new Font("Arial Black", Font.PLAIN, 14));
	    lblMembresa.setBounds(299, 193, 327, 20);
	    panelCredencial.add(lblMembresa);
	    
	    JLabel lblPeterParker = new JLabel("Peter Parker");
	    lblPeterParker.setHorizontalAlignment(SwingConstants.CENTER);	    lblPeterParker.setForeground(Color.BLACK);	    lblPeterParker.setFont(new Font("Arial Black", Font.PLAIN, 18));
	    lblPeterParker.setBounds(36, 260, 217, 20);
	    panelCredencial.add(lblPeterParker);
	    
	    //PANEL HISTORIAL PAGOS
	    JLabel lblTitulo = new JLabel("Historial de pagos");
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitulo.setBounds(320, 27, 276, 33);
		panelHistorialPagos.add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		panelHistorialPagos.setVisible(false);
	    		panelCredencial.setVisible(true);
	    	}
	    });
	    botonesDetallesClientes(btnVolver);
	    btnVolver.setBounds(676, 27, 151, 40);
	    panelHistorialPagos.add(btnVolver);
	    
	    
	    String titles[]= {"Membresía", "Fecha inicial", "Vencimiento", "Total"};
		DefaultTableModel modelo = new DefaultTableModel(null, titles) {
            @Override
            public boolean isCellEditable(int row, int column) {	              
                return false; //La tabla no se edita
            }
	     };
		JTable datosTabla = new JTable(modelo);
		JScrollPane tablaScroll = new JScrollPane(datosTabla);
		tablaScroll.setBounds(87, 160, 740, 210);
		panelHistorialPagos.add(tablaScroll);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 2.png")));
		lblNewLabel.setBounds(109, 27, 83, 81);
		panelHistorialPagos.add(lblNewLabel);
		
	    JLabel lblPeterParke = new JLabel("Peter Parker");
	    lblPeterParke.setHorizontalAlignment(SwingConstants.CENTER);	    lblPeterParke.setForeground(Color.BLACK);	    lblPeterParke.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    panelHistorialPagos.add(lblPeterParke);
	    lblPeterParke.setBounds(87, 117, 122, 20);

	    
	    //PANEL ASISTENCIAS
	    panelAsistencias = new JPanel();
	    panelAsistencias.setBackground(new Color(217, 217, 217));
	    panelAsistencias.setBounds(222, 250, 915, 400);
	    panel.add(panelAsistencias);
	    panelAsistencias.setVisible(false);
	    panelAsistencias.setLayout(null);
	    JLabel titulo2 = new JLabel("Historial de asistencia");
	    titulo2.setForeground(new Color(0, 0, 0));
	    titulo2.setHorizontalAlignment(SwingConstants.CENTER);
	    titulo2.setFont(new Font("Arial Black", Font.PLAIN, 20));
	    titulo2.setBounds(0, 27, 915, 33);
		panelAsistencias.add(titulo2);
		
		JButton btnVolver2 = new JButton("Volver");
		 btnVolver2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    		panelAsistencias.setVisible(false);
		    		panelCredencial.setVisible(true);
		    	}
		    	
		    });
		  botonesDetallesClientes(btnVolver2);
		  btnVolver2.setBounds(676, 27, 151, 40);
		  panelAsistencias.add(btnVolver2);
		    
		  String titulo[]= {"Fecha", "Hora de entrada", "Hora de salida"};
		  DefaultTableModel modelo2 = new DefaultTableModel(null, titulo) {
		      @Override
		      public boolean isCellEditable(int row, int column) {	              
		          return false; //La tabla no se edita
		    }
		 }; 
		JTable datosTabla2 = new JTable(modelo2);
		JScrollPane tablaScroll2 = new JScrollPane(datosTabla2);
		tablaScroll2.setBounds(87, 160, 740, 210);
        panelAsistencias.add(tablaScroll2);
	
        lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 2.png")));
		lblNewLabel.setBounds(109, 27, 83, 81);
		panelAsistencias.add(lblNewLabel);
        
        monthComboBox = new JComboBox<>();
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        for (String month : months) {
            monthComboBox.addItem(month);
        }
        monthComboBox.setBounds(407, 90, 100, 25);
        monthComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //    cargarAsistencias((String) monthComboBox.getSelectedItem());
            }
        });
        panelAsistencias.add(monthComboBox); 
		
        lblPeterParke = new JLabel("Peter Parker"); lblPeterParke.setBounds(87, 117, 122, 20); lblPeterParke.setHorizontalAlignment(SwingConstants.CENTER);	    lblPeterParke.setForeground(Color.BLACK);	    lblPeterParke.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    panelAsistencias.add(lblPeterParke);
	    
	}


	public void instructor() {
		panel();
		menuB();
	}
	public void checador() {
		panel();
		menuB();
	}
	public void clases() {
		panel();
		menuB();
	}
	
	public void confBtnMenuVertical(JButton btn) {
		btn.setBackground(Color.BLACK);
		btn.setFont(new Font("Arial Black", Font.PLAIN, 20));
		btn.setForeground(new Color(55,171,255));
		btn.setFocusable(false);
		btn.setBorder(null);
	}
	public void menuVerticalClientes()	{
		panelMenuVertical = new JPanel();
		panelMenuVertical.setBackground(Color.black);
		contentPane.add(panelMenuVertical);
		panelMenuVertical.setSize(170,620);
		panelMenuVertical.setLocation(0,70);
		panelMenuVertical.setLayout(null);
		
		JButton btnReg = new JButton("Registros");
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				clientes();
			}
		});
		confBtnMenuVertical(btnReg);
		btnReg.setBounds(0, 0, 170, 90);
		panelMenuVertical.add(btnReg);
		
		
		btnDetalles = new JButton ("Detalles");
		btnDetalles.setBounds(0, 132, 170, 90);
		confBtnMenuVertical(btnDetalles);
		btnDetalles.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
        		detallesClientes();
		        }
        });
		panelMenuVertical.add(btnDetalles);
		
		
		btnCrear = new JButton("Crear");
		confBtnMenuVertical(btnCrear);
		btnCrear.setBounds(0, 264, 170, 90);
		panelMenuVertical.add(btnCrear);
		
		
		JButton btnEditar = new JButton("Editar");
		confBtnMenuVertical(btnEditar);
		btnEditar.setBounds(0, 396, 170, 90);
		panelMenuVertical.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		confBtnMenuVertical(btnEliminar);
		btnEliminar.setBounds(0, 520, 170, 90);
		panelMenuVertical.add(btnEliminar);
		
	}
	
	
	
}
