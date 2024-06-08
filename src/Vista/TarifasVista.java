package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Modelo.*;
import controlador.MenuControlador;
import controlador.TarifasControlador;

public class TarifasVista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panelSup;
	JLabel lblTitulo, lblGym;
	private final JPanel panelNegro = new JPanel();
	 Color colorBtnVolver = new Color(174,174,174);
	 Color colorBtnGuardar = new Color(0,47,78); 
	 Color colorBtnEliminar = new Color(0,0,0); 
	 Color colorBtnEditar = new Color(89,89,89); 
	 private JTextField textNombreTarifa, textPrecioMensual, textDesc3, textDesc6, textDesc1, textPrecio3, textPrecio6, textPrecio1;
	 JButton btnVolver;
	 private ArrayList<String> tiposDePlan;
	 private Map<String, String> tarifasMap = new HashMap<>();
	JCheckBox box1;
	JCheckBox box3;
	JCheckBox box2;


	 private TarifasControlador controlador;
	private JTextArea infor3Meses;
	private JTextArea infor6Meses;
	private JTextArea info1anio;

	/**
	 * Create the frame.
	 */
	public TarifasVista(TarifasControlador controlador) {
		
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

		TarifaModelo.cargarTarifas();
		List<TarifaObj> tarifaObjList = TarifaModelo.getTarifa();

		ArrayList<String> detallesDePlan = new ArrayList<>();
		ArrayList<String> infoPlan = new ArrayList<>();

		for (TarifaObj tarifaObj : tarifaObjList) {
			double tarifa3 = (tarifaObj.getPlan().getPrecio() * 3) - ((tarifaObj.getPlan().getPrecio() / 100) * tarifaObj.getDescuento().getPorcentaje3meses());
			double tarifa6 = (tarifaObj.getPlan().getPrecio() * 6) - ((tarifaObj.getPlan().getPrecio() / 100) * tarifaObj.getDescuento().getPorcentaje6meses());
			double tarifa1anio = (tarifaObj.getPlan().getPrecio() * 12) - ((tarifaObj.getPlan().getPrecio() / 100) * tarifaObj.getDescuento().getPorcentaje1anio());

			detallesDePlan.add("<br>" + tarifaObj.getPlan().getNombre() +" - $" + tarifaObj.getPlan().getPrecio() +"/mes<br>" + tarifa3 +"/3Meses<br>$" + tarifa6 +"/6Meses<br>$"+ tarifa1anio+"/1Año");

			String[] beneficios = tarifaObj.getServicios().getBeneficio().split(",");
			String infoPlanStr = tarifaObj.getPlan().getNombre() + "\n" +
					"Plan Estándar - $" + tarifaObj.getPlan().getPrecio() + " /mes:\n";

			for (String beneficio: beneficios) {
				infoPlanStr += "- " + beneficio + "\n";
			}

			infoPlanStr+= "Plan Estándar - Trimestral ($"+ tarifa3 +"):\n"
					+ tarifaObj.getServicios().getDescripcion3meses().replace(",", "\n") + "\n\n"
					+ "Plan Estándar - 6 Meses ($"+tarifa6+"):\n"
					+ tarifaObj.getServicios().getDescripcion6meses().replace(",", "\n") + "\n\n"
					+ "Plan Estándar - Anual ($"+tarifa1anio+"):\n"
					+ tarifaObj.getServicios().getDescripcion1anio().replace(",", "\n");

			infoPlan.add(infoPlanStr);
		}


        for (int i = 0; i < Math.min(tarifaObjList.size(), Math.min(detallesDePlan.size(), infoPlan.size())); i++) {
            int index = i;
            JPanel panelTarifa = new JPanel(new BorderLayout());

			JPopupMenu popupMenu = new JPopupMenu();
			JMenuItem deleteItem = new JMenuItem("Eliminar");

			int finalI1 = i;
			deleteItem.addActionListener(e -> {
				TarifaModelo.removerTarifa(tarifaObjList.get(finalI1));
				JOptionPane.showMessageDialog(null, "Se elimino correctamente");
				controlador.tarifas();
			});

			popupMenu.add(deleteItem);
            
            JLabel info = new JLabel("<html><div style='text-align: center;'>" + tarifaObjList.get(i).getPlan().getNombre() + "<br>" + detallesDePlan.get(i) + "</div></html>");
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
			btnDetalles.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (e.isPopupTrigger()) {
						popupMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						popupMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
            btnDetalles.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, infoPlan.get(index));
                }
            });
            btnDetalles.setForeground(Color.white);
            btnDetalles.setFocusable(false);
            btnDetalles.setBorder(null);

            JButton btnEditar = new JButton("Editar");
			int finalI = i;
			btnEditar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controlador.editarTarifa(tarifaObjList.get(finalI));
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

	public JPanel editarTarifa(TarifaObj tarifa) {
		JPanel panel = getMenu();

		elementosEditarNuevaTarifas(panel, tarifa);
		JLabel lblTitutlo = new JLabel("Editar tarifa");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		
		
		
		JLabel panelAzul = new JLabel();
		panelAzul.setBackground(new Color(148, 182, 223));
		panelAzul.setOpaque(true);
		panelAzul.setIcon(new ImageIcon(TarifasVista.class.getResource("/img/logoTarifasPanelAzul.png")));
		panelAzul.setBounds(650, 185, 441, 448);
		panel.add(panelAzul);
		


		JButton btnGuardar_1 = new JButton("Guardar");
		btnGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camposVacios();
				if (textNombreTarifa.getText().isEmpty()) {
					System.out.println("Esta vacio");
					return;
				}
				double tarifaNueva;
				try {
					tarifaNueva = Double.parseDouble(textPrecioMensual.getText());
				} catch (NumberFormatException ignored) {
					System.out.println("No es un numero");
					return;
				}

				int descuento1;
				try {
					descuento1 = Integer.parseInt(textDesc1.getText());
				} catch (NumberFormatException ignored) {
					System.out.println("No es un numero");
					return;
				}

				int descuento3;
				try {
					descuento3 = Integer.parseInt(textDesc3.getText());
				} catch (NumberFormatException ignored) {
					System.out.println("No es un numero");
					return;
				}

				int descuento6;
				try {
					descuento6 = Integer.parseInt(textDesc6.getText());
				} catch (NumberFormatException ignored) {
					System.out.println("No es un numero");
					return;
				}

				String beneficios = "";

				if (box1.isSelected()) {
					beneficios += box1.getText() + ", ";
				}

				if (box2.isSelected()) {
					beneficios += box2.getText() + ", ";
				}

				if (box3.isSelected()) {
					beneficios += box3.getText() + ", ";
				}

				tarifa.getPlan().setNombre(textNombreTarifa.getText());
				tarifa.getPlan().setPrecio(tarifaNueva);
				tarifa.getDescuento().setPorcentaje1anio(descuento1);
				tarifa.getDescuento().setPorcentaje3meses(descuento3);
				tarifa.getDescuento().setPorcentaje6meses(descuento6);
				tarifa.getServicios().setBeneficio(beneficios);


				
				TarifaModelo.actualizarTarifa(tarifa);
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
	
	public void camposVacios() {
		textNombreTarifa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textPrecioMensual.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textDesc1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textDesc3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textDesc6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		boolean camposVacios = false;
		if (textNombreTarifa.getText().isEmpty()) {
			textNombreTarifa.setBorder(new LineBorder(Color.RED, 1));
			camposVacios=true;
		}
		if (textPrecioMensual.getText().isEmpty()) {
            textPrecioMensual.setBorder(BorderFactory.createLineBorder(Color.RED));
            camposVacios = true;
        } 
        if (textDesc1.getText().isEmpty()) {
            textDesc1.setBorder(BorderFactory.createLineBorder(Color.RED));
            camposVacios = true;
        } 
        if (textDesc3.getText().isEmpty()) {
            textDesc3.setBorder(BorderFactory.createLineBorder(Color.RED));
            camposVacios = true;
        }
        if (textDesc6.getText().isEmpty()) {
            textDesc6.setBorder(BorderFactory.createLineBorder(Color.RED));
            camposVacios = true;
        }
        if (camposVacios) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.WARNING_MESSAGE);
            return; // 
        }
	}
	public void elementosEditarNuevaTarifas(JPanel panel, TarifaObj tarifa) {
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
		lblNombrePlan.setBounds(109, 185, 200, 30);
		configurarLabelsDer(lblNombrePlan);
		panel.add(lblNombrePlan);
		
		JLabel lblPrecioMensual = new JLabel("Precio mensual:");
		lblPrecioMensual.setBounds(109, 235, 200, 30); configurarLabelsDer(lblPrecioMensual);
		panel.add(lblPrecioMensual);
		
		JLabel lblDescuentoMeses = new JLabel("Descuento 3 meses (%):");
		lblDescuentoMeses.setBounds(109, 285, 200, 30); configurarLabelsDer(lblDescuentoMeses);
		panel.add(lblDescuentoMeses);
		
		JLabel lblDescuentoMeses_1 = new JLabel("Descuento 6 meses (%):");
		lblDescuentoMeses_1.setBounds(109, 335, 200, 30); configurarLabelsDer(lblDescuentoMeses_1);
		panel.add(lblDescuentoMeses_1);
		
		JLabel lblDescuentoAo = new JLabel("Descuento 1 año (%):");
		lblDescuentoAo.setBounds(109, 385, 200, 30); configurarLabelsDer(lblDescuentoAo);
		panel.add(lblDescuentoAo);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(423, 284, 50, 30); configurarLabelsIzq(lblTotal);
		panel.add(lblTotal);
		
		JLabel lblTotal_1 = new JLabel("Total:");
		lblTotal_1.setBounds(423, 334, 50, 30); configurarLabelsIzq(lblTotal_1);
		panel.add(lblTotal_1);
		
		JLabel lblTotal_1_1 = new JLabel("Total:");
		lblTotal_1_1.setBounds(423, 384, 50, 30); configurarLabelsIzq(lblTotal_1_1);
		panel.add(lblTotal_1_1);
		
		box1 = new JCheckBox("Acceso ilimitado a las instalaciones del gimnasio.");
		box1.setFont(new Font("Arial Black", Font.PLAIN, 14));
		box1.setOpaque(false);
		box1.setFocusable(false);
		box1.setBounds(109, 445, 513, 20);
		panel.add(box1);
		
		box2 = new JCheckBox("Horario flexible, acceso al gimnasio 24/7.");
		box2.setFont(new Font("Arial Black", Font.PLAIN, 14));
		box2.setOpaque(false);
		box2.setFocusable(false);
		box2.setBounds(109, 485, 513, 20);
		panel.add(box2);
		
		box3 = new JCheckBox("Entrenador personal.");
		box3.setFont(new Font("Arial Black", Font.PLAIN, 14));
		box3.setOpaque(false);
		box3.setFocusable(false);
		box3.setBounds(109, 525, 513, 20);
		panel.add(box3);
		
		textNombreTarifa = new JTextField();
		textNombreTarifa.setBounds(331, 185, 230, 30);
		panel.add(textNombreTarifa);
		textNombreTarifa.setColumns(10);
		
		textPrecioMensual = new JTextField();
		textPrecioMensual.setColumns(10);
		textPrecioMensual.setBounds(331, 235, 230, 30);
		validacionNumerica(textPrecioMensual);
		panel.add(textPrecioMensual);
		
		textDesc3 = new JTextField();
		textDesc3.setBounds(331, 285, 70, 30);
		validacionNumerica(textDesc3);
		textDesc3.addActionListener(e -> {
			if (textDesc3.getText().isEmpty()) {
				textPrecio3.setText("");
				return;
			}

			try {
				double precio = Double.parseDouble(textPrecioMensual.getText());
				double precioNuevo = (precio * 3) - ((precio / 100) * Integer.parseInt(textDesc3.getText()));

				textPrecio3.setText(String.valueOf(precioNuevo));
			} catch (NumberFormatException numberFormatException) {
			}
		});
		panel.add(textDesc3);
		textDesc3.setColumns(10);
		
		textDesc6 = new JTextField();
		textDesc6.setColumns(10);
		textDesc6.addActionListener(e -> {
			if (textDesc6.getText().isEmpty()) {
				textPrecio6.setText("");
				return;
			}


			try {
				double precio = Double.parseDouble(textPrecioMensual.getText());
				double precioNuevo = (precio * 6) - ((precio / 100) * Integer.parseInt(textDesc6.getText()));

				textPrecio6.setText(String.valueOf(precioNuevo));
			} catch (NumberFormatException numberFormatException) {
			}
		});
		textDesc6.setBounds(331, 335, 70, 30);
		validacionNumerica(textDesc6);
		panel.add(textDesc6);
		
		textDesc1 = new JTextField();
		textDesc1.setColumns(10);
		textDesc1.addActionListener(e -> {
			if (textDesc1.getText().isEmpty()) {
				textPrecio1.setText("");
				return;
			}

			try {
				double precio = Double.parseDouble(textPrecioMensual.getText());
				double precioNuevo = (precio * 12) - ((precio / 100) * Integer.parseInt(textDesc1.getText()));

				textPrecio1.setText(String.valueOf(precioNuevo));
			} catch (NumberFormatException numberFormatException) {
			}
		});
		validacionNumerica(textDesc1);
		textDesc1.setBounds(331, 385, 70, 30);
		panel.add(textDesc1);
		
		textPrecio3 = new JTextField();
		textPrecio3.setEditable(false);
		textPrecio3.setColumns(10);
		textPrecio3.setBounds(490, 285, 70, 30);
		validacionNumerica(textPrecio3);
		panel.add(textPrecio3);
		
		textPrecio6 = new JTextField();
		textPrecio6.setColumns(10);
		textPrecio6.setEditable(false);
		textPrecio6.setBounds(490, 335, 70, 30);
		validacionNumerica(textPrecio6);
		panel.add(textPrecio6);
		
		textPrecio1 = new JTextField();
		textPrecio1.setColumns(10);
		textPrecio1.setEditable(false);
		validacionNumerica(textPrecio1);
		textPrecio1.setBounds(490, 385, 70, 30);
		panel.add(textPrecio1);

		if (tarifa != null) {
			textNombreTarifa.setText(tarifa.getPlan().getNombre());
			textPrecioMensual.setText(String.valueOf(tarifa.getPlan().getPrecio()));
			textDesc1.setText(String.valueOf(tarifa.getDescuento().getPorcentaje1anio()));
			textDesc3.setText(String.valueOf(tarifa.getDescuento().getPorcentaje3meses()));
			textDesc6.setText(String.valueOf(tarifa.getDescuento().getPorcentaje6meses()));

			double tarifa3 = (tarifa.getPlan().getPrecio() * 3) - ((tarifa.getPlan().getPrecio() / 100) * tarifa.getDescuento().getPorcentaje3meses());
			double tarifa6 = (tarifa.getPlan().getPrecio() * 6) - ((tarifa.getPlan().getPrecio() / 100) * tarifa.getDescuento().getPorcentaje6meses());
			double tarifa1anio = (tarifa.getPlan().getPrecio() * 12) - ((tarifa.getPlan().getPrecio() / 100) * tarifa.getDescuento().getPorcentaje1anio());

			textPrecio1.setText(String.valueOf(tarifa1anio));
			textPrecio3.setText(String.valueOf(tarifa3));
			textPrecio6.setText(String.valueOf(tarifa6));

			String[] beneficios = tarifa.getServicios().getBeneficio().split(",");

			for (String bene: beneficios) {
				if (bene.length() < 5) {
					continue;
				}

				if (box3.getText().toUpperCase(Locale.ROOT).contains(bene.substring(0, 5).toUpperCase(Locale.ROOT))) {
					box3.setSelected(true);
				}
				if (box2.getText().toUpperCase(Locale.ROOT).contains(bene.substring(0, 5).toUpperCase(Locale.ROOT))) {
					box2.setSelected(true);
				}

				if (box1.getText().toUpperCase(Locale.ROOT).contains(bene.substring(0, 5).toUpperCase(Locale.ROOT))) {
					box1.setSelected(true);
				}
			}
		}
	}

	public JPanel nuevaTarifa() {
		JPanel panel = getMenu();
		elementosEditarNuevaTarifas(panel, null);
		JLabel lblTitutlo = new JLabel("Nueva tarifa");
		lblTitutlo.setForeground(new Color(0, 0, 0));
		lblTitutlo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitutlo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblTitutlo.setBounds(427, 114, 346, 33);
		panel.add(lblTitutlo);
		
		JButton btnGuardar_1 = new JButton("Guardar");
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
		
		infor3Meses = new JTextArea();
		infor3Meses.setBorder(new LineBorder(new Color(0, 0, 0)));
		infor3Meses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		infor3Meses.setBounds(761, 185, 330, 80);
		panel.add(infor3Meses);
		infor3Meses.setLineWrap(true);
		infor3Meses.setWrapStyleWord(true);
		
		JLabel lblMesesInfo_3 = new JLabel("6 meses info:");
		lblMesesInfo_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMesesInfo_3.setForeground(Color.BLACK);
		lblMesesInfo_3.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblMesesInfo_3.setBounds(550, 285, 189, 30);
		panel.add(lblMesesInfo_3);
		
		infor6Meses = new JTextArea();
		infor6Meses.setBorder(new LineBorder(new Color(0, 0, 0)));
		infor6Meses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		infor6Meses.setBounds(761, 285, 330, 80);
		panel.add(infor6Meses);
		infor6Meses.setLineWrap(true);
		infor6Meses.setWrapStyleWord(true);
		
		JLabel lblAoInfo = new JLabel("1 año info:");
		lblAoInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAoInfo.setForeground(Color.BLACK);
		lblAoInfo.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblAoInfo.setBounds(550, 385, 189, 30);
		panel.add(lblAoInfo);
		
		info1anio = new JTextArea();
		info1anio.setBorder(new LineBorder(new Color(0, 0, 0)));
		info1anio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		info1anio.setBounds(761, 385, 330, 80);
		panel.add(info1anio);
		info1anio.setLineWrap(true);
		info1anio.setWrapStyleWord(true);

		btnGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNombreTarifa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				info1anio.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				infor3Meses.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				infor6Meses.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				textPrecioMensual.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				textDesc1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				textDesc3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				textDesc6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				boolean camposVacios = false;
				if (textNombreTarifa.getText().isEmpty()) {
					textNombreTarifa.setBorder(new LineBorder(Color.RED, 1));
					camposVacios=true;
				}
				if (info1anio.getText().isEmpty()) {
					info1anio.setBorder(new LineBorder(Color.RED, 1));
					camposVacios=true;
				}
				if (infor3Meses.getText().isEmpty()) {
					infor3Meses.setBorder(new LineBorder(Color.RED, 1));
					camposVacios=true;
				}
				if (infor6Meses.getText().isEmpty()) {
					infor6Meses.setBorder(new LineBorder(Color.RED, 1));
					camposVacios=true;
				}
				if (textPrecioMensual.getText().isEmpty()) {
		            textPrecioMensual.setBorder(BorderFactory.createLineBorder(Color.RED));
		            camposVacios = true;
		        } 
		        if (textDesc1.getText().isEmpty()) {
		            textDesc1.setBorder(BorderFactory.createLineBorder(Color.RED));
		            camposVacios = true;
		        } 
		        if (textDesc3.getText().isEmpty()) {
		            textDesc3.setBorder(BorderFactory.createLineBorder(Color.RED));
		            camposVacios = true;
		        }
		        if (textDesc6.getText().isEmpty()) {
		            textDesc6.setBorder(BorderFactory.createLineBorder(Color.RED));
		            camposVacios = true;
		        } 

				double tarifaNueva;
				try {
					tarifaNueva = Double.parseDouble(textPrecioMensual.getText());
				} catch (NumberFormatException ignored) {
					System.out.println("No es un numero");
					return;
				}

				int descuento1;
				try {
					descuento1 = Integer.parseInt(textDesc1.getText());
				} catch (NumberFormatException ignored) {
					System.out.println("No es un numero");
					return;
				}

				int descuento3;
				try {
					descuento3 = Integer.parseInt(textDesc3.getText());
				} catch (NumberFormatException ignored) {
					System.out.println("No es un numero");
					return;
				}

				int descuento6;
				try {
					descuento6 = Integer.parseInt(textDesc6.getText());
				} catch (NumberFormatException ignored) {
					System.out.println("No es un numero");
					return;
				}

				String beneficios = "";

				if (box1.isSelected()) {
					beneficios += box1.getText() + ", ";
				}

				if (box2.isSelected()) {
					beneficios += box2.getText() + ", ";
				}

				if (box3.isSelected()) {
					beneficios += box3.getText() + ", ";
				}

				int idPlanes = 1;
				int idServicio = 1;
				int idDescuento = 1;

				if (!PlanesModelo.getPlanes().isEmpty()) {
					idPlanes = PlanesModelo.getPlanes().getLast().getID() + 1;
				}

				if (!ServicioModelo.getServicioObjList().isEmpty()) {
					idServicio = ServicioModelo.getServicioObjList().getLast().getID() + 1;
				}

				if (!DescuentoModelo.getDescuentoObjcList().isEmpty()) {
					idDescuento = DescuentoModelo.getDescuentoObjcList().getLast().getID() + 1;
				}
				
				if (camposVacios) {
		            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.WARNING_MESSAGE);
		            return; // 
		        }

				TarifaModelo.subirTarifa(new TarifaObj(new PlanesObj(idPlanes, textNombreTarifa.getText(), tarifaNueva, "3 Meses"), new ServicioObj(idServicio, infor3Meses.getText(), infor6Meses.getText(), info1anio.getText(), beneficios), new DescuentoObj(idDescuento, descuento3, descuento6, descuento1)));
				JOptionPane.showMessageDialog(null, "¡Nueva tarifa agregada correctamente!", "Añadido exitoso", JOptionPane.INFORMATION_MESSAGE);
				controlador.tarifas();
			}
		});

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
	}


}