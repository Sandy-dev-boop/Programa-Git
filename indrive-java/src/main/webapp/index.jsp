<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.indrive.model.Usuario" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuarios - inDrive</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f7f5;
            margin: 0;
            padding: 25px;
        }

        .contenedor {
            max-width: 750px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 12px;
        }

        h1 {
            color: #438c35;
            text-align: center;
        }

        label {
            display: block;
            margin-top: 12px;
        }

        input {
            width: 100%;
            padding: 11px;
            margin-top: 5px;
            box-sizing: border-box;
        }

        button {
            margin-top: 18px;
            padding: 12px;
            background: #8fdc69;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        table {
            width: 100%;
            margin-top: 25px;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background: #8fdc69;
        }
    </style>
</head>

<body>
<div class="contenedor">

    <h1>inDrive - Gestión de usuarios</h1>

    <% if ("exitoso".equals(request.getParameter("registro"))) { %>
        <p>Usuario registrado correctamente.</p>
    <% } %>

    <h2>Registrar usuario</h2>

    <form action="<%= request.getContextPath() %>/usuarios" method="POST">

        <label>Nombre:</label>
        <input type="text" name="nombre" required>

        <label>Apellido:</label>
        <input type="text" name="apellido" required>

        <label>Correo electrónico:</label>
        <input type="email" name="correo" required>

        <label>Teléfono:</label>
        <input type="text" name="telefono">

        <label>Contraseña:</label>
        <input type="password" name="contrasena" required>

        <button type="submit">Registrar usuario</button>
    </form>

    <h2>Usuarios registrados</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Correo</th>
        </tr>

        <%
            List<Usuario> usuarios =
                (List<Usuario>) request.getAttribute("usuarios");

            if (usuarios != null) {
                for (Usuario u : usuarios) {
        %>
        <tr>
            <td><%= u.getIdUsuario() %></td>
            <td><%= u.getNombre() %></td>
            <td><%= u.getApellido() %></td>
            <td><%= u.getCorreo() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>

</div>
</body>
</html>
