package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controlador.InicioControlador;
import controlador.MenuControlador;

public class Inicio {

	private JTextField textNombre, textEmail;
	private JPasswordField textPass,  textPass2;

	private InicioControlador controlador;

	public JButton btnIniciar, btnAcceder,btnAceptar,botonRegistro,btnInicio;
	
	public Inicio(InicioControlador controlador) {
		this.controlador = controlador;
		iniciar();
	}
	
	public JPanel iniciar() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 720);
		panel.setBackground(Color.black);
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
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(e -> controlador.login());
		btnIniciar.setForeground(new Color(0, 0, 0));
		btnIniciar.setBackground(new Color(119, 182, 255));
		btnIniciar.setFont(new Font("Arial Black", Font.BOLD, 32));
		btnIniciar.setBounds(638, 556, 355, 50);
		btnIniciar.setFocusable(false);
		panel.add(btnIniciar);

		return panel;
	}
	
	public JPanel login() {
		JPanel panel = elementos();
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


		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.setForeground(new Color(255, 255, 255));
		btnAcceder.setBackground(new Color(1, 28, 45));
		btnAcceder.addActionListener(e -> {
            String usr = textNombre.getText();
            String pwd = new String(textPass.getPassword());
            if (usr.isEmpty()) {
                textNombre.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            } else {
                textNombre.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            }
            if (pwd.isEmpty()) {
                textPass.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            } else {
                textPass.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            }
            if (usr.isEmpty() || pwd.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (controlador.validarLogin(usr, pwd)) { //valida con la base de datos mediante metodo validacion en controlador
                //controlador.setInvisible();
				//MenuPrincipal c1 = new MenuPrincipal();
                //c1.setVisible(true);

				MenuControlador menuControlador = new MenuControlador();
				menuControlador.menu();

            } else {
                JOptionPane.showMessageDialog(null, "Usuario y contraseña incorrecta", "Error", JOptionPane.WARNING_MESSAGE);
            }

        });
		panel.add(btnAcceder);
		btnAcceder.setFont(new Font("Arial Black", Font.BOLD, 24));
		btnAcceder.setBounds(87, 556, 355, 45);
		btnAcceder.setFocusable(false);
		panel.add(btnAcceder);


		JLabel lblRegis = new JLabel("______");
		lblRegis.setForeground(new Color(0, 0, 0));
		lblRegis.setVerticalAlignment(SwingConstants.TOP);
		lblRegis.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblRegis.setBounds(87, 133, 224, 98);
		panel.add(lblRegis);

		return panel;
	}
	
	public JPanel registro() {
		JPanel panel = elementos();
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
		btnAceptar.addActionListener(e -> {
            /*
			String nombre = textNombre.getText().trim();
            String email = textEmail.getText().trim();
            String password = new String(textPass.getPassword());
            String confirmarPassword = new String(textPass2.getPassword());

            textNombre.setBorder(new JTextField().getBorder());
            textEmail.setBorder(new JTextField().getBorder());
            textPass.setBorder(new JTextField().getBorder());
            textPass2.setBorder(new JTextField().getBorder());

            if (nombre.isEmpty()) {
                textNombre.setBorder(new LineBorder(Color.RED, 3));
            }
            if (email.isEmpty()) {
                textEmail.setBorder(new LineBorder(Color.RED, 3));
            }

            if (password.isEmpty()) {
                textPass.setBorder(new LineBorder(Color.RED, 3));
            }
            if (confirmarPassword.isEmpty()) {
                textPass2.setBorder(new LineBorder(Color.RED, 3));
            }
            if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || confirmarPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!esEmailValido(email)) {
                JOptionPane.showMessageDialog(null, "Correo electrónico no válido", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }


            if (!password.equals(confirmarPassword)) {
                textPass.setBorder(new LineBorder(Color.RED, 3));
                textPass2.setBorder(new LineBorder(Color.RED, 3));
                JOptionPane.showMessageDialog(panel, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            InicioControlador.registrar(nombre, password,email);
            JOptionPane.showMessageDialog(panel, "Registro exitoso");
            */
			validarCampos();
        });

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
		
		JLabel lblRegis = new JLabel("____");
		lblRegis.setForeground(new Color(0, 0, 0));
		lblRegis.setVerticalAlignment(SwingConstants.TOP);
		lblRegis.setFont(new Font("Tahoma", Font.BOLD, 55));
		lblRegis.setBounds(291, 128, 195, 98);
		panel.add(lblRegis);

		return panel;
	}

	public JPanel elementos() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 720);
		panel.setBackground(new Color(217,217,217));
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

		botonRegistro.addActionListener(e -> controlador.registro());

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
		btnInicio.addActionListener(e -> controlador.login());
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
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(87, 162, 420, 28);
		panel.add(lblNewLabel);

		return panel;
	}

	private boolean validarCampos() { // Validación de campos de registro
	    String nombre = textNombre.getText().trim();
	    String email = textEmail.getText().trim();
	    String password = new String(textPass.getPassword());
	    String confirmarPassword = new String(textPass2.getPassword());

	    textNombre.setBorder(new JTextField().getBorder());
        textEmail.setBorder(new JTextField().getBorder());
        textPass.setBorder(new JTextField().getBorder());
        textPass2.setBorder(new JTextField().getBorder());

	    if (nombre.isEmpty()) {
	    	textNombre.setBorder(new LineBorder(Color.RED, 3));
	    }
	    if (email.isEmpty()) {
	    	 textEmail.setBorder(new LineBorder(Color.RED, 3));
	    }

	    if (password.isEmpty()) {
	    	textPass.setBorder(new LineBorder(Color.RED, 3));
	    }
	    if (confirmarPassword.isEmpty()) {
	    	textPass2.setBorder(new LineBorder(Color.RED, 3));
	    }
	    if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || confirmarPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
	    if (!esEmailValido(email)) {
	    	JOptionPane.showMessageDialog(null, "Correo electrónico no válido", "Error", JOptionPane.WARNING_MESSAGE);
	    	return false;
	    }


	    if (!password.equals(confirmarPassword)) {
	    	textPass.setBorder(new LineBorder(Color.RED, 3));
            textPass2.setBorder(new LineBorder(Color.RED, 3));
	        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    InicioControlador.registrar(nombre, password,email);
	    return true;
	}
	
	private boolean esEmailValido(String email) {
		String[] dominios = {".com", ".mx"};
        for (String dominio : dominios) {
            if (email.endsWith(dominio)) {
                return true;
            }
        }
        return false;
	}

}