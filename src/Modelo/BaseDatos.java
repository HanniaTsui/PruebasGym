package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import com.code.advancedsql.MySQL;
import com.code.advancedsql.query.Insert;
import com.code.advancedsql.query.Select;

public class BaseDatos {
    private static BaseDatos Istance = new BaseDatos();
    private MySQL mySQL;
    private Timer keepAliveTimer;

    public MySQL getMySQL() {
        return mySQL;
    }

    static List<Usuario> user = new ArrayList<Usuario>();

    public static List<Usuario> getUser() {
        return user;
    }

    public static BaseDatos optenerIstancia() {
        return Istance;
    }

    public void subirDatos(Usuario usuario) {
        Insert insertar = mySQL.table("login").insert();
        insertar.field("usuario", usuario.getNombre());
        insertar.field("contraseña", usuario.getContraseña());
        insertar.field("correo", usuario.getCorreo());

        try {
            insertar.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo añadir usuario", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }
        user.add(usuario);
        JOptionPane.showMessageDialog(null, "¡Registro exitoso!");
    }

    public void cargarBase() {
        try {
            mySQL = new MySQL("sql.freedb.tech", 3306, "freedb_jassiel", "!xVcQv$t3%h9vSB", "freedb_Gimjas");

            if (mySQL.isConnected()) {
                System.out.println("Connected!");

                // Cambiar el wait_timeout para la sesión actual
                Connection connection = mySQL.getConnection();
                setSessionTimeout(connection, 600);

                // Iniciar el keep-alive
                startKeepAlive(connection);

                Select nombreTabla = mySQL.table("login").select();
                List<Map<String, Object>> resultTableUser = nombreTabla.fetchAllAsList();
                for (Map<String, Object> map : resultTableUser) {
                    System.out.println("\n El Usuario es : " + map.get("usuario"));
                    System.out.println("La contraseña es: " + map.get("contraseña"));

                    String nombre = ((String) map.get("usuario"));
                    String contraseña = ((String) map.get("contraseña"));
                    String correo = ((String) map.get("correo"));

                    user.add(new Usuario(nombre, contraseña, correo));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setSessionTimeout(Connection connection, int timeout) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SET SESSION wait_timeout = ?");
            stmt.setInt(1, timeout);
            stmt.execute();
            stmt.close();
            System.out.println("El wait_timeout de la sesión ha sido cambiado a " + timeout + " segundos");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void startKeepAlive(Connection connection) {
        keepAliveTimer = new Timer(true);
        keepAliveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    PreparedStatement keepAliveStmt = connection.prepareStatement("SELECT 1");
                    keepAliveStmt.execute();
                    keepAliveStmt.close();
                    System.out.println("Keep-alive ejecutado.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5 * 60 * 1000); // 5 minutos en milisegundos
    }

    public void stopKeepAlive() {
        if (keepAliveTimer != null) {
            keepAliveTimer.cancel();
        }
    }
}