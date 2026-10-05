package co.sena.redsocial.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Centraliza la conexión JDBC con MySQL.
 */
public final class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/red_social"
            + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "CAMBIAR_PASSWORD";

    private ConexionBD() {
        // Evita instanciar esta clase de utilidad.
    }

    /**
     * Abre una conexión con la base de datos.
     *
     * @return conexión JDBC activa.
     * @throws SQLException si no es posible conectarse.
     */
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
