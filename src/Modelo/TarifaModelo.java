package Modelo;

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
