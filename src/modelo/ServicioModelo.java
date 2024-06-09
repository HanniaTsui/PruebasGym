package modelo;

import com.code.advancedsql.query.Delete;
import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;
import com.code.advancedsql.query.Update;

import objetos.ServicioObj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServicioModelo {
    public static ServicioModelo instance = new ServicioModelo();
    
    static List<ServicioObj> servicioObjList = new ArrayList<>();

    public static List<ServicioObj> getServicioObjList() {
        return servicioObjList;
    }

    public static ServicioModelo obtenerInstancia() {
        return instance;
    }

    public static void subirServicio(ServicioObj servicio) {
        Insert nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("servicio").insert();

        nombreTabla.field("ID", servicio.getID());
        nombreTabla.field("descripcio3meses", servicio.getDescripcion3meses());
        nombreTabla.field("descripcio6meses", servicio.getDescripcion6meses());
        nombreTabla.field("descripcio1anio", servicio.getDescripcion1anio());
        nombreTabla.field("beneficio", servicio.getBeneficio());

        try {
            nombreTabla.execute();
            System.out.println("Se subio");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminarServicio(ServicioObj servicio) {
        Delete nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("servicio").delete();

        nombreTabla.where("ID =?", servicio.getID());
        try {
            nombreTabla.execute();
            System.out.println("Se eliminoo");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void actualizarServicio(ServicioObj servicio) {
        Update nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("servicio").update();

        nombreTabla.field("descripcio3meses", servicio.getDescripcion3meses());
        nombreTabla.field("descripcio6meses", servicio.getDescripcion6meses());
        nombreTabla.field("descripcio1anio", servicio.getDescripcion1anio());
        nombreTabla.field("beneficio", servicio.getBeneficio());

        nombreTabla.where("ID =?", servicio.getID());
        try {
            nombreTabla.execute();
            System.out.println("Se actualizo");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cargarServicios() {
        servicioObjList.clear();
        Select nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("servicio").select();
        List<Map<String, Object>> resultTableUser;
        try {
            resultTableUser = nombreTabla.fetchAllAsList();

            for (Map<String, Object> map : resultTableUser) {
                int ID = (int) map.get("ID");
                String descripcion3meses = (String) map.get("descripcio3meses");
                String descripcion6meses = (String) map.get("descripcio6meses");
                String descripcion1anio = (String) map.get("descripcio1anio");
                String beneficio = (String) map.get("beneficio");
                servicioObjList.add(new ServicioObj(ID, descripcion3meses, descripcion6meses, descripcion1anio, beneficio));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
