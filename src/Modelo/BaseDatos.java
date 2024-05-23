package Modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.code.advancedsql.*;
import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;


public class BaseDatos {
    private static BaseDatos Istance=new BaseDatos();
    private MySQL mySQL;

    static List<Usuario> user = new ArrayList<Usuario>();
    

    public static List<Usuario> getUser() {
		return user;
	}

	
	public static BaseDatos optenerIstancia() {
        return Istance;
    }
	public void subirDatos(Usuario usuario) {
		Insert insertar=mySQL.table("login").insert();
		insertar.field("usuario",usuario.getNombre());
		insertar.field("contraseña",usuario.getContraseña());
		insertar.field("correo",usuario.getCorreo());
		
		try {
			insertar.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "No se pudo añadir usuario", "ERROR", JOptionPane.WARNING_MESSAGE);
			 return;
		}
		user.add(usuario);
		 JOptionPane.showMessageDialog(null, "se añadio correctamente");
	}
 
    public void cargarBase() {
        try {
        	 mySQL = new MySQL("sql.freedb.tech", 3306, "freedb_jassiel", "!xVcQv$t3%h9vSB", "freedb_Gimjas");

 		    if (mySQL.isConnected()) {
 		        System.out.println("Connected!");

            Select nombreTabla=mySQL.table("login").select();
            List<Map<String, Object>> resultTableUser = nombreTabla.fetchAllAsList();
            for (Map<String, Object> map : resultTableUser) {
                
                System.out.println("\n El Usuario es : "+map.get("usuario"));
                System.out.println("La contraseña es: "+map.get("contraseña"));

                String nombre=((String)map.get("usuario"));
                String contraseña=((String)map.get("contraseña"));
                String correo= ((String)map.get("correo"));
                

                user.add(new Usuario(nombre,contraseña,correo));
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}