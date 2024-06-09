package objetos;

public class ClasesObj {

    private int ID;
    private String nombre;
    private int idHorario;
    private int idDia;

    public ClasesObj(int iD, String nombre, int idDia, int idHorario) {

        ID = iD;
        this.nombre = nombre;
        this.idHorario = idHorario;
        this.idDia = idDia;
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

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }


}