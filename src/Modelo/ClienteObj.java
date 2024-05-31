package Modelo;

import java.awt.image.BufferedImage;

public class ClienteObj {
	int ID;
	String nombre; 
	String apellido; 
	String correo; 
	String telefono;
	String fechaInicial;
	String fechaFinal; 
	String tipoMembresia; 
	String planMembresia; 
	String fechaNacimiento;
	BufferedImage imagen;
	String metodoPago;
	String estado;
	
	
	public ClienteObj(int iD, String nombre, String apellido, String correo, String telefono, String fechaInicial,
			String fechaFinal, String tipoMembresia, String planMembresia, String fechaNacimiento, BufferedImage imagen,
			String metodoPago,String estado) {
		
		ID = iD;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.tipoMembresia = tipoMembresia;
		this.planMembresia = planMembresia;
		this.fechaNacimiento = fechaNacimiento;
		this.imagen = imagen;
		this.metodoPago = metodoPago;
		this.estado=estado;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public int getID() {
		return ID;
	}
	public int setID() {
		return ID;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public String getCorreo() {
		return correo;
	}


	public String getTelefono() {
		return telefono;
	}


	public String getFechaInicial() {
		return fechaInicial;
	}


	public String getFechaFinal() {
		return fechaFinal;
	}


	public String getTipoMembresia() {
		return tipoMembresia;
	}


	public String getPlanMembresia() {
		return planMembresia;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public BufferedImage getImagen() {
		return imagen;
	}


	public String getMetodoPago() {
		return metodoPago;
	}


	public void setID(int ID) {
		this.ID = ID;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public void setTipoMembresia(String tipoMembresia) {
		this.tipoMembresia = tipoMembresia;
	}

	public void setPlanMembresia(String planMembresia) {
		this.planMembresia = planMembresia;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

}
