package Modelo;

public class ChecadorObj {
    private int idCliente;
    private String nombreCliente;
    private String estadoCliente;
    private String horaEntrada;
    private String horaSalida;
    private String fecha;
    public ChecadorObj(int idCliente, String nombreCliente, String estadoCliente, String horaEntrada, String horaSalida,String fecha) {
    	this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.estadoCliente = estadoCliente;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.fecha=fecha;
    }


    public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }
    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }
	public String getFecha() {
		return fecha;
	}

    
}