package com.indrive.controller;

import com.indrive.dao.UsuarioDAO;
import com.indrive.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setAttribute("usuarios", usuarioDAO.listar());
            request.getRequestDispatcher("/index.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Error al consultar usuarios", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            Usuario usuario = new Usuario(
                request.getParameter("nombre"),
                request.getParameter("apellido"),
                request.getParameter("correo"),
                request.getParameter("telefono"),
                request.getParameter("contrasena"),
                "pasajero"
            );

            usuarioDAO.insertar(usuario);

            response.sendRedirect(
                request.getContextPath() + "/usuarios?registro=exitoso"
            );

        } catch (Exception e) {
            throw new ServletException("Error al registrar usuario", e);
        }
    }
}
^
