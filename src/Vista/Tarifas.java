package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controlador.ClientesControlador;
import controlador.MenuControlador;
import controlador.TarifasControlador;

public class Tarifas {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelSup;
	JLabel lblTitulo, lblGym;
	private final JPanel panelNegro = new JPanel();
	 Color colorBtnVolver = new Color(174,174,174);
	 Color colorBtnGuardar = new Color(0,47,78); 
	 Color colorBtnEliminar = new Color(0,0,0); 
	 Color colorBtnEditar = new Color(89,89,89); 
	 private JTextField textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7, textField_8;
	 JButton btnVolver;

	 private TarifasControlador controlador;

	/**
	 * Create the frame.
	 */
	public Tarifas(TarifasControlador controlador) {
		
		this.controlador = controlador;
	}

	public JPanel tarifas() {
	    JPanel panel = getMenu();

	    JPanel panel_1 = new JPanel();
	    panel_1.setBounds(36, 170, 1120, 477);
	    panel.add(panel_1);
	    panel_1.setLayout(new GridLayout(0, 3, 15, 15));

	    JButton btnChecador = new JButton("Nueva membresía");
        btnChecador.setForeground(Color.WHITE);
        btnChecador.setFocusable(false);
        btnChecador.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				controlador.nuevaTarifa();
	    	}
	    });
        btnChecador.setBackground(colorBtnGuardar);
        btnChecador.setBounds(880, 114, 200, 30);
        panel.add(btnChecador);
        
        ArrayList<String> tiposDePlan = new ArrayList<>();
        tiposDePlan.add("Plan general");
        tiposDePlan.add("Plan familiar");
        tiposDePlan.add("Plan estudiante");
        tiposDePlan.add("Plan dúo");
        tiposDePlan.add("Plan visitante");

        ArrayList<String> detallesDePlan = new ArrayList<>();
        detallesDePlan.add("<br>Plan Estándar - $399/mes<br>$1,077/3Meses<br>$2,394/6Meses<br>$4788/1Año");
        detallesDePlan.add("<br>Plan Estándar - $799/mes<br>$2,097/3Meses<br>$4,194/6Meses<br>$8,388/1Año");
        detallesDePlan.add("<br>Plan Estándar - $599/mes<br>$1,797/3Meses<br>$3,594/6Meses<br>$7,188/1Año");
        detallesDePlan.add("<br>Plan Estándar - $299/mes<br>$897/3Meses<br>$1,794/6Meses<br>$3,588/1Año");
        detallesDePlan.add("<br>Plan Visitante - $50/Día");

        ArrayList<String> infoPlan = new ArrayList<>();
        infoPlan.add("Plan general\n" +
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
                + "- ¡Aprovecha el mayor ahorro y logra tus objetivos de fitness a largo plazo!");
        infoPlan.add("Plan Familiar\n"
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
                + "- ¡Aprovecha el mayor ahorro y motiva a tu familia a lograr sus objetivos de fitness a largo plazo!");
        infoPlan.add("Plan Estudiante\n" +
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
                "- ¡Aprovecha el mayor ahorro y mantente en forma durante todo el año académico!");
        infoPlan.add("Plan Dúo\n"
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
                + "- Elige el plan más largo para disfrutar de la experiencia completa.\n");
        infoPlan.add("Plan Visitante\n"
                + "Plan Visitante - $50/día:\n"
                + "- Acceso ilimitado a todas las instalaciones del gimnasio durante un día.\n"
                + "- Acceso a clases grupales durante el día de visita.\n"
                + "- Soporte continuo y atención al cliente durante la estancia.\n"
                + "- ¡Perfecto para probar nuestras instalaciones o disfrutar de un día de fitness!");

	    for (int i = 0; i < tiposDePlan.size(); i++) {
	    	int index = i;
	        JPanel panelTarifa = new JPanel(new BorderLayout());
	        JLabel info = new JLabel("<html><div style='text-align: center;'>" + tiposDePlan.get(i) + "<br>" + detallesDePlan.get(i) + "</div></html>");
	        info.setFont(new Font("Arial Black", Font.PLAIN, 16));
	        info.setBorder(BorderFactory.createLineBorder(Color.black, 3));
	        info.setHorizontalAlignment(SwingConstants.CENTER);
	        info.setVerticalAlignment(SwingConstants.CENTER);
	        info.setOpaque(true);
	        info.setForeground(Color.black);
	        info.setBackground(new Color(148, 182, 223));
	        panelTarifa.add(info, BorderLayout.CENTER);

	        JButton btnDetalles = new JButton("Detalles");
	        btnDetalles.setBackground(Color.black);
	        btnDetalles.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		JOptionPane.showMessageDialog(null, infoPlan.get(index));
		    	}
		    });
	        btnDetalles.setForeground(Color.white);
	        btnDetalles.setFocusable(false);
	        btnDetalles.setBorder(null);

	        JButton btnEditar = new JButton("Editar");
	        btnEditar.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
					controlador.editarTarifa();
		    	}
		    });
	        btnEditar.setForeground(Color.white);
	        btnEditar.setFocusable(false);
	        btnEditar.setBorder(null);
	        btnEditar.setBackground(colorBtnGuardar);

	        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 5, 5));
	        panelBotones.add(btnDetalles);
	        panelBotones.add(btnEditar);
	        panelBotones.setBackground(new Color(119, 182, 255));
	        panelTarifa.add(panelBotones, BorderLayout.SOUTH);

	        panel_1.add(panelTarifa);
	    }

	    JLabel lblTitutlo = new JLabel("Tarifas");
	    lblTitutlo.setForeground(new Color(0, 0, 0));
	    lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
	    lblTitutlo.setBounds(427, 114, 346, 33);
	    panel.add(lblTitutlo);
	    
	    return panel;
	}

	public JPanel editarTarifa() {
		JPanel panel = getMenu();

		elementosEditarNuevaTarifas(panel);
		JLabel lblTitutlo = new JLabel("Editar tarifa");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		
		JPanel panelAzul = new JPanel();
		panelAzul.setBackground(new Color(148, 182, 223));
		panelAzul.setBounds(650, 185, 441, 448);
		panel.add(panelAzul);
		panelAzul.setLayout(null);

		JButton btnGuardar_1 = new JButton("Guardar");
		btnGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "¡Nuevos cambios realizados con éxito!", "Edición exitosa", JOptionPane.INFORMATION_MESSAGE);
                controlador.tarifas();
			}
		});
		btnGuardar_1.setForeground(Color.WHITE);
		btnGuardar_1.setFocusable(false);
		btnGuardar_1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnGuardar_1.setBackground(colorBtnGuardar);
		btnGuardar_1.setBounds(194, 593, 120, 40);
		panel.add(btnGuardar_1);
		
		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta tarifa?", "Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);
	             if (op == JOptionPane.OK_OPTION) {
	                 JOptionPane.showMessageDialog(null, "Tarifa eliminada con éxito", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
					 controlador.tarifas();
	             }
			}
		});
		btnEliminar_1.setForeground(Color.white);
		btnEliminar_1.setFocusable(false);
		btnEliminar_1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnEliminar_1.setBackground(colorBtnEliminar);
		btnEliminar_1.setBounds(345, 593, 120, 40);
		panel.add(btnEliminar_1);

		return panel;
	}
	
	public void elementosEditarNuevaTarifas(JPanel panel) {
		btnVolver=new JButton("Volver");
	    btnVolver.setForeground(new Color(255, 255, 255));
	    btnVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				controlador.tarifas();
	    	}
	    });
	    btnVolver.setFocusable(false);
	    btnVolver.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	    btnVolver.setBackground(new Color(0,0,0)); 
	    btnVolver.setBounds(109, 114, 120, 40);
	    panel.add(btnVolver);
	    
	    JLabel lblNombrePlan = new JLabel("Nombre del plan:");
		lblNombrePlan.setBounds(109, 185, 189, 30);
		configurarLabelsDer(lblNombrePlan);
		panel.add(lblNombrePlan);
		
		JLabel lblPrecioMensual = new JLabel("Precio mensual:");
		lblPrecioMensual.setBounds(109, 235, 189, 30); configurarLabelsDer(lblPrecioMensual);
		panel.add(lblPrecioMensual);
		
		JLabel lblDescuentoMeses = new JLabel("Descuento 3 meses:");
		lblDescuentoMeses.setBounds(109, 285, 189, 30); configurarLabelsDer(lblDescuentoMeses);
		panel.add(lblDescuentoMeses);
		
		JLabel lblDescuentoMeses_1 = new JLabel("Descuento 6 meses:");
		lblDescuentoMeses_1.setBounds(109, 335, 189, 30); configurarLabelsDer(lblDescuentoMeses_1);
		panel.add(lblDescuentoMeses_1);
		
		JLabel lblDescuentoAo = new JLabel("Descuento 1 año:");
		lblDescuentoAo.setBounds(109, 385, 189, 30); configurarLabelsDer(lblDescuentoAo);
		panel.add(lblDescuentoAo);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(410, 284, 50, 30); configurarLabelsIzq(lblTotal);
		panel.add(lblTotal);
		
		JLabel lblTotal_1 = new JLabel("Total:");
		lblTotal_1.setBounds(410, 334, 50, 30); configurarLabelsIzq(lblTotal_1);
		panel.add(lblTotal_1);
		
		JLabel lblTotal_1_1 = new JLabel("Total:");
		lblTotal_1_1.setBounds(410, 384, 50, 30); configurarLabelsIzq(lblTotal_1_1);
		panel.add(lblTotal_1_1);
		
		JCheckBox box1 = new JCheckBox("Acceso ilimitado a las instalaciones del gimnasio.");
		box1.setFont(new Font("Arial Black", Font.PLAIN, 14));
		box1.setOpaque(false);
		box1.setFocusable(false);
		box1.setBounds(109, 445, 513, 20);
		panel.add(box1);
		
		JCheckBox box2 = new JCheckBox("Horario flexible, acceso al gimnasio 24/7.");
		box2.setFont(new Font("Arial Black", Font.PLAIN, 14));
		box2.setOpaque(false);
		box2.setFocusable(false);
		box2.setBounds(109, 485, 513, 20);
		panel.add(box2);
		
		JCheckBox box3 = new JCheckBox("Entrenador personal.");
		box3.setFont(new Font("Arial Black", Font.PLAIN, 14));
		box3.setOpaque(false);
		box3.setFocusable(false);
		box3.setBounds(109, 525, 513, 20);
		panel.add(box3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(320, 185, 230, 30);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(320, 235, 230, 30);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(320, 285, 70, 30);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(320, 335, 70, 30);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(320, 385, 70, 30);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(480, 285, 70, 30);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(480, 335, 70, 30);
		panel.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(480, 385, 70, 30);
		panel.add(textField_8);
	
	}
	public JPanel nuevaTarifa() {
		JPanel panel = getMenu();
		elementosEditarNuevaTarifas(panel);
		JLabel lblTitutlo = new JLabel("Nueva tarifa");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		
		JButton btnGuardar_1 = new JButton("Guardar");
		btnGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "¡Nueva tarifa agregada correctamente!", "Añadido exitoso", JOptionPane.INFORMATION_MESSAGE);
				controlador.tarifas();
			}
		});
		btnGuardar_1.setForeground(Color.WHITE);
		btnGuardar_1.setFocusable(false);
		btnGuardar_1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnGuardar_1.setBackground(colorBtnGuardar);
		btnGuardar_1.setBounds(465, 593, 120, 40);
		panel.add(btnGuardar_1);
		
		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta tarifa?", "Confirmar eliminación", JOptionPane.OK_CANCEL_OPTION);
	             if (op == JOptionPane.OK_OPTION) {
	                 JOptionPane.showMessageDialog(null, "Tarifa eliminada con éxito", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
	                 controlador.nuevaTarifa();
	             }
			}
		});
		btnEliminar_1.setForeground(Color.WHITE);
		btnEliminar_1.setFocusable(false);
		btnEliminar_1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
		btnEliminar_1.setBackground(colorBtnEliminar);
		btnEliminar_1.setBounds(615, 593, 120, 40);
		panel.add(btnEliminar_1);
		
		JLabel lblMesesInfo = new JLabel("3 meses info:");
		lblMesesInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMesesInfo.setForeground(Color.BLACK);
		lblMesesInfo.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblMesesInfo.setBounds(550, 185, 189, 30);
		panel.add(lblMesesInfo);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea.setBounds(761, 185, 230, 80);
		panel.add(textArea);
		
		JLabel lblMesesInfo_3 = new JLabel("6 meses info:");
		lblMesesInfo_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMesesInfo_3.setForeground(Color.BLACK);
		lblMesesInfo_3.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblMesesInfo_3.setBounds(550, 285, 189, 30);
		panel.add(lblMesesInfo_3);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea_1.setBounds(761, 285, 230, 80);
		panel.add(textArea_1);
		
		JLabel lblAoInfo = new JLabel("1 año info:");
		lblAoInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAoInfo.setForeground(Color.BLACK);
		lblAoInfo.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblAoInfo.setBounds(550, 385, 189, 30);
		panel.add(lblAoInfo);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea_2.setBounds(761, 385, 230, 80);
		panel.add(textArea_2);
		return panel;
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

	public JPanel getMenu() {
		MenuControlador menuControlador = new MenuControlador();
		return menuControlador.getPanelMenu();
	}
	

	public void configurarBotones(JButton btn) {
    	btn.setForeground(Color.black);
    	btn.setFont(new Font("Arial Black", Font.BOLD, 12));
    	btn.setFocusable(false);
    	btn.setBackground(new Color(217, 217, 217)); 
    }


}
