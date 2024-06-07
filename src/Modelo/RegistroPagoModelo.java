package Modelo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.code.advancedsql.query.Select;

public class RegistroPagoModelo {

    public static RegistroPagoModelo instance = new RegistroPagoModelo();

    static List<RegistroPagoObj> pagos = new ArrayList<>();

    public static List<RegistroPagoObj> getPagos() {
        return pagos;
    }

    public static RegistroPagoModelo obtenerInstancia() {
        return instance;
    }

    public static void cargarPagosPorCliente(int clienteId) {
        pagos.clear();

        Select nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("registroPago").select();
        nombreTabla.where("IDCliente = ?", clienteId);
        List<Map<String, Object>> resultTableUser;

        try {
            resultTableUser = nombreTabla.fetchAllAsList();
            for (Map<String, Object> map : resultTableUser) {
                int ID = (int) map.get("ID");
                int IDCliente = (int) map.get("IDCliente");
                BigDecimal precioBigDecimal = (BigDecimal) map.get("monto");
                double monto = precioBigDecimal.doubleValue();
                String metodoPago = (String) map.get("metodoPago");
                String fechaPago = (String) map.get("fechaPago");

                pagos.add(new RegistroPagoObj(ID, IDCliente, fechaPago, monto, metodoPago));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}