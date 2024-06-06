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
}