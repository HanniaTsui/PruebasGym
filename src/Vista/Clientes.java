package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Modelo.Cliente;
import Modelo.ClienteModelo;
import controlador.ClientesControlador;
import controlador.InicioControlador;
import controlador.MenuControlador;

public class Clientes {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelInfo, panelCrear, panelMenuVertical, panelCredencial, p2, panelSup;
	private final JPanel panelNegro = new JPanel();
	private JButton btnBuscar, btnElim, btnReporte, btnGuardar, btnCancelar, btnPagar, btnEditar;
	JLabel lblTitulo, lblGym, lblPersona, lblCodigo, lblFecha, lblTlefono, lblCorreoElectrnico, lblFechaDeRegistro,
			lblMembresia, lblPeterParker, lblNewLabel;
	private JButton btnDetalles, btnCrear;
	private JTextField textField;
	private JComboBox<String> comboMes;
	private JLabel lblNombres, lblApellidos, lblTotalPago, lblMembresia_1, lblMtodoDePago, lblFoto;
	private JTextField textNombre, textApellidos, textEmail, textNacimiento, textTel;
	private JComboBox comboTipo, comboPago, comboMembresia;
	String ventanaActual;
	String fechaNacimiento1, fechaInicial1, fechaFinal1;
	BufferedImage imagen1;
	String path;
	DefaultTableModel modelo;
	private boolean datosCargados = false;
	private File selectedFile;
	Color colorBtnVolver = new Color(174, 174, 174);
	Color colorBtnGuardar = new Color(0, 47, 78);
	Color colorBtnEliminar = new Color(0, 0, 0);
	Color colorBtnEditar = new Color(89, 89, 89);
	List<Cliente> clientes;
	private ClientesControlador controlador;

	/**
	 * Create the frame.
	 */
	public Clientes(ClientesControlador controlador) {
		this.controlador = controlador;
		
	}

	public JPanel clientes() {
		 JPanel panel = getMenu();
	        panel.add(menuVerticalClientes());

	        JLabel lblTitulo = new JLabel("Clientes registrados");
	        lblTitulo.setForeground(new Color(0, 0, 0));
	        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
	        lblTitulo.setBounds(542, 114, 276, 33);
	        panel.add(lblTitulo);

	        String titles[] = { "ID", "Nombre", "Apellido", "Correo", "Teléfono", "Fecha de ingreso", "Tipo de membresía", "Estado" };
	        if (modelo == null) {
	            modelo = new DefaultTableModel(null, titles) {
	                @Override
	                public boolean isCellEditable(int row, int column) {
	                    return false; // La tabla no se edita
	                }
	            };
	        }
	        modelo.setRowCount(0);

	        JTable datosTabla = new JTable(modelo);
	        JScrollPane tablaScroll = new JScrollPane(datosTabla);
	        tablaScroll.setBounds(230, 220, 900, 350);
	        panel.add(tablaScroll);

	        if (!datosCargados) {
	            cargarDatosEnSegundoPlano();
	        } else {
	            actualizarTabla();
	        }

	        JComboBox<String> btnFiltro = new JComboBox<>();
	        btnFiltro.setModel(new DefaultComboBoxModel<>(new String[]{"Filtrar", "Todos", "Activos", "No activos"}));
	        btnFiltro.setForeground(new Color(0, 0, 0));
	        btnFiltro.setBounds(943, 120, 187, 30);
	        panel.add(btnFiltro);

	        return panel;
	}
	 private void cargarDatosEnSegundoPlano() {
	        SwingWorker<List<Cliente>, Void> worker = new SwingWorker<>() {
	            @Override
	            protected List<Cliente> doInBackground() {
	                ClienteModelo.cargarCliente();
	                return ClienteModelo.getClient();
	            }

	            @Override
	            protected void done() {
	                try {
	                    clientes = get();
	                    datosCargados = true;
	                    actualizarTabla();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        };
	        worker.execute();
	    }
	 private void actualizarTabla() {
	        for (Cliente cliente : clientes) {
	            Object[] row = new Object[8];
	            row[0] = cliente.getID();
	            row[1] = cliente.getNombre();
	            row[2] = cliente.getApellido();
	            row[3] = cliente.getCorreo();
	            row[4] = cliente.getTelefono();
	            row[5] = cliente.getFechaInicial();
	            row[6] = cliente.getTipoMembresia();
	            row[7] = cliente.getPlanMembresia();
	            modelo.addRow(row);
	        }
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

	public void confBtnMenuVertical(JButton btn) { // BOTONES PARA MENU VERTICAL EN CLIENTES
		btn.setBackground(Color.BLACK);
		btn.setFont(new Font("Arial Black", Font.PLAIN, 20));
		btn.setForeground(new Color(55, 171, 255));
		btn.setFocusable(false);
		btn.setBorder(null);
	}

	public JPanel menuVerticalClientes() {
		panelMenuVertical = new JPanel();
		panelMenuVertical.setBackground(Color.black);
		panelMenuVertical.setSize(170, 620);
		panelMenuVertical.setLocation(0, 70);
		panelMenuVertical.setLayout(null);

		JButton btnReg = new JButton("Registros");
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.clientes();
			}
		});
		confBtnMenuVertical(btnReg);
		btnReg.setBounds(0, 0, 170, 90);
		panelMenuVertical.add(btnReg);

		btnDetalles = new JButton("Detalles");
		btnDetalles.setBounds(0, 132, 170, 90);
		confBtnMenuVertical(btnDetalles);
		btnDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.detallesCliente();
			}
		});
		panelMenuVertical.add(btnDetalles);

		btnCrear = new JButton("Crear");
		confBtnMenuVertical(btnCrear);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.crearClientes();
			}
		});
		btnCrear.setBounds(0, 264, 170, 90);
		panelMenuVertical.add(btnCrear);

		JButton btnEditar = new JButton("Editar");
		confBtnMenuVertical(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.editarCliente();
			}
		});
		btnEditar.setBounds(0, 396, 170, 90);
		panelMenuVertical.add(btnEditar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.eliminarCliente();
			}
		});
		confBtnMenuVertical(btnEliminar);
		btnEliminar.setBounds(0, 520, 170, 90);
		panelMenuVertical.add(btnEliminar);

		return panelMenuVertical;
	}

	public JPanel panelCrearEditar() {
		JPanel p2 = new JPanel();
		p2.setBackground(new Color(255, 255, 255));
		p2.setPreferredSize(new Dimension(p2.getWidth(), 750));
		p2.setLayout(null);

		return p2;
	}

	public JPanel crearCliente() {
		JPanel panel = getMenu();
		panel.add(menuVerticalClientes());

		JPanel p2 = panelCrearEditar();

		JScrollPane scrollPane = new JScrollPane(p2);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(170, 67, 1020, 620);
		panel.add(scrollPane);

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
		configurarLabelsIzq(lblFecha);
		lblFecha.setBounds(360, 33, 200, 20);
		panelCrear.add(lblFecha);

		lblTlefono = new JLabel("Teléfono:");
		configurarLabelsIzq(lblTlefono);
		lblTlefono.setBounds(360, 119, 200, 20);
		panelCrear.add(lblTlefono);

		lblCorreoElectrnico = new JLabel("Correo electrónico: ");
		configurarLabelsIzq(lblCorreoElectrnico);
		lblCorreoElectrnico.setBounds(70, 215, 200, 20);
		panelCrear.add(lblCorreoElectrnico);

		lblMembresia = new JLabel("Tipo de membresía: ");
		configurarLabelsIzq(lblMembresia);
		lblMembresia.setBounds(360, 215, 200, 20);
		panelCrear.add(lblMembresia);

		lblNombres = new JLabel("Nombre (s):");
		configurarLabelsIzq(lblNombres);
		lblNombres.setBounds(70, 33, 200, 20);
		panelCrear.add(lblNombres);

		textNombre = new JTextField();
		textNombre.setBounds(70, 73, 200, 30);
		panelCrear.add(textNombre);
		validacionTexto(textNombre);
		textNombre.setColumns(10);

		lblApellidos = new JLabel("Apellidos:");
		configurarLabelsIzq(lblApellidos);
		lblApellidos.setBounds(70, 119, 200, 20);
		panelCrear.add(lblApellidos);

		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		validacionTexto(textApellidos);
		textApellidos.setBounds(70, 154, 200, 30);
		panelCrear.add(textApellidos);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(70, 255, 200, 30);
		panelCrear.add(textEmail);

		JSpinner spinnerFechaNacimiento = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor dateEditorFechaNac = new JSpinner.DateEditor(spinnerFechaNacimiento, "dd/MM/yyyy");
		spinnerFechaNacimiento.setEditor(dateEditorFechaNac);
		spinnerFechaNacimiento.setBounds(360, 73, 200, 30);
		panelCrear.add(spinnerFechaNacimiento);
		// sacar el modelo para convertirlo a string
		SpinnerDateModel modeloFechaNacimiento = (SpinnerDateModel) spinnerFechaNacimiento.getModel();
		Date fechaSeleccionada = modeloFechaNacimiento.getDate();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		fechaNacimiento1 = formatoFecha.format(fechaSeleccionada);

		textTel = new JTextField();
		textTel.setColumns(10);
		validacionTel(textTel);
		textTel.setBounds(360, 149, 200, 30);
		panelCrear.add(textTel);

		comboMembresia = new JComboBox();
		comboMembresia.setModel(new DefaultComboBoxModel(new String[] { "General", "Estudiante", "Familiar", "Dúo" }));
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
		// convertir spinner a texto con su formato
		SpinnerDateModel modeloFechaIn = (SpinnerDateModel) spinnerFechaIn.getModel();
		Date fechaSeleccionadaIn = modeloFechaIn.getDate();
		SimpleDateFormat formatoFechaIn = new SimpleDateFormat("dd/MM/yyyy");
		fechaInicial1 = formatoFechaIn.format(fechaSeleccionadaIn);

		JSpinner spinnerFechaFin = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor dateEditorFechaFin = new JSpinner.DateEditor(spinnerFechaFin, "dd/MM/yyyy");
		spinnerFechaFin.setEditor(dateEditorFechaFin);
		spinnerFechaFin.setBounds(70, 435, 200, 30);
		panelCrear.add(spinnerFechaFin);
		// convertir spinner a string
		SpinnerDateModel modeloFechaFin = (SpinnerDateModel) spinnerFechaFin.getModel();
		Date fechaSeleccionadaFin = modeloFechaFin.getDate();
		SimpleDateFormat formatoFechaFin = new SimpleDateFormat("dd/MM/yyyy");
		fechaFinal1 = formatoFechaFin.format(fechaSeleccionadaFin);

		comboTipo = new JComboBox();
		comboTipo.setModel(new DefaultComboBoxModel(new String[] { "  ", "1 mes", "3 meses", "6 meses", "1 año" }));
		comboTipo.setBounds(360, 350, 200, 30);
		panelCrear.add(comboTipo);

		comboPago = new JComboBox();
		comboPago.setModel(new DefaultComboBoxModel(new String[] { "  ", "Efectivo", "Visa", "Cheque" }));
		comboPago.setBounds(360, 435, 200, 30);
		panelCrear.add(comboPago);

		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(Clientes.class.getResource("/img/usuarioGym 1.png")));
		lblFoto.setBounds(642, 33, 217, 221);
		panelCrear.add(lblFoto);

		JButton btnFoto = new JButton("Subir foto");
		btnFoto.setForeground(new Color(255, 255, 255));
		btnFoto.setFocusable(false);
		btnFoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				subirFoto();
				if (selectedFile != null) {
					lblFoto.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
					panel.revalidate();
					panel.repaint();
					path = selectedFile.getAbsolutePath();
				}
			}
		});
		btnFoto.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnFoto.setBackground(new Color(89, 89, 89));
		btnFoto.setBounds(652, 270, 207, 40);
		panelCrear.add(btnFoto);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.crearClientes();
			}
		});
		btnCancelar.setFocusable(false);
		btnCancelar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnCancelar.setBackground(new Color(0, 0, 0));
		btnCancelar.setBounds(292, 490, 150, 40);
		panelCrear.add(btnCancelar);

		btnPagar = new JButton("Pagar");
		btnPagar.setForeground(new Color(255, 255, 255));
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarCamposCrear();
				// ticket();
				System.out.println("aaaa");
			}
		});
		btnPagar.setFocusable(false);
		btnPagar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnPagar.setBackground(new Color(0, 45, 78));
		btnPagar.setBounds(472, 490, 150, 40);
		panelCrear.add(btnPagar);

		lblTotalPago = new JLabel("Total a pagar: $");
		configurarLabelsIzq(lblTotalPago);
		lblTotalPago.setBounds(652, 338, 130, 20);
		panelCrear.add(lblTotalPago);

		return panel;
	}

	private boolean validarCamposCrear() { // validacion de los campos en metodo crearCliente
		

		int ID = 0;
		String nombre = textNombre.getText().trim();
		String apellido = textApellidos.getText().trim();
		String correo = textEmail.getText().trim();
		int telefono = Integer.parseInt(textTel.getText().trim());
		String fechaInicial = fechaInicial1;
		String fechaFinal = fechaFinal1;
		String tipoMembresia = (String) comboMembresia.getSelectedItem();
		String planMembresia = (String) comboTipo.getSelectedItem();
		;
		String fechaNacimiento = fechaNacimiento1;
		BufferedImage imagen;
		try {
			imagen = ImageIO.read(new File(path));
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(null, "Error al obtener imagen", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		;
		String metodoPago = (String) comboPago.getSelectedItem();

		ClientesControlador.registrarCliente(ID, nombre, apellido, correo, telefono, fechaInicial, fechaFinal,
				tipoMembresia, planMembresia, fechaNacimiento, imagen, metodoPago);

		// InicioControlador.registrar(nombre, password,email);
		return true;
	}

	public JPanel editarClientes() {
		JPanel panel = getMenu();
		panel.add(menuVerticalClientes());

		JPanel p2 = panelCrearEditar();

		JScrollPane scrollPane = new JScrollPane(p2);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(170, 67, 1020, 620);
		panel.add(scrollPane);

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
		configurarLabelsIzq(lblFecha);
		lblFecha.setBounds(360, 33, 200, 20);
		panelCrear.add(lblFecha);

		lblTlefono = new JLabel("Teléfono:");
		configurarLabelsIzq(lblTlefono);
		lblTlefono.setBounds(360, 119, 200, 20);
		panelCrear.add(lblTlefono);

		lblCorreoElectrnico = new JLabel("Correo electrónico: ");
		configurarLabelsIzq(lblCorreoElectrnico);
		lblCorreoElectrnico.setBounds(70, 215, 200, 20);
		panelCrear.add(lblCorreoElectrnico);

		lblMembresia = new JLabel("Tipo de membresía: ");
		configurarLabelsIzq(lblMembresia);
		lblMembresia.setBounds(360, 215, 200, 20);
		panelCrear.add(lblMembresia);

		lblNombres = new JLabel("Nombre (s):");
		configurarLabelsIzq(lblNombres);
		lblNombres.setBounds(70, 33, 200, 20);
		panelCrear.add(lblNombres);

		textNombre = new JTextField();
		validacionTexto(textNombre);
		textNombre.setBounds(70, 73, 200, 30);
		panelCrear.add(textNombre);
		textNombre.setColumns(10);

		lblApellidos = new JLabel("Apellidos:");
		configurarLabelsIzq(lblApellidos);
		lblApellidos.setBounds(70, 119, 200, 20);
		panelCrear.add(lblApellidos);

		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		validacionTexto(textApellidos);
		textApellidos.setBounds(70, 154, 200, 30);
		panelCrear.add(textApellidos);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(70, 255, 200, 30);
		panelCrear.add(textEmail);

		JSpinner spinnerFechaNacimiento = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor dateEditorFechaNac = new JSpinner.DateEditor(spinnerFechaNacimiento, "dd/MM/yyyy");
		spinnerFechaNacimiento.setEditor(dateEditorFechaNac);
		spinnerFechaNacimiento.setBounds(360, 73, 200, 30);
		panelCrear.add(spinnerFechaNacimiento);

		textTel = new JTextField();
		textTel.setColumns(10);
		validacionTel(textTel);
		textTel.setBounds(360, 149, 200, 30);
		panelCrear.add(textTel);

		JComboBox comboMembresia = new JComboBox();
		comboMembresia
				.setModel(new DefaultComboBoxModel(new String[] { "  ", "General", "Estudiante", "Familiar", "Dúo" }));
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
		comboTipo.setModel(new DefaultComboBoxModel(new String[] { "  ", "1 mes", "3 meses", "6 meses", "1 año" }));
		comboTipo.setBounds(360, 350, 200, 30);
		panelCrear.add(comboTipo);

		comboPago = new JComboBox();
		comboPago.setModel(new DefaultComboBoxModel(new String[] { "  ", "Efectivo", "Visa", "Cheque" }));
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
					panel.revalidate();
					panel.repaint();
				}
			}
		});
		btnFoto.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnFoto.setBackground(new Color(89, 89, 89));
		btnFoto.setBounds(652, 270, 207, 40);
		panelCrear.add(btnFoto);

		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(Clientes.class.getResource("/img/usuarioGym 1.png")));
		lblFoto.setBounds(642, 33, 217, 221);
		panelCrear.add(lblFoto);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.editarCliente();
			}
		});
		btnCancelar.setFocusable(false);
		btnCancelar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnCancelar.setBackground(new Color(0, 0, 0));
		btnCancelar.setBounds(302, 490, 120, 40);
		panelCrear.add(btnCancelar);

		JTextField textID = new JTextField("Ingrese ID");
		textID.setBackground(new Color(217, 217, 217));
		textID.setColumns(10);
		textID.setForeground(Color.GRAY);
		textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		textID.setBounds(359, 102, 251, 50);
		validacionNumerica(textID);
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
		btnBuscar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnBuscar.setIcon(new ImageIcon(Clientes.class.getResource("/img/buscar.png")));
		btnBuscar.setBackground(new Color(217, 217, 217));
		btnBuscar.setBounds(609, 102, 50, 50);
		p2.add(btnBuscar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "¡Cambios de cliente realizados con éxito!", "Edición exitosa",
						JOptionPane.INFORMATION_MESSAGE);
				panelCrear.setVisible(false);
			}
		});
		btnGuardar.setFocusable(false);
		btnGuardar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnGuardar.setBackground(new Color(0, 45, 78));
		btnGuardar.setBounds(462, 490, 120, 40);
		panelCrear.add(btnGuardar);

		return panel;
	}

	public JPanel detallesClientes() {
		JPanel panel = getMenu();
		panel.add(menuVerticalClientes());

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
		textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		textID.setBounds(533, 172, 251, 50);
		validacionNumerica(textID);
		panel.add(textID);

		textField = new JTextField(); // TEXTFIELD VACIO
		textField.setBounds(170, 80, 0, 0);
		panel.add(textField);

		int[] boundsX = { 222, 405, 588, 771, 954 };
		JButton[] botones = new JButton[5];
		// Crear y configurar los botones
		String[] nombres = { "Información", "Historial de pago", "Historial de asistencias", "Descargar reporte",
				"Credencial" };
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
			botones[i].setBackground(new Color(174, 174, 174));
			botones[i].setBounds(boundsX[i], 250, 183, 40);
			botones[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelInfo.removeAll();
					panelInfo.repaint();
					panelInfo.revalidate();
					panelInfo.setVisible(true);
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
		btnBuscar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnBuscar.setIcon(new ImageIcon(Clientes.class.getResource("/img/buscar.png")));
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

		return panel;
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

	public void credencialCliente() {
		btnElim = new JButton("Descargar");
		btnElim.setFocusable(false);
		btnElim.setBackground(new Color(89, 89, 89));
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
		lblPersona.setIcon(new ImageIcon(Clientes.class.getResource("/img/usuarioGym 1.png")));
		lblPersona.setBounds(36, 33, 217, 218);
		panelInfo.add(lblPersona);

		lblCodigo = new JLabel();
		lblCodigo.setIcon(new ImageIcon(Clientes.class.getResource("/img/codigoDeBarras.png")));
		lblCodigo.setBounds(299, 241, 327, 59);
		panelInfo.add(lblCodigo);

		lblFecha = new JLabel("Fecha de nacimiento: ");
		configurarLabels(lblFecha);
		lblFecha.setBounds(299, 33, 327, 20);
		panelInfo.add(lblFecha);

		lblTlefono = new JLabel("Teléfono: ");
		configurarLabels(lblTlefono);
		lblTlefono.setBounds(299, 73, 327, 20);
		panelInfo.add(lblTlefono);

		lblCorreoElectrnico = new JLabel("Correo electrónico: ");
		configurarLabels(lblCorreoElectrnico);
		lblCorreoElectrnico.setBounds(299, 113, 327, 20);
		panelInfo.add(lblCorreoElectrnico);

		lblFechaDeRegistro = new JLabel("Fecha de registro: ");
		configurarLabels(lblFechaDeRegistro);
		lblFechaDeRegistro.setBounds(299, 153, 327, 20);
		panelInfo.add(lblFechaDeRegistro);

		lblMembresia = new JLabel("Suscripción: ");
		configurarLabels(lblMembresia);
		lblMembresia.setBounds(299, 193, 327, 20);
		panelInfo.add(lblMembresia);

		lblPeterParker = new JLabel("Usuario");
		configurarLabels(lblPeterParker);
		lblPeterParker.setBounds(36, 270, 217, 20);
		panelInfo.add(lblPeterParker);
	}

	public void detallesInformacion() {

		JLabel lblPlanDeLa = new JLabel("Plan de la membresía:");
		configurarLabelsIzq(lblPlanDeLa);
		lblPlanDeLa.setBounds(80, 180, 200, 20);
		panelInfo.add(lblPlanDeLa);

		JLabel lblNombre = new JLabel("Nombre: ");
		configurarLabelsIzq(lblNombre);
		lblNombre.setBounds(80, 20, 200, 20);
		panelInfo.add(lblNombre);

		JLabel id_1 = new JLabel("Correo electrónico:");
		configurarLabelsIzq(id_1);
		id_1.setBounds(80, 100, 200, 20);
		panelInfo.add(id_1);

		JLabel lblTel = new JLabel("Teléfono:");
		configurarLabelsIzq(lblTel);
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
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/img/usuarioGym 1.png")));
		lblNewLabel.setBounds(650, 20, 217, 218);
		panelInfo.add(lblNewLabel);

	}

	public void detallesHistorialPago() { // HistorialPago clientes
		panelDetalles();
		lblMembresia = new JLabel("Suscripción: ");
		configurarLabelsIzq(lblMembresia);
		lblMembresia.setBounds(87, 55, 200, 20);
		panelInfo.add(lblMembresia);

		String titles[] = { "Membresía", "Fecha inicial", "Vencimiento", "Total" };
		DefaultTableModel modelo = new DefaultTableModel(null, titles) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // La tabla no se edita
			}
		};
		JTable datosTabla = new JTable(modelo);
		JScrollPane tablaScroll = new JScrollPane(datosTabla);
		tablaScroll.setBounds(87, 95, 730, 200);
		panelInfo.add(tablaScroll);
	}

	public void detallesHistorialAsistencia() { // HistorialAsistencia clientes
		panelDetalles();
		String titulo[] = { "Fecha", "Hora de entrada", "Hora de salida" };
		DefaultTableModel modelo2 = new DefaultTableModel(null, titulo) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // La tabla no se edita
			}
		};
		JTable datosTabla2 = new JTable(modelo2);
		JScrollPane tablaScroll2 = new JScrollPane(datosTabla2);
		tablaScroll2.setBounds(87, 95, 730, 210);
		panelInfo.add(tablaScroll2);

		comboMes = new JComboBox<>();
		String[] months = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre", "Diciembre" };
		for (String month : months) {
			comboMes.addItem(month);
		}
		comboMes.setBounds(600, 60, 215, 25);
		comboMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cargarAsistencias((String) monthComboBox.getSelectedItem());
			}
		});
		panelInfo.add(comboMes);
		JLabel lblAsistenciasTotales = new JLabel("Asistencias totales:");
		configurarLabelsIzq(lblAsistenciasTotales);
		lblAsistenciasTotales.setBounds(87, 55, 237, 25);
		panelInfo.add(lblAsistenciasTotales);

	}

	public void renovar() {
		// Crear una nueva ventana para editar la clase
		JFrame renovar = new JFrame("Renovar membresía");
		renovar.setSize(550, 500);
		renovar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		renovar.setVisible(true);
		JPanel panelEditar = new JPanel();
		panelEditar.setLayout(null);
		panelEditar.setBounds(0, 0, 550, 500);
		renovar.getContentPane().add(panelEditar);
		JLabel titulo = new JLabel("Renovar membresía");
		titulo.setBounds(0, 35, 550, 20);
		panelEditar.add(titulo);
		configurarLabels(titulo);
		JLabel id = new JLabel("Cliente ID:");
		id.setBounds(70, 85, 150, 20);
		panelEditar.add(id);
		configurarLabelsIzq(id);
		JLabel newHorario = new JLabel("Tipo de membresía: ");
		newHorario.setBounds(70, 165, 200, 20);
		panelEditar.add(newHorario);
		configurarLabelsIzq(newHorario);
		String[] tipo = { "General", "Estudiante", "Familiar", "Dúo" };
		JComboBox<String> comboTipo = new JComboBox<>(tipo);
		comboTipo.setBounds(70, 205, 170, 30);
		panelEditar.add(comboTipo);
		String[] plan = { "1 mes", "3 meses", "6 meses", "1 año" };
		JComboBox<String> comboPlan = new JComboBox<>(plan);
		comboPlan.setBounds(287, 205, 170, 30);
		panelEditar.add(comboPlan);
		String[] metodo = { "Efectivo", "Visa", "Cheque" };
		JComboBox<String> comboMetodo = new JComboBox<>(metodo);
		comboMetodo.setBounds(70, 295, 170, 30);
		panelEditar.add(comboMetodo);
		JButton btnG = new JButton("Renovar");
		btnG.setFocusable(false);
		panelEditar.add(btnG);
		btnG.setBounds(110, 380, 150, 30);
		btnG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				renovar.dispose();
				ticket();
			}
		});
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFocusable(false);
		panelEditar.add(btnCancelar);
		btnCancelar.setBounds(290, 380, 150, 30);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				renovar.dispose();
			}
		});

		JLabel lblMtodoDePago = new JLabel("Método de pago: ");
		configurarLabelsIzq(lblMtodoDePago);
		lblMtodoDePago.setBounds(70, 255, 200, 20);
		panelEditar.add(lblMtodoDePago);

		JLabel lblPlanDeLa = new JLabel("Plan de la membresía:");
		configurarLabelsIzq(lblPlanDeLa);
		lblPlanDeLa.setBounds(287, 165, 200, 20);
		panelEditar.add(lblPlanDeLa);

		JLabel lblNombre = new JLabel("Nombre: ");
		configurarLabelsIzq(lblNombre);
		lblNombre.setBounds(287, 85, 200, 20);
		panelEditar.add(lblNombre);

		JLabel id_1 = new JLabel("00000");
		configurarLabelsIzq(id_1);
		id_1.setBounds(70, 125, 150, 20);
		panelEditar.add(id_1);

		JLabel lblName = new JLabel("aaaa");
		configurarLabelsIzq(lblName);
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

		JPanel panelEditar = new JPanel();
		panelEditar.setLayout(null);
		panelEditar.setBounds(0, 0, 400, 500);
		ticket.getContentPane().add(panelEditar);

		JLabel lblNewLabel = new JLabel(
				"<html>Larry's Gym<br>5.0 / Fitness Club<br> Cliente: <br>Vendedor: <br>Tipo de membresía: <br>Valor de la membresía: <br><br><br>Fecha inicial: <br>Fecha de vencimiento: <br><br><br>Monto total     MXN   <br>Pago realizado en <br>Cambio</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(50, -30, 300, 450);
		panelEditar.add(lblNewLabel);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFocusable(false);
		btnImprimir.setBounds(150, 400, 100, 20);
		panelEditar.add(btnImprimir);
		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Impresión exitosa", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				ticket.dispose();
			}

		});
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
			JOptionPane.showMessageDialog(null, "Agrega una imagen valida");
		}

	}

	public JPanel getMenu() {
		MenuControlador menuControlador = new MenuControlador();
		return menuControlador.getPanelMenu();
	}

	public void quitarComponentes() {
		contentPane.removeAll();
		contentPane.revalidate();
		contentPane.repaint();
	}

	public JPanel eliminarCliente() {
		JPanel panel = getMenu();
		panel.add(menuVerticalClientes());

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
		textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		textID.setBounds(533, 172, 251, 50);
		validacionNumerica(textID);
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
		btnBuscar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnBuscar.setIcon(new ImageIcon(Clientes.class.getResource("/img/buscar.png")));
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
				int op = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este cliente?",
						"Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);
				if (op == JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito", "Eliminación exitosa",
							JOptionPane.INFORMATION_MESSAGE);
					panelCredencial.setVisible(false);
				}

			}
		});
		btnElim.setFocusable(false);
		btnElim.setBounds(690, 130, 180, 50);
		panelCredencial.add(btnElim);

		lblPersona = new JLabel();
		lblPersona.setIcon(new ImageIcon(Clientes.class.getResource("/img/usuarioGym 1.png")));
		lblPersona.setBounds(36, 23, 217, 218);
		panelCredencial.add(lblPersona);

		lblCodigo = new JLabel();
		lblCodigo.setIcon(new ImageIcon(Clientes.class.getResource("/img/codigoDeBarras.png")));
		lblCodigo.setBounds(299, 229, 327, 59);
		panelCredencial.add(lblCodigo);

		lblFecha = new JLabel("Fecha de nacimiento: ");
		configurarLabels(lblFecha);
		lblFecha.setBounds(299, 33, 327, 20);
		panelCredencial.add(lblFecha);

		lblTlefono = new JLabel("Teléfono: ");
		configurarLabels(lblTlefono);
		lblTlefono.setBounds(299, 73, 327, 20);
		panelCredencial.add(lblTlefono);

		lblCorreoElectrnico = new JLabel("Correo electrónico: ");
		configurarLabels(lblCorreoElectrnico);
		lblCorreoElectrnico.setBounds(299, 113, 327, 20);
		panelCredencial.add(lblCorreoElectrnico);

		lblFechaDeRegistro = new JLabel("Fecha de registro: ");
		configurarLabels(lblFechaDeRegistro);
		lblFechaDeRegistro.setBounds(299, 153, 327, 20);
		panelCredencial.add(lblFechaDeRegistro);

		lblMembresia = new JLabel("Membresía: ");
		configurarLabels(lblMembresia);
		lblMembresia.setBounds(299, 193, 327, 20);
		panelCredencial.add(lblMembresia);

		lblPeterParker = new JLabel("Usuario");
		configurarLabels(lblPeterParker);
		lblPeterParker.setBounds(36, 260, 217, 20);
		panelCredencial.add(lblPeterParker);

		return panel;
	}

	public void configurarBotones(JButton btn) {
		btn.setForeground(Color.black);
		btn.setFont(new Font("Arial Black", Font.BOLD, 12));
		btn.setFocusable(false);
		btn.setBackground(new Color(217, 217, 217));
	}

	public void validacionTexto(JTextField text) {
		text.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char l = e.getKeyChar();
				if (!Character.isLetter(l)) {
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
	}

	public void validacionTel(JTextField txt) {
		txt.addKeyListener(new KeyListener() {
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
		txt.setDocument(new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (getLength() + str.length() <= 10) {
					super.insertString(offs, str, a);
				}
			}
		});
	}

	public void validacionNumerica(JTextField text) {
		text.addKeyListener(new KeyListener() {
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
		text.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (text.getText().equals("Ingrese ID")) {
					text.setText("");
					text.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (text.getText().isEmpty()) {
					text.setText("Ingrese ID");
					text.setForeground(Color.GRAY);
				}
			}
		});
	}

}
