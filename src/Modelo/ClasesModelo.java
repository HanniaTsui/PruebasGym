package Modelo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

import com.code.advancedsql.query.Delete;
import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;
import com.code.advancedsql.query.Update;

public class ClasesModelo {
	public static ClasesModelo instance = new ClasesModelo();
	
	static List<ClasesObj> clases = new ArrayList<ClasesObj>();
	public static List<ClasesObj> getCheck() {
		return clases;
		
	}

	List<ClienteObj> clientes = ClienteModelo.obtenerInstancia().getClient();
	public static ClasesModelo obtenerInstancia() {
		return instance;
	}

	public static List<ClasesObj> cargarClases() {
		clases.clear();
		Select nombreTabla=BaseDatos.optenerIstancia().getMySQL().table("clase").select();
        List<Map<String, Object>> resultTableUser;
		try {
			resultTableUser = nombreTabla.fetchAllAsList();
			for (Map<String, Object> map : resultTableUser) {
				
	            
	        	int ID=(int)map.get("ID");
	        	String nombre=((String)map.get("nombreClase")); 
	        	int dia=((int)map.get("IDDia")); 
	        	int horario=((int)map.get("IDHorario")); 
	      //  	int IDClase = (int)map.get("IDClase");
	            clases.add(new ClasesObj(ID,  nombre,  dia,  horario));
	            
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return clases;
	}
	public static int obtenerHorarioPorId(String idHorario) {
        Select nombreTabla=BaseDatos.optenerIstancia().getMySQL().table("horario").select();
        nombreTabla.where("horarioTiempo = ?", idHorario);
        try {
            List<Map<String, Object>> resultTableUser = nombreTabla.fetchAllAsList();
            if (!resultTableUser.isEmpty()) {
                Map<String, Object> map = resultTableUser.get(0);
                return (int) map.get("ID");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
	
	 public void editarClase(ClasesObj clase) {
			Update insertar =BaseDatos.optenerIstancia().getMySQL().table("clase").update();
			insertar.field("nombreClase", clase.getNombre());
			insertar.field("IDDia", clase.getIdDia());
			insertar.field("IDHorario", clase.getIdHorario());


			insertar.where("ID =?", clase.getID());

			try {
				insertar.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se actualizó la clase", "ERROR", JOptionPane.WARNING_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(null, "Se actualizó la clase correctamente");
		}
	 public boolean subirDatosClases(ClasesObj clase) {
		 boolean idDiaValido = verificarExistenciaIDDia(clase.getIdDia());
		 System.out.println(clase.getID());
		    // Si el IDDia no es válido, mostrar un mensaje de error y retornar false
		    if (!idDiaValido) {
		    	
		        JOptionPane.showMessageDialog(null, "El ID de día proporcionado no es válido", "ERROR", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }

		 
		 Insert insertar=BaseDatos.optenerIstancia().getMySQL().table("clase").insert();
         insertar.field("ID",clase.getID());
         insertar.field("nombreClase",clase.getNombre());
         insertar.field("IDDia", clase.getIdDia());
         insertar.field("IDHorario", clase.getIdHorario());;
         try {
             insertar.execute();
         } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
              JOptionPane.showMessageDialog(null, "No se pudo añadir la clase", "ERROR", JOptionPane.WARNING_MESSAGE);
              return false;
         }
          JOptionPane.showMessageDialog(null, "Se añadió la clase correctamente");
          return true;
     }
	 
	 private boolean verificarExistenciaIDDia(int idDia) {
		    // Realizar una consulta para verificar si el IDDia existe en la tabla dia
		    Select select = BaseDatos.optenerIstancia().getMySQL().table("dia").select().where("ID = ?", idDia);

		    try {
		        List<Map<String, Object>> result = select.fetchAllAsList();
		        // Si la consulta devuelve al menos una fila, el IDDia es válido
		        return !result.isEmpty();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

  public void eliminarClases(ClasesObj cla) {
         try {
             // Eliminar el cliente de la base de datos
             Delete query = BaseDatos.optenerIstancia().getMySQL().table("clase").delete().where("ID = ?", Integer.toString(cla.getID()));
             int execute = query.execute();

             // Imprimir la consulta y el resultado
             System.out.println(query);
             System.out.println(execute);

             // Si la eliminación en la base de datos fue exitosa, eliminar el cliente de la lista en memoria
             if (execute > 0) {
                 System.out.println("se elimino");
                 clases.remove(cla);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
  public static boolean inscribirClienteEnClase(int idCliente, int idClase) {
	    try {
	        Insert insertar = BaseDatos.optenerIstancia().getMySQL().table("inscripciones").insert();
	        insertar.field("IDCliente", idCliente);
	        insertar.field("IDClase", idClase);
	        insertar.execute();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}