package Modelo;

public class ServicioObj {
	private int ID;
	private String descripcion;
	private String beneficio;
	
	public ServicioObj(int iD, String descripcion, String beneficio) {
		
		ID = iD;
		this.descripcion = descripcion;
		this.beneficio = beneficio;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}
	
	

}
