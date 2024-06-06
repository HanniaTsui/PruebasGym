package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.ClasesModelo;
import Modelo.ClasesObj;
import Modelo.ClienteModelo;
import Modelo.ClienteObj;
import controlador.ClasesControlador;
import controlador.ClientesControlador;
import controlador.MenuControlador;

public class ClasesVista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelCrear, panelSup;
	private final JPanel panelNegro = new JPanel();
	private JButton btnBuscar, btnVolver, btnPagar;
	JLabel lblTitulo, lblGym, lblPersona, lblCodigo, lblFecha, lblTlefono, lblCorreoElectrnico, lblFechaDeRegistro, lblMembresia, lblPeterParker, lblNewLabel;
	private JButton btnRegistros;
	String ventanaActual;
	ClasesObj clasesA = null;
	private JLabel lblUsuariosInscritos, lblNewLabel_2, lblNewLabel_3,lblNewLabel_4, lblNewLabel_5;
	private JButton btnEdit, btnEliminar_2;
	Color colorBtnVolver = new Color(174,174,174);
	Color colorBtnGuardar = new Color(0,47,78); 
	Color colorBtnEliminar = new Color(0,0,0); 
	Color colorBtnEditar = new Color(89,89,89); 
	ClienteObj cliente = null;
	private ClasesControlador controlador;
	private ClientesControlador controladorClientes;
	private JTextField textID;
	private JTextField textNombre;
	private static JPanel panel_1;
	static boolean cargarDatos=false;
    static List<ClasesObj> clases;
	/**
	 * Create the frame.
	 */
	public ClasesVista(ClasesControlador controlador) {
		
		this.controlador = controlador;
		
	}

	private void cargarClasesEnSegundoPlano(JPanel panel_1) {
        SwingWorker<List<ClasesObj>, Void> worker = new SwingWorker<List<ClasesObj>, Void>() {
            @Override
            protected List<ClasesObj> doInBackground() throws Exception {
            	ClienteModelo.cargarCliente();
                return ClasesModelo.cargarClases(); 
            }

            @Override
            protected void done() {
                try {
                    clases = get();
                    actualizarPanelConClases(clases, panel_1);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al cargar las clases", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        worker.execute(); 
    }
	
	private void actualizarPanelConClases(List<ClasesObj> clases, JPanel panel_1) {
        panel_1.removeAll(); // Limpia el panel antes de agregar nuevas clases
        for (ClasesObj clase : clases) {
        	String horarioText;
            String diaText;
            
            // Obtener el horario según el ID de la clase
            switch (clase.getIdHorario()) {
            case 1:
                horarioText = "06:00 - 07:00";
                break;
            case 2:
                horarioText = "07:00 - 08:00";
                break;
            case 3:
                horarioText = "08:00 - 09:00";
                break;
            case 4:
                horarioText = "09:00 - 10:00";
                break;  
            case 5:
                horarioText = "10:00 - 11:00";
                break; 
            case 6:
                horarioText = "11:00 - 12:00";
                break;  
            case 7:
                horarioText = "12:00 - 13:00";
                break; 
            case 8:
                horarioText = "13:00 - 14:00";
                break;
            case 9:
                horarioText = "14:00 - 15:00";
                break;
            case 10:
                horarioText = "15:00 - 16:00";
                break;
            case 11:
                horarioText = "16:00 - 17:00";
                break;
            case 12:
                horarioText = "17:00 - 18:00";
                break;
            case 13:
                horarioText = "18:00 - 19:00";
                break;
            case 14:
                horarioText = "19:00 - 20:00";
                break;
            default:
                horarioText = "";
                break;
            }
            switch (clase.getIdDia()) {
            case 1:
                diaText = "Lunes";
                break;
            case 2:
                diaText = "Martes";
                break;
            case 3:
                diaText = "Miércoles";
                break;
            case 4:
                diaText = "Jueves";
                break;
            case 5:
                diaText = "Viernes";
                break;
            default:
                diaText = "";
                break;
        }
            JPanel panelTarifa = new JPanel(new BorderLayout());
            JLabel info = new JLabel("<html><div style='text-align: center;'>" + clase.getNombre() + 
                                     "<br>Día: " + diaText + 
                                     "<br>Horario: " + horarioText + 
                                     "</div></html>");
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
                    ClasesObj claseA = controlador.buscarClase(clase.getID());
                    controlador.detallesClase(clase);
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
                    ClasesObj claseA = controlador.buscarClase(clase.getID());
                    controlador.inscribirseClase(clase);
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
        panel_1.revalidate(); 
        panel_1.repaint(); 
    }
	
	public JPanel clases() {
        JPanel panel = getMenu();

        panel_1 = new JPanel();
        panel_1.setBounds(36, 170, 1126, 477);
        panel.add(panel_1);
        panel_1.setLayout(new GridLayout(0, 3, 15, 15));

        if(!cargarDatos) {
	        cargarClasesEnSegundoPlano(panel_1);
	        cargarDatos=true;
        }else {
        	actualizarPanelConClases(clases, panel_1);
        }
        JButton btnChecador = new JButton("Nueva clase");
        btnChecador.setForeground(Color.WHITE);
        btnChecador.setFocusable(false);
        btnChecador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.nuevaClase();
            }
        });
        btnChecador.setBackground(new Color(0, 33, 81));
        btnChecador.setBounds(890, 112, 200, 30);
        panel.add(btnChecador);

        JLabel lblTitulo = new JLabel("Clases");
        lblTitulo.setForeground(new Color(0, 0, 0));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
        lblTitulo.setBounds(427, 114, 346, 33);
        panel.add(lblTitulo);


        return panel;
    }
	
	public JPanel nuevaClase() {
		JPanel panel = getMenu();

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
	    
	    textNombre = new JTextField("");
	    textNombre.setColumns(10);
	    textNombre.setForeground(Color.black);
	    textNombre.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textNombre.setBounds(498, 315, 333, 30);
	    panel.add(textNombre);
	    
	    String[] horarios = {
	            "06:00 - 07:00",  "07:00 - 08:00","08:00 - 09:00",   "09:00 - 10:00",    "10:00 - 11:00",    "11:00 - 12:00",   "12:00 - 13:00",
	            "13:00 - 14:00", "14:00 - 15:00",   "15:00 - 16:00",   "16:00 - 17:00",   "17:00 - 18:00",  "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00",  "21:00 - 22:00"   };
	    JComboBox<String> comboBox = new JComboBox<>(horarios);
        comboBox.setBounds(498, 459, 333, 30);
	    panel.add(comboBox);
	    
	    ButtonGroup grupoDiasSemana = new ButtonGroup();
	    
	    JRadioButton checkLunes = new JRadioButton("Lunes");
	    checkLunes.setOpaque(false);
	    checkLunes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkLunes.setBounds(498, 369, 93, 30);
	    panel.add(checkLunes);
	    grupoDiasSemana.add(checkLunes);
	    
	    JRadioButton checkMartes = new JRadioButton("Martes");
	    checkMartes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkMartes.setOpaque(false);
	    checkMartes.setBounds(591, 369, 93, 30);
	    panel.add(checkMartes);
	    grupoDiasSemana.add(checkMartes);
	    JRadioButton checkMiercoles = new JRadioButton("Miércoles");
	    checkMiercoles.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkMiercoles.setOpaque(false);
	    checkMiercoles.setBounds(684, 369, 93, 30);
	    panel.add(checkMiercoles);
	    grupoDiasSemana.add(checkMiercoles);
	    JRadioButton checkJueves = new JRadioButton("Jueves");
	    checkJueves.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkJueves.setOpaque(false);
	    checkJueves.setBounds(498, 401, 93, 30);
	    panel.add(checkJueves);
	    grupoDiasSemana.add(checkJueves);
	    
	    JRadioButton checkViernes = new JRadioButton("Viernes");
	    checkViernes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkViernes.setOpaque(false);
	    checkViernes.setBounds(591, 401, 93, 30);
	    panel.add(checkViernes);
	    grupoDiasSemana.add(checkViernes);
	    
	 //   int idDiaClase = clases.getIdDia();
	    int idDiaClase=1; //CAMBIAR
	    // Seleccionar el JRadioButton correspondiente según el ID del día de la clase
	    switch (idDiaClase) {
	     case 1:
	    	 checkLunes.setSelected(true);
	         break;
	     case 2:
	    	 checkMartes.setSelected(true);
	         break;
	     case 3:
	    	 checkMiercoles.setSelected(true);
	         break;
	     case 4:
	    	 checkJueves.setSelected(true);
	         break;
	     case 5:
	    	 checkViernes.setSelected(true);
	         break;
	    }
	    
	    btnPagar = new JButton("Crear nueva clase");
	    btnPagar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int ID = 0;
	    		String nombre = textNombre.getText().trim();
	    		String horario = (String)comboBox.getSelectedItem();
	    		int diaSeleccionado = 0;
	            if (checkLunes.isSelected()) {
	                diaSeleccionado = 1;
	            } else if (checkMartes.isSelected()) {
	                diaSeleccionado = 2;
	            } else if (checkMiercoles.isSelected()) {
	                diaSeleccionado = 3;
	            } else if (checkJueves.isSelected()) {
	                diaSeleccionado = 4;
	            } else if (checkViernes.isSelected()) {
	                diaSeleccionado = 5;
	            }
	            if (nombre.isEmpty()) {
	            	JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error",
	    					JOptionPane.ERROR_MESSAGE);
	            }
	            int idHorario = ClasesControlador.obtenerIdHorario(horario); // Método para obtener el ID del horario

	    		ClasesControlador.registrarClaseNueva(ID, nombre, idHorario, diaSeleccionado);
	    		controlador.clases();	 
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
	             	//quitarComponentes(); nuevaClase();
					 controlador.nuevaClase();
	             }
	    	}
	    });
		btnRegistros.setForeground(Color.white);
		btnRegistros.setFocusable(false);
		btnRegistros.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnRegistros.setBackground(colorBtnEliminar); 
		btnRegistros.setBounds(937, 114, 120, 40);
		panel.add(btnRegistros);
	    panelInscribirseDetallesClase(panel);

		return panel;
	}
	
	public JPanel inscribirseClase(ClasesObj clases) {
		JPanel panel = getMenu();
		JLabel lblTitutlo = new JLabel("Clase de "+clases.getNombre());
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
	    
	    textID = new JTextField();
	    textID.setColumns(10);
	    textID.setForeground(Color.black);
	    textID.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textID.setBounds(561, 259, 255, 30);
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
	    desactivarBoton(btnBuscar);
	    btnBuscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		cliente = null;
		        cliente = controladorClientes.buscarClientePorID(Integer.parseInt(textID.getText()));
		        
		        if (cliente != null) {
		           
		            } else {
		            
		            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con la ID proporcionada",
		                    "Cliente no encontrado", JOptionPane.INFORMATION_MESSAGE);
		        }

	    	}
	    });

	    btnBuscar.setFocusable(false);
	    btnBuscar.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnBuscar.setIcon(new ImageIcon(ClasesVista.class.getResource("/img/buscar.png")));
	    btnBuscar.setBackground(new Color(217, 217, 217)); 
	    btnBuscar.setBounds(816, 259, 30, 30);
	    panel.add(btnBuscar);
	    
	    JLabel horario = new JLabel();
	    horario.setBounds(561,318,285,30);
	    String[] horarios = {
	    	    "06:00 - 07:00",  "07:00 - 08:00", "08:00 - 09:00",   "09:00 - 10:00",    
	    	    "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00",   "13:00 - 14:00", 
	    	    "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00",   "17:00 - 18:00",  
	    	    "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00",   "21:00 - 22:00" 
	    	};

	    	int idHorario = clases.getIdHorario();
	    	if (idHorario >= 1 && idHorario < horarios.length) {
	    	    String horarioTexto = horarios[idHorario-1];
	    	    horario.setText(horarioTexto);
	    	} else {
	    	    // Manejar el caso en que el ID del horario está fuera del rango
	    	    horario.setText("Horario no válido");
	    	}

	    panel.add(horario);
	    JComboBox<String> comboBox = new JComboBox<>(horarios);
	//    comboBox.setSelectedItem(clases.getIdHorario());
        comboBox.setBounds(561, 318, 285, 30);
	//    panel.add(comboBox);
	    
        ButtonGroup grupoDiasSemana = new ButtonGroup();
	    
	    JRadioButton checkLunes = new JRadioButton("Lunes");
	    checkLunes.setOpaque(false);
	    checkLunes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkLunes.setBounds(567, 372, 93, 30);
	    panel.add(checkLunes);
	    grupoDiasSemana.add(checkLunes);
	    
	    JRadioButton checkMartes = new JRadioButton("Martes");
	    checkMartes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkMartes.setOpaque(false);
	    checkMartes.setBounds(660, 372, 93, 30);
	    panel.add(checkMartes);
	    grupoDiasSemana.add(checkMartes);
	    
	    JRadioButton checkMiercoles = new JRadioButton("Miércoles");
	    checkMiercoles.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkMiercoles.setOpaque(false);
	    checkMiercoles.setBounds(753, 372, 93, 30);
	    panel.add(checkMiercoles);
	    grupoDiasSemana.add(checkMiercoles);
	    
	    JRadioButton checkJueves = new JRadioButton("Jueves");
	    checkJueves.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkJueves.setOpaque(false);
	    checkJueves.setBounds(567, 424, 93, 30);
	    panel.add(checkJueves);
	    grupoDiasSemana.add(checkJueves);
	    
	    JRadioButton checkViernes = new JRadioButton("Viernes");
	    checkViernes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkViernes.setOpaque(false);
	    checkViernes.setBounds(660, 424, 93, 30);
	    panel.add(checkViernes);
	    grupoDiasSemana.add(checkViernes);
	    
	    int idDiaClase = clases.getIdDia();
	    // Seleccionar el JRadioButton correspondiente según el ID del día de la clase
	    switch (idDiaClase) {
	     case 1:
	    	 checkLunes.setSelected(true);
	         break;
	     case 2:
	    	 checkMartes.setSelected(true);
	         break;
	     case 3:
	    	 checkMiercoles.setSelected(true);
	         break;
	     case 4:
	    	 checkJueves.setSelected(true);
	         break;
	     case 5:
	    	 checkViernes.setSelected(true);
	         break;
	    }
	    
	    btnPagar = new JButton("Inscribir");
	    btnPagar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		  //quitarComponentes();
	    		  //clases(); //vuelve al panel de clases
				controlador.clases();
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
				controlador.registrosClase();
	    	}
	    });
		btnRegistros.setForeground(Color.white);
		btnRegistros.setFocusable(false);
		btnRegistros.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnRegistros.setBackground(new Color(0,47,78)); 
		btnRegistros.setBounds(907, 114, 150, 40);
		panel.add(btnRegistros);
	    panelInscribirseDetallesClase(panel);

		return panel;
	}
	
	public JPanel registrosClase() {
		JPanel panel = getMenu();

		btnVolver=new JButton("Volver");
	    btnVolver.setForeground(new Color(255, 255, 255));
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				//controlador.inscribirseClase();
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
	                 //quitarComponentes(); registrosClase();
					 controlador.registrosClase();
	             } 
	             
	    	}
	    });
		btnEliminar_2.setFocusable(false); btnEliminar_2.setForeground(Color.white);
		btnEliminar_2.setBackground(colorBtnEliminar);
		btnEliminar_2.setBounds(763, 20, 111, 30);
		panelCrear.add(btnEliminar_2);

		return panel;
	}
	
	public void panelInscribirseDetallesClase(JPanel panel) {
	    btnVolver=new JButton("Volver");
	    btnVolver.setForeground(new Color(255, 255, 255));
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		controlador.clases();
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
	
	public JPanel detallesClase(ClasesObj clases) { //EDITAR CLASE
		JPanel panel = getMenu();
		JLabel lblTitutlo = new JLabel("Clase de "+clases.getNombre());
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
	    
	    textNombre = new JTextField(clases.getNombre());
	    textNombre.setColumns(10);
	    textNombre.setForeground(Color.black);
	    textNombre.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    textNombre.setBounds(498, 315, 333, 30);
	    panel.add(textNombre);
	    
	    String[] horarios = {
	            "06:00 - 07:00",  "07:00 - 08:00","08:00 - 09:00",   "09:00 - 10:00",    "10:00 - 11:00",    "11:00 - 12:00",   "12:00 - 13:00",
	            "13:00 - 14:00", "14:00 - 15:00",   "15:00 - 16:00",   "16:00 - 17:00",   "17:00 - 18:00",  "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00",  "21:00 - 22:00"   };
	    JComboBox<String> comboBox = new JComboBox<>(horarios);
	    comboBox.setSelectedItem(clases.getIdHorario());
        comboBox.setBounds(498, 459, 333, 30);
	    panel.add(comboBox);
	    
	    ButtonGroup grupoDiasSemana = new ButtonGroup();
	    
	    JRadioButton checkLunes = new JRadioButton("Lunes");
	    checkLunes.setOpaque(false);
	    checkLunes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkLunes.setBounds(498, 369, 93, 30);
	    panel.add(checkLunes);
	    grupoDiasSemana.add(checkLunes);
	    
	    JRadioButton checkMartes = new JRadioButton("Martes");
	    checkMartes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkMartes.setOpaque(false);
	    checkMartes.setBounds(591, 369, 93, 30);
	    panel.add(checkMartes);
	    grupoDiasSemana.add(checkMartes);
	    
	    JRadioButton checkMiercoles = new JRadioButton("Miércoles");
	    checkMiercoles.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkMiercoles.setOpaque(false);
	    checkMiercoles.setBounds(684, 369, 93, 30);
	    panel.add(checkMiercoles);
	    grupoDiasSemana.add(checkMiercoles);
	    
	    JRadioButton checkJueves = new JRadioButton("Jueves");
	    checkJueves.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkJueves.setOpaque(false);
	    checkJueves.setBounds(498, 401, 93, 30);
	    panel.add(checkJueves);
	    grupoDiasSemana.add(checkJueves);
	    
	    JRadioButton checkViernes = new JRadioButton("Viernes");
	    checkViernes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    checkViernes.setOpaque(false);
	    checkViernes.setBounds(591, 401, 93, 30);
	    panel.add(checkViernes);
	    grupoDiasSemana.add(checkViernes);
	    
	    int idDiaClase = clases.getIdDia();

	    // Seleccionar el JRadioButton correspondiente según el ID del día de la clase
	    switch (idDiaClase) {
	     case 1:
	    	 checkLunes.setSelected(true);
	         break;
	     case 2:
	    	 checkMartes.setSelected(true);
	         break;
	     case 3:
	    	 checkMiercoles.setSelected(true);
	         break;
	     case 4:
	    	 checkJueves.setSelected(true);
	         break;
	     case 5:
	    	 checkViernes.setSelected(true);
	         break;
	    }
	    
	    btnPagar = new JButton("Guardar cambios");
	    btnPagar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				//controlador.clases();
	    		clases.setNombre(textNombre.getText());
	    		int diaSeleccionado = 1;
	            if (checkLunes.isSelected()) {
	                diaSeleccionado = 1;
	            } else if (checkMartes.isSelected()) {
	                diaSeleccionado = 2;
	            } else if (checkMiercoles.isSelected()) {
	                diaSeleccionado = 3;
	            } else if (checkJueves.isSelected()) {
	                diaSeleccionado = 4;
	            } else if (checkViernes.isSelected()) {
	                diaSeleccionado = 5;
	            }
	            
	            clases.setIdDia(diaSeleccionado);
	            int indiceHorarioSeleccionado = comboBox.getSelectedIndex();
	            clases.setIdHorario(indiceHorarioSeleccionado+1);
	    		ClasesModelo.obtenerInstancia().editarClase(clases);
	    		controlador.clases();
	    		 
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
	            	 if (clases != null) {
							ClasesModelo.obtenerInstancia().eliminarClases(clases);
						}
	                 JOptionPane.showMessageDialog(null, "¡Clase eliminada con éxito!", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
					 controlador.clases();
	             }	 
	    	}
	    });
		btnRegistros.setForeground(new Color(255,255,255));
		btnRegistros.setFocusable(false);
		btnRegistros.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnRegistros.setBackground(colorBtnEliminar); 
		btnRegistros.setBounds(610, 580, 150, 40);
		panel.add(btnRegistros);
	    panelInscribirseDetallesClase(panel);

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
	

	public void configurarBotones(JButton btn) {
    	btn.setForeground(Color.black);
    	btn.setFont(new Font("Arial Black", Font.BOLD, 12));
    	btn.setFocusable(false);
    	btn.setBackground(new Color(217, 217, 217)); 
    }
	
	public void desactivarBoton(JButton btn) {
		btn.setEnabled(false);
		textID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textID.getText().isEmpty()) {
					btn.setEnabled(false);
				} else
					btn.setEnabled(true);
			}
		});
	}


}