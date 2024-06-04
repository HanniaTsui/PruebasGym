package Modelo;

import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import com.code.advancedsql.query.Select;

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
	        	int IDClase = (int)map.get("IDClase");
	        			
	        	
	        	BufferedImage imagen;
	        
				try {
					imagen = convertByteArrayToImage((byte[])map.get("imagen"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
				
	        	

	            instructor.add(new InstructorObj(ID,  nombre,  apellido,  correo,  telefono,
	        			 fechaContratacion,  especialidad, imagen,  IDClase));
	            
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
