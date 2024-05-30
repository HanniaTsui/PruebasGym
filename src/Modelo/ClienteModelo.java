package Modelo;

import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.code.advancedsql.MySQL;
import com.code.advancedsql.query.Delete;
import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;
import com.code.advancedsql.query.Update;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

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

	public void generarPDFCredencial(Cliente cliente) {
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

				table.addHeaderCell(new Cell(1, 2).add(new Paragraph("Credencial del Cliente")
						.setFont(font)
						.setFontSize(18)
						.setFontColor(DeviceGray.WHITE)
						.setBackgroundColor(new DeviceRgb(0, 102, 204))
						.setTextAlignment(TextAlignment.CENTER)));

				table.addCell(new Cell().add(new Paragraph("Nombre:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(cliente.getNombre()))
						.setFont(font).setFontSize(12));

				table.addCell(new Cell().add(new Paragraph("Fecha de Nacimiento:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(cliente.getFechaNacimiento()))
						.setFont(font).setFontSize(12));

				table.addCell(new Cell().add(new Paragraph("Teléfono:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(cliente.getTelefono()))
						.setFont(font).setFontSize(12));

				table.addCell(new Cell().add(new Paragraph("Correo Electrónico:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(cliente.getCorreo()))
						.setFont(font).setFontSize(12));

				table.addCell(new Cell().add(new Paragraph("Fecha de Registro:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(cliente.getFechaInicial()))
						.setFont(font).setFontSize(12));

				table.addCell(new Cell().add(new Paragraph("Suscripción: "))
						.setFont(font).setFontSize(12).setBold());

				Paragraph fechaParagraph = new Paragraph(cliente.getFechaInicial() + " - " + cliente.getFechaFinal())
						.setFont(font)
						.setFontSize(12);
				table.addCell(new Cell().add(fechaParagraph));

				doc.add(new Paragraph("Larry's Gym - Credencial del Cliente\n\n")
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

	public void generarPDFReporte(Cliente cliente) {
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

				table.addHeaderCell(new Cell(1, 2).add(new Paragraph("Reporte del Cliente")
						.setFont(font)
						.setFontSize(18)
						.setFontColor(DeviceGray.BLACK)
						.setBackgroundColor(new DeviceRgb(119, 182, 255))
						.setTextAlignment(TextAlignment.CENTER)));

				Paragraph nombre = new Paragraph(cliente.getNombre() + " " + cliente.getApellido())
						.setFont(font)
						.setFontSize(12);

				table.addCell(new Cell().add(new Paragraph("Cliente:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(nombre));

				table.addCell(new Cell().add(new Paragraph("Correo Electrónico:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(cliente.getCorreo()))
						.setFont(font).setFontSize(12));

				table.addCell(new Cell().add(new Paragraph("Suscripción: "))
						.setFont(font).setFontSize(12).setBold());

				Paragraph fechaParagraph = new Paragraph(cliente.getFechaInicial() + " - " + cliente.getFechaFinal())
						.setFont(font)
						.setFontSize(12);
				table.addCell(new Cell().add(fechaParagraph));

				table.addCell(new Cell().add(new Paragraph("Historial de asistencias:"))
						.setFont(font).setFontSize(12).setBold());
				table.addCell(new Cell().add(new Paragraph(cliente.getFechaNacimiento()))
						.setFont(font).setFontSize(12));

				doc.add(new Paragraph("Larry's Gym - Reporte del Cliente\n\n")
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

	public void editarCliente(Cliente cliente) {
		Update insertar =BaseDatos.optenerIstancia().getMySQL().table("cliente").update();
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

		insertar.where("ID =?", cliente.getID());

		try {
			insertar.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se actualizar el cliente", "ERROR", JOptionPane.WARNING_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null, "se actualizar el cliente correctamente");
	}

	public boolean subirDatosCliente(Cliente cliente) {
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
			 return false;
		}
		//client.add(cliente);
		 JOptionPane.showMessageDialog(null, "se añadio cliente correctamente");
		 return true;
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
	        	String telefono=(String)map.get("telefono");
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
	// Metodos para blob de imagen 
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
