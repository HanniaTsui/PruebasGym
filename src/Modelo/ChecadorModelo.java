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
	  public void cargarChecador() {
	        check.clear();
	    	List<ClienteObj> clientes = ClienteModelo.obtenerInstancia().getClient();
	        Select nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("checador").select();
	        try {
	            List<Map<String, Object>> resultTableUser = nombreTabla.fetchAllAsList();
	            for (Map<String, Object> map : resultTableUser) {
	                int IDCliente = (int) map.get("IDCliente");
	                String nombreCliente = (String) map.get("nombreCliente");
	                String horaEntrada = (String) map.get("horaEntrada");
	                String horaSalida = (String) map.get("horaSalida");
	                String estadoCliente = (String) map.get("estadoCliente");

	                registros.add(new ChecadorObj(IDCliente, nombreCliente, estadoCliente, horaEntrada, horaSalida));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	public ChecadorObj registrarChecada(int idCliente, String nombreCliente, String horaActual) {
        // Buscar si el cliente ya tiene un registro de entrada
        ChecadorObj registroExistente = buscarRegistroEntrada(idCliente);

        if (registroExistente == null) {
            // Si no hay registro de entrada, crear uno nuevo con la hora de entrada proporcionada
            ChecadorObj checadorObj= new ChecadorObj(idCliente, nombreCliente, "Activo", horaActual, null);
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
	public List<ChecadorObj> getRegistros() {
		return registros;
	}
	
	

}
