package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FramePrincipal extends JFrame{
	private static FramePrincipal Instancia=new FramePrincipal();
	public FramePrincipal() {
        this.setSize(1200, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Larry's Gym");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setLayout(null);
	}
	 public void agregarPanel(JPanel panel) {
		 this.getContentPane().removeAll();
		 this.getContentPane().add(panel);
		 this.getContentPane().revalidate();
		 this.getContentPane().repaint();
		 this.setVisible(true);
	}
	 
	public static FramePrincipal obtenerInstancia() {
		return Instancia;
	}
}
