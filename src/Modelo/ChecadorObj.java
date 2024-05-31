package Modelo;

public class ChecadorObj {
    private int idCliente;
    private String estadoCliente;
    private String horaEntrada;
    private String horaSalida;

    public ChecadorObj(int idCliente, String estadoCliente, String horaEntrada, String horaSalida) {
        this.idCliente = idCliente;
        this.estadoCliente = estadoCliente;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
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
}