package Modelo;

import com.code.advancedsql.query.Select;
import com.code.advancedsql.query.Update;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

public class PlanesModelo {
    public static PlanesModelo instance = new PlanesModelo();
    
    static List<PlanesObj> planes = new ArrayList<>();

    public static List<PlanesObj> getPlanes() {
        return planes;
    }

    public static PlanesModelo obtenerInstancia() {
        return instance;
    }

    public static void actualizarPlan(PlanesObj plan) {
        Update nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("planes").update();

        nombreTabla.field("nombre", plan.getNombre());
        nombreTabla.field("precio", BigDecimal.valueOf(plan.getPrecio()));
        nombreTabla.field("duracion", plan.getDuracion());

        nombreTabla.where("ID =?", plan.getID());
        try {
            nombreTabla.execute();
            System.out.println("Se actualizo");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cargarPlan() {
        planes.clear();
        Select nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("planes").select();
        List<Map<String, Object>> resultTableUser;
        try {
            resultTableUser = nombreTabla.fetchAllAsList();
            for (Map<String, Object> map : resultTableUser) {
                int ID = (int) map.get("ID");
                String nombre = (String) map.get("nombre");
                BigDecimal precioBigDecimal = (BigDecimal) map.get("precio");
                double precio = precioBigDecimal.doubleValue();
                String duracion = (String) map.get("duracion");
                planes.add(new PlanesObj(ID, nombre, precio, duracion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}