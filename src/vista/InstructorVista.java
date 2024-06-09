package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import controlador.ClientesControlador;
import controlador.InstructorControlador;
import controlador.MenuControlador;
import modelo.ClasesModelo;
import modelo.ClienteModelo;
import modelo.InstructorModelo;
import objetos.ClasesObj;
import objetos.ClienteObj;
import objetos.InstructorObj;

public class InstructorVista{

	 private static final long serialVersionUID = 1L;
	 private JPanel contentPane, panel, panelCrear, panelCredencial, panelSup;
	 private final JPanel panelNegro = new JPanel();
	 private JButton btnVolver,btnHistorial, btnDescargarCredencial, btnReporte, btnGuardar, btnCancelar, btnPagar;
	 JLabel lblTitulo, lblGym, lblCodigo, lblFecha, lblTlefono, lblCorreoElectrnico, lblFechaDeRegistro, lblMembresia, lblPeterParker, lblNewLabel;
	 private JLabel lblNombres, lblApellidos, lblFoto;
	 private JTextField textNombre, textApellidos, textEmail, textTel;
	 String ventanaActual;
	 DefaultTableModel modelo;
	 private File selectedFile;
	 private JLabel  lblEspec;
	 Color colorBtnVolver = new Color(174,174,174);
	 Color colorBtnGuardar = new Color(0,47,78); 
	 Color colorBtnEliminar = new Color(0,0,0); 
	 Color colorBtnEditar = new Color(89,89,89);
	 InstructorControlador controlador;
	 private static boolean datosCargados = false;
	 static List<InstructorObj> instructores;
	 private JButton btnEditar;
	 InstructorObj instructor=null;
	 private JButton btnDetalles;
	 private JTable datosTabla;
	 String path;
	 private JComboBox comboEspecialidad;
	 private SpinnerDateModel modeloFechaIn;
	 private String fechaInicial1;
	private JSpinner spinnerFechaC;
	private int id;
	private boolean camposVacios;
	/**
	 * Create the frame.
	 */
	public InstructorVista(InstructorControlador controlador) {
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
		
		btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int filaSeleccionada = datosTabla.getSelectedRow();
	            if (filaSeleccionada != -1) {
	                // Obtener los datos del instructor de la fila seleccionada
	                InstructorObj instructorSeleccionado = obtenerInstructorDeFila(filaSeleccionada);
	                // Llamar al método editarInstructor() con el instructor seleccionado como argumento
	                controlador.editarInstructor(instructorSeleccionado);
	            }
	    	}
	    });
		btnEditar.setFocusable(false);
		btnEditar.setEnabled(false);
		btnEditar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnEditar.setBackground(new Color(0, 0, 0)); 
		btnEditar.setBounds(739, 190, 120, 40);
	    panel.add(btnEditar);
	    
	    String titles[]= {"ID", "Nombre", "Apellido", "Correo", "Telefono", "Fecha de contratación", "Clase"};
	    if(modelo==null) {
	    	 modelo = new DefaultTableModel(null, titles) {
	             @Override
	             public boolean isCellEditable(int row, int column) {	              
	                 return false; //La tabla no se edita
	             }
	 	     };
	    }
	     datosTabla = new JTable(modelo);
	     JScrollPane tablaScroll = new JScrollPane(datosTabla);
	     tablaScroll.setBounds(200,250,800,350);
	     datosTabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	         public void valueChanged(ListSelectionEvent event) {
	             // Verificar si se ha seleccionado una fila
	             if (!event.getValueIsAdjusting()) {
	                 // Habilitar o deshabilitar los botones según si hay una fila seleccionada
	                 boolean filaSeleccionada = datosTabla.getSelectedRow() != -1;
	                btnEditar.setEnabled(filaSeleccionada);
	                btnDetalles.setEnabled(filaSeleccionada);
	             }
	         }
	     });
	     panel.add(tablaScroll);
	     
	     if (!datosCargados) {
				cargarDatosEnSegundoPlano();
				datosCargados=true;
			} else {
				actualizarTabla();
			}
	     
	     btnDetalles = new JButton("Detalles");
         btnDetalles.setEnabled(false);
         btnDetalles.setForeground(new Color(255, 255, 255)); 
         btnDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = datosTabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener los datos del instructor de la fila seleccionada
                    InstructorObj instructorSeleccionado = obtenerInstructorDeFila(filaSeleccionada);
                    // Llamar al método editarInstructor() con el instructor seleccionado como argumento
                    controlador.detallesInstructor(instructorSeleccionado);
                }
            }
         });
         btnDetalles.setFocusable(false);
         btnDetalles.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
         btnDetalles.setBackground(new Color(0, 45, 78)); 
         btnDetalles.setBounds(885, 190, 115, 40);
         panel.add(btnDetalles);
	     
	     JButton btnGuardar_1 = new JButton("Nuevo instructor");
	     btnGuardar_1.setForeground(Color.WHITE);
	     btnGuardar_1.setFocusable(false);
	     btnGuardar_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		boolean todasClasesAsignadas = todasClasesAsignadas();
		    	    if (todasClasesAsignadas) {
		    	        JOptionPane.showMessageDialog(null, "No hay más clases disponibles por asignar", "Información", JOptionPane.INFORMATION_MESSAGE);
		    	    } else {
		    	    	controlador.nuevoInstructor();
		    	    }
		    	}
		    });
	     btnGuardar_1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	     btnGuardar_1.setBackground(new Color(0, 45, 78));
	     btnGuardar_1.setBounds(593, 190, 120, 40);
	     panel.add(btnGuardar_1);
	     
	    return panel;
	}
	
	private InstructorObj obtenerInstructorDeFila(int fila) {
	    int id = (int) datosTabla.getValueAt(fila, 0);
	    String nombre = (String) datosTabla.getValueAt(fila, 1);
	    String apellido = (String) datosTabla.getValueAt(fila, 2);
	    String correo = (String) datosTabla.getValueAt(fila, 3);
	    String telefono = (String) datosTabla.getValueAt(fila, 4);
	    String fechaContratacion = (String) datosTabla.getValueAt(fila, 5);
	    String especialidad = (String) datosTabla.getValueAt(fila, 6);
	    BufferedImage imagen=null;
	    for (InstructorObj instructorObj : instructores) {
			if(instructorObj.getID()==id) {
				imagen=instructorObj.getImagen();
				break;
			}else {
				 imagen=null;
			}
		}
	    // Crear un nuevo objeto InstructorObj con los datos obtenidos
	    return new InstructorObj(id, nombre, apellido, correo, telefono, fechaContratacion, especialidad, imagen, 0);
	}
	
	private void cargarDatosEnSegundoPlano() {
		SwingWorker<List<InstructorObj>, Void> worker = new SwingWorker<>() {
			@Override
			protected List<InstructorObj> doInBackground() {
				InstructorModelo.cargarInstructor();
				ClasesModelo.cargarClases();
				return InstructorModelo.getInstructor();
			}

			@Override
			protected void done() {
				try {
					instructores = get();
				    instructores=InstructorModelo.getInstructor();
					actualizarTabla();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		worker.execute();
	}
	private void actualizarTabla() {
	    // Limpiar el modelo de la tabla
	    modelo.setRowCount(0);
	    // Agregar todos los instructores al modelo de la tabla
	    for (InstructorObj instructor : instructores) {
	        agregarInstructorATabla(instructor);
	    }
	}

	private void agregarInstructorATabla(InstructorObj instructor) {
	    modelo.addRow(new Object[]{
	            instructor.getID(),
	            instructor.getNombre(),
	            instructor.getApellido(),
	            instructor.getCorreo(),
	            instructor.getTelefono(),
	            instructor.getFechaContratacion(),
	            instructor.getEspecialidad(),
	            
	    });
	}
	
	public JPanel detallesInstructor(InstructorObj instructor) {
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
	                 InstructorModelo.obtenerInstancia().eliminarInstructor(instructor);

	                 Iterator<InstructorObj> iterator = instructores.iterator();
	                 while (iterator.hasNext()) {
	                     InstructorObj instructorObj = iterator.next();
	                     if (instructorObj.getID() == instructor.getID()) {
	                         iterator.remove();
	                     }
	                 }

	                 controlador.instructor();
	                 actualizarTabla();
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
	    
	    BufferedImage imagenOriginal = instructor.getImagen();
        Image escala = imagenOriginal.getScaledInstance(217, 218, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(escala);
        lblFoto = new JLabel("",0);
        lblFoto.setIcon(scaledIcon);
        lblFoto.setBounds(36, 23, 217, 218);
        panelCredencial.add(lblFoto);
	    
	    lblCodigo = new JLabel();
	    lblCodigo.setIcon(new ImageIcon(InstructorVista.class.getResource("/img/codigoDeBarras.png")));
	    lblCodigo.setBounds(299, 229, 327, 59);
	    panelCredencial.add(lblCodigo);

	    lblCorreoElectrnico = new JLabel("Correo electrónico: "+instructor.getCorreo());
	    configurarLabels(lblCorreoElectrnico);lblCorreoElectrnico.setBounds(299, 124, 327, 20);
	    panelCredencial.add(lblCorreoElectrnico);
	    
	    lblPeterParker = new JLabel(instructor.getNombre() + " "+ instructor.getApellido());
	    configurarLabels(lblPeterParker);lblPeterParker.setBounds(36, 260, 217, 20);
	    panelCredencial.add(lblPeterParker);
	    
	    btnHistorial = new JButton("Historial de clases");
	    btnHistorial.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		controlador.historialClases(instructor);
	    	}
	    	
	    });
	    botonesDetallesClientes(btnHistorial);
	    btnHistorial.setBounds(690, 60, 215, 50);
	    panelCredencial.add(btnHistorial);
	    
	    btnDescargarCredencial = new JButton("Descargar credencial");
        btnDescargarCredencial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InstructorModelo.obtenerInstancia().generarPDFCredencial(instructor);
            }
        });
        botonesDetallesClientes(btnDescargarCredencial);
        btnDescargarCredencial.setBounds(690, 132, 215, 50);
        panelCredencial.add(btnDescargarCredencial);

        btnReporte = new JButton("Descargar reporte");
        btnReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InstructorModelo.obtenerInstancia().generarPDFReporte(instructor);}
        });
        botonesDetallesClientes(btnReporte);
        btnReporte.setBounds(690, 204, 215, 50);
        panelCredencial.add(btnReporte);
	    
	    JLabel lblEspecialidad = new JLabel("Clase: "+instructor.getEspecialidad());
	    configurarLabels(lblEspecialidad);
	    lblEspecialidad.setBounds(299, 74, 327, 20);
	    panelCredencial.add(lblEspecialidad);
	    
	    JLabel lblFechaDeContratacin = new JLabel("Fecha de contratación: "+ instructor.getFechaContratacion());
	    lblFechaDeContratacin.setBounds(299, 174, 327, 20);
	    configurarLabels(lblFechaDeContratacin);
	    panelCredencial.add(lblFechaDeContratacin);
	    return panel;
	}
	public JPanel historialClases(InstructorObj instructor) {
	    JPanel panel = getMenu();
	    JLabel lblTitulo = new JLabel("Historial de clases");
	    lblTitulo.setForeground(new Color(0, 0, 0));
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
	    lblTitulo.setBounds(427, 114, 346, 33);
	    panel.add(lblTitulo);

	    panelCrear = new JPanel();
	    panel.add(panelCrear);
	    panelCrear.setBackground(new Color(217, 217, 217));
	    panelCrear.setLayout(null);
	    panelCrear.setBounds(142, 100, 915, 550);
	    
	    // Columnas de la tabla
	    String[] columnas = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
	    String[][] datos = {
	        {"06:00 - 07:00", "", "", "", "", ""},
	        {"07:00 - 08:00", "", "", "", "", ""},
	        {"08:00 - 09:00", "", "", "", "", ""},
	        {"09:00 - 10:00", "", "", "", "", ""},
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

	    ClasesModelo.obtenerInstancia().cargarClases();
	    
	    for (InstructorObj instructor1 : instructores) {
	        if (instructor1.getID() == instructor.getID()) {
	            int idClase = instructor1.getIDClase();  // Obtener ID de la clase del instructor
	            String horarioYDia = ClasesModelo.obtenerHorarioYDiaPorIdClase(idClase);  // Obtener horario y día por ID de clase
	            System.out.println(instructor1.getIDClase());
	            if (horarioYDia != null) {
	                String[] partes = horarioYDia.split(" - ");
	                if (partes.length == 3) {
	                    String horario = partes[0] + " - " + partes[1];
	                    String dia = partes[2];

	                    // Actualizar la tabla con el horario y día de la clase del instructor
	                    for (int i = 0; i < datos.length; i++) {
	                        if (datos[i][0].equals(horario)) {
	                            switch (dia) {
	                                case "Lunes":
	                                    datos[i][1] = "Clase del instructor";
	                                    break;
	                                case "Martes":
	                                    datos[i][2] = "Clase del instructor";
	                                    break;
	                                case "Miércoles":
	                                    datos[i][3] = "Clase del instructor";
	                                    break;
	                                case "Jueves":
	                                    datos[i][4] = "Clase del instructor";
	                                    break;
	                                case "Viernes":
	                                    datos[i][5] = "Clase del instructor";
	                                    break;
	                                default:
	                                    System.out.println("Día no válido: " + dia);
	                                    break;
	                            }
	                            break;
	                        }
	                    }
	                } else {
	                    System.out.println("Formato de horario y día incorrecto: " + horarioYDia);
	                }
	            } else {
	                System.out.println("El horario y día es nulo");
	            }
	        }
	    }

	    // Agregar tabla a un JScrollPane
	    JScrollPane scrollPane = new JScrollPane(tablaHorario);
	    scrollPane.setBounds(80, 130, 755, 372);
	    panelCrear.add(scrollPane, BorderLayout.CENTER);

	    JButton btnVolver = new JButton("Volver");
	    btnVolver.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            controlador.detallesInstructor(instructor);
	        }
	    });
	    btnVolver.setFocusable(false);
	    btnVolver.setForeground(Color.white);
	    btnVolver.setBackground(Color.black);
	    btnVolver.setFont(new Font("Arial Black", Font.PLAIN, 13));
	    btnVolver.setBounds(80, 45, 132, 40);
	    panelCrear.add(btnVolver);

	    JLabel lblNewLabel = new JLabel();
	    BufferedImage imagenOriginal = instructor.getImagen();
		Image escala = imagenOriginal.getScaledInstance(83, 85, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(escala);
	    lblNewLabel.setIcon(scaledIcon);
	    lblNewLabel.setBounds(727, 23, 83, 85);
	    panelCrear.add(lblNewLabel);

	    JLabel lblPeterParker = new JLabel(instructor.getNombre());
	    configurarLabels(lblPeterParker);
	    lblPeterParker.setBounds(693, 105, 142, 20);
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
	    validacionTexto(textNombre);
	    panelCrear.add(textNombre);
	    textNombre.setColumns(10);
	    
	    lblApellidos = new JLabel("Apellidos:");
	    configurarLabelsIzq(lblApellidos);
	    lblApellidos.setBounds(360, 33, 200, 20);
	    panelCrear.add(lblApellidos);
	    
	    textApellidos = new JTextField();
	    textApellidos.setColumns(10);
	    validacionTexto(textApellidos);
	    textApellidos.setBounds(360, 73, 200, 30);
	    panelCrear.add(textApellidos);
	    
	    textEmail = new JTextField();
	    textEmail.setColumns(10);
	    textEmail.setBounds(70, 255, 200, 30);
	    panelCrear.add(textEmail);
	    
	    textTel = new JTextField();
	    textTel.setColumns(10);
	    validacionTel(textTel);
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
	 // convertir spinner a texto con su formato
        modeloFechaIn = (SpinnerDateModel) spinnerFechaIn.getModel();
        Date fechaSeleccionadaIn = modeloFechaIn.getDate();
        SimpleDateFormat formatoFechaIn = new SimpleDateFormat("dd/MM/yyyy");
        fechaInicial1 = formatoFechaIn.format(fechaSeleccionadaIn);
        
	    panelCrear.add(spinnerFechaIn);
	    
	    
	    JLabel lblFoto = new JLabel("",0);
	    lblFoto.setIcon(new ImageIcon(InstructorVista.class.getResource("/img/usuarioGym 1.png")));
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
		            try {
		                BufferedImage originalImage = ImageIO.read(selectedFile);
		                if (originalImage != null) {
		                    Image scaledImage = originalImage.getScaledInstance(217, 221, Image.SCALE_SMOOTH);
		                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		                    lblFoto.setIcon(scaledIcon);
		                    panel.revalidate();
		                    panel.repaint();
		                    path = selectedFile.getAbsolutePath();
		                } else {
		                    lblFoto.setIcon(new ImageIcon(InstructorVista.class.getResource("/img/usuarioGym 1.png")));
		                    panel.revalidate();
		                    panel.repaint();
		                }
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
				}
			}
	    });
	    btnFoto.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnFoto.setBackground(new Color(89, 89, 89));
	    btnFoto.setBounds(652, 270, 207, 40);
	    panelCrear.add(btnFoto);
	    
	    comboEspecialidad = new JComboBox<>();
	    comboEspecialidad.setBounds(70, 153, 200, 30);
	    panelCrear.add(comboEspecialidad);

	    // Cargar las clases desde la base de datos y añadirlas al JComboBox
	    List<String> nombresClases = ClasesModelo.obtenerNombresClases();
	    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

	    for (String nombreClase : nombresClases) {
	        comboBoxModel.addElement(nombreClase);
	    }

	    comboEspecialidad.setModel(comboBoxModel);
	    
	    lblEspec = new JLabel("Clase:");
	    configurarLabelsIzq(lblEspec);
	    lblEspec.setBounds(70, 119, 200, 20);
	    panelCrear.add(lblEspec);
	    
	    btnPagar = new JButton("Añadir");
	    btnPagar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if (validarCamposVacios()) { // Sobre campos vacios
	    			validarCamposCrear(); 
	    		}
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
	
	private boolean validarCamposVacios() { //BORDES ROJOS
	    boolean camposCompletos = true;
	    textNombre.setBorder(new JTextField().getBorder());
        textEmail.setBorder(new JTextField().getBorder());
        textApellidos.setBorder(new JTextField().getBorder());
        textTel.setBorder(new JTextField().getBorder());
	    // Verificar si algún campo está vacío
	    if (textNombre.getText().trim().isEmpty() || textApellidos.getText().trim().isEmpty() ||
	            textEmail.getText().trim().isEmpty() || textTel.getText().trim().isEmpty() ||
	             comboEspecialidad.getSelectedItem().toString().isEmpty()) {

        // Cambiar el borde de los campos vacíos a rojo
        if (textNombre.getText().trim().isEmpty()) {
            textNombre.setBorder(new LineBorder(Color.RED, 2));
        }
        if (textApellidos.getText().trim().isEmpty()) {
            textApellidos.setBorder(new LineBorder(Color.RED, 2));
        }
        if (textEmail.getText().trim().isEmpty()) {
            textEmail.setBorder(new LineBorder(Color.RED, 2));
        }
        if (textTel.getText().trim().isEmpty()) {
            textTel.setBorder(new LineBorder(Color.RED, 2));
        }
        
        // Mostrar mensaje de alerta
        if (!camposCompletos) {
	        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
	        return false; 
	    }
	    }

	    return true;
	}

	
	public JPanel editarInstructor(InstructorObj instructor) {
		id=instructor.getID();
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
	    
	    textNombre = new JTextField(instructor.getNombre());
	    textNombre.setBounds(70, 73, 200, 30);
	    validacionTexto(textNombre);
	    panelCrear.add(textNombre);
	    textNombre.setColumns(10);
	    
	    lblApellidos = new JLabel("Apellidos:");
	    configurarLabelsIzq(lblApellidos);
	    lblApellidos.setBounds(360, 33, 200, 20);
	    panelCrear.add(lblApellidos);
	    
	    textApellidos = new JTextField(instructor.getApellido());
	    textApellidos.setColumns(10);
	    validacionTexto(textApellidos);
	    textApellidos.setBounds(360, 73, 200, 30);
	    panelCrear.add(textApellidos);
	    
	    textEmail = new JTextField(instructor.getCorreo());
	    textEmail.setColumns(10);
	    textEmail.setBounds(70, 255, 200, 30);
	    panelCrear.add(textEmail);
	    
	    textTel = new JTextField();
	    textTel.setColumns(10);
	    validacionTel(textTel);
	    textTel.setText(String.valueOf(instructor.getTelefono()));
	    textTel.setBounds(360, 149, 200, 30);
	    panelCrear.add(textTel);
	        
	    JLabel lblFechaInicial = new JLabel("Fecha de contratación:");
	    configurarLabelsIzq(lblFechaInicial);
	    lblFechaInicial.setBounds(360, 215, 200, 20);
	    panelCrear.add(lblFechaInicial);

	    String fechaContratacion = instructor.getFechaContratacion();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = null;
		try {
			fecha = dateFormat.parse(fechaContratacion);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Crear el modelo de fecha del spinner con la fecha de nacimiento obtenida
		SpinnerDateModel model = new SpinnerDateModel(fecha, null, null, java.util.Calendar.DAY_OF_MONTH);
		// Crear el spinner con el modelo
		spinnerFechaC = new JSpinner(model);
		JSpinner.DateEditor dateEditorFechaC = new JSpinner.DateEditor(spinnerFechaC, "dd/MM/yyyy");
		spinnerFechaC.setEditor(dateEditorFechaC);
		spinnerFechaC.setPreferredSize(new Dimension(200, 30));
	    spinnerFechaC.setBounds(360, 250, 200, 30);
	    panelCrear.add(spinnerFechaC);
	    
	    SpinnerDateModel modeloFechaCon = (SpinnerDateModel) spinnerFechaC.getModel();
		Calendar calendar = Calendar.getInstance();
        calendar.set(1900, Calendar.JANUARY, 1); //  Fechs inicial: Enero 1, 1900
        Date startDate = calendar.getTime();
        
        calendar.set(2024, Calendar.DECEMBER, 31); // Fecha final: Diciembre 31, 2024
        Date endDate = calendar.getTime();
        
        modeloFechaCon.setStart(startDate);
        modeloFechaCon.setEnd(endDate);
	    JButton btnFoto = new JButton("Subir foto");
	    btnFoto.setForeground(new Color(255, 255, 255));
	    btnFoto.setFocusable(false);
	    path = "Predeterminado";
	    btnFoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				subirFoto();
		        if (selectedFile != null && lblFoto != null) {
		            lblFoto.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
		            path = selectedFile.getAbsolutePath();
		        }
			}
	    });
	    btnFoto.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnFoto.setBackground(new Color(89, 89, 89));
	    btnFoto.setBounds(652, 270, 207, 40);
	    panelCrear.add(btnFoto);
	    
	    lblFoto = new JLabel("",0);
	    BufferedImage imagenOriginal = instructor.getImagen();
		Image escala = imagenOriginal.getScaledInstance(217, 221, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(escala);
	    lblFoto.setIcon(scaledIcon);
	    lblFoto.setBounds(642, 33, 217, 221);
	    panelCrear.add(lblFoto);
	    
	    
	    JComboBox<String> comboEspecialidad = new JComboBox<>();
	    comboEspecialidad.setBounds(70, 153, 200, 30);
	    panelCrear.add(comboEspecialidad);

	    // Cargar las clases desde la base de datos y añadirlas al JComboBox
	    List<String> nombresClases = ClasesModelo.obtenerNombresClases();
	    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
	    String claseInstructor = null;
	    int idClase=0;
	    for (InstructorObj instructorSelec : instructores) {
			if(id==instructorSelec.getID()) {
				idClase=instructorSelec.getIDClase();
				break;
			}
		}
	    for (String nombreClase : nombresClases) {
	        comboBoxModel.addElement(nombreClase);
	        
	    }

	    claseInstructor=ClasesModelo.obtenerNombreClase(idClase);
	    comboEspecialidad.setModel(comboBoxModel);
	    System.out.println(claseInstructor);
	    // Seleccionar el valor por defecto
	    if (claseInstructor != null && nombresClases.contains(claseInstructor)) {
	        comboEspecialidad.setSelectedItem(claseInstructor);
	    } else if (nombresClases.size() > 0) {
	        comboEspecialidad.setSelectedItem(nombresClases.get(0));
	    }

	    lblEspec = new JLabel("Clase:");
	    configurarLabelsIzq(lblEspec);
	    lblEspec.setBounds(70, 119, 200, 20);
	    panelCrear.add(lblEspec);
	    
	    btnGuardar = new JButton("Guardar");
	    btnGuardar.setForeground(new Color(255, 255, 255));
	    btnGuardar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	ClasesModelo.obtenerInstancia().cargarClases();
	        	String claseSeleccionada = (String) comboEspecialidad.getSelectedItem();
	            int idClaseSeleccionada = ClasesModelo.obtenerIdClasePorNombre(claseSeleccionada);
	            
	            if (ClasesModelo.claseAsignadaAOtroInstructor(idClaseSeleccionada, instructor.getID())) {
	                JOptionPane.showMessageDialog(null, "Otro instructor ya tiene asignada esa clase", "Error", JOptionPane.ERROR_MESSAGE);
	                return; // Detener el proceso si la clase está asignada a otro instructor
	            }
	            
	            validarCamposEditar();
	            if (camposVacios) {
	                return; // Detener el proceso si algún campo está vacío
	            }
	            
	            // Continuar con el proceso de guardar si todos los campos están llenos
	            instructor.setNombre(textNombre.getText());
	            instructor.setApellido(textApellidos.getText());
	            instructor.setCorreo(textEmail.getText());
	            instructor.setTelefono(textTel.getText());
	            instructor.setEspecialidad((String) comboEspecialidad.getSelectedItem());
	            // Obtener y asignar las nuevas fechas seleccionadas
	            Date fechaNac = (Date) spinnerFechaC.getValue();
	            instructor.setFechaContratacion(new SimpleDateFormat("dd/MM/yyyy").format(fechaNac));
	            //instructor.setIDClase(idClaseSeleccionada);
	            BufferedImage imagen = null;
	            if (!path.equals("Predeterminado")) {
	                
	                try {
	                    imagen = ImageIO.read(new File(path));
	                    instructor.setImagen(imagen);
	                } catch (IOException exception) {
	                    JOptionPane.showMessageDialog(null, "Error al obtener imagen", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	            for (InstructorObj instructor : instructores) {
	                if(instructor.getID()==id) {
	                	instructor.setImagen(imagen);
	                    instructor.setNombre(textNombre.getText());
	                    instructor.setApellido(textApellidos.getText());
	                    instructor.setCorreo(textEmail.getText());
	                    instructor.setTelefono(textTel.getText());
	                    instructor.setEspecialidad((String) comboEspecialidad.getSelectedItem());
	                    instructor.setFechaContratacion(new SimpleDateFormat("dd/MM/yyyy").format(fechaNac));
	    	            ClasesModelo.obtenerInstancia().eliminarInstructorDeClase(id, instructor.getIDClase());
	                    instructor.setIDClase(idClaseSeleccionada);
	    	            InstructorModelo.obtenerInstancia().editarInstructor(instructor);
	    	            InstructorModelo.obtenerInstancia().actualizarIDInstructorEnClase( idClaseSeleccionada,  instructor.getID());
	                    break;
	                }
	            }
	            actualizarTabla();
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
	
	public boolean todasClasesAsignadas() {
	    List<String> nombresClases = ClasesModelo.obtenerNombresClases();

	    for (String nombreClase : nombresClases) {
	        int idClase = ClasesModelo.obtenerIdClasePorNombre(nombreClase);
	        if (!ClasesModelo.obtenerInstancia().claseAsignada(idClase)) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public void validarCamposEditar() {
		camposVacios = false;
	    textNombre.setBorder(new JTextField().getBorder());
        textEmail.setBorder(new JTextField().getBorder());
        textApellidos.setBorder(new JTextField().getBorder());
        textTel.setBorder(new JTextField().getBorder());
	    // Verificar si algún campo está vacío
        if (textNombre.getText().isEmpty()) {
        	textNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
            camposVacios = true;
        } 
        if (textEmail.getText().isEmpty()) {
        	textEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
            camposVacios = true;
        } 
        if (textApellidos.getText().isEmpty()) {
        	textApellidos.setBorder(BorderFactory.createLineBorder(Color.RED));
            camposVacios = true;
        } 
        if (textTel.getText().isEmpty() ) {
        	textTel.setBorder(BorderFactory.createLineBorder(Color.RED));
            camposVacios = true;
        } 
        if(textTel.getText().length() !=10 ) {
        	JOptionPane.showMessageDialog(null, "El teléfono debe tener exactamente 10 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    textTel.setBorder(BorderFactory.createLineBorder(Color.RED)); // Marcar el campo en rojo
		    camposVacios=true;
		    return;
        }
        if(!textEmail.getText().contains("@")) {
        	textEmail.setBorder(new LineBorder(Color.RED));
        	JOptionPane.showMessageDialog(null, "La dirección de correo electrónico debe contener el carácter '@'.", "Error", JOptionPane.ERROR_MESSAGE);
        	camposVacios=true;
        	return;
        }
        // Mostrar mensaje de alerta
        if (camposVacios) {
        	JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        
            return; // Detener el proceso si algún campo está vacío
        }
	    
		
	
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

	public void validacionTexto(JTextField text) {
		text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char l = e.getKeyChar();
                if (!Character.isLetter(l) && l != 32) {
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
            @Override public void keyPressed(KeyEvent e) {  }
            @Override public void keyReleased(KeyEvent e) {}
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
	
	private boolean validarCamposCrear() {
		int ID = 0;
		String nombre = textNombre.getText().trim();
		String apellido = textApellidos.getText().trim();
		String correo = textEmail.getText().trim();
		String telefono = textTel.getText().trim();
		String fecha = fechaInicial1;
		String especialidad = (String) comboEspecialidad.getSelectedItem();

		String nombreClaseSeleccionada = (String) comboEspecialidad.getSelectedItem(); 
	    int idClase = ClasesModelo.obtenerIdClasePorNombre(nombreClaseSeleccionada);

		BufferedImage imagen = null; // Inicializamos la imagen como nula
		if (path == null || path.equals("usuarioGym 1")) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una imagen.", "Error", JOptionPane.ERROR_MESSAGE);
			return false; 
		}
		if (!esEmailValido(correo)) { 
	    	JOptionPane.showMessageDialog(null, "Correo electrónico no válido", "Error", JOptionPane.WARNING_MESSAGE);
	    	return false;
	    }
		if (telefono.length() != 10) {
		    JOptionPane.showMessageDialog(null, "El teléfono debe tener exactamente 10 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
		    textTel.setBorder(BorderFactory.createLineBorder(Color.RED)); // Marcar el campo en rojo
		    return false;
		} else {
		    textTel.setBorder(null);
		}
		try {
			// Intentar leer la imagen seleccionada
			imagen = ImageIO.read(new File(path));
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(null, "Error al obtener imagen", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (ClasesModelo.claseAsignadaAOtroInstructor(idClase, -1)) {
            JOptionPane.showMessageDialog(null, "Otro instructor ya tiene asignada esa clase", "Error", JOptionPane.ERROR_MESSAGE);
            return false; 
        }
		InstructorControlador.registrarInstructor(ID, nombre, apellido, correo, telefono, fecha, especialidad, imagen, idClase);
		
		controlador.instructor();
		return true;
	}
	
	private boolean esEmailValido(String email) {
		if (!email.contains("@")) {
	        return false;
	    }
        return true;
	}

}