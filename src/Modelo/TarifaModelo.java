package Modelo;

import com.code.advancedsql.query.Delete;
import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;
import com.code.advancedsql.query.Update;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TarifaModelo {
	public static TarifaModelo instance= new TarifaModelo();
	
	static List<TarifaObj> tarifa = new ArrayList<TarifaObj>();
	
	public static List<TarifaObj> getTarifa() {
		return tarifa;
	}

	public static TarifaModelo obtenerInstancia() {
		return instance;
	}

    public static void actualizarTarifa(TarifaObj tarifaActualizada) {
        ServicioModelo.actualizarServicio(tarifaActualizada.getServicios());
        PlanesModelo.actualizarPlan(tarifaActualizada.getPlan());
        DescuentoModelo.actualizarDescuento(tarifaActualizada.getDescuento());
    }

    public static void removerTarifa(TarifaObj tarifa) {
        Delete nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("planServicio").delete();

        nombreTabla.where("IDPlan =? AND IDServicio =? AND IDDescuento =?", tarifa.getPlan().getID(), tarifa.getServicios().getID(), tarifa.getDescuento().getID());
        //nombreTabla.where("IDServicio =?", tarifa.getServicios().getID());
        //nombreTabla.where("IDDescuento =?", tarifa.getDescuento().getID());

        try {
            nombreTabla.execute();
            System.out.println("Se elimino");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ServicioModelo.eliminarServicio(tarifa.getServicios());
        PlanesModelo.eliminarPlan(tarifa.getPlan());
        DescuentoModelo.eliminarDescuento(tarifa.getDescuento());
    }

    public static void subirTarifa(TarifaObj tarifa) {
        ServicioModelo.subirServicio(tarifa.getServicios());
        PlanesModelo.subirPlan(tarifa.getPlan());
        DescuentoModelo.subirDescuento(tarifa.getDescuento());

        Insert nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("planServicio").insert();

        nombreTabla.field("IDPlan", tarifa.getPlan().getID());
        nombreTabla.field("IDDescuento", tarifa.getDescuento().getID());
        nombreTabla.field("IDServicio", tarifa.getServicios().getID());

        try {
            nombreTabla.execute();
            System.out.println("Se subio Tarifa");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cargarTarifas() {
        tarifa.clear();

        PlanesModelo.cargarPlan();
        List<PlanesObj> planesObjs = PlanesModelo.getPlanes();

        ServicioModelo.cargarServicios();
        List<ServicioObj> serviciosObjs = ServicioModelo.getServicioObjList();

        DescuentoModelo.cargarDescuentos();
        List<DescuentoObj> descuentos = DescuentoModelo.getDescuentoObjcList();

        Select nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("planServicio").select();
        List<Map<String, Object>> resultTableUser;
        try {
            resultTableUser = nombreTabla.fetchAllAsList();

            for (Map<String, Object> map : resultTableUser) {
                int IDPlan = (int) map.get("IDPlan");
                int IDServicio = (int) map.get("IDServicio");
                int IDDescuento = (int) map.get("IDDescuento");

                Optional<PlanesObj> planObj = planesObjs.stream().filter(p -> p.getID() == IDPlan).findFirst();
                Optional<ServicioObj> servicioObj = serviciosObjs.stream().filter(p -> p.getID() == IDServicio).findFirst();
                Optional<DescuentoObj> descuentoObj = descuentos.stream().filter(p -> p.getID() == IDDescuento).findFirst();

                if (planObj.isPresent() && servicioObj.isPresent() && descuentoObj.isPresent()) {
                    tarifa.add(new TarifaObj(planObj.get(), servicioObj.get(), descuentoObj.get()));
                } else {
                    System.out.println("ERROR al agregar una tarifa");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTarifa(int id) {
        tarifa.removeIf(tarifa -> tarifa.getPlan().getID() == id);
    }

}