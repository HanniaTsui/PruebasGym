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
	private List<ChecadorObj> registros = new ArrayList<>();
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
	public ChecadorObj registrarChecada(int idCliente, String horaActual) {
        // Buscar si el cliente ya tiene un registro de entrada
        ChecadorObj registroExistente = buscarRegistroEntrada(idCliente);

        if (registroExistente == null) {
            // Si no hay registro de entrada, crear uno nuevo con la hora de entrada proporcionada
            ChecadorObj checadorObj= new ChecadorObj(idCliente, "Entrada", horaActual, null);
            registros.add(checadorObj);
            return checadorObj;
        } else {
            // Si ya hay un registro de entrada, actualizar la hora de salida
            registroExistente.setHoraSalida(horaActual);
            return registroExistente;
        }
    }
	private ChecadorObj buscarRegistroEntrada(int idCliente) {
        for (ChecadorObj registro : registros) {
            if (registro.getIdCliente() == idCliente && registro.getHoraSalida() == null) {
                // Si el cliente tiene un registro de entrada sin hora de salida, retornarlo
                return registro;
            }
        }
        return null; // Si no se encuentra ningún registro de entrada para el cliente, retornar null
    }

}
