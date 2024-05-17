package Vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JCheckBox;

public class Sistema extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelSup, panelMenuVertical, panelInfo, panelCredencial, panelCrear, p2;
	private final JPanel panelNegro = new JPanel();
	private JButton btnBuscar, btnElim, btnVolver,btnHistorial, btnDescargarCredencial, btnReporte, btnGuardar, btnCancelar, btnPagar, btnEditar;
	JMenuBar menuBar;
	JLabel lblTitulo, lblGym, lblPersona, lblCodigo, lblFecha, lblTlefono, lblCorreoElectrnico, lblFechaDeRegistro, lblMembresia, lblPeterParker, lblNewLabel;
	private JButton btnDetalles, btnCrear, btnRegistros;
	private JTextField textField;
	 private JComboBox<String> comboMes;
	 private JLabel lblNombres, lblApellidos, lblTotalPago, lblMembresia_1, lblMtodoDePago, lblFoto;
	 private JTextField textNombre, textApellidos, textEmail, textNacimiento, textTel;
	 private JComboBox comboTipo, comboPago;
	 String ventanaActual;
	 private JTable table;
	 private File selectedFile;
	 private JTextField textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7, textField_8;
	 private JLabel lblUsuariosInscritos, lblNewLabel_2, lblNewLabel_3,lblNewLabel_4, lblNewLabel_5, lblEspec;
	 private JButton btnEdit, btnEliminar_2;
	 Color colorBtnVolver = new Color(174,174,174);
	 Color colorBtnGuardar = new Color(0,47,78); 
	 Color colorBtnEliminar = new Color(0,0,0); 
	 Color colorBtnEditar = new Color(89,89,89); 
	 

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
		
	//	menuPrincipal();
detallesClase();
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
		
		JPanel panelBar = new JPanel();
		panelSup.add(panelBar, BorderLayout.SOUTH);
		panelBar.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton[] botones = new JButton[7];
		// Crear y configurar los botones
		String[] nombres = {"Inicio","Clientes", "Tarifas", "Instructores", "Clases", "Checador", "Salir"};
		for (int i = 0; i < botones.length; i++) {
		    final int index = i; 
		    botones[i] = new JButton(nombres[i]);
		    botones[i].setForeground(Color.black);
		    botones[i].setFont(new Font("Arial Black", Font.BOLD, 12));
		    botones[i].setFocusable(false);
		    botones[i].setBackground(new Color(217,217,217));
		    botones[i].addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            quitarComponentes();
		            switch (index) {
		            case 0:
		            	menuPrincipal();
		            	break;
		                case 1:
		                    clientes();
		                    break;
		                case 2:
		                    tarifas();
		                    break;
		                case 3:
		                    instructor();
		                    break;
		                case 4:
		                    clases();
		                    break;
		                case 5:
		                    checador();
		                    break;
		                case 6:
		                	dispose();
		                	Inicio i1 = new Inicio();
		        		    i1.setVisible(true);
		                default:
		                    break;
		            }
		        }
		    });
		    panelBar.add(botones[i]);
		}
	}

	public void quitarComponentes() {
		contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
	}
	
	public void clientes()  { // Clientes registrados
		menuVerticalClientes();
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Clientes registrados");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(542, 114, 276, 33);
		panel.add(lblTitutlo);
		
		String titles[]= {"ID", "Nombre", "Apellido", "Correo", "Teléfono", "Fecha de ingreso", "Tipo de membresía", "Estado"};
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
		btnFiltro.setBounds(943, 120, 187, 30);
		panel.add(btnFiltro);
	}

	public void botonesDetallesClientes(JButton btn) {
		btn.setFocusable(false);
	    btn.setForeground(new Color(255, 255, 255));
	    btn.setBackground(new Color(116, 116, 116));
	    btn.setFont(new Font("Arial Black", Font.PLAIN, 13));
	}
	
	public void configurarLabels(JLabel lbl) { // configurar Labels al centro
		lbl.setForeground(new Color(0, 0, 0));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Arial Black", Font.PLAIN, 14));
	}
	
	public void configurarLabelsIzq(JLabel lbl) { // Configurar Labels a la izquierda  
		lbl.setForeground(new Color(0, 0, 0));
		lbl.setHorizontalAlignment(SwingConstants.LEFT);
		lbl.setFont(new Font("Arial Black", Font.PLAIN, 14));
	}
	
	public void configurarLabelsDer(JLabel lbl) { // Configurar Labels a la derecha  
		lbl.setForeground(new Color(0, 0, 0));
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
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
	   
	    textField = new JTextField(); // TEXTFIELD VACIO
	    textField.setBounds(170, 80, 0, 0);
	    panel.add(textField);
	    
	    int[] boundsX = {222, 405, 588, 771, 954};
	    JButton[] botones = new JButton[5];
		// Crear y configurar los botones
		String[] nombres = {"Información","Historial de pago", "Historial de asistencias", "Descargar reporte", "Credencial"};
		panelInfo = new JPanel();
	    panelInfo.setBackground(new Color(217, 217, 217));
	    panelInfo.setBounds(222, 290, 915, 350);
	    panelInfo.setVisible(false);
	    panel.add(panelInfo);
	    panelInfo.setLayout(null);
		for (int i = 0; i < botones.length; i++) {
		    final int index = i; 
		    botones[i] = new JButton(nombres[i]);
		    botones[i].setForeground(Color.black);
		    botones[i].setFocusable(false);
		    botones[i].setEnabled(false);  
		    botones[i].setBackground(new Color(174,174,174));
		    botones[i].setBounds(boundsX[i],250, 183, 40);
		    botones[i].addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	 panelInfo.removeAll();
		        	 panelInfo.repaint();
		        	 panelInfo.revalidate();
		        	 panelInfo.setVisible(true);
		             // Resetear el color de todos los botones
		             for (JButton boton : botones) {
		                 boton.setBackground(new Color(174, 174, 174));
		             }
		             // Establecer el color del botón actual
		             botones[index].setBackground(new Color(217, 217, 217));
		             switch (index) {
		            	case 0:
		            		detallesInformacion();
		            	break;
		                case 1:
		                	detallesHistorialPago();
		                    break;
		                case 2:
		                	detallesHistorialAsistencia();
		                    break;
		                case 3:
		                	detallesInformacion();
		                	JOptionPane.showMessageDialog(null, "¡Descarga exitosa!", "", JOptionPane.INFORMATION_MESSAGE);        
		                	break;
		                case 4:
		                	credencialCliente();
		                    break;
		                default:
		                    break;
		            }
		            panelInfo.repaint();
		            panelInfo.revalidate();
		        }
		    });
		    panel.add(botones[i]);
		}
		
		btnBuscar = new JButton("");
	    btnBuscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	            // Habilitar los botones cuando se presiona el botón Buscar
	            for (JButton boton : botones) {
	                boton.setEnabled(true);
	            }
	        }
	    });
	    btnBuscar.setFocusable(false);
	    btnBuscar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnBuscar.setIcon(new ImageIcon(Sistema.class.getResource("/img/buscar.png")));
	    btnBuscar.setBackground(new Color(217, 217, 217)); 
	    btnBuscar.setBounds(783, 172, 50, 50);
	    panel.add(btnBuscar);
	    
		btnReporte = new JButton("Renovar suscripción");
	    btnReporte.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		renovar();         
            }
	    });
	    btnReporte.setForeground(Color.white);
	    btnReporte.setFocusable(false);
	    btnReporte.setBackground(colorBtnGuardar);
	    btnReporte.setBounds(954, 177, 183, 40);
	    panel.add(btnReporte);
	    
	}
	
	public void panelDetalles() {
	    JLabel id = new JLabel("ID del cliente: ");
	    configurarLabelsIzq(id);
	    panelInfo.add(id);
	    id.setBounds(87, 20, 500, 20);
	    
	    JLabel nombre = new JLabel("Nombre: ");
	    configurarLabelsIzq(nombre);
	    panelInfo.add(nombre);
	    nombre.setBounds(600, 20, 300, 20);
	}
	
	public void detallesInformacion() {

	    JLabel lblPlanDeLa = new JLabel("Plan de la membresía:");configurarLabelsIzq(lblPlanDeLa);
		 lblPlanDeLa.setBounds(80, 180, 200, 20);
		 panelInfo.add(lblPlanDeLa);
		 
		 JLabel lblNombre = new JLabel("Nombre: ");configurarLabelsIzq(lblNombre);
		 lblNombre.setBounds(80, 20, 200, 20);
		 panelInfo.add(lblNombre);
		 
		 JLabel id_1 = new JLabel("Correo electrónico:"); configurarLabelsIzq(id_1);
		 id_1.setBounds(80, 100, 200, 20);
		 panelInfo.add(id_1);
		 
		 JLabel lblTel = new JLabel("Teléfono:");configurarLabelsIzq(lblTel);
		 configurarLabelsIzq(lblTel);
		 lblTel.setBounds(400, 100, 200, 20);
		 panelInfo.add(lblTel);
		 
		 JLabel lblApellido = new JLabel("Apellido:");
		 configurarLabelsIzq(lblApellido);
		 lblApellido.setBounds(400, 20, 200, 20);
		 panelInfo.add(lblApellido);
		 
		 JLabel lblTipoDeLa = new JLabel("Tipo de la membresía:");
		 configurarLabelsIzq(lblTipoDeLa);
		 lblTipoDeLa.setBounds(400, 180, 200, 20);
		 panelInfo.add(lblTipoDeLa);
		 
		 JLabel lblFechaInicial = new JLabel("Fecha inicial: ");
		 configurarLabelsIzq(lblFechaInicial);
		 lblFechaInicial.setBounds(80, 260, 200, 20);
		 panelInfo.add(lblFechaInicial);
		 
		 JLabel lblFechaFinal = new JLabel("Fecha final:");
		 configurarLabelsIzq(lblFechaFinal);
		 lblFechaFinal.setBounds(400, 260, 200, 20);
		 panelInfo.add(lblFechaFinal);
		 
		 lblNewLabel = new JLabel();
		 lblNewLabel.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 1.png")));
	     lblNewLabel.setBounds(650, 20, 217, 218);
	     panelInfo.add(lblNewLabel);
		
	}
	
	public void detallesHistorialPago() { // HistorialPago clientes
		panelDetalles();
	    lblMembresia = new JLabel("Suscripción: " );
	    configurarLabelsIzq(lblMembresia);lblMembresia.setBounds(87, 55, 200, 20);
	    panelInfo.add(lblMembresia);
	    
	    String titles[]= {"Membresía", "Fecha inicial", "Vencimiento", "Total"};
		DefaultTableModel modelo = new DefaultTableModel(null, titles) {
            @Override
            public boolean isCellEditable(int row, int column) {	              
                return false; //La tabla no se edita
            }
	     };
		JTable datosTabla = new JTable(modelo);
		JScrollPane tablaScroll = new JScrollPane(datosTabla);
		tablaScroll.setBounds(87, 95, 730, 200);
		panelInfo.add(tablaScroll);
	}
	
	public void detallesHistorialAsistencia() { //HistorialAsistencia clientes
		panelDetalles();
		String titulo[]= {"Fecha", "Hora de entrada", "Hora de salida"};
		DefaultTableModel modelo2 = new DefaultTableModel(null, titulo) {
		    @Override
		    public boolean isCellEditable(int row, int column) {	              
		        return false; //La tabla no se edita
		  }
		}; 
		JTable datosTabla2 = new JTable(modelo2);
		JScrollPane tablaScroll2 = new JScrollPane(datosTabla2);
		tablaScroll2.setBounds(87, 95, 730, 210);
        panelInfo.add(tablaScroll2);
       
       comboMes = new JComboBox<>();
       String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
       for (String month : months) {
           comboMes.addItem(month);
       }
       comboMes.setBounds(600, 60, 215, 25);
       comboMes.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
           //    cargarAsistencias((String) monthComboBox.getSelectedItem());
           }
       });
       panelInfo.add(comboMes); 
	   JLabel lblAsistenciasTotales = new JLabel("Asistencias totales:");
	   configurarLabelsIzq(lblAsistenciasTotales); lblAsistenciasTotales.setBounds(87, 55, 237, 25);
	   panelInfo.add(lblAsistenciasTotales);
	
	}
	public void renovar() {
		// Crear una nueva ventana para editar la clase 
        JFrame renovar = new JFrame("Renovar membresía");
        renovar.setSize(550, 500);
        renovar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        renovar.setVisible(true);
        JPanel panelEditar = new JPanel(); panelEditar.setLayout(null);
        panelEditar.setBounds(0,0,550,500); renovar.getContentPane().add(panelEditar);
        JLabel titulo = new JLabel("Renovar membresía"); titulo.setBounds(0, 35, 550, 20); panelEditar.add(titulo); configurarLabels(titulo);
        JLabel id = new JLabel("Cliente ID:"); id.setBounds(70, 85, 150, 20); panelEditar.add(id); configurarLabelsIzq(id);
        JLabel newHorario = new JLabel ("Tipo de membresía: "); newHorario.setBounds(70,165,200,20); panelEditar.add(newHorario); configurarLabelsIzq(newHorario);
        String[] tipo = {"General", "Estudiante", "Familiar", "Dúo"  };
	    JComboBox<String> comboTipo = new JComboBox<>(tipo);
        comboTipo.setBounds(70, 205, 170, 30);
	    panelEditar.add(comboTipo);
	    String[] plan = {"1 mes", "3 meses", "6 meses", "1 año"  };
	    JComboBox<String> comboPlan = new JComboBox<>(plan);
        comboPlan.setBounds(287, 205, 170, 30);
	    panelEditar.add(comboPlan);
	    String[] metodo = {"Efectivo", "Visa", "Cheque" };
	    JComboBox<String> comboMetodo = new JComboBox<>(metodo);
        comboMetodo.setBounds(70, 295, 170, 30);
	    panelEditar.add(comboMetodo);
	    JButton btnG = new JButton("Renovar"); btnG.setFocusable(false); panelEditar.add(btnG); btnG.setBounds(110,380,150,30); 
	    btnG.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				renovar.dispose();
				ticket();
			}
	    });
	    JButton btnCancelar = new JButton("Cancelar"); btnCancelar.setFocusable(false); panelEditar.add(btnCancelar); btnCancelar.setBounds(290,380,150,30);
	    btnCancelar.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				renovar.dispose();
			}
	    });
 
		 JLabel lblMtodoDePago = new JLabel("Método de pago: ");configurarLabelsIzq(lblMtodoDePago);
		 lblMtodoDePago.setBounds(70, 255, 200, 20);
		 panelEditar.add(lblMtodoDePago);
		 
		 JLabel lblPlanDeLa = new JLabel("Plan de la membresía:");configurarLabelsIzq(lblPlanDeLa);
		 lblPlanDeLa.setBounds(287, 165, 200, 20);
		 panelEditar.add(lblPlanDeLa);
		 
		 JLabel lblNombre = new JLabel("Nombre: ");configurarLabelsIzq(lblNombre);
		 lblNombre.setBounds(287, 85, 200, 20);
		 panelEditar.add(lblNombre);
		 
		 JLabel id_1 = new JLabel("00000"); configurarLabelsIzq(id_1);
		 id_1.setBounds(70, 125, 150, 20);
		 panelEditar.add(id_1);
		 
		 JLabel lblName = new JLabel("aaaa");configurarLabelsIzq(lblName);
		 lblName.setBounds(287, 125, 200, 20);
		 panelEditar.add(lblName);
	     renovar.setLocationRelativeTo(null); 
	}
	
	public void ticket() {
		JFrame ticket = new JFrame("");
        ticket.setSize(400, 500);
        ticket.setLocationRelativeTo(null);
        ticket.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ticket.setVisible(true);
        
        JPanel panelEditar = new JPanel(); panelEditar.setLayout(null);
        panelEditar.setBounds(0,0,400,500); ticket.getContentPane().add(panelEditar);
 
		JLabel lblNewLabel = new JLabel("<html>Larry's Gym<br>5.0 / Fitness Club<br> Cliente: <br>Vendedor: <br>Tipo de membresía: <br>Valor de la membresía: <br><br><br>Fecha inicial: <br>Fecha de vencimiento: <br><br><br>Monto total     MXN   <br>Pago realizado en <br>Cambio</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(50, -30, 300, 450);
		panelEditar.add(lblNewLabel);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFocusable(false);
		btnImprimir.setBounds(150,400,100,20); panelEditar.add(btnImprimir); btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Impresión exitosa", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				ticket.dispose();
			}
			
		});
	}

	public void tarifas() {
	    panel();
	    menuB();
	    JPanel panel_1 = new JPanel();
	    panel_1.setBounds(36, 170, 1120, 477);
	    panel.add(panel_1);
	    panel_1.setLayout(new GridLayout(0, 3, 15, 15));

	    JButton btnChecador = new JButton("Nueva membresía");
        btnChecador.setForeground(Color.WHITE);
        btnChecador.setFocusable(false);
        btnChecador.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		nuevaTarifa();
	    	}
	    });
        btnChecador.setBackground(colorBtnGuardar);
        btnChecador.setBounds(880, 114, 200, 30);
        panel.add(btnChecador);
        
        ArrayList<String> tiposDePlan = new ArrayList<>();
        tiposDePlan.add("Plan general");
        tiposDePlan.add("Plan familiar");
        tiposDePlan.add("Plan estudiante");
        tiposDePlan.add("Plan dúo");
        tiposDePlan.add("Plan visitante");

        ArrayList<String> detallesDePlan = new ArrayList<>();
        detallesDePlan.add("<br>Plan Estándar - $399/mes<br>$1,077/3Meses<br>$2,394/6Meses<br>$4788/1Año");
        detallesDePlan.add("<br>Plan Estándar - $799/mes<br>$2,097/3Meses<br>$4,194/6Meses<br>$8,388/1Año");
        detallesDePlan.add("<br>Plan Estándar - $599/mes<br>$1,797/3Meses<br>$3,594/6Meses<br>$7,188/1Año");
        detallesDePlan.add("<br>Plan Estándar - $299/mes<br>$897/3Meses<br>$1,794/6Meses<br>$3,588/1Año");
        detallesDePlan.add("<br>Plan Visitante - $50/Día");

        ArrayList<String> infoPlan = new ArrayList<>();
        infoPlan.add("Plan general\n" +
                "Plan Estándar - $399/mes:\n"
                + "- Acceso ilimitado a todas las instalaciones del gimnasio.\n"
                + "- Horario flexible: acceso al gimnasio 24/7.\n"
                + "- Soporte continuo y atención al cliente.\n\n"
                + "Plan Estándar - Trimestral ($1,077):\n"
                + "- Disfruta de 3 meses de acceso ilimitado.\n"
                + "- Ahorro frente al plan mensual individual.\n"
                + "- ¡Perfecto para comprometerse por un periodo corto!\n\n"
                + "Plan Estándar - 6 Meses ($2,394):\n"
                + "- Disfruta de 6 meses de acceso ilimitado.\n"
                + "- Mayor ahorro al elegir un plan de medio año.\n"
                + "- ¡Mantente en forma durante medio año!\n\n"
                + "Plan Estándar - Anual ($4,788):\n"
                + "- Disfruta de un año completo de acceso ilimitado.\n"
                + "- Elige el plan más largo para la experiencia completa.\n"
                + "- ¡Aprovecha el mayor ahorro y logra tus objetivos de fitness a largo plazo!");
        infoPlan.add("Plan Familiar\n"
                + "Plan Familiar - $699/mes:\n"
                + "- Acceso ilimitado a todas las instalaciones del gimnasio para 3 personas.\n"
                + "- Horario flexible: acceso al gimnasio 24/7.\n"
                + "- Soporte continuo y atención al cliente.\n\n"
                + "Plan Familiar - Trimestral ($2,097):\n"
                + "- Disfruta de 3 meses de acceso ilimitado para 3 personas.\n"
                + "- Ahorro frente al plan mensual individual.\n"
                + "- ¡Perfecto para familias que buscan un compromiso a corto plazo!\n\n"
                + "Plan Familiar - 6 Meses ($4,194):\n"
                + "- Disfruta de 6 meses de acceso ilimitado para 3 personas.\n"
                + "- Mayor ahorro al elegir un plan de medio año.\n"
                + "- ¡Mantente en forma con tu familia durante medio año!\n\n"
                + "Plan Familiar - Anual ($8,388):\n"
                + "- Disfruta de un año completo de acceso ilimitado para 3 personas.\n"
                + "- Elige el plan más largo para disfrutar de la experiencia completa.\n"
                + "- ¡Aprovecha el mayor ahorro y motiva a tu familia a lograr sus objetivos de fitness a largo plazo!");
        infoPlan.add("Plan Estudiante\n" +
                "Plan Estudiante - $299/mes:\n" +
                "- Acceso ilimitado a todas las instalaciones del gimnasio.\n" +
                "- Horario flexible: acceso al gimnasio 24/7.\n" +
                "- Soporte continuo y atención al cliente.\n\n" +
                "Plan Estudiante - Trimestral ($897):\n" +
                "- Disfruta de 3 meses de acceso ilimitado.\n" +
                "- Ahorro frente al plan mensual individual.\n" +
                "- ¡Ideal para estudiantes comprometidos con sus objetivos de fitness!\n\n" +
                "Plan Estudiante - 6 Meses ($1,794):\n" +
                "- Disfruta de 6 meses de acceso ilimitado.\n" +
                "- Mayor ahorro al elegir un plan de medio año.\n" +
                "- ¡Mantente en forma durante medio año mientras estudias!\n\n" +
                "Plan Estudiante - Anual ($3,588):\n" +
                "- Disfruta de un año completo de acceso ilimitado.\n" +
                "- Elige el plan más largo para la experiencia completa.\n" +
                "- ¡Aprovecha el mayor ahorro y mantente en forma durante todo el año académico!");
        infoPlan.add("Plan Dúo\n"
                + "Plan Dúo - $599/mes:\n"
                + "- Acceso ilimitado a todas las instalaciones del gimnasio para 3 personas.\n"
                + "- Horario flexible: acceso al gimnasio 24/7.\n"
                + "- Soporte continuo y atención al cliente.\n\n"
                + "Plan Dúo - Trimestral ($1,797):\n"
                + "- Disfruta de 3 meses de acceso ilimitado para 3 personas.\n"
                + "- Ahorro frente al plan mensual individual.\n\n"
                + "Plan Dúo - 6 Meses ($3,594):\n"
                + "- Disfruta de 6 meses de acceso ilimitado para 3 personas.\n"
                + "- Mayor ahorro al elegir un plan de medio año.\n"
                + "Plan Dúo - Anual ($7,188):\n"
                + "- Disfruta de un año completo de acceso ilimitado para 3 personas.\n"
                + "- Elige el plan más largo para disfrutar de la experiencia completa.\n");
        infoPlan.add("Plan Visitante\n"
                + "Plan Visitante - $50/día:\n"
                + "- Acceso ilimitado a todas las instalaciones del gimnasio durante un día.\n"
                + "- Acceso a clases grupales durante el día de visita.\n"
                + "- Soporte continuo y atención al cliente durante la estancia.\n"
                + "- ¡Perfecto para probar nuestras instalaciones o disfrutar de un día de fitness!");

	    for (int i = 0; i < tiposDePlan.size(); i++) {
	    	int index = i;
	        JPanel panelTarifa = new JPanel(new BorderLayout());
	        JLabel info = new JLabel("<html><div style='text-align: center;'>" + tiposDePlan.get(i) + "<br>" + detallesDePlan.get(i) + "</div></html>");
	        info.setFont(new Font("Arial Black", Font.PLAIN, 16));
	        info.setBorder(BorderFactory.createLineBorder(Color.black, 3));
	        info.setHorizontalAlignment(SwingConstants.CENTER);
	        info.setVerticalAlignment(SwingConstants.CENTER);
	        info.setOpaque(true);
	        info.setForeground(Color.black);
	        info.setBackground(new Color(148, 182, 223));
	        panelTarifa.add(info, BorderLayout.CENTER);

	        JButton btnDetalles = new JButton("Detalles");
	        btnDetalles.setBackground(Color.black);
	        btnDetalles.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		JOptionPane.showMessageDialog(null, infoPlan.get(index));
		    	}
		    });
	        btnDetalles.setForeground(Color.white);
	        btnDetalles.setFocusable(false);
	        btnDetalles.setBorder(null);

	        JButton btnEditar = new JButton("Editar");
	        btnEditar.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		quitarComponentes();
		    		editarTarifa();
		    	}
		    });
	        btnEditar.setForeground(Color.white);
	        btnEditar.setFocusable(false);
	        btnEditar.setBorder(null);
	        btnEditar.setBackground(colorBtnGuardar);

	        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 5, 5));
	        panelBotones.add(btnDetalles);
	        panelBotones.add(btnEditar);
	        panelBotones.setBackground(new Color(119, 182, 255));
	        panelTarifa.add(panelBotones, BorderLayout.SOUTH);

	        panel_1.add(panelTarifa);
	    }

	    JLabel lblTitutlo = new JLabel("Tarifas");
	    lblTitutlo.setForeground(new Color(0, 0, 0));
	    lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
	    lblTitutlo.setBounds(427, 114, 346, 33);
	    panel.add(lblTitutlo);
	    
	    
	}

	public void editarTarifa() {
		panel();
		menuB();
		elementosEditarNuevaTarifas();
		JLabel lblTitutlo = new JLabel("Editar tarifa");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		
		JPanel panelAzul = new JPanel();
		panelAzul.setBackground(new Color(148, 182, 223));
		panelAzul.setBounds(650, 185, 441, 448);
		panel.add(panelAzul);
		panelAzul.setLayout(null);

		JButton btnGuardar_1 = new JButton("Guardar");
		btnGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "¡Nuevos cambios realizados con éxito!", "Edición exitosa", JOptionPane.INFORMATION_MESSAGE);
                quitarComponentes();
                tarifas();
			}
		});
		btnGuardar_1.setForeground(Color.WHITE);
		btnGuardar_1.setFocusable(false);
		btnGuardar_1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnGuardar_1.setBackground(colorBtnGuardar);
		btnGuardar_1.setBounds(194, 593, 120, 40);
		panel.add(btnGuardar_1);
		
		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta tarifa?", "Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);
	             if (op == JOptionPane.OK_OPTION) {
	                 JOptionPane.showMessageDialog(null, "Tarifa eliminada con éxito", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
	                 quitarComponentes();
	                 tarifas();
	             }
			}
		});
		btnEliminar_1.setForeground(Color.white);
		btnEliminar_1.setFocusable(false);
		btnEliminar_1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnEliminar_1.setBackground(colorBtnEliminar);
		btnEliminar_1.setBounds(345, 593, 120, 40);
		panel.add(btnEliminar_1);  
	}
	
	public void elementosEditarNuevaTarifas() {
		btnVolver=new JButton("Volver");
	    btnVolver.setForeground(new Color(255, 255, 255));
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		tarifas();
	    	}
	    });
	    btnVolver.setFocusable(false);
	    btnVolver.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnVolver.setBackground(new Color(0,0,0)); 
	    btnVolver.setBounds(109, 114, 120, 40);
	    panel.add(btnVolver);
	    
	    JLabel lblNombrePlan = new JLabel("Nombre del plan:");
		lblNombrePlan.setBounds(109, 185, 189, 30);
		configurarLabelsDer(lblNombrePlan);
		panel.add(lblNombrePlan);
		
		JLabel lblPrecioMensual = new JLabel("Precio mensual:");
		lblPrecioMensual.setBounds(109, 235, 189, 30); configurarLabelsDer(lblPrecioMensual);
		panel.add(lblPrecioMensual);
		
		JLabel lblDescuentoMeses = new JLabel("Descuento 3 meses:");
		lblDescuentoMeses.setBounds(109, 285, 189, 30); configurarLabelsDer(lblDescuentoMeses);
		panel.add(lblDescuentoMeses);
		
		JLabel lblDescuentoMeses_1 = new JLabel("Descuento 6 meses:");
		lblDescuentoMeses_1.setBounds(109, 335, 189, 30); configurarLabelsDer(lblDescuentoMeses_1);
		panel.add(lblDescuentoMeses_1);
		
		JLabel lblDescuentoAo = new JLabel("Descuento 1 año:");
		lblDescuentoAo.setBounds(109, 385, 189, 30); configurarLabelsDer(lblDescuentoAo);
		panel.add(lblDescuentoAo);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(410, 284, 50, 30); configurarLabelsIzq(lblTotal);
		panel.add(lblTotal);
		
		JLabel lblTotal_1 = new JLabel("Total:");
		lblTotal_1.setBounds(410, 334, 50, 30); configurarLabelsIzq(lblTotal_1);
		panel.add(lblTotal_1);
		
		JLabel lblTotal_1_1 = new JLabel("Total:");
		lblTotal_1_1.setBounds(410, 384, 50, 30); configurarLabelsIzq(lblTotal_1_1);
		panel.add(lblTotal_1_1);
		
		JCheckBox box1 = new JCheckBox("Acceso ilimitado a las instalaciones del gimnasio.");
		box1.setFont(new Font("Arial Black", Font.PLAIN, 14));
		box1.setOpaque(false);
		box1.setFocusable(false);
		box1.setBounds(109, 445, 513, 20);
		panel.add(box1);
		
		JCheckBox box2 = new JCheckBox("Horario flexible, acceso al gimnasio 24/7.");
		box2.setFont(new Font("Arial Black", Font.PLAIN, 14));
		box2.setOpaque(false);
		box2.setFocusable(false);
		box2.setBounds(109, 485, 513, 20);
		panel.add(box2);
		
		JCheckBox box3 = new JCheckBox("Entrenador personal.");
		box3.setFont(new Font("Arial Black", Font.PLAIN, 14));
		box3.setOpaque(false);
		box3.setFocusable(false);
		box3.setBounds(109, 525, 513, 20);
		panel.add(box3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(320, 185, 230, 30);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(320, 235, 230, 30);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(320, 285, 70, 30);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(320, 335, 70, 30);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(320, 385, 70, 30);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(480, 285, 70, 30);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(480, 335, 70, 30);
		panel.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(480, 385, 70, 30);
		panel.add(textField_8);
	
	}
	public void nuevaTarifa() {
		panel();
		menuB();
		elementosEditarNuevaTarifas();
		JLabel lblTitutlo = new JLabel("Nueva tarifa");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		
		JButton btnGuardar_1 = new JButton("Guardar");
		btnGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "¡Nueva tarifa agregada correctamente!", "Añadido exitoso", JOptionPane.INFORMATION_MESSAGE);
                quitarComponentes();
                tarifas();
			}
		});
		btnGuardar_1.setForeground(Color.WHITE);
		btnGuardar_1.setFocusable(false);
		btnGuardar_1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnGuardar_1.setBackground(colorBtnGuardar);
		btnGuardar_1.setBounds(465, 593, 120, 40);
		panel.add(btnGuardar_1);
		
		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta tarifa?", "Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);
	             if (op == JOptionPane.OK_OPTION) {
	                 JOptionPane.showMessageDialog(null, "Tarifa eliminada con éxito", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
	                 quitarComponentes();
	                 nuevaTarifa();
	             }
			}
		});
		btnEliminar_1.setForeground(Color.WHITE);
		btnEliminar_1.setFocusable(false);
		btnEliminar_1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnEliminar_1.setBackground(colorBtnEliminar);
		btnEliminar_1.setBounds(615, 593, 120, 40);
		panel.add(btnEliminar_1);
		
		JLabel lblMesesInfo = new JLabel("3 meses info:");
		lblMesesInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMesesInfo.setForeground(Color.BLACK);
		lblMesesInfo.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblMesesInfo.setBounds(550, 185, 189, 30);
		panel.add(lblMesesInfo);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea.setBounds(761, 185, 230, 80);
		panel.add(textArea);
		
		JLabel lblMesesInfo_3 = new JLabel("6 meses info:");
		lblMesesInfo_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMesesInfo_3.setForeground(Color.BLACK);
		lblMesesInfo_3.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblMesesInfo_3.setBounds(550, 285, 189, 30);
		panel.add(lblMesesInfo_3);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea_1.setBounds(761, 285, 230, 80);
		panel.add(textArea_1);
		
		JLabel lblAoInfo = new JLabel("1 año info:");
		lblAoInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAoInfo.setForeground(Color.BLACK);
		lblAoInfo.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblAoInfo.setBounds(550, 385, 189, 30);
		panel.add(lblAoInfo);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea_2.setBounds(761, 385, 230, 80);
		panel.add(textArea_2);
		
	}
	public void instructor() { // TABLA INSTRUCTOR
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Instructores registrados");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		editarInstructor();
	    	}
	    });
		btnEditar.setFocusable(false);
		btnEditar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnEditar.setBackground(new Color(0, 0, 0)); 
		btnEditar.setBounds(739, 190, 120, 40);
	    panel.add(btnEditar);
	    
	    String titles[]= {"ID", "Nombre", "Apellido", "Correo", "Telefono", "Fecha de contratación", "Especialidad"};
		DefaultTableModel modelo = new DefaultTableModel(null, titles) {
            @Override
            public boolean isCellEditable(int row, int column) {	              
                return false; //La tabla no se edita
            }
	     };
	     JTable datosTabla = new JTable(modelo);
	     JScrollPane tablaScroll = new JScrollPane(datosTabla);
	     tablaScroll.setBounds(200,250,800,350);
	     panel.add(tablaScroll);
	     
	     btnEditar = new JButton("Detalles");
	     btnEditar.setForeground(new Color(255, 255, 255)); 
	     btnEditar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		detallesInstructor();
	    	}
	     });
	     btnEditar.setFocusable(false);
	     btnEditar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	     btnEditar.setBackground(new Color(0, 45, 78)); 
	     btnEditar.setBounds(885, 190, 115, 40);
	     panel.add(btnEditar);
	     
	     JButton btnGuardar_1 = new JButton("Nuevo instructor");
	     btnGuardar_1.setForeground(Color.WHITE);
	     btnGuardar_1.setFocusable(false);
	     btnGuardar_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		quitarComponentes();
		    		nuevoInstructor();
		    	}
		    });
	     btnGuardar_1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	     btnGuardar_1.setBackground(new Color(0, 45, 78));
	     btnGuardar_1.setBounds(593, 190, 120, 40);
	     panel.add(btnGuardar_1);
	    
	}
	
	public void detallesInstructor() {
		panel(); 
		menuB();
		JLabel lblTitutlo = new JLabel("Detalles de instructor");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		
		btnGuardar = new JButton("Eliminar");
	    btnGuardar.setForeground(new Color(255,255,255));
	    btnGuardar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int op = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este instructor?", "Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);
	             if (op == JOptionPane.OK_OPTION) {
	                 JOptionPane.showMessageDialog(null, "Instructor eliminado con éxito", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
	                 quitarComponentes();
	                 instructor();
	             }
	    	}
	    });
	    btnGuardar.setFocusable(false);
	    btnGuardar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnGuardar.setBackground(colorBtnEliminar); 
	    btnGuardar.setBounds(937, 114, 120, 40);
	    panel.add(btnGuardar);
	    
	    btnVolver=new JButton("Volver");
	    btnVolver.setForeground(new Color(255, 255, 255));
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		instructor();
	    	}
	    });
	    btnVolver.setFocusable(false);
	    btnVolver.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnVolver.setBackground(Color.black); 
	    btnVolver.setBounds(142, 114, 120, 40);
	    panel.add(btnVolver);
	    
	    panelCredencial = new JPanel();
	    panelCredencial.setBackground(new Color(217, 217, 217));
	    panelCredencial.setBounds(142, 256, 915, 310);
	    panel.add(panelCredencial);
	    panelCredencial.setLayout(null);
	    
	    lblPersona = new JLabel();
	    lblPersona.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 1.png")));
	    lblPersona.setBounds(36, 23, 217, 218);
	    panelCredencial.add(lblPersona);
	    
	    lblCodigo = new JLabel();
	    lblCodigo.setIcon(new ImageIcon(Sistema.class.getResource("/img/codigoDeBarras.png")));
	    lblCodigo.setBounds(299, 229, 327, 59);
	    panelCredencial.add(lblCodigo);

	    lblCorreoElectrnico = new JLabel("Correo electrónico: "+"Castillo2@gmail.com");
	    configurarLabels(lblCorreoElectrnico);lblCorreoElectrnico.setBounds(299, 124, 327, 20);
	    panelCredencial.add(lblCorreoElectrnico);
	    
	    lblPeterParker = new JLabel("Luis Castillo");
	    configurarLabels(lblPeterParker);lblPeterParker.setBounds(36, 260, 217, 20);
	    panelCredencial.add(lblPeterParker);
	    
	    btnHistorial = new JButton("Historial de clases");
	    btnHistorial.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		historialClases();
	    	}
	    	
	    });
	    botonesDetallesClientes(btnHistorial);
	    btnHistorial.setBounds(690, 60, 215, 50);
	    panelCredencial.add(btnHistorial);
	    
	    btnDescargarCredencial = new JButton("Descargar credencial");
	    btnDescargarCredencial.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 JOptionPane.showMessageDialog(null, "Credencial descargada correctamente", "Descarga exitosa", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    });
	    botonesDetallesClientes(btnDescargarCredencial);
	    btnDescargarCredencial.setBounds(690, 132, 215, 50);
	    panelCredencial.add(btnDescargarCredencial);
	    
	    btnReporte = new JButton("Descargar reporte");
	    btnReporte.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 JOptionPane.showMessageDialog(null, "Reporte descargado correctamente", "Descarga exitosa", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    });
	    botonesDetallesClientes(btnReporte);
	    btnReporte.setBounds(690, 204, 215, 50);
	    panelCredencial.add(btnReporte);
	    
	    JLabel lblEspecialidad = new JLabel("Instructor de levantamiento de pesas");
	    configurarLabels(lblEspecialidad);
	    lblEspecialidad.setBounds(299, 74, 327, 20);
	    panelCredencial.add(lblEspecialidad);
	    
	    JLabel lblFechaDeContratacin = new JLabel("Fecha de contratación: 21/07/2022");
	    lblFechaDeContratacin.setBounds(299, 174, 327, 20);
	    configurarLabels(lblFechaDeContratacin);
	    panelCredencial.add(lblFechaDeContratacin);
	}

	public void historialClases() {
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Historial de clases");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		
		panelCrear = new JPanel();
	    panel.add(panelCrear);
	    panelCrear.setBackground(new Color(217, 217, 217));
	    panelCrear.setLayout(null);
	    panelCrear.setBounds(142, 100, 915, 550);
		String[] columnas = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        String[][] datos = {
            {"6:00 - 7:00", "", "", "", "", ""},
            {"7:00 - 8:00", "", "", "", "", ""},
            {"8:00 - 9:00", "", "", "", "", ""},
            {"9:00 - 10:00", "", "", "", "", ""},
            {"11:00 - 12:00", "", "", "", "", ""},
            {"12:00 - 13:00", "", "", "", "", ""},
            {"13:00 - 14:00", "", "", "", "", ""},
            {"14:00 - 15:00", "", "", "", "", ""},
            {"15:00 - 16:00", "", "", "", "", ""},
            {"16:00 - 17:00", "", "", "", "", ""},
            {"17:00 - 18:00", "", "", "", "", ""},
            {"18:00 - 19:00", "", "", "", "", ""},
            {"19:00 - 20:00", "", "", "", "", ""}
        };
        JTable tablaHorario = new JTable(datos, columnas);
        tablaHorario.setRowHeight(40);

        // Ajustar anchos de columnas
        for (int i = 0; i < columnas.length; i++) {
            if (i == 0) {
                tablaHorario.getColumnModel().getColumn(i).setPreferredWidth(100);
            } else {
                tablaHorario.getColumnModel().getColumn(i).setPreferredWidth(150);
            }
        }

        // Agregar tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaHorario);
        scrollPane.setBounds(80,130,755,372);
        panelCrear.add(scrollPane, BorderLayout.CENTER);
        
        btnVolver = new JButton("Volver");
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		detallesInstructor();
	    	}
	    });
	    btnVolver.setFocusable(false);
	    btnVolver.setForeground(Color.white);
	    btnVolver.setBackground(Color.black);
	    btnVolver.setFont(new Font("Arial Black", Font.PLAIN, 13));
	    btnVolver.setBounds(80, 45, 132, 40);
	    panelCrear.add(btnVolver);
	    
	    lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 2.png")));
		lblNewLabel.setBounds(727, 23, 83, 85);
		panelCrear.add(lblNewLabel);
		
		lblPeterParker = new JLabel("Usuario");
	    configurarLabels(lblPeterParker);lblPeterParker.setBounds(693, 105, 142, 20);
	    panelCrear.add(lblPeterParker);
	}
	
	public void nuevoInstructor() {
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Nuevo instructor");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		btnVolver = new JButton("Volver");
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		instructor(); // Regresa a instructores registrados
	    	}
	    });
	    btnVolver.setBackground(Color.black); btnVolver.setForeground(Color.white); btnVolver.setFocusable(false);
	    btnVolver.setBounds(142, 114, 120, 40);
	    panel.add(btnVolver);
	    
	    panelCrear = new JPanel();
	    panel.add(panelCrear);
	    panelCrear.setBackground(new Color(217, 217, 217));
	    panelCrear.setLayout(null);
	    panelCrear.setBounds(142, 194, 915, 456);
	    panel.add(panelCrear);
	    
	    lblTlefono = new JLabel("Teléfono:");
	    configurarLabelsIzq(lblTlefono);lblTlefono.setBounds(360, 119, 200, 20);
	    panelCrear.add(lblTlefono);
	    
	    lblCorreoElectrnico = new JLabel("Correo electrónico: ");
	    configurarLabelsIzq(lblCorreoElectrnico);lblCorreoElectrnico.setBounds(70, 215, 200, 20);
	    panelCrear.add(lblCorreoElectrnico);
	    
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
	    lblApellidos.setBounds(360, 33, 200, 20);
	    panelCrear.add(lblApellidos);
	    
	    textApellidos = new JTextField();
	    textApellidos.setColumns(10);
	    textApellidos.setBounds(360, 73, 200, 30);
	    panelCrear.add(textApellidos);
	    
	    textEmail = new JTextField();
	    textEmail.setColumns(10);
	    textEmail.setBounds(70, 255, 200, 30);
	    panelCrear.add(textEmail);
	    
	    textTel = new JTextField();
	    textTel.setColumns(10);
	    textTel.setBounds(360, 149, 200, 30);
	    panelCrear.add(textTel);
	    
	    
	    JLabel lblFechaInicial = new JLabel("Fecha de contratación:");
	    configurarLabelsIzq(lblFechaInicial);
	    lblFechaInicial.setBounds(360, 215, 200, 20);
	    panelCrear.add(lblFechaInicial);

	    JSpinner spinnerFechaIn = new JSpinner(new SpinnerDateModel());
	    JSpinner.DateEditor dateEditorFechaIn = new JSpinner.DateEditor(spinnerFechaIn, "dd/MM/yyyy");
	    spinnerFechaIn.setEditor(dateEditorFechaIn);
	    spinnerFechaIn.setBounds(360, 250, 200, 30);
	    panelCrear.add(spinnerFechaIn);

	    JButton btnFoto = new JButton("Subir foto");
	    btnFoto.setForeground(new Color(255, 255, 255));
	    btnFoto.setFocusable(false);
	    btnFoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				subirFoto();
				 if (selectedFile != null) {
	                    lblFoto.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
	                    revalidate(); repaint();
	              }
			}
	    });
	    btnFoto.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnFoto.setBackground(new Color(89, 89, 89));
	    btnFoto.setBounds(652, 270, 207, 40);
	    panelCrear.add(btnFoto);
	    
	    lblFoto = new JLabel("");
	    lblFoto.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 1.png")));
	    lblFoto.setBounds(642, 33, 217, 221);
	    panelCrear.add(lblFoto);
	    
	    JComboBox comboEspecialidad = new JComboBox();
	    comboEspecialidad.setModel(new DefaultComboBoxModel(new String[] {"Levantamiento de pesas", "Aeróbic","Gimnasia de mantenimiento", "Circuito de entrenamiento"}));
	    comboEspecialidad.setBounds(70, 153, 200, 30);
	    panelCrear.add(comboEspecialidad);
	    
	    lblEspec = new JLabel("Especialidad:");
	    configurarLabelsIzq(lblEspec);
	    lblEspec.setBounds(70, 119, 200, 20);
	    panelCrear.add(lblEspec);
	    
	    btnPagar = new JButton("Añadir");
	    btnPagar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		  quitarComponentes();
	    		  instructor(); //vuelve al panel de instructores registrados
	    		 JOptionPane.showMessageDialog(null, "¡Nuevo instructor agregado con exito!", " ", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    });
		btnPagar.setForeground(new Color(255, 255, 255));
		btnPagar.setFocusable(false);
		btnPagar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnPagar.setBackground(new Color(0, 45, 78)); 
		btnPagar.setBounds(382, 389, 150, 40);
	    panelCrear.add(btnPagar);  
	}
	
	public void editarInstructor() {
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Editar instructor");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		
	    panelCrear = new JPanel();
	    panel.add(panelCrear);
	    panelCrear.setBackground(new Color(217, 217, 217));
	    panelCrear.setLayout(null);
	    panelCrear.setBounds(142, 194, 915, 456);
	    panel.add(panelCrear);
	    
	    lblTlefono = new JLabel("Teléfono:");
	    configurarLabelsIzq(lblTlefono);lblTlefono.setBounds(360, 119, 200, 20);
	    panelCrear.add(lblTlefono);
	    
	    lblCorreoElectrnico = new JLabel("Correo electrónico: ");
	    configurarLabelsIzq(lblCorreoElectrnico);lblCorreoElectrnico.setBounds(70, 215, 200, 20);
	    panelCrear.add(lblCorreoElectrnico);
	    
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
	    lblApellidos.setBounds(360, 33, 200, 20);
	    panelCrear.add(lblApellidos);
	    
	    textApellidos = new JTextField();
	    textApellidos.setColumns(10);
	    textApellidos.setBounds(360, 73, 200, 30);
	    panelCrear.add(textApellidos);
	    
	    textEmail = new JTextField();
	    textEmail.setColumns(10);
	    textEmail.setBounds(70, 255, 200, 30);
	    panelCrear.add(textEmail);
	    
	    textTel = new JTextField();
	    textTel.setColumns(10);
	    textTel.setBounds(360, 149, 200, 30);
	    panelCrear.add(textTel);
	        
	    JLabel lblFechaInicial = new JLabel("Fecha de contratación:");
	    configurarLabelsIzq(lblFechaInicial);
	    lblFechaInicial.setBounds(360, 215, 200, 20);
	    panelCrear.add(lblFechaInicial);

	    JSpinner spinnerFechaIn = new JSpinner(new SpinnerDateModel());
	    JSpinner.DateEditor dateEditorFechaIn = new JSpinner.DateEditor(spinnerFechaIn, "dd/MM/yyyy");
	    spinnerFechaIn.setEditor(dateEditorFechaIn);
	    spinnerFechaIn.setBounds(360, 250, 200, 30);
	    panelCrear.add(spinnerFechaIn);

	    JButton btnFoto = new JButton("Subir foto");
	    btnFoto.setForeground(new Color(255, 255, 255));
	    btnFoto.setFocusable(false);
	    btnFoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				subirFoto();
				 if (selectedFile != null) {
	                    lblFoto.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
	                    revalidate(); repaint();
	              }
			}
	    });
	    btnFoto.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnFoto.setBackground(new Color(89, 89, 89));
	    btnFoto.setBounds(652, 270, 207, 40);
	    panelCrear.add(btnFoto);
	    
	    lblFoto = new JLabel("");
	    lblFoto.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 1.png")));
	    lblFoto.setBounds(642, 33, 217, 221);
	    panelCrear.add(lblFoto);
	    
	    JComboBox comboEspecialidad = new JComboBox();
	    comboEspecialidad.setModel(new DefaultComboBoxModel(new String[] {"Levantamiento de pesas", "Aeróbic","Gimnasia de mantenimiento", "Circuito de entrenamiento"}));
	    comboEspecialidad.setBounds(70, 153, 200, 30);
	    panelCrear.add(comboEspecialidad);
	    
	    lblEspec = new JLabel("Especialidad:");
	    configurarLabelsIzq(lblEspec);
	    lblEspec.setBounds(70, 119, 200, 20);
	    panelCrear.add(lblEspec);
	    
	    btnGuardar = new JButton("Guardar");
	    btnGuardar.setForeground(new Color(255, 255, 255));
	    btnGuardar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JOptionPane.showMessageDialog(null, "¡Cambios de instructor realizados con éxito!", "Edición exitosa", JOptionPane.INFORMATION_MESSAGE);
                quitarComponentes();
	    		instructor();
	    	}
	    });
	    btnGuardar.setFocusable(false);
	    btnGuardar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnGuardar.setBackground(new Color(0, 45, 78)); 
	    btnGuardar.setBounds(326, 380, 120, 40);
	    panelCrear.add(btnGuardar);
	    
	    btnCancelar = new JButton("Cancelar");
	    btnCancelar.setForeground(new Color(255, 255, 255));
	    btnCancelar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		instructor();
	    	}
	    });
	    btnCancelar.setFocusable(false);
	    btnCancelar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnCancelar.setBackground(new Color(0, 0, 0)); 
	    btnCancelar.setBounds(466, 380, 120, 40);
	    panelCrear.add(btnCancelar);
		
	}
	
	public void checador() {
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Checador");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		
		btnVolver=new JButton("Volver");
	    btnVolver.setForeground(new Color(255, 255, 255));
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		menuPrincipal();
	    	}
	    });
	    btnVolver.setFocusable(false);
	    btnVolver.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnVolver.setBackground(Color.black); 
	    btnVolver.setBounds(73, 114, 120, 40);
	    panel.add(btnVolver);
	    
	    Date fechaActual = new Date();   // Formatear la fecha actual
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);
        JLabel labelFecha = new JLabel("Fecha: " + fechaFormateada); // Crear un JLabel para mostrar la fecha
        labelFecha.setSize(150, 20);
        labelFecha.setLocation(258, 134);
        
        panel.add(labelFecha);
	    
	    String titles[]= {"ID", "Nombre", "Estado de la suscripción", "Hora de entrada", "Hora de salida"};
		DefaultTableModel modelo = new DefaultTableModel(null, titles) {
            @Override
            public boolean isCellEditable(int row, int column) {	              
                return false; //La tabla no se edita
            }
	     };
		JTable datosTabla = new JTable(modelo);
		JScrollPane tablaScroll = new JScrollPane(datosTabla);
		tablaScroll.setBounds(73,170,500,470);
		panel.add(tablaScroll);
		
		JLabel lblClientesActivos = new JLabel("Clientes activos: ");
		configurarLabelsIzq(lblClientesActivos);
		lblClientesActivos.setBounds(808, 140, 150, 20);
		panel.add(lblClientesActivos);
		
		JLabel lblInstruccion1 = new JLabel("Pase su credencial por el escáner, o ingrese su ID.");
		configurarLabelsIzq(lblInstruccion1);
		lblInstruccion1.setBounds(670, 170, 400, 20);
		panel.add(lblInstruccion1);
		
		JLabel lblIngresar = new JLabel("Ingresar ID:");
		configurarLabelsIzq(lblIngresar);
		lblIngresar.setBounds(733, 200, 100, 20);
		panel.add(lblIngresar);
	    
		JTextField textID = new JTextField("");
		textID.setBackground(new Color(217, 217, 217));
	    textID.setColumns(10);
	    textID.setForeground(Color.black);
	    textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textID.setBounds(833, 200, 150, 20);
	    panel.add(textID);
	    
	    btnBuscar = new JButton("");
	    btnBuscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
	    btnBuscar.setFocusable(false);
	    btnBuscar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnBuscar.setIcon(new ImageIcon(Sistema.class.getResource("/img/lupaChecador.png")));
	    btnBuscar.setBackground(new Color(217, 217, 217)); 
	    btnBuscar.setBounds(983, 200, 20, 20);
	    panel.add(btnBuscar);
	    
	    JLabel lblEscanear = new JLabel("");
	    lblEscanear.setIcon(new ImageIcon(Sistema.class.getResource("/img/escaneo-de-codigo-de-barras 1.png")));
	    lblEscanear.setBounds(777, 230, 206, 203);
	    panel.add(lblEscanear);
	    
	    JLabel lblMarco = new JLabel("");
	    lblMarco.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	    lblMarco.setBounds(616, 450, 510, 190);
	    panel.add(lblMarco);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBounds(631, 485, 480, 140);
	    panel.add(panel_1);
	    panel_1.setLayout(null);
	    
	    JLabel lblNewLabel_1 = new JLabel("Ultimo cliente identificado");
	    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1.setBounds(616, 460, 510, 20);
	    panel.add(lblNewLabel_1);
	    
	    JLabel lblLogoCheck = new JLabel("");
	    lblLogoCheck.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym Ch.png")));
	    lblLogoCheck.setBounds(5, 5, 116, 116);
	    panel_1.add(lblLogoCheck);
	    
	    JLabel lblEstadoMem = new JLabel("Estado de la membresía");
	    lblEstadoMem.setHorizontalAlignment(SwingConstants.CENTER);
	    lblEstadoMem.setBounds(140, 5, 315, 20);
	    panel_1.add(lblEstadoMem);
	    
	    JLabel lblEstado = new JLabel("Activo");
	    lblEstado.setFont(new Font("Arial Black", Font.PLAIN, 14));
	    lblEstado.setForeground(new Color(0, 128, 0));
	    lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
	    lblEstado.setBounds(140, 35, 315, 20);
	    panel_1.add(lblEstado);
	    
	    JLabel lblInicioSus = new JLabel("Suscripcion: -");
	    lblInicioSus.setHorizontalAlignment(SwingConstants.CENTER);
	    lblInicioSus.setBounds(140, 65, 315, 20);
	    panel_1.add(lblInicioSus);
	    
	    JLabel lblCodigoBarra = new JLabel("");
	    lblCodigoBarra.setIcon(new ImageIcon(Sistema.class.getResource("/img/image 10.png")));
	    lblCodigoBarra.setBounds(140, 95, 315, 23);
	    panel_1.add(lblCodigoBarra);
	    
	    JLabel lblUserCheck = new JLabel("Usuario");
	    lblUserCheck.setHorizontalAlignment(SwingConstants.CENTER);
	    lblUserCheck.setBounds(0, 125, 125, 13);
	    panel_1.add(lblUserCheck);
	}
	public void clases() {
		panel();
		menuB();
		JPanel panel_1 = new JPanel();
	    panel_1.setBounds(36, 170, 1126, 477);
	    panel.add(panel_1);
	    panel_1.setLayout(new GridLayout(0, 3, 15, 15));

	    JButton btnChecador = new JButton("Nueva clase");
        btnChecador.setForeground(Color.WHITE);
        btnChecador.setFocusable(false);
        btnChecador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitarComponentes(); nuevaClase();
			}
        });
        btnChecador.setBackground(new Color(0,33,81));
        btnChecador.setBounds(890, 112, 200, 30);
        panel.add(btnChecador);
        
        ArrayList<String> tiposClase = new ArrayList<>();
        tiposClase.add("Cardio");
        tiposClase.add("Entrenamientos de fuerza");
        tiposClase.add("Entrenamientos de resistencia");
        tiposClase.add("Flexibilidad y equilibrio");

        ArrayList<String> detallesDeClase = new ArrayList<>();
        detallesDeClase.add("<br>De lunes a domingo<br>Horarios disponibles:<br>6:00 - 20:00");
        detallesDeClase.add("<br>De lunes a domingo<br>Horarios disponibles:<br>6:00 - 20:00");
        detallesDeClase.add("<br>De lunes a domingo<br>Horarios disponibles:<br>6:00 - 20:00");
        detallesDeClase.add("<br>De lunes a domingo<br>Horarios disponibles:<br>6:00 - 20:00");

       
	    for (int i = 0; i < tiposClase.size(); i++) {
	        JPanel panelTarifa = new JPanel(new BorderLayout());
	        JLabel info = new JLabel("<html><div style='text-align: center;'>" + tiposClase.get(i) + "<br>" + detallesDeClase.get(i) + "</div></html>");
	        info.setFont(new Font("Arial Black", Font.PLAIN, 16));
	        info.setBorder(BorderFactory.createLineBorder(Color.black, 3));
	        info.setHorizontalAlignment(SwingConstants.CENTER);
	        info.setVerticalAlignment(SwingConstants.CENTER);
	        info.setOpaque(true);
	        info.setForeground(Color.black);
	        info.setBackground(new Color(148, 182, 223));
	        panelTarifa.add(info, BorderLayout.CENTER);

	        JButton btnDetalles = new JButton("Editar");
	        btnDetalles.setBackground(Color.black);
	        btnDetalles.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		quitarComponentes();
		    		detallesClase();
		    	}
		    });
	        btnDetalles.setForeground(Color.white);
	        btnDetalles.setFocusable(false);
	        btnDetalles.setBorder(null);

	        JButton btnEditar = new JButton("Inscribirse");
	        btnEditar.setForeground(Color.white);
	        btnEditar.setFocusable(false);
	        btnEditar.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		quitarComponentes();
		    		inscribirseClase();
		    	}
		    });
	        btnEditar.setBorder(null);
	        btnEditar.setBackground(new Color(0, 33, 83));

	        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 5, 5));
	        panelBotones.add(btnDetalles);
	        panelBotones.add(btnEditar);
	        panelBotones.setBackground(new Color(119, 182, 255));
	        panelTarifa.add(panelBotones, BorderLayout.SOUTH);

	        panel_1.add(panelTarifa);
	    }

	    JLabel lblTitutlo = new JLabel("Clases");
	    lblTitutlo.setForeground(new Color(0, 0, 0));
	    lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
	    lblTitutlo.setBounds(427, 114, 346, 33);
	    panel.add(lblTitutlo);
	}
	
	public void nuevaClase() {
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Nueva clase ");
	    lblTitutlo.setForeground(new Color(0, 0, 0));
	    lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
	    lblTitutlo.setBounds(0, 114, 1200, 33);
	    panel.add(lblTitutlo);
	    
	    lblUsuariosInscritos = new JLabel("Detalles de la nueva clase ");
	    configurarLabels(lblUsuariosInscritos);
	    lblUsuariosInscritos.setHorizontalAlignment(SwingConstants.CENTER);
	    lblUsuariosInscritos.setBounds(0, 250, 1200, 20);
	    panel.add(lblUsuariosInscritos);
	    
	    elementosDetallesNuevaClase(); //ComboBox, nombre, horario
	    
	    btnPagar = new JButton("Crear nueva clase");
	    btnPagar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		  quitarComponentes();
	    		  clases(); //vuelve al panel de clases 
	    		 JOptionPane.showMessageDialog(null, "¡Clase nueva agregada con éxito!", " ", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    });
		btnPagar.setForeground(new Color(255, 255, 255));
		btnPagar.setFocusable(false);
		btnPagar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnPagar.setBackground(new Color(0,47,78)); 
		btnPagar.setBounds(525, 580, 150, 40);
		panel.add(btnPagar);
		
		btnRegistros = new JButton("Eliminar");
		btnRegistros.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int op = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta clase?", "Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);
	             if (op == JOptionPane.OK_OPTION) {
	                 JOptionPane.showMessageDialog(null, "¡Clase eliminada con éxito!", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
	             	quitarComponentes(); nuevaClase();
	             }
	    		 
	    	}
	    });
		btnRegistros.setForeground(Color.white);
		btnRegistros.setFocusable(false);
		btnRegistros.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnRegistros.setBackground(colorBtnEliminar); 
		btnRegistros.setBounds(937, 114, 120, 40);
		panel.add(btnRegistros);
	    panelInscribirseDetallesClase();
	}
	
	public void inscribirseClase() {
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Clase de ");
	    lblTitutlo.setForeground(new Color(0, 0, 0));
	    lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
	    lblTitutlo.setBounds(0, 114, 1200, 33);
	    panel.add(lblTitutlo);
	    lblUsuariosInscritos = new JLabel("Total de usuarios inscritos: ");
	    lblUsuariosInscritos.setHorizontalAlignment(SwingConstants.CENTER);
	    lblUsuariosInscritos.setBounds(0, 187, 1200, 20);
	    panel.add(lblUsuariosInscritos);
	    
	    lblNewLabel_2 = new JLabel("Ingresar la ID del cliente:");
	    lblNewLabel_2.setBounds(172, 259, 365, 30);
	    configurarLabelsDer(lblNewLabel_2);
	    panel.add(lblNewLabel_2);
	    
	    lblNewLabel_3 = new JLabel("Horarios disponibles:");
	    configurarLabelsDer(lblNewLabel_3);
	    lblNewLabel_3.setBounds(172, 313, 365, 30);
	    panel.add(lblNewLabel_3);
	    
	    lblNewLabel_4 = new JLabel("Días de la semana disponibles:");
	    configurarLabelsDer(lblNewLabel_4);
	    lblNewLabel_4.setBounds(172, 367, 365, 30);
	    panel.add(lblNewLabel_4);
	    
	    JTextField textID = new JTextField("");
	    textID.setColumns(10);
	    textID.setForeground(Color.black);
	    textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textID.setBounds(561, 259, 255, 30);
	    panel.add(textID);
	    
	    btnBuscar = new JButton("");
	    btnBuscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
	    btnBuscar.setFocusable(false);
	    btnBuscar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnBuscar.setIcon(new ImageIcon(Sistema.class.getResource("/img/buscar.png")));
	    btnBuscar.setBackground(new Color(217, 217, 217)); 
	    btnBuscar.setBounds(816, 259, 30, 30);
	    panel.add(btnBuscar);
	    
	    String[] horarios = {
	            "06:00 - 07:00",  "07:00 - 08:00","08:00 - 09:00",   "09:00 - 10:00",    "10:00 - 11:00",    "11:00 - 12:00",   "12:00 - 13:00",
	            "13:00 - 14:00", "14:00 - 15:00",   "15:00 - 16:00",   "16:00 - 17:00",   "17:00 - 18:00",  "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00",  "21:00 - 22:00"   };
	    JComboBox<String> comboBox = new JComboBox<>(horarios);
        comboBox.setBounds(561, 318, 285, 30);
	    panel.add(comboBox);
	    
	    JCheckBox checkLunes = new JCheckBox("Lunes");
	    checkLunes.setOpaque(false);
	    checkLunes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkLunes.setBounds(567, 372, 93, 30);
	    panel.add(checkLunes);
	    
	    JCheckBox checkMartes = new JCheckBox("Martes");
	    checkMartes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkMartes.setOpaque(false);
	    checkMartes.setBounds(660, 372, 93, 30);
	    panel.add(checkMartes);
	    
	    JCheckBox checkMiercoles = new JCheckBox("Miércoles");
	    checkMiercoles.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkMiercoles.setOpaque(false);
	    checkMiercoles.setBounds(753, 372, 93, 30);
	    panel.add(checkMiercoles);
	    
	    JCheckBox checkJueves = new JCheckBox("Jueves");
	    checkJueves.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkJueves.setOpaque(false);
	    checkJueves.setBounds(567, 424, 93, 30);
	    panel.add(checkJueves);
	    
	    JCheckBox checkViernes = new JCheckBox("Viernes");
	    checkViernes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkViernes.setOpaque(false);
	    checkViernes.setBounds(660, 424, 93, 30);
	    panel.add(checkViernes);
	    
	    JCheckBox checkSabado = new JCheckBox("Sábado");
	    checkSabado.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkSabado.setOpaque(false);
	    checkSabado.setBounds(753, 424, 93, 30);
	    panel.add(checkSabado);
	    
	    btnPagar = new JButton("Inscribir");
	    btnPagar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		  quitarComponentes();
	    		  clases(); //vuelve al panel de clases 
	    		 JOptionPane.showMessageDialog(null, "¡Cliente inscrito correctamente!", " ", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    });
		btnPagar.setForeground(Color.white);
		btnPagar.setFocusable(false);
		btnPagar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnPagar.setBackground(new Color(0,47,78)); 
		btnPagar.setBounds(525, 580, 150, 40);
		panel.add(btnPagar);
		
		btnRegistros = new JButton("Consultar registros");
		btnRegistros.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		  quitarComponentes();
	    		  registrosClase();
	    		 
	    	}
	    });
		btnRegistros.setForeground(Color.white);
		btnRegistros.setFocusable(false);
		btnRegistros.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnRegistros.setBackground(new Color(0,47,78)); 
		btnRegistros.setBounds(907, 114, 150, 40);
		panel.add(btnRegistros);
	    panelInscribirseDetallesClase();
	}
	
	public void registrosClase() {
		panel();
		menuB();
		btnVolver=new JButton("Volver");
	    btnVolver.setForeground(new Color(255, 255, 255));
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		inscribirseClase();
	    	}
	    });
	    btnVolver.setFocusable(false);
	    btnVolver.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnVolver.setBackground(new Color(0,0,0)); 
	    btnVolver.setBounds(109, 114, 120, 40);
	    panel.add(btnVolver);
	    JLabel lblTitutlo = new JLabel("Clase de " + ""+ "Registros");
	    lblTitutlo.setForeground(new Color(0, 0, 0));
	    lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
	    lblTitutlo.setBounds(0, 114, 1200, 33);
	    panel.add(lblTitutlo);
	    panelCrear = new JPanel();
	    panel.add(panelCrear);
	    panelCrear.setBackground(new Color(217, 217, 217));
	    panelCrear.setLayout(null);
	    panelCrear.setBounds(109, 218, 948, 432);
	    lblUsuariosInscritos = new JLabel("Total de usuarios inscritos: ");configurarLabelsIzq(lblUsuariosInscritos);
	    lblUsuariosInscritos.setBounds(74, 30, 300, 20);
	    panelCrear.add(lblUsuariosInscritos);
	    
	    String titles[]= {"ID", "Nombre", "Horario"};
		DefaultTableModel modelo = new DefaultTableModel(null, titles) {
            @Override
            public boolean isCellEditable(int row, int column) {	              
                return false; //La tabla no se edita
            }
	     };
		JTable datosTabla = new JTable(modelo);
		JScrollPane tablaScroll = new JScrollPane(datosTabla);
		tablaScroll.setBounds(74,90,800,300);
		panelCrear.add(tablaScroll);
		
		btnEdit = new JButton("Editar");
		btnEdit.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnEdit.setBackground(colorBtnGuardar);
		btnEdit.setForeground(Color.white);
		btnEdit.setFocusable(false);
		btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una nueva ventana para editar la clase 
                JFrame editarHorario = new JFrame("Editar horario");
                editarHorario.setSize(475, 300);
                editarHorario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editarHorario.setVisible(true);
                JPanel panelEditar = new JPanel(); panelEditar.setLayout(null);
                panelEditar.setBounds(0,0,475,300); editarHorario.getContentPane().add(panelEditar);
                JLabel titulo = new JLabel("Editar horario"); titulo.setBounds(0, 10, 475, 20); panelEditar.add(titulo); configurarLabels(titulo);
                JLabel id = new JLabel("ID"); id.setBounds(0, 50, 475, 20); panelEditar.add(id); configurarLabels(id);
                JLabel newHorario = new JLabel ("Nuevo Horario:"); newHorario.setBounds(100,90,300,20); panelEditar.add(newHorario); configurarLabelsIzq(newHorario);
                String[] horarios = {
        	            "06:00 - 07:00",  "07:00 - 08:00","08:00 - 09:00",   "09:00 - 10:00",    "10:00 - 11:00",    "11:00 - 12:00",   "12:00 - 13:00",
        	            "13:00 - 14:00", "14:00 - 15:00",   "15:00 - 16:00",   "16:00 - 17:00",   "17:00 - 18:00",  "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00",  "21:00 - 22:00"   };
        	    JComboBox<String> comboBox = new JComboBox<>(horarios);
                comboBox.setBounds(240, 90, 100, 30);
        	    panelEditar.add(comboBox);
        	    JButton btnG = new JButton("Guardar cambios"); btnG.setFocusable(false); panelEditar.add(btnG); btnG.setBounds(72,160,150,30); 
        	    btnG.addActionListener(new ActionListener () {
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "¡Cambios guardados con éxito!", "", JOptionPane.INFORMATION_MESSAGE);
						editarHorario.dispose();
					}
        	    });
        	    JButton btnCancelar = new JButton("Cancelar"); btnCancelar.setFocusable(false); panelEditar.add(btnCancelar); btnCancelar.setBounds(252,160,150,30); 
        	    btnCancelar.addActionListener(new ActionListener () {
					@Override
					public void actionPerformed(ActionEvent e) {
						editarHorario.dispose();
					}
        	    });
        	    editarHorario.setLocationRelativeTo(null);              
            }
        });
		btnEdit.setBounds(630, 20, 111, 30);
		panelCrear.add(btnEdit);
		
		btnEliminar_2 = new JButton("Eliminar");
		btnEliminar_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnEliminar_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 int op = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar la suscripción de este cliente? \nID: " +"" + "\tNombre: "+"", "Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);
	             if (op == JOptionPane.OK_OPTION) {
	                 JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
	                 quitarComponentes(); registrosClase();
	             } 
	             
	    	}
	    });
		btnEliminar_2.setFocusable(false); btnEliminar_2.setForeground(Color.white);
		btnEliminar_2.setBackground(colorBtnEliminar);
		btnEliminar_2.setBounds(763, 20, 111, 30);
		panelCrear.add(btnEliminar_2);
	}
	
	public void panelInscribirseDetallesClase() {
	    btnVolver=new JButton("Volver");
	    btnVolver.setForeground(new Color(255, 255, 255));
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitarComponentes();
	    		clases();
	    	}
	    });
	    btnVolver.setFocusable(false);
	    btnVolver.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnVolver.setBackground(new Color(0,0,0)); 
	    btnVolver.setBounds(109, 114, 120, 40);
	    panel.add(btnVolver);
	    
	    panelCrear = new JPanel();
	    panel.add(panelCrear);
	    panelCrear.setBackground(new Color(217, 217, 217));
	    panelCrear.setLayout(null);
	    panelCrear.setBounds(109, 218, 948, 432);    
	}
	
	public void detallesClase() { //EDITAR CLASE
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Clase de ");
	    lblTitutlo.setForeground(new Color(0, 0, 0));
	    lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
	    lblTitutlo.setBounds(0, 114, 1200, 33);
	    panel.add(lblTitutlo);
	    
	    lblUsuariosInscritos = new JLabel("Editar la clase ");
	    configurarLabels(lblUsuariosInscritos);
	    lblUsuariosInscritos.setHorizontalAlignment(SwingConstants.CENTER);
	    lblUsuariosInscritos.setBounds(0, 250, 1200, 20);
	    panel.add(lblUsuariosInscritos);
	    
	   elementosDetallesNuevaClase(); // ComboBox, nombre, horario
	    
	    btnPagar = new JButton("Guardar cambios");
	    btnPagar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		  quitarComponentes();
	    		  clases(); //vuelve al panel de clases 
	    		 JOptionPane.showMessageDialog(null, "¡Cambios guardados con éxito!", " ", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    });
		btnPagar.setForeground(new Color(255,255,255));
		btnPagar.setFocusable(false);
		btnPagar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnPagar.setBackground(new Color(0,47,78)); 
		btnPagar.setBounds(440, 580, 150, 40);
		panel.add(btnPagar);
		
		btnRegistros = new JButton("Eliminar");
		btnRegistros.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int op = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta clase?", "Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);
	             if (op == JOptionPane.OK_OPTION) {
	                 JOptionPane.showMessageDialog(null, "¡Clase eliminada con éxito!", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
	             	quitarComponentes(); clases();
	             }
	    		 
	    	}
	    });
		btnRegistros.setForeground(new Color(255,255,255));
		btnRegistros.setFocusable(false);
		btnRegistros.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnRegistros.setBackground(colorBtnEliminar); 
		btnRegistros.setBounds(610, 580, 150, 40);
		panel.add(btnRegistros);
	    panelInscribirseDetallesClase();
	    
	}
	
	public void elementosDetallesNuevaClase() { // Labels Horarios y dias
		 lblNewLabel_2 = new JLabel("Nombre de la clase:");
		    lblNewLabel_2.setBounds(109, 315, 365, 30);
		    configurarLabelsDer(lblNewLabel_2);
		    panel.add(lblNewLabel_2);
		    
		    lblNewLabel_3 = new JLabel("Días disponibles:");
		    configurarLabelsDer(lblNewLabel_3);
		    lblNewLabel_3.setBounds(109, 386, 365, 30);
		    panel.add(lblNewLabel_3);
		    
		    lblNewLabel_4 = new JLabel("Horario disponible:");
		    configurarLabelsDer(lblNewLabel_4);
		    lblNewLabel_4.setBounds(109, 459, 365, 30);
		    panel.add(lblNewLabel_4);
		    
		    lblNewLabel_5 = new JLabel("/");    configurarLabels(lblNewLabel_5);
		    lblNewLabel_5.setBounds(652, 459, 20, 30);
		    panel.add(lblNewLabel_5);
		    
		    JTextField textID = new JTextField("");
		    textID.setColumns(10);
		    textID.setForeground(Color.black);
		    textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		    textID.setBounds(498, 315, 333, 30);
		    panel.add(textID);
		    
		    String[] horarios = { "06:00 am",  "07:00 am","08:00 am",   "09:00 am",    "10:00 am",    "11:00 am",   "12:00 pm",
		            "01:00 pm", "02:00 pm", "03:00 pm", "04:00 pm", "05:00 pm", "06:00 pm", "07:00 pm", "08:00 pm"  };
		    JComboBox<String> comboBox = new JComboBox<>(horarios);
	        comboBox.setBounds(498, 459, 144, 30);
		    panel.add(comboBox);
		    
		    String[] horarios2 = { "08:00 am",   "09:00 am",    "10:00 am",    "11:00 am",   "12:00 pm",
		            "01:00 pm", "02:00 pm", "03:00 pm", "04:00 pm", "05:00 pm", "06:00 pm", "07:00 pm", "08:00 pm", "09:00 pm", "10:00 pm"  };
		    JComboBox<String> comboBox2 = new JComboBox<>(horarios2);
	        comboBox2.setBounds(687, 459, 144, 30);
		    panel.add(comboBox2);
		    
		    JCheckBox checkLunes = new JCheckBox("Lunes");
		    checkLunes.setOpaque(false);
		    checkLunes.setFont(new Font("Arial Black", Font.PLAIN, 12));
		    checkLunes.setBounds(498, 369, 93, 30);
		    panel.add(checkLunes);
		    
		    JCheckBox checkMartes = new JCheckBox("Martes");
		    checkMartes.setFont(new Font("Arial Black", Font.PLAIN, 12));
		    checkMartes.setOpaque(false);
		    checkMartes.setBounds(591, 369, 93, 30);
		    panel.add(checkMartes);
		    
		    JCheckBox checkMiercoles = new JCheckBox("Miércoles");
		    checkMiercoles.setFont(new Font("Arial Black", Font.PLAIN, 12));
		    checkMiercoles.setOpaque(false);
		    checkMiercoles.setBounds(684, 369, 93, 30);
		    panel.add(checkMiercoles);
		    
		    JCheckBox checkJueves = new JCheckBox("Jueves");
		    checkJueves.setFont(new Font("Arial Black", Font.PLAIN, 12));
		    checkJueves.setOpaque(false);
		    checkJueves.setBounds(498, 401, 93, 30);
		    panel.add(checkJueves);
		    
		    JCheckBox checkViernes = new JCheckBox("Viernes");
		    checkViernes.setFont(new Font("Arial Black", Font.PLAIN, 12));
		    checkViernes.setOpaque(false);
		    checkViernes.setBounds(591, 401, 93, 30);
		    panel.add(checkViernes);
		    
		    JCheckBox checkSabado = new JCheckBox("Sábado");
		    checkSabado.setFont(new Font("Arial Black", Font.PLAIN, 12));
		    checkSabado.setOpaque(false);
		    checkSabado.setBounds(684, 401, 93, 30);
		    panel.add(checkSabado);
	}
	
	public void confBtnMenuVertical(JButton btn) { // BOTONES PARA MENU VERTICAL EN CLIENTES
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
	    comboMembresia.setModel(new DefaultComboBoxModel(new String[] {"General", "Estudiante","Familiar", "Dúo"}));
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
	    comboTipo.setModel(new DefaultComboBoxModel(new String[] {"  ", "1 mes", "3 meses","6 meses", "1 año"}));
	    comboTipo.setBounds(360, 350, 200, 30);
	    panelCrear.add(comboTipo);
	    
	    comboPago = new JComboBox();
	    comboPago.setModel(new DefaultComboBoxModel(new String[] {"  ", "Efectivo", "Visa","Cheque"}));
	    comboPago.setBounds(360, 435, 200, 30);
	    panelCrear.add(comboPago);
	    
	    JButton btnFoto = new JButton("Subir foto");
	    btnFoto.setForeground(new Color(255, 255, 255));
	    btnFoto.setFocusable(false);
	    btnFoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				subirFoto();
				 if (selectedFile != null) {
	                    lblFoto.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
	                    revalidate(); repaint();
	              }	
			}
	    });
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
    		ticket();
    	//	quitarComponentes(); crearCliente(); // Despues de imprimir, se deben limpiar los elementos
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
	    panelCrear.setBounds(52, 175, 915, 550);
	    
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
	    comboTipo.setModel(new DefaultComboBoxModel(new String[] {"  ", "1 mes", "3 meses","6 meses", "1 año"}));
	    comboTipo.setBounds(360, 350, 200, 30);
	    panelCrear.add(comboTipo);
	    
	    comboPago = new JComboBox();
	    comboPago.setModel(new DefaultComboBoxModel(new String[] {"  ", "Efectivo", "Visa","Cheque"}));
	    comboPago.setBounds(360, 435, 200, 30);
	    panelCrear.add(comboPago);
	    
	    JButton btnFoto = new JButton("Subir foto");
	    btnFoto.setForeground(new Color(255, 255, 255));
	    btnFoto.setFocusable(false);
	    btnFoto.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
			subirFoto();
			 if (selectedFile != null) {
                   lblFoto.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
                   revalidate(); repaint();
             }
			}
	    });
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
	    btnCancelar.setBounds(302, 490, 120, 40);
	    panelCrear.add(btnCancelar);
		
		JTextField textID = new JTextField("Ingrese ID");
		textID.setBackground(new Color(217, 217, 217));
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
	    		JOptionPane.showMessageDialog(null, "¡Cambios de cliente realizados con éxito!", "Edición exitosa", JOptionPane.INFORMATION_MESSAGE);
                panelCrear.setVisible(false);
	    	}
	    });
	    btnGuardar.setFocusable(false);
	    btnGuardar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnGuardar.setBackground(new Color(0, 45, 78)); 
	    btnGuardar.setBounds(462, 490, 120, 40);
	    panelCrear.add(btnGuardar);
		
		
	}
	
	public void subirFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Jpg", "jpg"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Png", "png"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
             selectedFile = fileChooser.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(this, "Agrega una imagen valida");
        }
      
	}
	public void credencialCliente() {
	    btnElim = new JButton("Descargar");
	    btnElim.setFocusable(false);
	    btnElim.setBackground(new Color(89,89,89));
	    btnElim.setForeground(Color.white);
	    btnElim.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JOptionPane.showMessageDialog(null, "¡Descarga exitosa!", "", JOptionPane.INFORMATION_MESSAGE);           	
	    	}
	    });
	    btnElim.setFocusable(false);
	    btnElim.setBounds(690, 135, 190, 50);
	    panelInfo.add(btnElim);
	    
	    lblPersona = new JLabel();
	    lblPersona.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 1.png")));
	    lblPersona.setBounds(36, 33, 217, 218);
	    panelInfo.add(lblPersona);
	    
	    lblCodigo = new JLabel();
	    lblCodigo.setIcon(new ImageIcon(Sistema.class.getResource("/img/codigoDeBarras.png")));
	    lblCodigo.setBounds(299, 241, 327, 59);
	    panelInfo.add(lblCodigo);
	    
	    lblFecha = new JLabel("Fecha de nacimiento: " );
	    configurarLabels(lblFecha);
	    lblFecha.setBounds(299, 33, 327, 20);
	    panelInfo.add(lblFecha);
	    
	    lblTlefono = new JLabel("Teléfono: ");
	    configurarLabels(lblTlefono);lblTlefono.setBounds(299, 73, 327, 20);
	    panelInfo.add(lblTlefono);
	    
	    lblCorreoElectrnico = new JLabel("Correo electrónico: ");
	    configurarLabels(lblCorreoElectrnico);lblCorreoElectrnico.setBounds(299, 113, 327, 20);
	    panelInfo.add(lblCorreoElectrnico);
	    
	    lblFechaDeRegistro = new JLabel("Fecha de registro: ");
	    configurarLabels(lblFechaDeRegistro); lblFechaDeRegistro.setBounds(299, 153, 327, 20);
	    panelInfo.add(lblFechaDeRegistro);
	    
	    lblMembresia = new JLabel("Suscripción: ");
	    configurarLabels(lblMembresia);lblMembresia.setBounds(299, 193, 327, 20);
	    panelInfo.add(lblMembresia);
	    
	    lblPeterParker = new JLabel("Usuario");
	    configurarLabels(lblPeterParker);lblPeterParker.setBounds(36, 270, 217, 20);
	    panelInfo.add(lblPeterParker);
	}
	
	public void panelCrearEditar() {
		p2 = new JPanel();
		p2.setBackground(new Color(255, 255, 255));
		p2.setPreferredSize(new Dimension(p2.getWidth(), 750)); 
		p2.setLayout(null); 
	    
	    JScrollPane scrollPane = new JScrollPane(p2);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setBounds(170, 67, 1020, 620); 
	    panel.add(scrollPane);

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
	    btnElim.setBackground(colorBtnEliminar);
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
	    btnElim.setFocusable(false);
	    btnElim.setBounds(690, 130, 180, 50);
	    panelCredencial.add(btnElim);
	    
	    lblPersona = new JLabel();
	    lblPersona.setIcon(new ImageIcon(Sistema.class.getResource("/img/usuarioGym 1.png")));
	    lblPersona.setBounds(36, 23, 217, 218);
	    panelCredencial.add(lblPersona);
	    
	    lblCodigo = new JLabel();
	    lblCodigo.setIcon(new ImageIcon(Sistema.class.getResource("/img/codigoDeBarras.png")));
	    lblCodigo.setBounds(299, 229, 327, 59);
	    panelCredencial.add(lblCodigo);
	    
	    lblFecha = new JLabel("Fecha de nacimiento: ");
	    configurarLabels(lblFecha);
	    lblFecha.setBounds(299, 33, 327, 20);
	    panelCredencial.add(lblFecha);
	    
	    lblTlefono = new JLabel("Teléfono: ");
	    configurarLabels(lblTlefono);lblTlefono.setBounds(299, 73, 327, 20);
	    panelCredencial.add(lblTlefono);
	    
	    lblCorreoElectrnico = new JLabel("Correo electrónico: ");
	    configurarLabels(lblCorreoElectrnico);lblCorreoElectrnico.setBounds(299, 113, 327, 20);
	    panelCredencial.add(lblCorreoElectrnico);
	    
	    lblFechaDeRegistro = new JLabel("Fecha de registro: ");
	    configurarLabels(lblFechaDeRegistro); lblFechaDeRegistro.setBounds(299, 153, 327, 20);
	    panelCredencial.add(lblFechaDeRegistro);
	    
	    lblMembresia = new JLabel("Membresía: ");
	    configurarLabels(lblMembresia);lblMembresia.setBounds(299, 193, 327, 20);
	    panelCredencial.add(lblMembresia);
	    
	    lblPeterParker = new JLabel("Usuario");
	    configurarLabels(lblPeterParker);lblPeterParker.setBounds(36, 260, 217, 20);
	    panelCredencial.add(lblPeterParker);
	}

}
