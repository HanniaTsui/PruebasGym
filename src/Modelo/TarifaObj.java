

package Modelo;

import java.util.List;

public class TarifaObj {
    private PlanesObj plan;
    private List<ServicioObj> servicios;
    private List<RegistroPagoObj> registrosPago;
    private DescuentoObj descuento;

    public TarifaObj(PlanesObj plan, List<ServicioObj> servicios, List<RegistroPagoObj> registrosPago, DescuentoObj descuento) {
        this.plan = plan;
        this.servicios = servicios;
        this.registrosPago = registrosPago;
        this.descuento = descuento;
    }

	public PlanesObj getPlan() {
		return plan;
	}

	public void setPlan(PlanesObj plan) {
		this.plan = plan;
	}

	public List<ServicioObj> getServicios() {
		return servicios;
	}

	public void setServicios(List<ServicioObj> servicios) {
		this.servicios = servicios;
	}

	public List<RegistroPagoObj> getRegistrosPago() {
		return registrosPago;
	}

	public void setRegistrosPago(List<RegistroPagoObj> registrosPago) {
		this.registrosPago = registrosPago;
	}

	public DescuentoObj getDescuento() {
		return descuento;
	}

	public void setDescuento(DescuentoObj descuento) {
		this.descuento = descuento;
	}

    // Getters y setters
 
    public double calcularTarifaFinal() {
        double tarifaBase = plan.getPrecio();
        double descuentoAplicado = tarifaBase * (descuento.getPorcentaje() / 100);
        return tarifaBase - descuentoAplicado;
    }
    
    
    
}