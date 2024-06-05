package Modelo;

public class RegistroPagoObj {
	private int ID;
	private int IDCliente;
	private double monto;
	private String MetodoPago;
	public RegistroPagoObj(int iD, int iDCliente, double monto, String metodoPago) {
		
		ID = iD;
		IDCliente = iDCliente;
		this.monto = monto;
		MetodoPago = metodoPago;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getIDCliente() {
		return IDCliente;
	}
	public void setIDCliente(int iDCliente) {
		IDCliente = iDCliente;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getMetodoPago() {
		return MetodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		MetodoPago = metodoPago;
	}
	
	
}
