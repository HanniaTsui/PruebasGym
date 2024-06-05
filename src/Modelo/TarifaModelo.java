package Modelo;

import java.util.ArrayList;
import java.util.List;

public class TarifaModelo {
	public static TarifaModelo instance= new TarifaModelo();
	
	static List<TarifaObj> tarifa = new ArrayList<TarifaObj>();
	
	public static List<TarifaObj> getTarifa() {
		return tarifa;
		
	}
	public static TarifaModelo obtenerInstancia() {
		return instance;
	}
	public TarifaObj obtenerTarifaPorId(int id) {
        return tarifa.stream().filter(tarifa -> tarifa.getPlan().getID() == id).findFirst().orElse(null);
    }

    public void actualizarTarifa(TarifaObj tarifaActualizada) {
        TarifaObj tarifa = obtenerTarifaPorId(tarifaActualizada.getPlan().getID());
        if (tarifa != null) {
            tarifa.setPlan(tarifaActualizada.getPlan());
            tarifa.setServicios(tarifaActualizada.getServicios());
            tarifa.setRegistrosPago(tarifaActualizada.getRegistrosPago());
            tarifa.setDescuento(tarifaActualizada.getDescuento());
        }
    }

    public void eliminarTarifa(int id) {
        tarifa.removeIf(tarifa -> tarifa.getPlan().getID() == id);
    }

    public double calcularTarifaFinal(int id) {
        TarifaObj tarifa = obtenerTarifaPorId(id);
        if (tarifa != null) {
            return tarifa.calcularTarifaFinal();
        }
        return 0.0;
    }

}
