package Modelo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.code.advancedsql.query.Delete;
import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;

public class ChecadorModelo {
    public static ChecadorModelo instance = new ChecadorModelo();
    public static List<ChecadorObj> registros = new ArrayList<>();
    public static List<ChecadorObj> registroEntrada = new ArrayList<>();
    static List<ClienteObj> clientes = ClienteModelo.obtenerInstancia().getClient();
    
    public static ChecadorModelo obtenerInstancia() {
        return instance;
    }
    
  /*  public static void cargarChecador() {
        registros.clear(); // Limpiar la lista antes de cargar los registros
        Select nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("checador").select();
        try {
            List<Map<String, Object>> resultTableUser = nombreTabla.fetchAllAsList();
            for (Map<String, Object> map : resultTableUser) {
                int IDCliente = (int) map.get("IDCliente");
                String nombreCliente = (String) map.get("nombreCliente");
                
                Time horaEntradaTime = (Time) map.get("horaEntrada");
                Time horaSalidaTime = (Time) map.get("horaSalida");
                
                String horaEntrada = horaEntradaTime != null ? horaEntradaTime.toString() : null;
                String horaSalida = horaSalidaTime != null ? horaSalidaTime.toString() : null;
                Date fechaFormato=(Date) map.get("fecha");
                String estadoCliente = (String) map.get("estadoCliente");
                DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = formatoFecha.format(fechaFormato);
                registros.add(new ChecadorObj(IDCliente, nombreCliente, estadoCliente, horaEntrada, horaSalida,fecha));
                System.out.println(IDCliente);
            }
            System.out.println("Registros cargados: " + registros.size()); // Mensaje de depuración
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    
    public static void cargarChecador() {
        registros.clear(); // Limpiar la lista antes de cargar los registros
        Select nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("checador").select();
        // Obtener la fecha actual en formato "yyyy-MM-dd"
        DateFormat formatoFechaSQL = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatoFechaSQL.format(new java.util.Date());

        nombreTabla.where("fecha = ?", fechaActual); // Obtener solo los registros de la fecha actual
        try {
            List<Map<String, Object>> resultTableUser = nombreTabla.fetchAllAsList();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            for (Map<String, Object> map : resultTableUser) {
                int IDCliente = (int) map.get("IDCliente");
                String nombreCliente = (String) map.get("nombreCliente");
                Time horaEntradaTime = (Time) map.get("horaEntrada");
                Time horaSalidaTime = (Time) map.get("horaSalida");
                Date fechaFormato=(Date) map.get("fecha");
                String estadoCliente = (String) map.get("estadoCliente");


                String horaEntrada = horaEntradaTime != null ? dateFormat.format(horaEntradaTime) : null;
                String horaSalida = horaSalidaTime != null ? dateFormat.format(horaSalidaTime) : null;
                DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
                String fecha = formatoFecha.format(fechaFormato);
                registros.add(new ChecadorObj(IDCliente, nombreCliente, estadoCliente, horaEntrada, horaSalida,fecha));
                System.out.println(IDCliente);
            }
            System.out.println("Registros cargados: " + registros.size()); // Mensaje de depuración
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ChecadorObj registrarChecada(int idCliente, String nombreCliente, String horaActual,String fechaActual) {
    	for (ChecadorObj registro : registroEntrada) {
            if (registro.getIdCliente() == idCliente && registro.getHoraSalida() == null ) {
                registro.setHoraSalida(horaActual);
                subirDatosChecador(registro);
                registros.add(registro);
                return registro;
            }
        }

        String fechaFinal = null;
        for (ClienteObj cliente : clientes) {
            if (idCliente == cliente.getID()) {
                fechaFinal = cliente.getFechaFinal();
                break;
            }
        }

        String estadoActual = ClienteModelo.obtenerInstancia().estado(fechaFinal) ? "Activo" : "No activo";
        ChecadorObj nuevoRegistro = new ChecadorObj(idCliente, nombreCliente, estadoActual, horaActual, null,fechaActual);
        registroEntrada.add(nuevoRegistro);
        return nuevoRegistro; 
    }

    public boolean subirDatosChecador(ChecadorObj checador) {
        Insert insertar = BaseDatos.optenerIstancia().getMySQL().table("checador").insert();
        insertar.field("IDCliente", checador.getIdCliente());
        insertar.field("nombreCliente", checador.getNombreCliente());
        insertar.field("estadoCliente", checador.getEstadoCliente());
        insertar.field("horaEntrada", checador.getHoraEntrada());
        insertar.field("horaSalida", checador.getHoraSalida());
        insertar.field("fecha", checador.getFecha());
        
        try {
            insertar.execute();
            registros.add(checador);
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
        return true;
    }
    
    public List<ChecadorObj> getRegistros() {
        return registros;
    }
    
    public static int cargarAsistencias(int clienteId, int mes, DefaultTableModel modelo) {
        List<Map<String, Object>> resultTableUser = null;
        try {
            Select nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("checador").select();
            nombreTabla.where("IDCliente = ? AND MONTH(fecha) = ?", clienteId, mes); // Ajuste aquí
            resultTableUser = nombreTabla.fetchAllAsList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultTableUser != null) {
            modelo.setRowCount(0); // Limpiar la tabla antes de cargar los registros
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT")); 

            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

            for (Map<String, Object> map : resultTableUser) {
                Date fechaFormato = (Date) map.get("fecha");
                String fecha = formatoFecha.format(fechaFormato);

                Time horaEntradaTime = (Time) map.get("horaEntrada");
                Time horaSalidaTime = (Time) map.get("horaSalida");
                String horaEntrada = horaEntradaTime != null ? dateFormat.format(horaEntradaTime) : null;
                String horaSalida = horaSalidaTime != null ? dateFormat.format(horaSalidaTime) : null;
                
                modelo.addRow(new Object[]{fecha, horaEntrada, horaSalida});
            }
            return resultTableUser.size();
        }
        return 0;
    }

}