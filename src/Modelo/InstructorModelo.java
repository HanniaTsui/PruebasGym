package Modelo;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.code.advancedsql.query.Delete;
import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;
import com.code.advancedsql.query.Update;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

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
	        	    byte[] imagenBytes = (byte[]) map.get("imagen");
	        	    imagen = convertByteArrayToImage(imagenBytes);
	        	} catch (IOException e) {
	        	    e.printStackTrace();
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

    public void editarInstructor(InstructorObj instructor) {
		Update insertar =BaseDatos.optenerIstancia().getMySQL().table("instructor").update();
		insertar.field("nombre",instructor.getNombre());
		insertar.field("apellido",instructor.getApellido());
		insertar.field("correo",instructor.getCorreo());
		insertar.field("telefono",instructor.getTelefono());
		insertar.field("fechaContratacion",instructor.getFechaContratacion());
		insertar.field("especialidad",instructor.getEspecialidad());
		insertar.field("IDclase",instructor.getIDClase());

		
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
		insertar.field("IDClase", instructor.getIDClase());

		try {
			insertar.field("imagen",convertImageToBinary(instructor.getImagen()));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			insertar.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "No se pudo añadir el instructor", "ERROR", JOptionPane.WARNING_MESSAGE);
			 return false;
		}
		int idInstructor = obtenerUltimoIDInstructor();

	    // Asignar el ID del instructor a la clase correspondiente
	    if (idInstructor != -1) {
	        actualizarIDInstructorEnClase(instructor.getIDClase(), idInstructor);
	    } else {
	        JOptionPane.showMessageDialog(null, "Error al obtener el ID del instructor", "ERROR", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }
		JOptionPane.showMessageDialog(null, "Se añadió instructor correctamente");
		return true;
	}
    
    private static byte[] convertBufferedImageToByteArray(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    public void generarPDFCredencial(InstructorObj instructor) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter pdfs = new FileNameExtensionFilter("Documentos PDF", "pdf");
		chooser.addChoosableFileFilter(pdfs);
		chooser.setFileFilter(pdfs);

		int result = chooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			if (!file.getName().endsWith(".pdf")) {
				file = new File(file + ".pdf");
			}

			try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
				 Document doc = new Document(pdfDoc, PageSize.A4)) {

				PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
				Table table = new Table(UnitValue.createPercentArray(new float[]{1, 3})).useAllAvailableWidth();

				table.addHeaderCell(new Cell(1, 2).add(new Paragraph("Credencial del instructor")
						.setFont(font)
						.setFontSize(18)
						.setFontColor(DeviceGray.BLACK)
						.setBackgroundColor(new DeviceRgb(180, 180, 255))
						.setTextAlignment(TextAlignment.CENTER)));

				table.addCell(new Cell().add(new Paragraph("Nombre:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(instructor.getNombre() + " " + instructor.getApellido()))
						.setFont(font).setFontSize(12));

				table.addCell(new Cell().add(new Paragraph("Especialidad:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(instructor.getEspecialidad()))
						.setFont(font).setFontSize(12));

				table.addCell(new Cell().add(new Paragraph("Correo Electrónico:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(instructor.getCorreo()))
						.setFont(font).setFontSize(12));
				
				table.addCell(new Cell().add(new Paragraph("Fecha de contratación:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(instructor.getFechaContratacion()))
						.setFont(font).setFontSize(12));
				
				if (instructor.getImagen() != null) {
	                try {
	                    BufferedImage bufferedImage = instructor.getImagen();
	                    byte[] imageData = convertBufferedImageToByteArray(bufferedImage);
	                    ImageData image = ImageDataFactory.create(imageData);
	                    Image img = new Image(image);
	                    img.scaleToFit(150, 150); 

	                    table.addCell(new Cell().add(new Paragraph("Foto:"))
	                            .setFont(font).setFontSize(12).setBold());
	                    table.addCell(new Cell().add(img));
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	            }

				doc.add(new Paragraph("Larry's Gym - Credencial del instructor\n\n")
						.setFontSize(22)
						.setTextAlignment(TextAlignment.CENTER));
				doc.add(table);
				JOptionPane.showMessageDialog(null, "¡Descarga exitosa!", "", JOptionPane.INFORMATION_MESSAGE);

				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().open(file);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "No se pudo abrir el documento", "", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Hubo un error al generar el PDF.", "", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void generarPDFReporte(InstructorObj instruc) {
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter pdfs = new FileNameExtensionFilter("Documentos PDF", "pdf");
		chooser.addChoosableFileFilter(pdfs);
		chooser.setFileFilter(pdfs);

		int result = chooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			if (!file.getName().endsWith(".pdf")) {
				file = new File(file + ".pdf");
			}

			try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
				 Document doc = new Document(pdfDoc, PageSize.A4)) {

				PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
				Table table = new Table(UnitValue.createPercentArray(new float[]{1, 3})).useAllAvailableWidth();

				table.addHeaderCell(new Cell(1, 2).add(new Paragraph("Reporte del Instructor")
						.setFont(font)
						.setFontSize(18)
						.setFontColor(DeviceGray.BLACK)
						.setBackgroundColor(new DeviceRgb(180, 180, 255))
						.setTextAlignment(TextAlignment.CENTER)));

				table.addCell(new Cell().add(new Paragraph("Instructor:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(instruc.getNombre() + " " + instruc.getApellido()))
						.setFont(font).setFontSize(12));
				
				table.addCell(new Cell().add(new Paragraph("Correo Electrónico:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(instruc.getCorreo()))
						.setFont(font).setFontSize(12));

				table.addCell(new Cell().add(new Paragraph("Especialidad:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(instruc.getEspecialidad()))
						.setFont(font).setFontSize(12));
				
				String horarioYDia = null;
				ClasesModelo.obtenerInstancia().cargarClases();
			    for (InstructorObj instructor1 : instructor) {
			        if (instructor1.getID() == instruc.getID()) {
			        	int idClase = instructor1.getIDClase(); 
			        	horarioYDia = ClasesModelo.obtenerHorarioYDiaPorIdClase(idClase); 
			        }
			    }
	            
				table.addCell(new Cell().add(new Paragraph("Historial de clase:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(horarioYDia)))
						.setFont(font).setFontSize(12);

				doc.add(new Paragraph("Larry's Gym - Reporte del instructor\n\n")
						.setFontSize(22)
						.setTextAlignment(TextAlignment.CENTER));
				doc.add(table);
				JOptionPane.showMessageDialog(null, "¡Descarga exitosa!", "", JOptionPane.INFORMATION_MESSAGE);

				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().open(file);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "No se pudo abrir el documento", "", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Hubo un error al generar el PDF.", "", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void eliminarInstructor(InstructorObj ins) {
		try {
			// Eliminar el cliente de la base de datos
			Delete query = BaseDatos.optenerIstancia().getMySQL().table("instructor").delete().where("ID = ?", Integer.toString(ins.getID()));
			int execute = query.execute();


			// Si la eliminación en la base de datos fue exitosa, eliminar el cliente de la lista en memoria
			if (execute > 0) {
				System.out.println("se elimino");
				instructor.remove(ins);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static InstructorObj obtenerInstructorPorIdClase(int idClase) {
	    Select select = BaseDatos.optenerIstancia().getMySQL().table("instructor").select().where("IDClase = ?", idClase);
	    List<Map<String, Object>> result;
	    try {
	        result = select.fetchAllAsList();
	        if (!result.isEmpty()) {
	            Map<String, Object> map = result.get(0);

	            int ID = (int) map.get("ID");
	            String nombre = (String) map.get("nombre");
	            String apellido = (String) map.get("apellido");
	            String correo = (String) map.get("correo");
	            String telefono = (String) map.get("telefono");
	            String especialidad = (String) map.get("especialidad");
	            String fechaContratacion = (String) map.get("fechaContratacion");

	            BufferedImage imagen;
	            try {
	                byte[] imagenBytes = (byte[]) map.get("imagen");
	                imagen = convertByteArrayToImage(imagenBytes);
	            } catch (IOException e) {
	                e.printStackTrace();
	                throw new RuntimeException(e);
	            }

	            return new InstructorObj(ID, nombre, apellido, correo, telefono, fechaContratacion, especialidad, imagen, idClase);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	private int obtenerUltimoIDInstructor() {
	    try {
	        Select select = BaseDatos.optenerIstancia().getMySQL().table("instructor").select(new String[]{"MAX(ID) AS ultimoID"});
	        List<Map<String, Object>> result = select.fetchAllAsList();
	        if (!result.isEmpty()) {
	            Map<String, Object> row = result.get(0);
	            return (int) row.get("ultimoID");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}


	public void actualizarIDInstructorEnClase(int idClase, int idInstructor) {
	    Update updateClase = BaseDatos.optenerIstancia().getMySQL().table("clase").update();
	    updateClase.field("IDInstructor", idInstructor);
	    updateClase.where("ID = ?", idClase);
	    try {
	        updateClase.execute();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al actualizar el ID del instructor en la clase", "ERROR", JOptionPane.WARNING_MESSAGE);
	    }
	}
	

}