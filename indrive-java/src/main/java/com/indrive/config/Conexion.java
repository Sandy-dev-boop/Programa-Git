package com.indrive.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mariadb://127.0.0.1:3306/indrive";

    private static final String USUARIO = "indrive_app";

    public static Connection conectar() throws SQLException {
        String clave = System.getenv("INDRIVE_DB_PASSWORD");

        if (clave == null || clave.isEmpty()) {
            throw new SQLException("No se ha configurado la contraseña de la base de datos.");
        }

        return DriverManager.getConnection(URL, USUARIO, clave);
    }

    public static void main(String[] args) {
        try (Connection conexion = conectar()) {
            System.out.println("¡Conexión exitosa con MariaDB!");
            System.out.println("Base de datos: " +
                    conexion.getCatalog());
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
