package mx.edu.iems.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.edu.iems.contracts.IUsuarioDao;
import mx.edu.iems.dao.UsuarioDaoImp;
import mx.edu.iems.dto.UsuarioDTO;

public class AutentificaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute("loginUser") != null){
			
			String sistema = (String)session.getAttribute("sistema");
			
			if (sistema.equals("escolar"))
			request.getRequestDispatcher("pages/sicse/mainescolar.jsp")
					.forward(request, response);
			
			if (sistema.equals("semiescolar"))
				request.getRequestDispatcher("pages/sicse/mainsemiescolar.jsp")
						.forward(request, response);
			
		}else
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username").toUpperCase();
		String password = request.getParameter("password");
		String sistema = request.getParameter("sistema");

		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setUsuario(username);
		usuario.setPassword(password);

		IUsuarioDao usuarioJDBC = new UsuarioDaoImp();

		if (usuarioJDBC.esUsuarioCorrecto(usuario)
				&& (usuarioJDBC.isAdministrativo(usuario) || usuario
						.getUsuario().equals("AHERNANDEZ"))) {
			// creamos nueva sesion
			HttpSession session = request.getSession();

			session.setAttribute("loginUser", usuario.getUsuario());
			session.setAttribute("sistema", sistema);

			if (sistema.equals("escolar"))
				request.getRequestDispatcher("pages/sicse/mainescolar.jsp")
						.forward(request, response);

			if (sistema.equals("semiescolar"))
				request.getRequestDispatcher("pages/sicse/mainsemiescolar.jsp")
						.forward(request, response);

		} else {

			String msg = null;

			if (!usuarioJDBC.isAdministrativo(usuario))
				msg = "Usted no tiene autorización para entrar a esta aplicación";
			else
				msg = "Usuario/Password incorrecto";

			request.setAttribute("mensaje", msg);
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);

		}

	}

}
