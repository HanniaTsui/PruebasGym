package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel;
	private JTextField textNombre, textEmail;
	private JPasswordField textPass,  textPass2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,720);
		setTitle("Larry's Gym");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		iniciar();
	//	registro();

	}
	
	
	public void iniciar() {
		panel = new JPanel();
		panel.setBounds(0, 0, 1200, 720);
		panel.setBackground(Color.black);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblLogo = new JLabel();
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Inicio.class.getResource("/img/logoInicio.png")));
		lblLogo.setBounds(73, 143, 285, 285);
		panel.add(lblLogo);
		JLabel lblFoto = new JLabel();
		lblFoto.setIcon(new ImageIcon(Inicio.class.getResource("/img/inicioF.png")));
		lblFoto.setBounds(0, 0, 441, 700);
		lblFoto.setOpaque(true);
		panel.add(lblFoto);
		
		JLabel lblInicio = new JLabel();
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setIcon(new ImageIcon(Inicio.class.getResource("/img/letrasInicio.png")));
		lblInicio.setBounds(440, 82, 750, 202);
		panel.add(lblInicio);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				login();
			}
		});
		btnIniciar.setForeground(new Color(255, 255, 255));
		btnIniciar.setBackground(new Color(128, 0, 0));
		btnIniciar.setFont(new Font("Arial Black", Font.BOLD, 32));
		btnIniciar.setBounds(638, 556, 355, 50);
		btnIniciar.setFocusable(false);
		panel.add(btnIniciar);
	}
	
	public void login() {
		elementos();
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(0, 0, 0));
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblUsuario.setBounds(87, 261, 146, 25);
		panel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setForeground(new Color(0, 0, 0));
		lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasea.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblContrasea.setBounds(87, 401, 206, 25);
		panel.add(lblContrasea);
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.setForeground(new Color(255, 255, 255));
		btnAcceder.setBackground(new Color(1, 28, 45));
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Sistema c1 = new Sistema();
		        c1.setVisible(true);
			}
		});
		btnAcceder.setFont(new Font("Arial Black", Font.BOLD, 24));
		btnAcceder.setBounds(87, 556, 355, 45);
		btnAcceder.setFocusable(false);
		panel.add(btnAcceder);
		
		textNombre = new JTextField();
		textNombre.setBounds(87, 296, 355, 45);
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textPass = new JPasswordField();
		textPass.setBackground(new Color(255, 255, 255));
		textPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPass.setBounds(87, 436, 355, 45);
		panel.add(textPass);
		
		JLabel lblRegis = new JLabel("______");
		lblRegis.setForeground(new Color(0, 0, 0));
		lblRegis.setVerticalAlignment(SwingConstants.TOP);
		lblRegis.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblRegis.setBounds(87, 133, 224, 98);
		panel.add(lblRegis);
	}
	
	public void registro() {
		elementos();
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNombre.setBounds(87, 205, 100, 25);
		panel.add(lblNombre);
		
		JLabel lblEmail = new JLabel("Correo electrónico:");
		lblEmail.setForeground(new Color(0, 0, 0));
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblEmail.setBounds(87, 300, 258, 25);
		panel.add(lblEmail);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setBackground(new Color(1, 28, 45));
		btnAceptar.setFocusable(false);
		btnAceptar.setFont(new Font("Arial Black", Font.BOLD, 24));
		btnAceptar.setBounds(87, 600, 355, 45);
		panel.add(btnAceptar);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textNombre.setBounds(87, 240, 355, 40);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textPass = new JPasswordField();
		textPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPass.setBackground(new Color(255, 255, 255));
		textPass.setBounds(87, 435, 355, 40);
		panel.add(textPass);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textEmail.setColumns(10);
		textEmail.setBounds(87, 335, 355, 40);
		panel.add(textEmail);
		
		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setForeground(Color.BLACK);
		lblPass.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblPass.setBounds(87, 400, 258, 25);
		panel.add(lblPass);
		
		JLabel lblPass2 = new JLabel("Confirmar contraseña:");
		lblPass2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass2.setForeground(Color.BLACK);
		lblPass2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblPass2.setBounds(87, 500, 258, 25);
		panel.add(lblPass2);
		
		textPass2 = new JPasswordField();
		textPass2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPass2.setBackground(Color.WHITE);
		textPass2.setBounds(87, 535, 355, 40);
		panel.add(textPass2);
		
		JLabel lblRegis = new JLabel("_____");
		lblRegis.setForeground(new Color(0, 0, 0));
		lblRegis.setVerticalAlignment(SwingConstants.TOP);
		lblRegis.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblRegis.setBounds(285, 133, 180, 98);
		panel.add(lblRegis);
	}
	
	public void quitarComponentes() {
		contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
	}

	public void elementos() {
		panel = new JPanel();
		panel.setBounds(0, 0, 1200, 720);
		panel.setBackground(new Color(152,166,178));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLogo = new JLabel();
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Inicio.class.getResource("/img/logoLoginRegistroLarryGyms.png")));
		lblLogo.setBounds(611, 102, 511, 435);
		panel.add(lblLogo);

		JButton botonRegistro = new JButton("Registro");
		botonRegistro.setBorder(null);
		botonRegistro.setForeground(new Color(0, 0, 0));
		botonRegistro.setFont(new Font("Arial Black", Font.BOLD, 24));
		botonRegistro.setFocusPainted(false);
		botonRegistro.setFocusable(false);
		botonRegistro.setContentAreaFilled(false);
		botonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
		//		elementos();
				registro();
			}
		});
		botonRegistro.setBounds(290, 143, 152, 35);
		panel.add(botonRegistro);
		
		JButton btnInicio = new JButton("Iniciar sesión");
		btnInicio.setHorizontalAlignment(SwingConstants.LEFT);
		btnInicio.setBorder(null);
		btnInicio.setForeground(new Color(0, 0, 0));
		btnInicio.setFont(new Font("Arial Black", Font.BOLD, 24));
		btnInicio.setFocusPainted(false);
		btnInicio.setFocusable(false);
		btnInicio.setContentAreaFilled(false);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				login();
			}
		});
		btnInicio.setBounds(87, 143, 206, 35);
		panel.add(btnInicio);
			
		JLabel lblFoto = new JLabel();
		lblFoto.setIcon(new ImageIcon(Inicio.class.getResource("/img/fondoLoginRegistro.png")));
		
		lblFoto.setBounds(529, 0, 661, 700);
		lblFoto.setOpaque(true);
		panel.add(lblFoto);
		
		JLabel lblPesa = new JLabel("");
		lblPesa.setIcon(new ImageIcon(Inicio.class.getResource("/img/pesa.png")));
		lblPesa.setBounds(174, 25, 182, 116);
		panel.add(lblPesa);
		
		JLabel lblNewLabel = new JLabel("_______________________");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(87, 162, 360, 28);
		panel.add(lblNewLabel);
	}
}
