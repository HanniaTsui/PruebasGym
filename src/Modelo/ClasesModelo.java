package Modelo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
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
	
	public static HorarioObj obtenerHorarioPorId(int idHorario) {
		Select nombreTabla=BaseDatos.optenerIstancia().getMySQL().table("horario").select();
		nombreTabla.where("ID = ?", idHorario);
		try {
            List<Map<String, Object>> resultTableUser = nombreTabla.fetchAllAsList();
            if (!resultTableUser.isEmpty()) {
                Map<String, Object> map = resultTableUser.get(0);
                return new HorarioObj((int) map.get("ID"), (String) map.get("horarioTiempo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static DiaObj obtenerDiaPorId(int idDia) {
		Select nombreTabla=BaseDatos.optenerIstancia().getMySQL().table("dia").select();
		nombreTabla.where("ID = ?", idDia);
		try {
            List<Map<String, Object>> resultTableUser = nombreTabla.fetchAllAsList();
            if (!resultTableUser.isEmpty()) {
                Map<String, Object> map = resultTableUser.get(0);
                return new DiaObj((int) map.get("ID"), (String) map.get("nombreDia"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

}