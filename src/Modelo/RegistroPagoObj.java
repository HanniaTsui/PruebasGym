package Modelo;

public class RegistroPagoObj {
    private int ID;
    private int IDCliente;
    private String fechaPago;
    private double monto;
    private String metodoPago;
    public RegistroPagoObj(int iD, int iDCliente,String fechaPago ,double monto, String metodoPago) {

        ID = iD;
        IDCliente = iDCliente;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.metodoPago = metodoPago;
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
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    public String getFechaPago() {
        return fechaPago;
    }
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }


}