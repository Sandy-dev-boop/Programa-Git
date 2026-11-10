package com.indrive;

import com.indrive.dao.UsuarioDAO;
import com.indrive.model.Usuario;

public class PruebaUsuarioDAO {

    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();

        String correoPrueba = "prueba.crud@indrive.test";
        int idUsuario = 0;

        try {
            // INSERT
            Usuario nuevo = new Usuario(
                    "Usuario",
                    "Prueba",
                    correoPrueba,
                    "3000000000",
                    "clave_temporal",
                    "pasajero"
            );

            if (dao.insertar(nuevo)) {
                System.out.println("INSERT: usuario registrado.");
            }

            // SELECT
            for (Usuario usuario : dao.listar()) {
                if (correoPrueba.equals(usuario.getCorreo())) {
                    idUsuario = usuario.getIdUsuario();
                    System.out.println("SELECT: usuario encontrado. ID: " + idUsuario);
                    break;
                }
            }

            if (idUsuario == 0) {
                throw new Exception("No se encontró el usuario de prueba.");
            }

            // UPDATE
            Usuario actualizado = new Usuario(
                    "UsuarioActualizado",
                    "Prueba",
                    correoPrueba,
                    "3010000000",
                    "clave_temporal",
                    "pasajero"
            );

            actualizado.setIdUsuario(idUsuario);

            if (dao.actualizar(actualizado)) {
                System.out.println("UPDATE: usuario actualizado.");
            }

            // DELETE
            if (dao.eliminar(idUsuario)) {
                System.out.println("DELETE: usuario eliminado.");
            }

            System.out.println("Prueba CRUD finalizada.");

        } catch (Exception e) {
            System.out.println("Error en la prueba CRUD: " + e.getMessage());
        }
    }
}
