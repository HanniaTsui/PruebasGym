package Vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Sistema extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelSup, panelMenuVertical, panelAsistencias, panelHistorialPagos, panelCredencial, panelCrear, p2;
	private final JPanel panelNegro = new JPanel();
	private JButton btnBuscar, btnElim, btnVolver,btnHistorial, btnHistorialDeAsistencias, btnDescargarCredencial, btnReporte, btnGuardar, btnCancelar, btnPagar;
	JMenuBar menuBar;
	JLabel lblTitulo, lblGym, lblPersona, lblCodigo, lblFecha, lblTlefono, lblCorreoElectrnico, lblFechaDeRegistro, lblMembresia, lblPeterParker, lblNewLabel;
	private JButton btnDetalles, btnCrear;
	private JTextField textField;
	 private JComboBox<String> comboMes;
	 private JLabel lblNombres, lblApellidos, lblTotalPago, lblMembresia_1, lblMtodoDePago;
	 private JTextField textNombre, textApellidos, textEmail, textNacimiento, textTel;
	 private JComboBox comboTipo, comboPago;
	 String ventanaActual;

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
		
	 	menuPrincipal();


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
		            switch (index) {
		                case 0:
		                    clientes();
		                    break;
		                case 1:
		                    tarifas();
		                    break;
		                case 2:
		                    instructor();
		                    break;
		                case 3:
		                    clases();
		                    break;
		                case 4:
		                    checador();
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
		lblLogo.setIcon(new ImageIcon(Sistema.class.getResource("/img/logoMenu.png")));
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
		    lblColores[i].setIcon(new ImageIcon(Sistema.class.getResource(imagenes[i])));
		    panel.add(lblColores[i]);
		}
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
				tarifas();
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
		menuBar.add(Box.createHorizontalStrut(80));
	
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
	
	public void configurarLabels(JLabel lbl) {
		lbl.setForeground(new Color(0, 0, 0));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Arial Black", Font.PLAIN, 14));
	}
	
	public void configurarLabelsIzq(JLabel lbl) {
		lbl.setForeground(new Color(0, 0, 0));
		lbl.setHorizontalAlignment(SwingConstants.LEFT);
		lbl.setFont(new Font("Arial Black", Font.PLAIN, 14));
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
	    //PANEL CREDENCIAL
	    panelCredencial = new JPanel();
	    panelCredencial.setBackground(new Color(217, 217, 217));
	    panelCredencial.setBounds(222, 306, 915, 310);
	    panel.add(panelCredencial);
	    panelCredencial.setVisible(false);
	    panelCredencial.setLayout(null);
	    
	    btnBuscar = new JButton("");
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
	    
	    lblPersona = new JLabel();
	    lblPersona.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 1.png")));
	    lblPersona.setBounds(36, 23, 217, 218);
	    panelCredencial.add(lblPersona);
	    
	    lblCodigo = new JLabel();
	    lblCodigo.setIcon(new ImageIcon(Sistema.class.getResource("/img/codigoDeBarras.png")));
	    lblCodigo.setBounds(299, 229, 327, 59);
	    panelCredencial.add(lblCodigo);
	    
	    lblFecha = new JLabel("Fecha de nacimiento: " + "12/09/2004");
	    configurarLabels(lblFecha);lblFecha.setBounds(299, 33, 327, 20);
	    panelCredencial.add(lblFecha);
	    
	    lblTlefono = new JLabel("Teléfono: "+"2346546734");
	    lblTlefono.setBounds(299, 73, 327, 20); configurarLabels(lblTlefono);
	    panelCredencial.add(lblTlefono);
	    
	    lblCorreoElectrnico = new JLabel("Correo electrónico: "+"peterBp1@gmail.com");
	    configurarLabels(lblCorreoElectrnico);lblCorreoElectrnico.setBounds(299, 113, 327, 20);
	    panelCredencial.add(lblCorreoElectrnico);
	    
	    lblFechaDeRegistro = new JLabel("Fecha de registro: "+"21/07/2023");
	    configurarLabels(lblFechaDeRegistro); lblFechaDeRegistro.setBounds(299, 153, 327, 20);
	    panelCredencial.add(lblFechaDeRegistro);
	    
	    lblMembresia = new JLabel("Membresía: " + "Individual");
	    configurarLabels(lblMembresia);lblMembresia.setBounds(299, 193, 327, 20);
	    panelCredencial.add(lblMembresia);
	    
	    lblPeterParker = new JLabel("Peter Parker");
	    configurarLabels(lblPeterParker);lblPeterParker.setBounds(36, 260, 217, 20);
	    panelCredencial.add(lblPeterParker);
	    
	    panelHistorialPagos = new JPanel();
	    panelHistorialPagos.setBackground(new Color(217, 217, 217));
	    panelHistorialPagos.setBounds(222, 250, 915, 400);
	    panel.add(panelHistorialPagos);
	    panelHistorialPagos.setVisible(false);
	    panelHistorialPagos.setLayout(null);
	    
	    btnHistorial = new JButton("Historial de pagos");
	    btnHistorial.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		panelCredencial.setVisible(false);
	    		panelHistorialPagos.setVisible(true);
	    	}
	    	
	    });
	    botonesDetallesClientes(btnHistorial);
	    btnHistorial.setBounds(690, 22, 215, 50);
	    panelCredencial.add(btnHistorial);
	    
	    btnHistorialDeAsistencias = new JButton("Historial de asistencias");
	    btnHistorialDeAsistencias.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		panelCredencial.setVisible(false);
	    		panelAsistencias.setVisible(true);
	    	}
	    });
	    botonesDetallesClientes(btnHistorialDeAsistencias);
	    btnHistorialDeAsistencias.setBounds(690, 94, 215, 50);
	    panelCredencial.add(btnHistorialDeAsistencias);
	    
	    btnDescargarCredencial = new JButton("Descargar credencial");
	    btnDescargarCredencial.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 JOptionPane.showMessageDialog(null, "Credencial descargada correctamente", "Descarga exitosa", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    });
	    botonesDetallesClientes(btnDescargarCredencial);
	    btnDescargarCredencial.setBounds(690, 166, 215, 50);
	    panelCredencial.add(btnDescargarCredencial);
	    
	    btnReporte = new JButton("Descargar reporte");
	    btnReporte.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 JOptionPane.showMessageDialog(null, "Reporte descargado correctamente", "Descarga exitosa", JOptionPane.INFORMATION_MESSAGE);
	 	    	
	    	}
	    });
	    botonesDetallesClientes(btnReporte);
	    btnReporte.setBounds(690, 238, 215, 50);
	    panelCredencial.add(btnReporte);
	    
	    //PANEL HISTORIAL PAGOS
	    lblTitulo = new JLabel("Historial de pagos");
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitulo.setBounds(320, 27, 276, 33);
		panelHistorialPagos.add(lblTitulo);
		
		btnVolver = new JButton("Volver");
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
		
		lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 2.png")));
		lblNewLabel.setBounds(109, 27, 83, 81);
		panelHistorialPagos.add(lblNewLabel);
		
	    JLabel lblPeterParke = new JLabel("Peter Parker");
	    configurarLabels(lblPeterParke);
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
        
        comboMes = new JComboBox<>();
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        for (String month : months) {
            comboMes.addItem(month);
        }
        comboMes.setBounds(407, 90, 100, 25);
        comboMes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //    cargarAsistencias((String) monthComboBox.getSelectedItem());
            }
        });
        panelAsistencias.add(comboMes); 
		
        lblPeterParke = new JLabel("Peter Parker"); lblPeterParke.setBounds(87, 117, 122, 20); lblPeterParke.setHorizontalAlignment(SwingConstants.CENTER);	    lblPeterParke.setForeground(Color.BLACK);	    lblPeterParke.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    panelAsistencias.add(lblPeterParke);
	    
	    JLabel lblAsistenciasTotales = new JLabel("Asistencias totales:");
	    lblAsistenciasTotales.setHorizontalAlignment(SwingConstants.CENTER); lblAsistenciasTotales.setForeground(Color.BLACK);lblAsistenciasTotales.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    lblAsistenciasTotales.setBounds(588, 117, 237, 20);
	    panelAsistencias.add(lblAsistenciasTotales);
	    
	}

	public void tarifas() {
		panel();
		menuB();
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
		btnCrear.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
        		crearCliente();
		        }
        });
		btnCrear.setBounds(0, 264, 170, 90);
		panelMenuVertical.add(btnCrear);
		
		
		JButton btnEditar = new JButton("Editar");
		confBtnMenuVertical(btnEditar);
		btnEditar.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
        		editarClientes();
		        }
        });
		btnEditar.setBounds(0, 396, 170, 90);
		panelMenuVertical.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
        		eliminarCliente();
		        }
        });
		confBtnMenuVertical(btnEliminar);
		btnEliminar.setBounds(0, 520, 170, 90);
		panelMenuVertical.add(btnEliminar);
		
	}
	
	public void crearCliente() {
		menuVerticalClientes();
		panel();
		menuB();
		panelCrearEditar();
		
		JLabel lblTitutlo = new JLabel("Nuevo cliente");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(372, 44, 276, 33);
		p2.add(lblTitutlo);
		
		panelCrear = new JPanel();
	    p2.add(panelCrear);
	    panelCrear.setBackground(new Color(217, 217, 217));
	    panelCrear.setLayout(null);
	    panelCrear.setBounds(53, 112, 915, 550);
	    
	    lblFecha = new JLabel("Fecha de nacimiento: ");
	    configurarLabelsIzq(lblFecha);lblFecha.setBounds(360, 33, 200, 20);
	    panelCrear.add(lblFecha);
	    
	    lblTlefono = new JLabel("Teléfono:");
	    configurarLabelsIzq(lblTlefono);lblTlefono.setBounds(360, 119, 200, 20);
	    panelCrear.add(lblTlefono);
	    
	    lblCorreoElectrnico = new JLabel("Correo electrónico: ");
	    configurarLabelsIzq(lblCorreoElectrnico);lblCorreoElectrnico.setBounds(70, 215, 200, 20);
	    panelCrear.add(lblCorreoElectrnico);

	    lblMembresia = new JLabel("Tipo de membresía: ");
	    configurarLabelsIzq(lblMembresia);lblMembresia.setBounds(360, 215, 200, 20);
	    panelCrear.add(lblMembresia);
	    
	    lblNombres = new JLabel("Nombre (s):");
	    configurarLabelsIzq(lblNombres);
	    lblNombres.setBounds(70, 33, 200, 20);
	    panelCrear.add(lblNombres);
	    
	    textNombre = new JTextField();
	    textNombre.setBounds(70, 73, 200, 30);
	    panelCrear.add(textNombre);
	    textNombre.setColumns(10);
	    
	    lblApellidos = new JLabel("Apellidos:");
	    configurarLabelsIzq(lblApellidos);
	    lblApellidos.setBounds(70, 119, 200, 20);
	    panelCrear.add(lblApellidos);
	    
	    textApellidos = new JTextField();
	    textApellidos.setColumns(10);
	    textApellidos.setBounds(70, 154, 200, 30);
	    panelCrear.add(textApellidos);
	    
	    textEmail = new JTextField();
	    textEmail.setColumns(10);
	    textEmail.setBounds(70, 255, 200, 30);
	    panelCrear.add(textEmail);
	    
	    textNacimiento = new JTextField();
	    textNacimiento.setColumns(10);
	    textNacimiento.setBounds(360, 73, 200, 30);
	    panelCrear.add(textNacimiento);
	    
	    textTel = new JTextField();
	    textTel.setColumns(10);
	    textTel.setBounds(360, 149, 200, 30);
	    panelCrear.add(textTel);
	    
	    JComboBox comboMembresia = new JComboBox();
	    comboMembresia.setFont(new Font("Arial Black", Font.PLAIN, 14));
	    comboMembresia.setModel(new DefaultComboBoxModel(new String[] {"  ", "General", "Estudiante","Familiar", "Dúo"}));
	    comboMembresia.setBounds(360, 255, 200, 30);
	    panelCrear.add(comboMembresia);
	    
	    JLabel lblFechaInicial = new JLabel("Fecha inicial:");
	    configurarLabelsIzq(lblFechaInicial);
	    lblFechaInicial.setBounds(70, 315, 200, 20);
	    panelCrear.add(lblFechaInicial);
	    
	    JLabel lblFechaFinal = new JLabel("Fecha final:");
	    configurarLabelsIzq(lblFechaFinal);
	    lblFechaFinal.setBounds(70, 400, 200, 20);
	    panelCrear.add(lblFechaFinal);
	    
	    lblMembresia_1 = new JLabel("Plan de membresía: ");
	    configurarLabelsIzq(lblMembresia_1);
	    lblMembresia_1.setBounds(360, 315, 200, 20);
	    panelCrear.add(lblMembresia_1);
	    
	    lblMtodoDePago = new JLabel("Método de pago:");
	    configurarLabelsIzq(lblMtodoDePago);
	    lblMtodoDePago.setBounds(360, 400, 200, 20);
	    panelCrear.add(lblMtodoDePago);
	    
	    JSpinner spinnerFechaIn = new JSpinner(new SpinnerDateModel());
	    JSpinner.DateEditor dateEditorFechaIn = new JSpinner.DateEditor(spinnerFechaIn, "dd/MM/yyyy");
	    spinnerFechaIn.setEditor(dateEditorFechaIn);
	    spinnerFechaIn.setBounds(70, 350, 200, 30);
	    panelCrear.add(spinnerFechaIn);

	    JSpinner spinnerFechaFin = new JSpinner(new SpinnerDateModel());
	    JSpinner.DateEditor dateEditorFechaFin = new JSpinner.DateEditor(spinnerFechaFin, "dd/MM/yyyy");
	    spinnerFechaFin.setEditor(dateEditorFechaFin);
	    spinnerFechaFin.setBounds(70, 435, 200, 30);
	    panelCrear.add(spinnerFechaFin);
	    
	    comboTipo = new JComboBox();
	    comboTipo.setFont(new Font("Arial Black", Font.PLAIN, 14));
	    comboTipo.setModel(new DefaultComboBoxModel(new String[] {"  ", "1 mes", "3 meses","6 meses", "1 año"}));
	    comboTipo.setBounds(360, 350, 200, 30);
	    panelCrear.add(comboTipo);
	    
	    comboPago = new JComboBox();
	    comboPago.setFont(new Font("Arial Black", Font.PLAIN, 14));
	    comboPago.setModel(new DefaultComboBoxModel(new String[] {"  ", "Efectivo", "Visa","Cheque"}));
	    comboPago.setBounds(360, 435, 200, 30);
	    panelCrear.add(comboPago);
	    
	    JButton btnFoto = new JButton("Subir foto");
	    btnFoto.setForeground(new Color(255, 255, 255));
	    btnFoto.setFocusable(false);
	    btnFoto.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnFoto.setBackground(new Color(89, 89, 89));
	    btnFoto.setBounds(652, 270, 207, 40);
	    panelCrear.add(btnFoto);
	    
	    JLabel lblFoto = new JLabel("");
	    lblFoto.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 1.png")));
	    lblFoto.setBounds(642, 33, 217, 221);
	    panelCrear.add(lblFoto);
	    
	    btnCancelar = new JButton("Cancelar");
	    btnCancelar.setForeground(new Color(255, 255, 255));
	    btnCancelar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
				crearCliente();
	    	}
	    });
	    btnCancelar.setFocusable(false);
	    btnCancelar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnCancelar.setBackground(new Color(0, 0, 0)); 
	    btnCancelar.setBounds(292, 490, 150, 40);
	    panelCrear.add(btnCancelar);
	
		btnPagar = new JButton("Pagar");
		btnPagar.setForeground(new Color(255, 255, 255));
		btnPagar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		JTextArea textArea = new JTextArea(); // Falta que devuelva los datos en un ticket
    		textArea.setEditable(false);
    		JScrollPane scrollPane = new JScrollPane(textArea);
    		scrollPane.setPreferredSize(new Dimension(400, 400));

    		Object[] options = {"Imprimir", "Cancelar"}; 
    		int choice = JOptionPane.showOptionDialog(null, scrollPane, "Comprobante", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    		if (choice == JOptionPane.YES_OPTION) {
    		    // Cuando se selecciona "Imprimir"
    		    JOptionPane.showMessageDialog(null, "Impresión exitosa", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    		}
    		}
	    });
		btnPagar.setFocusable(false);
		btnPagar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnPagar.setBackground(new Color(0, 45, 78)); 
		btnPagar.setBounds(472, 490, 150, 40);
	    panelCrear.add(btnPagar);
	    
	    lblTotalPago = new JLabel("Total a pagar: $");
	    configurarLabelsIzq(lblTotalPago);
	    lblTotalPago.setBounds(652, 338, 130, 20);
	    panelCrear.add(lblTotalPago);
	    
	}
	
	public void editarClientes() {
		menuVerticalClientes();
		panel();
		menuB();
		panelCrearEditar();
		
		JLabel lblTitutlo = new JLabel("Editar cliente");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(372, 44, 276, 33);
		p2.add(lblTitutlo);
		
		panelCrear = new JPanel();
	    p2.add(panelCrear);
	    panelCrear.setBackground(new Color(217, 217, 217));
	    panelCrear.setLayout(null);
	    panelCrear.setVisible(false);
	    panelCrear.setBounds(52, 200, 915, 550);
	    
	    lblFecha = new JLabel("Fecha de nacimiento: ");
	    configurarLabelsIzq(lblFecha);lblFecha.setBounds(360, 33, 200, 20);
	    panelCrear.add(lblFecha);
	    
	    lblTlefono = new JLabel("Teléfono:");
	    configurarLabelsIzq(lblTlefono);lblTlefono.setBounds(360, 119, 200, 20);
	    panelCrear.add(lblTlefono);
	    
	    lblCorreoElectrnico = new JLabel("Correo electrónico: ");
	    configurarLabelsIzq(lblCorreoElectrnico);lblCorreoElectrnico.setBounds(70, 215, 200, 20);
	    panelCrear.add(lblCorreoElectrnico);

	    lblMembresia = new JLabel("Tipo de membresía: ");
	    configurarLabelsIzq(lblMembresia);lblMembresia.setBounds(360, 215, 200, 20);
	    panelCrear.add(lblMembresia);
	    
	    lblNombres = new JLabel("Nombre (s):");
	    configurarLabelsIzq(lblNombres);
	    lblNombres.setBounds(70, 33, 200, 20);
	    panelCrear.add(lblNombres);
	    
	    textNombre = new JTextField();
	    textNombre.setBounds(70, 73, 200, 30);
	    panelCrear.add(textNombre);
	    textNombre.setColumns(10);
	    
	    lblApellidos = new JLabel("Apellidos:");
	    configurarLabelsIzq(lblApellidos);
	    lblApellidos.setBounds(70, 119, 200, 20);
	    panelCrear.add(lblApellidos);
	    
	    textApellidos = new JTextField();
	    textApellidos.setColumns(10);
	    textApellidos.setBounds(70, 154, 200, 30);
	    panelCrear.add(textApellidos);
	    
	    textEmail = new JTextField();
	    textEmail.setColumns(10);
	    textEmail.setBounds(70, 255, 200, 30);
	    panelCrear.add(textEmail);
	    
	    textNacimiento = new JTextField();
	    textNacimiento.setColumns(10);
	    textNacimiento.setBounds(360, 73, 200, 30);
	    panelCrear.add(textNacimiento);
	    
	    textTel = new JTextField();
	    textTel.setColumns(10);
	    textTel.setBounds(360, 149, 200, 30);
	    panelCrear.add(textTel);
	    
	    JComboBox comboMembresia = new JComboBox();
	    comboMembresia.setFont(new Font("Arial Black", Font.PLAIN, 14));
	    comboMembresia.setModel(new DefaultComboBoxModel(new String[] {"  ", "General", "Estudiante","Familiar", "Dúo"}));
	    comboMembresia.setBounds(360, 255, 200, 30);
	    panelCrear.add(comboMembresia);
	    
	    JLabel lblFechaInicial = new JLabel("Fecha inicial:");
	    configurarLabelsIzq(lblFechaInicial);
	    lblFechaInicial.setBounds(70, 315, 200, 20);
	    panelCrear.add(lblFechaInicial);
	    
	    JLabel lblFechaFinal = new JLabel("Fecha final:");
	    configurarLabelsIzq(lblFechaFinal);
	    lblFechaFinal.setBounds(70, 400, 200, 20);
	    panelCrear.add(lblFechaFinal);
	    
	    lblMembresia_1 = new JLabel("Plan de membresía: ");
	    configurarLabelsIzq(lblMembresia_1);
	    lblMembresia_1.setBounds(360, 315, 200, 20);
	    panelCrear.add(lblMembresia_1);
	    
	    lblMtodoDePago = new JLabel("Método de pago:");
	    configurarLabelsIzq(lblMtodoDePago);
	    lblMtodoDePago.setBounds(360, 400, 200, 20);
	    panelCrear.add(lblMtodoDePago);
	    
	    JSpinner spinnerFechaIn = new JSpinner(new SpinnerDateModel());
	    JSpinner.DateEditor dateEditorFechaIn = new JSpinner.DateEditor(spinnerFechaIn, "dd/MM/yyyy");
	    spinnerFechaIn.setEditor(dateEditorFechaIn);
	    spinnerFechaIn.setBounds(70, 350, 200, 30);
	    panelCrear.add(spinnerFechaIn);

	    JSpinner spinnerFechaFin = new JSpinner(new SpinnerDateModel());
	    JSpinner.DateEditor dateEditorFechaFin = new JSpinner.DateEditor(spinnerFechaFin, "dd/MM/yyyy");
	    spinnerFechaFin.setEditor(dateEditorFechaFin);
	    spinnerFechaFin.setBounds(70, 435, 200, 30);
	    panelCrear.add(spinnerFechaFin);
	    
	    comboTipo = new JComboBox();
	    comboTipo.setFont(new Font("Arial Black", Font.PLAIN, 14));
	    comboTipo.setModel(new DefaultComboBoxModel(new String[] {"  ", "1 mes", "3 meses","6 meses", "1 año"}));
	    comboTipo.setBounds(360, 350, 200, 30);
	    panelCrear.add(comboTipo);
	    
	    comboPago = new JComboBox();
	    comboPago.setFont(new Font("Arial Black", Font.PLAIN, 14));
	    comboPago.setModel(new DefaultComboBoxModel(new String[] {"  ", "Efectivo", "Visa","Cheque"}));
	    comboPago.setBounds(360, 435, 200, 30);
	    panelCrear.add(comboPago);
	    
	    JButton btnFoto = new JButton("Subir foto");
	    btnFoto.setForeground(new Color(255, 255, 255));
	    btnFoto.setFocusable(false);
	    btnFoto.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnFoto.setBackground(new Color(89, 89, 89));
	    btnFoto.setBounds(652, 270, 207, 40);
	    panelCrear.add(btnFoto);
	    
	    JLabel lblFoto = new JLabel("");
	    lblFoto.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 1.png")));
	    lblFoto.setBounds(642, 33, 217, 221);
	    panelCrear.add(lblFoto);
	    
	    btnCancelar = new JButton("Cancelar");
	    btnCancelar.setForeground(new Color(255, 255, 255));
	    btnCancelar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
				editarClientes();
	    	}
	    });
	    btnCancelar.setFocusable(false);
	    btnCancelar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnCancelar.setBackground(new Color(0, 0, 0)); 
	    btnCancelar.setBounds(292, 490, 150, 40);
	    panelCrear.add(btnCancelar);
		
		JTextField textID = new JTextField("Ingrese ID");
		textID.setBackground(new Color(217, 217, 217));
		textID.setFont(new Font("Arial Black", Font.PLAIN, 20));
	    textID.setColumns(10);
	    textID.setForeground(Color.GRAY);
	    textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textID.setBounds(359, 102, 251, 50);
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
	    p2.add(textID);
	    
	    textField = new JTextField();
	    textField.setBounds(170, 80, 0, 0);
	    p2.add(textField);
		
	    btnBuscar = new JButton("");
	    btnBuscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		panelCrear.setVisible(true);
	    	}
	    });
	    btnBuscar.setFocusable(false);
	    btnBuscar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnBuscar.setIcon(new ImageIcon(Sistema.class.getResource("/img/buscar.png")));
	    btnBuscar.setBackground(new Color(217, 217, 217)); 
	    btnBuscar.setBounds(609, 102, 50, 50);
	    p2.add(btnBuscar);
	    
		
		btnGuardar = new JButton("Guardar");
	    btnGuardar.setForeground(new Color(255, 255, 255));
	    btnGuardar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
	    btnGuardar.setFocusable(false);
	    btnGuardar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnGuardar.setBackground(new Color(0, 45, 78)); 
	    btnGuardar.setBounds(472, 490, 150, 40);
	    panelCrear.add(btnGuardar);
		
		
	}
	
	public void eliminarCliente() {
		menuVerticalClientes();
		panel();
		menuB();
		
		JLabel lblTitutlo = new JLabel("Eliminar cliente");
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
	    btnBuscar = new JButton("");
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
	    
	    
	    panelCredencial.setVisible(false);
	    panelCredencial.setBackground(new Color(217, 217, 217));
	    panelCredencial.setBounds(222, 306, 915, 310);
	    panelCredencial.setLayout(null); 
	    panel.add(panelCredencial);
	    
	    btnElim = new JButton("Eliminar");
	    btnElim.setFocusable(false);
	    btnElim.setBackground(new Color(146, 25, 25));
	    btnElim.setForeground(new Color(255, 255, 255));
	    btnElim.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 int op = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este cliente?", "Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);
	             if (op == JOptionPane.OK_OPTION) {
	                 JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
	                 panelCredencial.setVisible(false);
	             } 
	             
	    	}
	    });
	    botonesDetallesClientes(btnElim);
	    btnElim.setBounds(690, 130, 215, 50);
	    panelCredencial.add(btnElim);
	    
	    lblPersona = new JLabel();
	    lblPersona.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 1.png")));
	    lblPersona.setBounds(36, 23, 217, 218);
	    panelCredencial.add(lblPersona);
	    
	    lblCodigo = new JLabel();
	    lblCodigo.setIcon(new ImageIcon(Sistema.class.getResource("/img/codigoDeBarras.png")));
	    lblCodigo.setBounds(299, 229, 327, 59);
	    panelCredencial.add(lblCodigo);
	    
	    lblFecha = new JLabel("Fecha de nacimiento: " + "12/09/2004");
	    configurarLabels(lblFecha);
	    lblFecha.setBounds(299, 33, 327, 20);
	    panelCredencial.add(lblFecha);
	    
	    lblTlefono = new JLabel("Teléfono: "+"2346546734");
	    configurarLabels(lblTlefono);lblTlefono.setBounds(299, 73, 327, 20);
	    panelCredencial.add(lblTlefono);
	    
	    lblCorreoElectrnico = new JLabel("Correo electrónico: "+"peterBp1@gmail.com");
	    configurarLabels(lblCorreoElectrnico);lblCorreoElectrnico.setBounds(299, 113, 327, 20);
	    panelCredencial.add(lblCorreoElectrnico);
	    
	    lblFechaDeRegistro = new JLabel("Fecha de registro: "+"21/07/2023");
	    configurarLabels(lblFechaDeRegistro); lblFechaDeRegistro.setBounds(299, 153, 327, 20);
	    panelCredencial.add(lblFechaDeRegistro);
	    
	    lblMembresia = new JLabel("Membresía: " + "Individual");
	    configurarLabels(lblMembresia);lblMembresia.setBounds(299, 193, 327, 20);
	    panelCredencial.add(lblMembresia);
	    
	    lblPeterParker = new JLabel("Peter Parker");
	    configurarLabels(lblPeterParker);lblPeterParker.setBounds(36, 260, 217, 20);
	    panelCredencial.add(lblPeterParker);
	}
	
	public void panelCrearEditar() {
		p2 = new JPanel();
		p2.setBackground(new Color(255, 255, 255));
		p2.setPreferredSize(new Dimension(p2.getWidth(), 750)); 
		p2.setLayout(null); 
	    
	    JScrollPane scrollPane = new JScrollPane(p2);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setBounds(170, 67, 1020, 619); 
	    panel.add(scrollPane);

	}
}
