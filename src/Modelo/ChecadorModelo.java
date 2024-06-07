package Modelo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.code.advancedsql.query.Delete;
import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;

public class ChecadorModelo {
    public static ChecadorModelo instance = new ChecadorModelo();
    
 //   static List<ChecadorObj> check = new ArrayList<ChecadorObj>();
    private static List<ChecadorObj> registros = new ArrayList<>();
    
/*    public static List<ChecadorObj> getCheck() {
        return check;
    }*/

    List<ClienteObj> clientes = ClienteModelo.obtenerInstancia().getClient();
    
    public static ChecadorModelo obtenerInstancia() {
        return instance;
    }
    
    public static void cargarChecador() {
        registros.clear(); // Limpiar la lista antes de cargar los registros
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
                System.out.println(IDCliente);
            }
            System.out.println("Registros cargados: " + registros.size()); // Mensaje de depuración
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ChecadorObj registrarChecada(int idCliente, String nombreCliente, String horaActual) {
        // Buscar si el cliente ya tiene un registro de entrada
        ChecadorObj registroExistente = buscarRegistroEntrada(idCliente);
        String fechaFinal=null;
        if (registroExistente == null) {
            for (ClienteObj cliente : clientes) {
                if(idCliente==cliente.getID()) {
                    fechaFinal=cliente.getFechaFinal();
                    break;
                }
            }
            String estadoActual = ClienteModelo.obtenerInstancia().estado(fechaFinal) ? "Activo" : "No activo";
            // Si no hay registro de entrada, crear uno nuevo con la hora de entrada proporcionada
            ChecadorObj checadorObj= new ChecadorObj(idCliente, nombreCliente, estadoActual, horaActual, null);
            registros.add(checadorObj);
            return checadorObj;
        } else {
            // Si ya hay un registro de entrada, actualizar la hora de salida
            registroExistente.setHoraSalida(horaActual);
            subirDatosChecador(registroExistente);
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
    
    public boolean subirDatosChecador(ChecadorObj checador) {
        Insert insertar=BaseDatos.optenerIstancia().getMySQL().table("checador").insert();
        insertar.field("IDCliente",checador.getIdCliente());
        insertar.field("nombreCliente",checador.getNombreCliente());
        insertar.field("estadoCliente",checador.getEstadoCliente());
        insertar.field("horaEntrada",checador.getHoraEntrada());
        insertar.field("horaSalida",checador.getHoraSalida());
        try {
            insertar.execute();
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
        return true;
    }
    
    public static List<ChecadorObj> getRegistros() {
        return registros;
    }
    
   
}
