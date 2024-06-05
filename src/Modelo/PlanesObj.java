package Modelo;

public class PlanesObj {
	private int ID;
	private String nombre;
	private double precio;
	private String duracion;
	
	public PlanesObj(int iD, String nombre, double precio, String duracion) {
		
		ID = iD;
		this.nombre = nombre;
		this.precio = precio;
		this.duracion = duracion;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	
	
	

}
