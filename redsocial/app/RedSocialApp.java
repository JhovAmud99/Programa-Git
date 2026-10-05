package co.sena.redsocial.app;

import co.sena.redsocial.model.Publicacion;
import co.sena.redsocial.service.PublicacionService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Aplicación de demostración del CRUD JDBC del módulo Inicio.
 */
public class RedSocialApp {

    private static final PublicacionService SERVICIO =
            new PublicacionService();

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            try {
                ejecutarOpcion(opcion);
            } catch (SQLException | IllegalArgumentException excepcion) {
                System.out.println("Error: " + excepcion.getMessage());
            }
        } while (opcion != 0);

        System.out.println("Aplicación finalizada.");
    }

    private static void mostrarMenu() {
        System.out.println("\n=== RED SOCIAL ADSO - CRUD PUBLICACIONES ===");
        System.out.println("1. Insertar publicación");
        System.out.println("2. Consultar publicaciones");
        System.out.println("3. Consultar publicación por ID");
        System.out.println("4. Actualizar publicación");
        System.out.println("5. Eliminar publicación");
        System.out.println("0. Salir");
    }

    private static void ejecutarOpcion(int opcion) throws SQLException {
        switch (opcion) {
            case 1 -> insertar();
            case 2 -> consultarTodos();
            case 3 -> consultarPorId();
            case 4 -> actualizar();
            case 5 -> eliminar();
            case 0 -> {
                // La salida se gestiona después del ciclo.
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void insertar() throws SQLException {
        String contenido = leerTexto("Contenido: ");
        int idUsuario = leerEntero("ID del usuario: ");

        Publicacion publicacion = new Publicacion(
                0,
                contenido,
                LocalDateTime.now(),
                idUsuario
        );

        int idGenerado = SERVICIO.registrarPublicacion(publicacion);
        System.out.println("Publicación insertada con ID: " + idGenerado);
    }

    private static void consultarTodos() throws SQLException {
        List<Publicacion> publicaciones = SERVICIO.listarPublicaciones();

        if (publicaciones.isEmpty()) {
            System.out.println("No hay publicaciones registradas.");
            return;
        }

        publicaciones.forEach(publicacion ->
                System.out.printf(
                        "ID: %d | Usuario: %d | Fecha: %s | Contenido: %s%n",
                        publicacion.getIdPublicacion(),
                        publicacion.getIdUsuario(),
                        publicacion.getFechaPublicacion(),
                        publicacion.getContenido()
                )
        );
    }

    private static void consultarPorId() throws SQLException {
        int id = leerEntero("ID de publicación: ");
        Publicacion publicacion = SERVICIO.buscarPublicacion(id);

        if (publicacion == null) {
            System.out.println("Publicación no encontrada.");
            return;
        }

        System.out.println("ID: " + publicacion.getIdPublicacion());
        System.out.println("Usuario: " + publicacion.getIdUsuario());
        System.out.println("Fecha: " + publicacion.getFechaPublicacion());
        System.out.println("Contenido: " + publicacion.getContenido());
    }

    private static void actualizar() throws SQLException {
        int id = leerEntero("ID de publicación: ");
        String contenido = leerTexto("Nuevo contenido: ");

        Publicacion publicacion = new Publicacion();
        publicacion.setIdPublicacion(id);
        publicacion.setContenido(contenido);

        if (SERVICIO.editarPublicacion(publicacion)) {
            System.out.println("Publicación actualizada correctamente.");
        } else {
            System.out.println("No se encontró la publicación.");
        }
    }

    private static void eliminar() throws SQLException {
        int id = leerEntero("ID de publicación: ");

        if (SERVICIO.borrarPublicacion(id)) {
            System.out.println("Publicación eliminada correctamente.");
        } else {
            System.out.println("No se encontró la publicación.");
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return SCANNER.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(SCANNER.nextLine().trim());
    }
}
