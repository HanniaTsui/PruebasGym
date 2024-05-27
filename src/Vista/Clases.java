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
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ClasesControlador;
import controlador.MenuControlador;

public class Clases {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelCrear, panelSup;
	private final JPanel panelNegro = new JPanel();
	private JButton btnBuscar, btnVolver, btnPagar;
	JLabel lblTitulo, lblGym, lblPersona, lblCodigo, lblFecha, lblTlefono, lblCorreoElectrnico, lblFechaDeRegistro, lblMembresia, lblPeterParker, lblNewLabel;
	private JButton btnRegistros;
	 String ventanaActual;
	 private JLabel lblUsuariosInscritos, lblNewLabel_2, lblNewLabel_3,lblNewLabel_4, lblNewLabel_5;
	 private JButton btnEdit, btnEliminar_2;
	 Color colorBtnVolver = new Color(174,174,174);
	 Color colorBtnGuardar = new Color(0,47,78); 
	 Color colorBtnEliminar = new Color(0,0,0); 
	 Color colorBtnEditar = new Color(89,89,89); 

	 private ClasesControlador controlador;

	/**
	 * Create the frame.
	 */
	public Clases(ClasesControlador controlador) {
		
		this.controlador = controlador;
		
	}

	
	public JPanel clases() {
		JPanel panel = getMenu();

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
				controlador.nuevaClase();
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
					//controlador.
					controlador.detallesCliente();
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
					controlador.inscribirseClase();
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
	    
	    elementosDetallesNuevaClase(panel); //ComboBox, nombre, horario
	    
	    btnPagar = new JButton("Crear nueva clase");
	    btnPagar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		  controlador.clases();
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
	
	public JPanel inscribirseClase() {
		JPanel panel = getMenu();
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
	    btnBuscar.setIcon(new ImageIcon(Clases.class.getResource("/img/buscar.png")));
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
				controlador.inscribirseClase();
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
	
	public JPanel detallesClase(JPanel panel) { //EDITAR CLASE
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
	    
	   elementosDetallesNuevaClase(panel); // ComboBox, nombre, horario
	    
	    btnPagar = new JButton("Guardar cambios");
	    btnPagar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				controlador.clases();
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
	
	public void elementosDetallesNuevaClase(JPanel panel) { // Labels Horarios y dias
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

}
