package Modelo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.code.advancedsql.query.Select;

public class ChecadorModelo {
	public static ChecadorModelo instance = new ChecadorModelo();
	
	static List<ChecadorObj> check = new ArrayList<ChecadorObj>();
	
	public static List<ChecadorObj> getCheck() {
		return check;
		
	}
	public static ChecadorModelo obtenerInstancia() {
		return instance;
	}
	public static void cargarChecador() {
		check.clear();
		Select nombreTabla=BaseDatos.optenerIstancia().getMySQL().table("checador").select();
        List<Map<String, Object>> resultTableUser;
		try {
			resultTableUser = nombreTabla.fetchAllAsList();
			for (Map<String, Object> map : resultTableUser) {
				
	            
	        	int IDCliente=(int)map.get("IDCliente");
	        	String horaEntrada=((String)map.get("horaEntrada"));
	        	String horaSalida=((String)map.get("horaSalida")); 
	        	String estadoCliente= ((String)map.get("estadoCliente"));
	            
	            

	            check.add(new ChecadorObj( IDCliente,estadoCliente,horaEntrada,horaSalida));
	            
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
