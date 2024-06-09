package modelo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;

import objetos.RegistroPagoObj;

public class RegistroPagoModelo {

    public static RegistroPagoModelo instance = new RegistroPagoModelo();

    static List<RegistroPagoObj> pagos = new ArrayList<>();

    public static List<RegistroPagoObj> getPagos() {
        return pagos;
    }

    public static RegistroPagoModelo obtenerInstancia() {
        return instance;
    }

    public static void registrarPago(RegistroPagoObj registroPagoObj) {
        Insert nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("registroPago").insert();

        nombreTabla.field("IDCliente", registroPagoObj.getIDCliente());
        nombreTabla.field("monto", registroPagoObj.getMonto());
        nombreTabla.field("metodoPago", registroPagoObj.getMetodoPago());
        nombreTabla.field("fechaFinal", registroPagoObj.getFechaFinal());
        nombreTabla.field("tipoMembresia", registroPagoObj.getTipoMembresia());
        nombreTabla.field("fechaPago", registroPagoObj.getFechaPago());

        try {
            nombreTabla.execute();
            System.out.println("Se registro el pago en la base de datos");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        cargarPagosPorCliente();
    }

    public static void cargarPagosPorCliente() {
        pagos.clear();

        Select nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("registroPago").select();
        //nombreTabla.where("IDCliente = ?", clienteId);
        List<Map<String, Object>> resultTableUser;

        try {
            resultTableUser = nombreTabla.fetchAllAsList();
            for (Map<String, Object> map : resultTableUser) {
                int ID = (int) map.get("ID");
                int IDCliente = (int) map.get("IDCliente");
                BigDecimal precioBigDecimal = (BigDecimal) map.get("monto");
                double monto = precioBigDecimal.doubleValue();
                String metodoPago = (String) map.get("metodoPago");
                String fechaFinal = (String) map.get("fechaFinal");
                String tipoMembresia = (String) map.get("tipoMembresia");
                String fechaPago = (String) map.get("fechaPago");

                pagos.add(new RegistroPagoObj(ID, IDCliente, fechaPago, fechaFinal, tipoMembresia, monto, metodoPago));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}