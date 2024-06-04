package Modelo;

import java.awt.image.BufferedImage;

public class InstructorObj {
	int ID;
	String nombre;
	String apellido;
	String correo;
	String telefono;
	String fechaContratacion;
	String especialidad;
	BufferedImage imagen ;
	int IDClase; 
	
	

	public InstructorObj(int iD, String nombre, String apellido, String correo, String telefono,
			String fechaContratacion, String especialidad, BufferedImage imagen, int iDClase) {
		
		ID = iD;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.fechaContratacion = fechaContratacion;
		this.especialidad = especialidad;
		this.imagen = imagen;
		IDClase = iDClase;
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



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getFechaContratacion() {
		return fechaContratacion;
	}



	public void setFechaContratacion(String fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}



	public String getEspecialidad() {
		return especialidad;
	}



	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}



	public BufferedImage getImagen() {
		return imagen;
	}



	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}



	public int getIDClase() {
		return IDClase;
	}



	public void setIDClase(int iDClase) {
		IDClase = iDClase;
	}

}
