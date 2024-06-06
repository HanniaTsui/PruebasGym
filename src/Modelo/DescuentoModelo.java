package Modelo;

import com.code.advancedsql.query.Select;
import com.code.advancedsql.query.Update;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DescuentoModelo {
    public static DescuentoModelo instance = new DescuentoModelo();
    
    static List<DescuentoObj> descuentoObjcList = new ArrayList<>();

    public static List<DescuentoObj> getDescuentoObjcList() {
        return descuentoObjcList;
    }

    public static DescuentoModelo obtenerInstancia() {
        return instance;
    }

    public static void actualizarDescuento(DescuentoObj descuento) {
        Update nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("descuento").update();

        nombreTabla.field("porcentaje3Meses", descuento.getPorcentaje3meses());
        nombreTabla.field("porcentaje6Meses", descuento.getPorcentaje6meses());
        nombreTabla.field("porcentaje1Anio", descuento.getPorcentaje1anio());

        nombreTabla.where("ID =?", descuento.getID());

        try {
            nombreTabla.execute();
            System.out.println("Se actualizo");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cargarDescuentos() {
        descuentoObjcList.clear();
        Select nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("descuento").select();
        List<Map<String, Object>> resultTableUser;
        try {
            resultTableUser = nombreTabla.fetchAllAsList();

            for (Map<String, Object> map : resultTableUser) {
                int ID = (int) map.get("ID");
                Integer porcentaje3 = (Integer) map.get("porcentaje3Meses");
                Integer porcentaje6 = (Integer) map.get("porcentaje6Meses");
                Integer porcentaje1anio = (Integer) map.get("porcentaje1Anio");
                descuentoObjcList.add(new DescuentoObj(ID, porcentaje3, porcentaje6, porcentaje1anio));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}