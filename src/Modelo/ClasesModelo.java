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
	 public static String obtenerHorarioYDiaPorIdClase(int idClase) {
	        for (ClasesObj clase : clases) {
	            if (clase.getID() == idClase) {
	                String horarioText;
	                String diaText;
	                
	                // Obtener el horario según el ID de la clase
	                switch (clase.getIdHorario()) {
	                case 1:
	                    horarioText = "06:00 - 07:00";
	                    break;
	                case 2:
	                    horarioText = "07:00 - 08:00";
	                    break;
	                case 3:
	                    horarioText = "08:00 - 09:00";
	                    break;
	                case 4:
	                    horarioText = "09:00 - 10:00";
	                    break;  
	                case 5:
	                    horarioText = "10:00 - 11:00";
	                    break; 
	                case 6:
	                    horarioText = "11:00 - 12:00";
	                    break;  
	                case 7:
	                    horarioText = "12:00 - 13:00";
	                    break; 
	                case 8:
	                    horarioText = "13:00 - 14:00";
	                    break;
	                case 9:
	                    horarioText = "14:00 - 15:00";
	                    break;
	                case 10:
	                    horarioText = "15:00 - 16:00";
	                    break;
	                case 11:
	                    horarioText = "16:00 - 17:00";
	                    break;
	                case 12:
	                    horarioText = "17:00 - 18:00";
	                    break;
	                case 13:
	                    horarioText = "18:00 - 19:00";
	                    break;
	                case 14:
	                    horarioText = "19:00 - 20:00";
	                    break;
	                default:
	                    horarioText = "";
	                    break;
	                }
	                switch (clase.getIdDia()) {
	                case 1:
	                    diaText = "Lunes";
	                    break;
	                case 2:
	                    diaText = "Martes";
	                    break;
	                case 3:
	                    diaText = "Miércoles";
	                    break;
	                case 4:
	                    diaText = "Jueves";
	                    break;
	                case 5:
	                    diaText = "Viernes";
	                    break;
	                default:
	                    diaText = "";
	                    break;
	            }
	                return horarioText + " - " + diaText;
	            }
	        }
	        return null;
	    }
	 public static List<String> obtenerNombresClases() {
		    List<String> nombresClases = new ArrayList<>();
		    Select nombreTabla = BaseDatos.optenerIstancia().getMySQL().table("clase").select(new String[]{"nombreClase"});
		    try {
		        List<Map<String, Object>> resultTableUser = nombreTabla.fetchAllAsList();
		        for (Map<String, Object> map : resultTableUser) {
		            String nombreClase = (String) map.get("nombreClase");
		            nombresClases.add(nombreClase);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return nombresClases;
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
  
  public List<ClienteObj> obtenerClientesInscritos(int idClase) {
	    List<ClienteObj> clientesInscritos = new ArrayList<>();

	    // Realiza una consulta para obtener los clientes inscritos en la clase específica
	    Select select = BaseDatos.optenerIstancia().getMySQL().table("inscripciones").select().where("idClase = ?", idClase);

	    try {
	        List<Map<String, Object>> result = select.fetchAllAsList();
	        for (Map<String, Object> map : result) {
	            Object idClienteObject = map.get("idCliente");
	            if (idClienteObject != null) {
	                int idCliente = (int) idClienteObject;
	                // Busca el cliente en la lista de clientes y agrégalo a la lista de clientes inscritos
	                for (ClienteObj cliente : clientes) {
	                    if (cliente.getID() == idCliente) {
	                        clientesInscritos.add(cliente);
	                        break; // Una vez que se encuentra el cliente, salir del bucle
	                    }
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return clientesInscritos;
	}
  
  public static boolean clienteEstaInscrito(int idCliente, int idClase) {
	    boolean estaInscrito = false;
	Select select = BaseDatos.optenerIstancia().getMySQL().table("inscripciones").select().where("IDCliente = ? AND IDClase = ?", idCliente, idClase);

	    try {
	        List<Map<String, Object>> result = select.fetchAllAsList();
	        if (!result.isEmpty()) {
	            estaInscrito = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return estaInscrito;
	}
  
  public boolean eliminarInscripcion(int idCliente, int idClase) {
	    try {
	        Delete query = BaseDatos.optenerIstancia().getMySQL().table("inscripciones")
	                .delete()
	                .where("IDCliente = ? AND IDClase = ?", idCliente, idClase);
	        int execute = query.execute();

	        if (execute > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; 
	}


  public String obtenerNombreInstructor(int idInstructor) {
	    String nombreInstructor = "";
	    Select select = BaseDatos.optenerIstancia().getMySQL().table("instructor").select(new String[]{"nombre", "apellido"}).where("ID = ?", idInstructor);
	    try {
	        List<Map<String, Object>> result = select.fetchAllAsList();
	        if (!result.isEmpty()) {
	            Map<String, Object> instructor = result.get(0);
	            String nombre = (String) instructor.get("nombre");
	            String apellido = (String) instructor.get("apellido");
	            nombreInstructor = nombre + " " + apellido;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return nombreInstructor;
	}

  public static int obtenerIdClasePorNombre(String nombreClase) {
	    int idClase = 0;

	    Select select = BaseDatos.optenerIstancia().getMySQL().table("clase").select().where("nombreClase = ?", nombreClase);

	    try {
	        List<Map<String, Object>> result = select.fetchAllAsList();
	        if (!result.isEmpty()) {
	            Map<String, Object> map = result.get(0);
	            idClase = (int) map.get("ID");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return idClase;
	}

  public static boolean claseAsignadaAOtroInstructor(int idClase, int idInstructorActual) {
      // Realizar una consulta para verificar si la clase está asignada a otro instructor
      Select select = BaseDatos.optenerIstancia().getMySQL().table("clase").select().where("ID = ?", idClase);
      try {
          List<Map<String, Object>> result = select.fetchAllAsList();
          if (!result.isEmpty()) {
              // Obtener el ID del instructor asignado a la clase
              Integer idInstructorAsignado = (Integer) result.get(0).get("IDInstructor");
              // Verificar si el ID del instructor asignado es diferente del ID del instructor actual
              if (idInstructorAsignado != null) {
                  return idInstructorAsignado.intValue() != idInstructorActual;
              }
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return false;
  }

  public boolean claseAsignada(int idClase) {
	    // Realizar una consulta para verificar si la clase está asignada a un instructor
	    Select select = BaseDatos.optenerIstancia().getMySQL().table("clase").select().where("ID = ?", idClase);
	    try {
	        List<Map<String, Object>> result = select.fetchAllAsList();
	        if (!result.isEmpty()) {
	            // Verificar si la clase está asignada a un instructor
	            Object idInstructorAsignadoObj = result.get(0).get("IDInstructor");
	            // Verificar si el ID del instructor asignado es null
	            if (idInstructorAsignadoObj == null) {
	                return false;
	            }
	            // Si no es null, obtener y verificar su valor
	            int idInstructorAsignado = ((Number) idInstructorAsignadoObj).intValue();
	            return idInstructorAsignado > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}