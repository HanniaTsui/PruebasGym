package Modelo;

public class HorarioObj {
	 private int id;
	    private String horarioTiempo;

	    public HorarioObj(int id, String horarioTiempo) {
	        this.id = id;
	        this.horarioTiempo = horarioTiempo;
	    }

	    public int getId() {
	    	return id; 
	    }
	    public String getHorarioTiempo() { 
	    	return horarioTiempo;
	    }

}