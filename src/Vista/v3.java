package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

public class v3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCalendar calendar;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					v3 frame = new v3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public v3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		calendar = new JCalendar();
		// Ubicar y agregar al panel
		calendar.setBounds(676,100,400,400);
		 
		contentPane.add(calendar);
		// Botones
		calendar.setTodayButtonVisible(true); // Fecha de hoy
		calendar.setNullDateButtonVisible(true); // ninguna fecha
		calendar.setWeekOfYearVisible(false); // numero de semana
		
		// Cambiar color de letra del numero de día 
	//	calendar.setForeground(Color.GREEN);
		 
		// Cambiar color de letra del dia domingo
	//	calendar.setSundayForeground(Color.BLUE);
		 
		// Cambiar color de letra de semana
	//	calendar.setWeekdayForeground(Color.RED);
		
	//	Calendar calendario = new GregorianCalendar(2014,5,10); //Establecer fecha en el calendarios
	//	calendar.setDate(calendario.getTime());
		
		// Fecha minima seleccionable
	//	calendar.setMinSelectableDate(new Date());
		// Fecha maxima seleccionable
	//	calendar.setMaxSelectableDate(new Date());
		
		
		
		// Instanciar Componente
		dateChooser = new JDateChooser();
		 
		// Ubicar y agregar al panel
		dateChooser.setBounds(162,126,400,50);
		contentPane.add(dateChooser);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setBounds(131, 363, 46, 19);
		contentPane.add(yearChooser);
		
		dateChooser.getJCalendar();
	}
}
