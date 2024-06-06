package Modelo;

public class TarifaObj {
    private PlanesObj plan;
    private ServicioObj servicios;
    private DescuentoObj descuento;

    public TarifaObj(PlanesObj plan, ServicioObj servicios, DescuentoObj descuento) {
        this.plan = plan;
        this.servicios = servicios;
        this.descuento = descuento;
    }

	public PlanesObj getPlan() {
		return plan;
	}

	public void setPlan(PlanesObj plan) {
		this.plan = plan;
	}

	public DescuentoObj getDescuento() {
		return descuento;
	}

	public void setDescuento(DescuentoObj descuento) {
		this.descuento = descuento;
	}

	public ServicioObj getServicios() {
		return servicios;
	}

	public void setServicios(ServicioObj servicios) {
		this.servicios = servicios;
	}

	// Getters y setters

}