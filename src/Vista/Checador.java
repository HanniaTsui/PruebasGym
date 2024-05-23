package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Checador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelSup;
	private final JPanel panelNegro = new JPanel();
	JLabel lblTitulo, lblGym;
	JButton btnVolver,btnBuscar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checador frame = new Checador();
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
	public Checador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,720);
		setTitle("Larry's Gym");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		checador();
	}


	public void checador() {
		
		panel();
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
	    		dispose();
	    		MenuPrincipal menu = new MenuPrincipal();
	    		menu.setVisible(true);
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
	    btnBuscar.setIcon(new ImageIcon(Checador.class.getResource("/img/lupaChecador.png")));
	    btnBuscar.setBackground(new Color(217, 217, 217)); 
	    btnBuscar.setBounds(983, 200, 20, 20);
	    panel.add(btnBuscar);
	    
	    JLabel lblEscanear = new JLabel("");
	    lblEscanear.setIcon(new ImageIcon(Checador.class.getResource("/img/escaneo-de-codigo-de-barras 1.png")));
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
	    lblLogoCheck.setIcon(new ImageIcon(Checador.class.getResource("/img/usuarioGym Ch.png")));
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
	    lblCodigoBarra.setIcon(new ImageIcon(Checador.class.getResource("/img/image 10.png")));
	    lblCodigoBarra.setBounds(140, 95, 315, 23);
	    panel_1.add(lblCodigoBarra);
	    
	    JLabel lblUserCheck = new JLabel("Usuario");
	    lblUserCheck.setHorizontalAlignment(SwingConstants.CENTER);
	    lblUserCheck.setBounds(0, 125, 125, 13);
	    panel_1.add(lblUserCheck);
	}

	public void configurarLabelsIzq(JLabel lbl) { // Configurar Labels a la izquierda  
		lbl.setForeground(new Color(0, 0, 0));
		lbl.setHorizontalAlignment(SwingConstants.LEFT);
		lbl.setFont(new Font("Arial Black", Font.PLAIN, 14));
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
				dispose();
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
			}
		 });
	     configurarBotones(btnInicio);
	     JButton btnClientes = new JButton("Clientes");
	     btnClientes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					Clientes cl = new Clientes();
					cl.setVisible(true);
				}
			 });
	     configurarBotones(btnClientes);
	     JButton btnTarifas = new JButton("Tarifas");
	     btnTarifas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					Tarifas tf = new Tarifas();
					tf.setVisible(true);
				}
			 });
	     configurarBotones(btnTarifas);
	     JButton btnInstructor = new JButton("Instructores");
	     btnInstructor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					Instructor ins = new Instructor();
					ins.setVisible(true);
				}
			 });
	     configurarBotones(btnInstructor);
	     JButton btnClases = new JButton("Clases");
	     btnClases.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					Clases clase = new Clases();
					clase.setVisible(true);
				}
			 });
	     configurarBotones(btnClases);
	     JButton btnChecador = new JButton("Checador");
	     btnChecador.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					quitarComponentes();
					checador();
				}
			 });
	     configurarBotones(btnChecador);
	     JButton btnSalir = new JButton("Salir");
	     btnSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					Inicio i1 = new Inicio();
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
