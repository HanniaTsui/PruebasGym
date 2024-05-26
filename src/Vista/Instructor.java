package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import Controlador.InstructorControlador;
import Controlador.MenuControlador;

public class Instructor{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelCrear, panelCredencial, panelSup;
	private final JPanel panelNegro = new JPanel();
	private JButton btnVolver,btnHistorial, btnDescargarCredencial, btnReporte, btnGuardar, btnCancelar, btnPagar;
	JLabel lblTitulo, lblGym, lblPersona, lblCodigo, lblFecha, lblTlefono, lblCorreoElectrnico, lblFechaDeRegistro, lblMembresia, lblPeterParker, lblNewLabel;
	 private JLabel lblNombres, lblApellidos, lblFoto;
	 private JTextField textNombre, textApellidos, textEmail, textTel;
	 String ventanaActual;
	 private File selectedFile;
	 private JLabel  lblEspec;
	 Color colorBtnVolver = new Color(174,174,174);
	 Color colorBtnGuardar = new Color(0,47,78); 
	 Color colorBtnEliminar = new Color(0,0,0); 
	 Color colorBtnEditar = new Color(89,89,89);
	 InstructorControlador controlador;
	/**
	 * Create the frame.
	 */
	public Instructor(InstructorControlador controlador) {
		this.controlador=controlador;
		
	}

	public JPanel instructor() { // TABLA INSTRUCTOR
		JPanel panel = getMenu();
		
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
	    		controlador.editarInstructor();
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
	    		controlador.detallesInstructor();
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
		    		controlador.nuevoInstructor();
		    	}
		    });
	     btnGuardar_1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	     btnGuardar_1.setBackground(new Color(0, 45, 78));
	     btnGuardar_1.setBounds(593, 190, 120, 40);
	     panel.add(btnGuardar_1);
	    return panel;
	}
	
	public JPanel detallesInstructor() {
		JPanel panel = getMenu();
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
	                 controlador.instructor();
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
	    		controlador.instructor();
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
	    lblPersona.setIcon(new ImageIcon(Instructor.class.getResource("/img/usuarioGym 1.png")));
	    lblPersona.setBounds(36, 23, 217, 218);
	    panelCredencial.add(lblPersona);
	    
	    lblCodigo = new JLabel();
	    lblCodigo.setIcon(new ImageIcon(Instructor.class.getResource("/img/codigoDeBarras.png")));
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
	    		controlador.historialClases();
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
	    return panel;
	}

	public JPanel historialClases() {
		JPanel panel = getMenu();
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
	    		controlador.detallesInstructor();
	    	}
	    });
	    btnVolver.setFocusable(false);
	    btnVolver.setForeground(Color.white);
	    btnVolver.setBackground(Color.black);
	    btnVolver.setFont(new Font("Arial Black", Font.PLAIN, 13));
	    btnVolver.setBounds(80, 45, 132, 40);
	    panelCrear.add(btnVolver);
	    
	    lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(Instructor.class.getResource("/img/usuarioGym 2.png")));
		lblNewLabel.setBounds(727, 23, 83, 85);
		panelCrear.add(lblNewLabel);
		
		lblPeterParker = new JLabel("Usuario");
	    configurarLabels(lblPeterParker);lblPeterParker.setBounds(693, 105, 142, 20);
	    panelCrear.add(lblPeterParker);
	    return panel;
	}
	
	public JPanel nuevoInstructor() {
		JPanel panel = getMenu();
		
		JLabel lblTitutlo = new JLabel("Nuevo instructor");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		btnVolver = new JButton("Volver");
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		controlador.instructor(); // Regresa a instructores registrados
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
	                    panel.revalidate(); panel.repaint();
	              }
			}
	    });
	    btnFoto.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnFoto.setBackground(new Color(89, 89, 89));
	    btnFoto.setBounds(652, 270, 207, 40);
	    panelCrear.add(btnFoto);
	    
	    lblFoto = new JLabel("");
	    lblFoto.setIcon(new ImageIcon(Instructor.class.getResource("/img/usuarioGym 1.png")));
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
	    		  controlador.instructor(); //vuelve al panel de instructores registrados
	    		 JOptionPane.showMessageDialog(null, "¡Nuevo instructor agregado con exito!", " ", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    });
		btnPagar.setForeground(new Color(255, 255, 255));
		btnPagar.setFocusable(false);
		btnPagar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnPagar.setBackground(new Color(0, 45, 78)); 
		btnPagar.setBounds(382, 389, 150, 40);
	    panelCrear.add(btnPagar); 
	    return panel;
	}
	
	public JPanel editarInstructor() {
		JPanel panel = getMenu();
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
	                    panel.revalidate(); panel.repaint();
	              }
			}
	    });
	    btnFoto.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnFoto.setBackground(new Color(89, 89, 89));
	    btnFoto.setBounds(652, 270, 207, 40);
	    panelCrear.add(btnFoto);
	    
	    lblFoto = new JLabel("");
	    lblFoto.setIcon(new ImageIcon(Instructor.class.getResource("/img/usuarioGym 1.png")));
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
	    		controlador.instructor();
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
	    		controlador.instructor();
	    	}
	    });
	    btnCancelar.setFocusable(false);
	    btnCancelar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnCancelar.setBackground(new Color(0, 0, 0)); 
	    btnCancelar.setBounds(466, 380, 120, 40);
	    panelCrear.add(btnCancelar);
		return panel;
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
	
	public void configurarLabelsIzq(JLabel lbl) { // Configurar Labels a la izquierda  
		lbl.setForeground(new Color(0, 0, 0));
		lbl.setHorizontalAlignment(SwingConstants.LEFT);
		lbl.setFont(new Font("Arial Black", Font.PLAIN, 14));
	}
	
	
	public void configurarLabels(JLabel lbl) { // configurar Labels al centro
		lbl.setForeground(new Color(0, 0, 0));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Arial Black", Font.PLAIN, 14));
	}

	public void configurarLabelsDer(JLabel lbl) { // Configurar Labels a la derecha  
		lbl.setForeground(new Color(0, 0, 0));
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl.setFont(new Font("Arial Black", Font.PLAIN, 14));
	}
	
	public void botonesDetallesClientes(JButton btn) {
		btn.setFocusable(false);
	    btn.setForeground(new Color(255, 255, 255));
	    btn.setBackground(new Color(116, 116, 116));
	    btn.setFont(new Font("Arial Black", Font.PLAIN, 13));
	}
	
	

	public void configurarBotones(JButton btn) {
    	btn.setForeground(Color.black);
    	btn.setFont(new Font("Arial Black", Font.BOLD, 12));
    	btn.setFocusable(false);
    	btn.setBackground(new Color(217, 217, 217)); 
    }
	public JPanel getMenu() {
		MenuControlador menuControlador = new MenuControlador();
		return menuControlador.getPanelMenu();
	}


}
