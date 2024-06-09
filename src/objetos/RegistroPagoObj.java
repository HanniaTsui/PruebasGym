package objetos;

public class RegistroPagoObj {
    private final int ID;
    private final int IDCliente;
    private final String fechaPago;
    private final double monto;
    private final String metodoPago;
    private final String fechaFinal;
    private final String tipoMembresia;

    public RegistroPagoObj(int iD, int IDCliente, String fechaPago, String fechaFinal, String tipoMembresia, double monto, String metodoPago) {
        this.ID = iD;
        this.IDCliente = IDCliente;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaFinal = fechaFinal;
        this.tipoMembresia = tipoMembresia;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public int getIDCliente() {
        return IDCliente;
    }

    public int getID() {
        return ID;
    }
}