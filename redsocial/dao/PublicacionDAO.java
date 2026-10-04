package co.sena.redsocial.dao;

import co.sena.redsocial.model.Publicacion;
import co.sena.redsocial.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Acceso a datos del módulo Publicación mediante JDBC.
 * Implementa las operaciones CRUD.
 */
public class PublicacionDAO {

    private static final String INSERTAR_SQL =
            "INSERT INTO publicacion (contenido, fecha_publicacion, id_usuario) "
            + "VALUES (?, ?, ?)";

    private static final String CONSULTAR_SQL =
            "SELECT id_publicacion, contenido, fecha_publicacion, id_usuario "
            + "FROM publicacion ORDER BY fecha_publicacion DESC";

    private static final String CONSULTAR_POR_ID_SQL =
            "SELECT id_publicacion, contenido, fecha_publicacion, id_usuario "
            + "FROM publicacion WHERE id_publicacion = ?";

    private static final String ACTUALIZAR_SQL =
            "UPDATE publicacion SET contenido = ? WHERE id_publicacion = ?";

    private static final String ELIMINAR_SQL =
            "DELETE FROM publicacion WHERE id_publicacion = ?";

    /**
     * Inserta una publicación.
     */
    public int insertar(Publicacion publicacion) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(INSERTAR_SQL,
                             java.sql.Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, publicacion.getContenido());
            sentencia.setTimestamp(2,
                    Timestamp.valueOf(publicacion.getFechaPublicacion()));
            sentencia.setInt(3, publicacion.getIdUsuario());

            sentencia.executeUpdate();

            try (ResultSet claves = sentencia.getGeneratedKeys()) {
                if (claves.next()) {
                    return claves.getInt(1);
                }
            }
        }

        return 0;
    }

    /**
     * Consulta todas las publicaciones.
     */
    public List<Publicacion> consultarTodos() throws SQLException {
        List<Publicacion> publicaciones = new ArrayList<>();

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(CONSULTAR_SQL);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                publicaciones.add(mapearPublicacion(resultado));
            }
        }

        return publicaciones;
    }

    /**
     * Consulta una publicación por su identificador.
     */
    public Publicacion consultarPorId(int idPublicacion) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(CONSULTAR_POR_ID_SQL)) {

            sentencia.setInt(1, idPublicacion);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return mapearPublicacion(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Actualiza el contenido de una publicación.
     */
    public boolean actualizar(Publicacion publicacion) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(ACTUALIZAR_SQL)) {

            sentencia.setString(1, publicacion.getContenido());
            sentencia.setInt(2, publicacion.getIdPublicacion());

            return sentencia.executeUpdate() > 0;
        }
    }

    /**
     * Elimina una publicación.
     */
    public boolean eliminar(int idPublicacion) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia =
                     conexion.prepareStatement(ELIMINAR_SQL)) {

            sentencia.setInt(1, idPublicacion);

            return sentencia.executeUpdate() > 0;
        }
    }

    private Publicacion mapearPublicacion(ResultSet resultado)
            throws SQLException {

        Timestamp fecha = resultado.getTimestamp("fecha_publicacion");

        return new Publicacion(
                resultado.getInt("id_publicacion"),
                resultado.getString("contenido"),
                fecha != null ? fecha.toLocalDateTime() : null,
                resultado.getInt("id_usuario")
        );
    }
}
