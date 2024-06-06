package Modelo;

public class ServicioObj {
	private int ID;
	private String descripcion3meses;
	private String descripcion6meses;
	private String descripcion1anio;
	private String beneficio;
	
	public ServicioObj(int iD, String descripcion3meses, String descripcion6meses, String descripcion1anio, String beneficio) {
		ID = iD;
		this.descripcion3meses = descripcion3meses;
		this.descripcion6meses = descripcion6meses;
		this.descripcion1anio = descripcion1anio;
		this.beneficio = beneficio;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}

	public String getDescripcion1anio() {
		return descripcion1anio;
	}

	public void setDescripcion1anio(String descripcion1anio) {
		this.descripcion1anio = descripcion1anio;
	}

	public String getDescripcion6meses() {
		return descripcion6meses;
	}

	public void setDescripcion6meses(String descripcion6meses) {
		this.descripcion6meses = descripcion6meses;
	}

	public String getDescripcion3meses() {
		return descripcion3meses;
	}

	public void setDescripcion3meses(String descripcion3meses) {
		this.descripcion3meses = descripcion3meses;
	}
}