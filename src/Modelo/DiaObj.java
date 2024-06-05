package Modelo;

public class DiaObj {
    private int id;
    private String nombreDia;

    public DiaObj(int id, String nombreDia) {
        this.id = id;
        this.nombreDia = nombreDia;
    }

    public int getId() { 
    	return id;
    }
    public String getNombreDia() { 
    	return nombreDia; 
    }
}