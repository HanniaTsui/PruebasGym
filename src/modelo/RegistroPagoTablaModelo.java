package modelo;

import javax.swing.table.AbstractTableModel;

import objetos.RegistroPagoObj;

import java.util.Vector;

public class RegistroPagoTablaModelo extends AbstractTableModel {

    private final String columnas[] = { "Membresía", "Fecha inicial", "Vencimiento","Metodo de pago", "Total" };
    private final Vector<RegistroPagoObj> registroPagos = new Vector<>();

    @Override
    public int getRowCount() {
        return registroPagos.size();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public String getColumnName(int col) {
        return columnas[col];
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    public void removeAll() {
        registroPagos.clear();
        fireTableRowsInserted(0, 0);
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return String.class;
    }

    @Override
    public Object getValueAt(int fila, int col) {
        RegistroPagoObj pago = registroPagos.elementAt(fila);

        return switch (col) {
            case 0 -> pago.getTipoMembresia();
            case 1 -> pago.getFechaPago();
            case 2 -> pago.getFechaFinal();
            case 3 -> pago.getMetodoPago();
            case 4 -> String.valueOf(pago.getMonto());
            default -> null;
        };
    }

    public void addRow(RegistroPagoObj registroPagoObj) {
        registroPagos.add(registroPagoObj);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }
}
