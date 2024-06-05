package Modelo;

import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;
import com.code.advancedsql.query.Update;

public class InstructorModelo {
	private static final int MAX_BLOB_SIZE = 65535;
	public static InstructorModelo instance = new InstructorModelo();
	
	static List<InstructorObj> instructor = new ArrayList<InstructorObj>();
	
	public static List<InstructorObj> getInstructor() {
		return instructor;
		
	}
	public static InstructorModelo obtenerInstancia() {
		return instance;
	}
	
	public static void cargarInstructor() {
		instructor.clear();
		Select nombreTabla=BaseDatos.optenerIstancia().getMySQL().table("instructor").select();
        List<Map<String, Object>> resultTableUser;
		try {
			resultTableUser = nombreTabla.fetchAllAsList();
			for (Map<String, Object> map : resultTableUser) {
				
	            
	        	int ID=(int)map.get("ID");
	        	String nombre=((String)map.get("nombre")); 
	        	String apellido=((String)map.get("apellido")); 
	        	String correo=((String)map.get("correo")); 
	        	String telefono=(String)map.get("telefono");
	        	String especialidad= (String)map.get("especialidad");
	        	String fechaContratacion=(String)map.get("fechaContratacion");
	      //  	int IDClase = (int)map.get("IDClase");
	        			
	        	
	        	BufferedImage imagen;
	        	try {
	        	    byte[] imagenBytes = (byte[]) map.get("imagen");
	        	    imagen = convertByteArrayToImage(imagenBytes);
	        	    System.out.println("Imagen cargada correctamente para el instructor con ID: " + ID);
	        	} catch (IOException e) {
	        	    e.printStackTrace();
	        	    System.out.println("Error al cargar la imagen para el instructor con ID: " + ID);
	        	    throw new RuntimeException(e);
	        	}
				
	        	

	            instructor.add(new InstructorObj(ID,  nombre,  apellido,  correo,  telefono,
	        			 fechaContratacion,  especialidad, imagen,  2));
	            
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

    public void editarInstructor(InstructorObj instructor) {
		Update insertar =BaseDatos.optenerIstancia().getMySQL().table("instructor").update();
		insertar.field("nombre",instructor.getNombre());
		insertar.field("apellido",instructor.getApellido());
		insertar.field("correo",instructor.getCorreo());
		insertar.field("telefono",instructor.getTelefono());
		insertar.field("fechaContratacion",instructor.getFechaContratacion());
		insertar.field("especialidad",instructor.getEspecialidad());
//		insertar.field("IDclase",instructor.getIDClase());

		
		try {
			insertar.field("imagen",convertImageToBinary(instructor.getImagen()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		insertar.where("ID =?", instructor.getID());

		try {
			insertar.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se actualizó el instructor", "ERROR", JOptionPane.WARNING_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null, "Se actualizó el instructor correctamente");
	}

    public boolean subirDatosInstructor(InstructorObj instructor) {
		Insert insertar=BaseDatos.optenerIstancia().getMySQL().table("instructor").insert();
		insertar.field("nombre",instructor.getNombre());
		insertar.field("apellido",instructor.getApellido());
		insertar.field("correo",instructor.getCorreo());
		insertar.field("telefono",instructor.getTelefono());
		insertar.field("fechaContratacion",instructor.getFechaContratacion());
		insertar.field("especialidad",instructor.getEspecialidad());
		try {
			insertar.field("imagen",convertImageToBinary(instructor.getImagen()));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			insertar.execute();
			System.out.println("Inserción de instructor exitosa.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "No se pudo añadir el instructor", "ERROR", JOptionPane.WARNING_MESSAGE);
			 return false;
		}
		//client.add(cliente);
		 JOptionPane.showMessageDialog(null, "Se añadió instructor correctamente");
		 return true;
	}
    

}