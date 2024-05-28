package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import controlador.ChecadorControlador;
import controlador.MenuControlador;

public class Checador extends JFrame  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelSup;
	private final JPanel panelNegro = new JPanel();
	JLabel lblTitulo, lblGym;
	JButton btnVolver,btnBuscar;
	JTextField textID;
	
	private ChecadorControlador controlador;

	public Checador(ChecadorControlador controlador) {
		this.controlador = controlador;
	}


	public JPanel checador() {
		JPanel panel = getMenu();
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
	    		MenuControlador menu=new MenuControlador();
	    		menu.menu();
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
	    
		textID = new JTextField("");
		textID.setBackground(new Color(217, 217, 217));
	    textID.setColumns(10);
	    textID.setForeground(Color.black);
	    textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textID.setBounds(833, 200, 150, 20);
	    textID.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isDigit(l)) {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
		});
		textID.setColumns(10);
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

		return panel;
	}

	public void configurarLabelsIzq(JLabel lbl) { // Configurar Labels a la izquierda  
		lbl.setForeground(new Color(0, 0, 0));
		lbl.setHorizontalAlignment(SwingConstants.LEFT);
		lbl.setFont(new Font("Arial Black", Font.PLAIN, 14));
	}

	public JPanel getMenu() {
		MenuControlador menuControlador = new MenuControlador();
		return menuControlador.getPanelMenu();
	}
	
	public void panel() {
		panel = new JPanel();
		panel.setBounds(0, 0, 1200, 720);
		panel.setBackground(Color.white);
		contentPane.add(panel);
		panel.setLayout(null);
	}
	public void configurarBotones(JButton btn) {
    	btn.setForeground(Color.black);
    	btn.setFont(new Font("Arial Black", Font.BOLD, 12));
    	btn.setFocusable(false);
    	btn.setBackground(new Color(217, 217, 217)); 
    }

}
