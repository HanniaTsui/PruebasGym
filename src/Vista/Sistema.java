package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Sistema extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelSup, panelTarifas, panelMenuVertical;

	private final JPanel panelNegro = new JPanel();
	JMenuBar menuBar;
	JLabel lblTitulo, lblGym;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
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
		
	   	menuPrincipal();
		//clientes();
		//menuVerticalClientes();
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
		JButton btnClases = new JButton("Clases");
		btnClases.setForeground(Color.WHITE);
		btnClases.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnClases.setFocusable(false);
		btnClases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				clases();
			}
		});
		btnClases.setBackground(Color.BLACK);
		btnClases.setBounds(466, 589, 266, 40);
		panel.add(btnClases);
		
		JButton btnChecador = new JButton("Checador");
		btnChecador.setForeground(Color.WHITE);
		btnChecador.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnChecador.setFocusable(false);
		btnChecador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				checador();
			}
		});
		btnChecador.setBackground(Color.BLACK);
		btnChecador.setBounds(832, 589, 266, 40);
		panel.add(btnChecador);
		
		JButton btnInstructores = new JButton("Instructores");
		btnInstructores.setForeground(Color.WHITE);
		btnInstructores.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnInstructores.setFocusable(false);
		btnInstructores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				instructor();
			}
		});
		btnInstructores.setBackground(Color.BLACK);
		btnInstructores.setBounds(100, 589, 266, 40);
		panel.add(btnInstructores);
		
		JButton btnTarifas = new JButton("Tarifas");
		btnTarifas.setForeground(Color.WHITE);
		btnTarifas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnTarifas.setFocusable(false);
		btnTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				tarifas();
			}
		});
		btnTarifas.setBackground(Color.BLACK);
		btnTarifas.setBounds(832, 302, 266, 40);
		panel.add(btnTarifas);
		
		JButton btn = new JButton("Clientes");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				clientes();
			}
		});
		btn.setFocusable(false);
		btn.setForeground(new Color(255, 255, 255));
		btn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btn.setBackground(new Color(0, 0, 0));
		btn.setBounds(466, 302, 266, 40);
		panel.add(btn);
	
		
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
		
		JLabel lblColor = new JLabel("");
		lblColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor.setIcon(new ImageIcon(Sistema.class.getResource("/img/clientesLogoMenu.png")));
		lblColor.setBackground(new Color(119, 182, 255));
		lblColor.setOpaque(true);
		lblColor.setBounds(466, 112, 266, 230);
		panel.add(lblColor);
		
		JLabel lblColor_1 = new JLabel("");
		lblColor_1.setVerticalAlignment(SwingConstants.TOP);
		lblColor_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor_1.setIcon(new ImageIcon(Sistema.class.getResource("/img/tarifasMenu.png")));
		lblColor_1.setOpaque(true);
		lblColor_1.setBackground(new Color(119, 182, 255));
		lblColor_1.setBounds(832, 112, 266, 230);
		panel.add(lblColor_1);
		
		JLabel lblColor_2 = new JLabel("");
		lblColor_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor_2.setIcon(new ImageIcon(Sistema.class.getResource("/img/InstructorMenu.png")));
		lblColor_2.setOpaque(true);
		lblColor_2.setBackground(new Color(119, 182, 255));
		lblColor_2.setBounds(100, 399, 266, 230);
		panel.add(lblColor_2);
		
		JLabel lblColor_3 = new JLabel("");
		lblColor_3.setVerticalAlignment(SwingConstants.TOP);
		lblColor_3.setIcon(new ImageIcon(Sistema.class.getResource("/img/clasesMenu.png")));
		lblColor_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor_3.setOpaque(true);
		lblColor_3.setBackground(new Color(119, 182, 255));
		lblColor_3.setBounds(466, 399, 266, 230);
		panel.add(lblColor_3);
		
		JLabel lblColor_4 = new JLabel("");
		lblColor_4.setIcon(new ImageIcon(Sistema.class.getResource("/img/checadorMenu.png")));
		lblColor_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor_4.setOpaque(true);
		lblColor_4.setBackground(new Color(119, 182, 255));
		lblColor_4.setBounds(832, 399, 266, 230);
		panel.add(lblColor_4);
		
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
		lblTitulo.setFont(new Font("Forte", Font.BOLD, 38));
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(20, 3, 131, 50);
		panelNegro.add(lblTitulo);
		
		lblGym = new JLabel("Gym");
		lblGym.setBackground(new Color(255, 255, 255));
		lblGym.setForeground(new Color(0, 124, 163));
		lblGym.setFont(new Font("Forte", Font.BOLD, 38));
		lblGym.setBounds(155, 3, 97, 50);
		panelNegro.add(lblGym);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(148, 42, 101, 22);
		panelSup.add(menuBar, BorderLayout.SOUTH);
		menuBar.add(Box.createHorizontalStrut(100)); //Separar cada menu
		JMenuItem menuInicio = new JMenuItem("Inicio");
		menuInicio.setOpaque(false);
		menuInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				menuPrincipal();
			}
		});
		menuBar.add(menuInicio);
		
		menuBar.add(Box.createHorizontalStrut(100)); //Separar cada menu
		
		JMenuItem menuClientes = new JMenuItem("Clientes");
		menuClientes.setOpaque(false);
		menuClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				clientes();
			}
		});
		menuBar.add(menuClientes);
		menuBar.add(Box.createHorizontalStrut(100)); //Separar cada menu
		JMenuItem menuTarifas = new JMenuItem("Tarifas");
		menuTarifas.setOpaque(false);
		menuTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				tarifas();
				
			}
		});
		menuBar.add(menuTarifas);
		menuBar.add(Box.createHorizontalStrut(80)); //Separar cada menu
		JMenuItem menuInst = new JMenuItem("Instructores");
		menuInst.setOpaque(false);
		menuInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				instructor();
			}
		});
		menuBar.add(menuInst);
		menuBar.add(Box.createHorizontalStrut(80)); //Separar cada menu
		JMenuItem menuClases = new JMenuItem("Clases");
		menuClases.setOpaque(false);
		menuClases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				clases();
			}
		});
		menuBar.add(menuClases);
		menuBar.add(Box.createHorizontalStrut(100)); //Separar cada menu
		JMenuItem menuCheck = new JMenuItem("Checador");
		menuCheck.setOpaque(false);
		menuCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarComponentes();
				checador();
			}
		});
		menuBar.add(menuCheck);
		menuBar.add(Box.createHorizontalStrut(100)); //Separar cada menu
		JMenuItem menuSalir = new JMenuItem("Salir");
		menuSalir.setOpaque(false);
		menuSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inicio i1 = new Inicio();
		        i1.setVisible(true);
			}
		});
		menuBar.add(menuSalir);
	
	}

	public void quitarComponentes() {
		contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
	}
	
	public void clientes() {

		menuVerticalClientes();
		panel();
		menuB();
		JLabel lblTitutlo = new JLabel("Clientes registrados");
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Forte", Font.PLAIN, 25));
		lblTitutlo.setBounds(572, 120, 225, 33);
		panel.add(lblTitutlo);
		
		String titles[]= {"ID", "Nombre", "Apellido", "Correo", "Telefono", "Fecha de ingreso", "Tipo de membresía", "Estado"};
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
		btnFiltro.setForeground(Color.black);
		btnFiltro.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		btnFiltro.setBounds(943, 120, 155, 30);
		panel.add(btnFiltro);
	}
	
	public void tarifas() {
		panel();
		menuB();
		JButton btnChecador = new JButton("Nueva membresía");
		btnChecador.setForeground(Color.WHITE);
		btnChecador.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnChecador.setFocusable(false);
		btnChecador.setBackground(Color.BLACK);
		btnChecador.setBounds(832, 112, 266, 40);
		panel.add(btnChecador);
		
		panelTarifas = new JPanel();
		panelTarifas.setBackground(new Color(255, 255, 255));
		panelTarifas.setBounds(86, 210, 1000, 400);
		panel.add(panelTarifas);
		panelTarifas.setLayout(new GridLayout(0,3, 15, 15));
		
		 String[] tiposDePlan = {"Plan general", "Plan familiar", "Plan estudiante", "Plan dúo", "Plan visitante"};
		 String[] detallesDePlan = {
				 "<br>Plan Estándar - $399/mes<br>$1,077/3Meses<br>$2,394/6Meses<br>$4788/1Año",
		            "<br>Plan Estándar - $799/mes<br>$2,097/3Meses<br>$4,194/6Meses<br>$8,388/1Año",
		            "<br>Plan Estándar - $599/mes<br>$1,797/3Meses<br>$3,594/6Meses<br>$7,188/1Año",
		            "<br>Plan Estándar - $299/mes<br>$897/3Meses<br>$1,794/6Meses<br>$3,588/1Año",
		            "<br>Plan Visitante - $50/Día"
		        };
		 for (int i = 0; i < tiposDePlan.length; i++) {
	            String tipoPlan = tiposDePlan[i];
	            String detallePlan = detallesDePlan[i];

	            // Crear un panel para contener el texto y los botones
	            JPanel panelPlan = new JPanel(new BorderLayout());
	            panelPlan.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde para mejorar la apariencia

	            // Crear el label con el texto del plan
	            JLabel lblPlan = new JLabel("<html><div style='text-align: center;'>" + tipoPlan + "<br>" + detallePlan + "</div></html>");
	            lblPlan.setFont(new Font("Tahoma", Font.PLAIN, 18));
	            lblPlan.setBorder(BorderFactory.createLineBorder(Color.black, 3));
	            lblPlan.setHorizontalAlignment(SwingConstants.CENTER);
	            lblPlan.setVerticalAlignment(SwingConstants.CENTER);
	            lblPlan.setOpaque(true);
	            lblPlan.setForeground(Color.white);
	            lblPlan.setBackground(new Color(119, 182, 255));
	            panelPlan.add(lblPlan, BorderLayout.CENTER);

	            JButton btnDetalles = new JButton("Detalles");
	            btnDetalles.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    // Mostrar los detalles ampliados en un cuadro de diálogo
	                    JOptionPane.showMessageDialog(null, getDetallesAmpliados(tipoPlan));
	                }
	            });
	            btnDetalles.setBackground(Color.black); btnDetalles.setForeground(Color.white); btnDetalles.setFocusable(false); btnDetalles.setBorder(null);
	            JButton btnEditar = new JButton("Editar"); btnEditar.setForeground(Color.white); btnEditar.setFocusable(false);  btnEditar.setBorder(null);
	            btnEditar.setBackground(new Color(0,33,83));
	            btnEditar.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                   panel.revalidate();
	                   panel.repaint();
	                }
	            });

	            JPanel panelBotones = new JPanel(new GridLayout(1, 2, 5, 5)); 
	            panelBotones.add(btnDetalles);
	            panelBotones.add(btnEditar);
	            panelBotones.setBackground(new Color(119, 182, 255));
	            panelPlan.add(panelBotones, BorderLayout.SOUTH);

	            panelTarifas.add(panelPlan);
	        }

	}
	
	private String getDetallesAmpliados(String tipoPlan) {
        // Aquí puedes colocar la lógica para obtener los detalles ampliados del plan según el tipo
        // Por ahora, devolvemos un texto de ejemplo
        if (tipoPlan.equals("Plan estudiante")) {
            return "Plan Estudiante\n" +
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
                    "- ¡Aprovecha el mayor ahorro y mantente en forma durante todo el año académico!";
        } else  if (tipoPlan.equals("Plan general")) {
        	return "Plan general\n" +
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
                    + "- ¡Aprovecha el mayor ahorro y logra tus objetivos de fitness a largo plazo!";
        } else  if (tipoPlan.equals("Plan familiar")) {
        	return "Plan Familiar\n"
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
        			+ "- ¡Aprovecha el mayor ahorro y motiva a tu familia a lograr sus objetivos de fitness a largo plazo!";
        } else  if (tipoPlan.equals("Plan dúo")) {
        	return "Plan Dúo\n"
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
        			+ "- Elige el plan más largo para disfrutar de la experiencia completa.\n";
        } else  if (tipoPlan.equals("Plan visitante")) {
        	return "Plan Visitante\n"
        			+ "Plan Visitante - $50/día:\n"
        			+ "- Acceso ilimitado a todas las instalaciones del gimnasio durante un día.\n"
        			+ "- Acceso a clases grupales durante el día de visita.\n"
        			+ "- Soporte continuo y atención al cliente durante la estancia.\n"
        			+ "- ¡Perfecto para probar nuestras instalaciones o disfrutar de un día de fitness!";
        } 
        else 
        	return tipoPlan;
    }
	public void instructor() {
		panel();
		menuB();
	}
	public void checador() {
		panel();
		menuB();
	}
	public void clases() {
		panel();
		menuB();
	}
	
	
	public void menuVerticalClientes()	{
		panelMenuVertical = new JPanel();
		panelMenuVertical.setBackground(Color.black);
		contentPane.add(panelMenuVertical);
		panelMenuVertical.setSize(170,620);
		panelMenuVertical.setLocation(0,70);
		panelMenuVertical.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JMenuBar menuBarV2 = new JMenuBar();
		menuBarV2.setBorder(null);
		menuBarV2.setBackground(Color.black);
		menuBarV2.setLayout(new GridLayout(0, 1)); 
		panelMenuVertical.add(menuBarV2);
		
		JMenuItem itemE_1 = new JMenuItem("<html><div style='text-align: center;'><br>Consultar<br>registros</div></html>");
		itemE_1.setHorizontalAlignment(SwingConstants.CENTER);
		itemE_1.setFont(new Font("Forte", Font.PLAIN, 25));
		itemE_1.setForeground(new Color(55,171,255));
		itemE_1.setOpaque(false);
		itemE_1.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
		        }
        });
		menuBarV2.add(itemE_1);
		
		JMenuItem itemE_2 = new JMenuItem("<html><div style='text-align: center;'><br>Detalles del<br>cliente<br></div></html>");
		itemE_2.setHorizontalAlignment(SwingConstants.CENTER);
		itemE_2.setFont(new Font("Forte", Font.PLAIN, 25));
		itemE_2.setForeground(new Color(55,171,255));
		itemE_2.setOpaque(false);
		itemE_2.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
        	}
        });
		menuBarV2.add(itemE_2);
		
		JMenuItem itemE_3 = new JMenuItem("Crear");
		itemE_3.setHorizontalAlignment(SwingConstants.CENTER);
		itemE_3.setFont(new Font("Forte", Font.PLAIN, 25));
		itemE_3.setForeground(new Color(55,171,255));
		itemE_3.setOpaque(false);
		itemE_3.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
        	}
        });
		menuBarV2.add(itemE_3);
		
		JMenuItem itemE_4 = new JMenuItem("Editar");
		itemE_4.setHorizontalAlignment(SwingConstants.CENTER);
		itemE_4.setFont(new Font("Forte", Font.PLAIN, 25));
		itemE_4.setForeground(new Color(55,171,255));
		itemE_4.setOpaque(false);
		itemE_4.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
        	}
        });
		menuBarV2.add(itemE_4);
		
		JMenuItem itemE_5 = new JMenuItem("Eliminar");
		itemE_5.setHorizontalAlignment(SwingConstants.CENTER);
		itemE_5.setFont(new Font("Forte", Font.PLAIN, 25));
		itemE_5.setForeground(new Color(55,171,255));
		itemE_5.setOpaque(false);
		itemE_5.addActionListener(new ActionListener()	{
        	public void actionPerformed(ActionEvent e) {
        		quitarComponentes();
        	}
        });
		menuBarV2.add(itemE_5);
	}
}
