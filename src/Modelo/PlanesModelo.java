package Modelo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.code.advancedsql.query.Select;

public class PlanesModelo {
	public static PlanesModelo instance = new PlanesModelo();
	private static final int MAX_BLOB_SIZE = 65535;
	
	
	static List<PlanesObj> planes = new ArrayList<PlanesObj>();

	public static List<PlanesObj> getPlanes() {
		return planes;
		
	}
	public static PlanesModelo obtenerInstancia() {
		return instance;
	}
	public static void cargarPlan() {
		planes.clear();
		Select nombreTabla=BaseDatos.optenerIstancia().getMySQL().table("planes").select();
        List<Map<String, Object>> resultTableUser;
		try {
			resultTableUser = nombreTabla.fetchAllAsList();
			for (Map<String, Object> map : resultTableUser) {
				
	            
	        	int ID=(int)map.get("ID");
	        	String nombre=((String)map.get("nombre")); 
	        	
	        	String duracion=((String)map.get("duracion"));
	        	BigDecimal precioBigDecimal = (BigDecimal) map.get("precio");
	        	double precio = precioBigDecimal.doubleValue();
	        	
	            
	            

	            planes.add(new PlanesObj( ID, nombre,  precio, duracion));
	            
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
}
