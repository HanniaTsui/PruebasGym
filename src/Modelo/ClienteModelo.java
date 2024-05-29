package Modelo;

import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JOptionPane;

import com.code.advancedsql.MySQL;
import com.code.advancedsql.query.Delete;
import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;

public class ClienteModelo {
	public static ClienteModelo instance = new ClienteModelo();
	private static final int MAX_BLOB_SIZE = 65535;
	
	
	static List<Cliente> client = new ArrayList<Cliente>();

	public static List<Cliente> getClient() {
		return client;
		
	}
	public static ClienteModelo obtenerInstancia() {
		return instance;
	}
	
	public void eliminarCliente(Cliente cliente) {
		try {
			// Eliminar el cliente de la base de datos
			Delete query = BaseDatos.optenerIstancia().getMySQL().table("cliente").delete().where("ID = ?", Integer.toString(cliente.getID()));
			int execute = query.execute();

			// Imprimir la consulta y el resultado
			System.out.println(query);
			System.out.println(execute);

			// Si la eliminación en la base de datos fue exitosa, eliminar el cliente de la lista en memoria
			if (execute > 0) {
				System.out.println("se elimino");
				client.remove(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void subirDatosCliente(Cliente cliente) {
		Insert insertar=BaseDatos.optenerIstancia().getMySQL().table("cliente").insert();
		insertar.field("ID",cliente.getID());
		insertar.field("nombre",cliente.getNombre());
		insertar.field("apellido",cliente.getApellido());
		insertar.field("correo",cliente.getCorreo());
		insertar.field("telefono",cliente.getTelefono());
		insertar.field("fechaInicial",cliente.getFechaInicial());
		insertar.field("fechaFinal",cliente.getFechaFinal());
		insertar.field("tipoMembresia",cliente.getTipoMembresia());
		insertar.field("planMembresia",cliente.getPlanMembresia());
		insertar.field("fechaNacimiento",cliente.getFechaNacimiento());
		try {
			insertar.field("imagen",convertImageToBinary(cliente.getImagen()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insertar.field("metodoPago",cliente.getMetodoPago());
		
		
		try {
			insertar.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "No se pudo añadir el cliente", "ERROR", JOptionPane.WARNING_MESSAGE);
			 return;
		}
		client.add(cliente);
		 JOptionPane.showMessageDialog(null, "se añadio cliente correctamente");
	}
	
	public static void cargarCliente() {
		client.clear();
		Select nombreTabla=BaseDatos.optenerIstancia().getMySQL().table("cliente").select();
        List<Map<String, Object>> resultTableUser;
		try {
			resultTableUser = nombreTabla.fetchAllAsList();
			for (Map<String, Object> map : resultTableUser) {
				
	            
	        	int ID=(int)map.get("ID");
	        	String nombre=((String)map.get("nombre")); 
	        	String apellido=((String)map.get("apellido")); 
	        	String correo=((String)map.get("correo")); 
	        	int telefono=(int)map.get("telefono");
	        	String fechaInicial=((String)map.get("fechaInicial"));
	        	String fechaFinal=((String)map.get("fechaFinal")); 
	        	String tipoMembresia=((String)map.get("tipoMembresia")); 
	        	String planMembresia=((String)map.get("planMembresia")); 
	        	String fechaNacimiento=((String)map.get("fechaNacimiento"));
	        	BufferedImage imagen;
				try {
					imagen = convertByteArrayToImage((byte[])map.get("imagen"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
	        	String metodoPago=((String)map.get("metodoPago"));
	            
	            

	            client.add(new Cliente( ID,  nombre, apellido,  correo,  telefono,  fechaInicial,
	        			 fechaFinal,  tipoMembresia,  planMembresia,  fechaNacimiento,  imagen,
	        			 metodoPago));
	            
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	private static BufferedImage convertByteArrayToImage(byte[] imageData) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(imageData);
        return ImageIO.read(inputStream);
    }
	

    private static InputStream convertImageToBinary(BufferedImage image) throws IOException {
        BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        ColorConvertOp op = new ColorConvertOp(null);
        op.filter(image, rgbImage);

        byte[] imageData = compressImage(rgbImage, Integer.MAX_VALUE);

        if (imageData.length > MAX_BLOB_SIZE) {
            imageData = compressImage(rgbImage, MAX_BLOB_SIZE);
        }

        return new ByteArrayInputStream(imageData);
    }

    private static byte[] compressImage(BufferedImage image, int maxSize) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        float quality = 1.0f;
        boolean success = false;

        while (quality > 0.0f) {
            byteArrayOutputStream.reset();
            if (writeImageWithQuality(image, byteArrayOutputStream, quality)) {
                byte[] imageData = byteArrayOutputStream.toByteArray();
                if (imageData.length <= maxSize) {
                    success = true;
                    break;
                }
            }
            quality -= 0.1f;
        }

        if (!success) {
            throw new IOException("No se pudo comprimir la imagen por debajo del tamaño máximo permitido.");
        }

        return byteArrayOutputStream.toByteArray();
    }

    private static boolean writeImageWithQuality(BufferedImage image, ByteArrayOutputStream outputStream, float quality) {
        try {
            ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
            ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
            jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            jpgWriteParam.setCompressionQuality(quality);

            try (ImageOutputStream ios = ImageIO.createImageOutputStream(outputStream)) {
                jpgWriter.setOutput(ios);
                jpgWriter.write(null, new IIOImage(image, null, null), jpgWriteParam);
                ios.flush();
            }
            jpgWriter.dispose();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
