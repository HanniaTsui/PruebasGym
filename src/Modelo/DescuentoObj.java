package Modelo;

public class DescuentoObj {
	private int ID;
	private int porcentaje3meses;
	private int porcentaje6meses;
	private int porcentaje1anio;

	public DescuentoObj(int iD, int porcentaje3meses, int porcentaje6meses, int porcentaje1anio) {
		ID = iD;
		this.porcentaje1anio = porcentaje1anio;
		this.porcentaje3meses = porcentaje3meses;
		this.porcentaje6meses = porcentaje6meses;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getPorcentaje3meses() {
		return porcentaje3meses;
	}

	public void setPorcentaje3meses(int porcentaje3meses) {
		this.porcentaje3meses = porcentaje3meses;
	}

	public int getPorcentaje6meses() {
		return porcentaje6meses;
	}

	public void setPorcentaje6meses(int porcentaje6meses) {
		this.porcentaje6meses = porcentaje6meses;
	}

	public int getPorcentaje1anio() {
		return porcentaje1anio;
	}

	public void setPorcentaje1anio(int porcentaje1anio) {
		this.porcentaje1anio = porcentaje1anio;
	}
}