package com.indrive.dao;

import com.indrive.config.Conexion;
import com.indrive.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // INSERT: registrar un usuario
    public boolean insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios "
                + "(nombre, apellido, correo, telefono, contrasena, tipo_usuario) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, usuario.getNombre());
            consulta.setString(2, usuario.getApellido());
            consulta.setString(3, usuario.getCorreo());
            consulta.setString(4, usuario.getTelefono());
            consulta.setString(5, usuario.getContrasena());
            consulta.setString(6, usuario.getTipoUsuario());

            return consulta.executeUpdate() > 0;
        }
    }

    // SELECT: consultar todos los usuarios
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql);
             ResultSet resultado = consulta.executeQuery()) {

            while (resultado.next()) {
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(resultado.getInt("id_usuario"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setTelefono(resultado.getString("telefono"));
                usuario.setContrasena(resultado.getString("contrasena"));
                usuario.setTipoUsuario(resultado.getString("tipo_usuario"));

                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

    // UPDATE: actualizar los datos de un usuario
    public boolean actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, "
                + "correo = ?, telefono = ? WHERE id_usuario = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, usuario.getNombre());
            consulta.setString(2, usuario.getApellido());
            consulta.setString(3, usuario.getCorreo());
            consulta.setString(4, usuario.getTelefono());
            consulta.setInt(5, usuario.getIdUsuario());

            return consulta.executeUpdate() > 0;
        }
    }

    // DELETE: eliminar un usuario por su identificador
    public boolean eliminar(int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setInt(1, idUsuario);

            return consulta.executeUpdate() > 0;
        }
    }
}
