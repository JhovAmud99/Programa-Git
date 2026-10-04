<<<<<<< HEAD
# Programa-Git
=======
# EV06 - CRUD JDBC - RedSocial ADSO

Módulo: Publicación / Inicio.

## Tecnologías
- Java 21
- JDBC
- MySQL
- Maven
- Git / GitHub
- Visual Studio Code o IntelliJ IDEA / NetBeans

## Arquitectura
- `model`: entidades.
- `dao`: acceso a datos JDBC.
- `service`: reglas básicas del módulo.
- `util`: conexión a la base de datos.
- `app`: ejecución de demostración CRUD.

## Antes de ejecutar
1. Instalar MySQL.
2. Ejecutar `sql/red_social_jdbc.sql`.
3. Abrir `ConexionBD.java`.
4. Cambiar `CONTRASENA` por la contraseña real del usuario MySQL.
5. Tener Maven instalado.
6. Ejecutar:
   `mvn clean compile`
7. Ejecutar:
   `mvn exec:java`

## CRUD
- C: insertar publicación.
- R: consultar publicaciones / consultar por ID.
- U: actualizar publicación.
- D: eliminar publicación.

## Versionamiento
```bash
git init
git branch -M main
git add .
git commit -m "feat: implementar CRUD de publicaciones con JDBC"
git remote add origin URL_DEL_REPOSITORIO
git push -u origin main
```

## Importante
No subir contraseñas reales a GitHub. Para un proyecto real, las credenciales deben gestionarse mediante variables de entorno o configuración segura.
>>>>>>> 890d3be (feat: implementar CRUD de publicaciones con JDBC)
