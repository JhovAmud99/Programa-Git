package co.sena.redsocial.model;

import java.time.LocalDateTime;

/**
 * Entidad del módulo Inicio: representa una publicación.
 */
public class Publicacion {

    private int idPublicacion;
    private String contenido;
    private LocalDateTime fechaPublicacion;
    private int idUsuario;

    public Publicacion() {
    }

    public Publicacion(int idPublicacion, String contenido,
                       LocalDateTime fechaPublicacion, int idUsuario) {
        this.idPublicacion = idPublicacion;
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
        this.idUsuario = idUsuario;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
