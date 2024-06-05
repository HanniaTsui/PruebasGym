package Modelo;

public class DescuentoObj {
	private int ID;
	private String periodo;
	private double porcentaje;
	private String duracion;
	public DescuentoObj(int iD, String periodo, double porcentaje, String duracion) {
		
		ID = iD;
		this.periodo = periodo;
		this.porcentaje = porcentaje;
		this.duracion = duracion;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	
	

}
