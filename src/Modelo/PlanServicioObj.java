package Modelo;

public class PlanServicioObj {
	private int IDPlan;
	private int IDServicio;
	
	public PlanServicioObj(int iDPlan, int iDServicio) {
		
		IDPlan = iDPlan;
		IDServicio = iDServicio;
	}

	public int getIDPlan() {
		return IDPlan;
	}

	public void setIDPlan(int iDPlan) {
		IDPlan = iDPlan;
	}

	public int getIDServicio() {
		return IDServicio;
	}

	public void setIDServicio(int iDServicio) {
		IDServicio = iDServicio;
	}
	
	
}
