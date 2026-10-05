package co.sena.redsocial.service;

import co.sena.redsocial.dao.PublicacionDAO;
import co.sena.redsocial.model.Publicacion;

import java.sql.SQLException;
import java.util.List;

/**
 * Capa de servicio para las reglas básicas del módulo Publicación.
 */
public class PublicacionService {

    private final PublicacionDAO publicacionDAO = new PublicacionDAO();

    public int registrarPublicacion(Publicacion publicacion)
            throws SQLException {
        validarContenido(publicacion.getContenido());

        if (publicacion.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("El usuario es obligatorio.");
        }

        return publicacionDAO.insertar(publicacion);
    }

    public List<Publicacion> listarPublicaciones() throws SQLException {
        return publicacionDAO.consultarTodos();
    }

    public Publicacion buscarPublicacion(int idPublicacion)
            throws SQLException {
        return publicacionDAO.consultarPorId(idPublicacion);
    }

    public boolean editarPublicacion(Publicacion publicacion)
            throws SQLException {
        validarContenido(publicacion.getContenido());
        return publicacionDAO.actualizar(publicacion);
    }

    public boolean borrarPublicacion(int idPublicacion)
            throws SQLException {
        if (idPublicacion <= 0) {
            throw new IllegalArgumentException(
                    "El identificador debe ser mayor que cero.");
        }

        return publicacionDAO.eliminar(idPublicacion);
    }

    private void validarContenido(String contenido) {
        if (contenido == null || contenido.isBlank()) {
            throw new IllegalArgumentException(
                    "El contenido de la publicación no puede estar vacío.");
        }
    }
}
