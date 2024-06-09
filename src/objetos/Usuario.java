package objetos;

public class Usuario {
	String nombre;
	String contraseña;
	String correo;

	public Usuario(String nombre, String contraseña,String correo) {
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.correo=correo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContraseña() {
		return contraseña;
	}
	public String getCorreo() {
		return correo;
	}

}
