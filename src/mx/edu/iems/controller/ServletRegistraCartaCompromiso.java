package mx.edu.iems.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.edu.iems.contracts.IAlumnoCartaCompromisoDao;
import mx.edu.iems.dao.AlumnoCartaCompromisoDaoImpl;
import mx.edu.iems.dao.AlumnoDaoImpl;
import mx.edu.iems.dto.AlumnoCartaCompromisoDTO;
import mx.edu.iems.dto.AlumnoDTO;

@WebServlet("/ServletRegistraCartaCompromiso")
public class ServletRegistraCartaCompromiso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletRegistraCartaCompromiso() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IAlumnoCartaCompromisoDao alumnocp = new AlumnoCartaCompromisoDaoImpl();
		AlumnoCartaCompromisoDTO dto = new AlumnoCartaCompromisoDTO();

		HttpSession session = null;
		ResourceBundle bundle = null;

		if (request.getParameter("matricula") != null) {

			// Leemos el semestre que termina del archivo de configuración
			// config.properties
			bundle = ResourceBundle.getBundle("config");
			String semestre_que_termina = bundle
					.getString("semestre_que_termina");

			dto.setMatricula(request.getParameter("matricula"));
			dto.setSemestre(semestre_que_termina);
			dto.setAprovechamiento(request.getParameter("porcentaje"));
			session = request.getSession();
			dto.setUsuario((String) session.getAttribute("loginUser"));
			Date dia = new Date();
			DateFormat fm = DateFormat.getDateInstance(DateFormat.MEDIUM,
					new Locale("es", "MX"));
			String fecha = fm.format(dia);
			dto.setDate(fecha);
			dto.setEntregoacta(true);

			System.out.println(fecha);

			if (alumnocp.add(dto)) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("pages/sicse/escolar/ListaAlumnosCartaCompromiso.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			// creamos nueva sesion
			session = request.getSession();

			if (session.getAttribute("inscritos") != null) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("pages/sicse/escolar/ListaAlumnosCartaCompromiso.jsp");
				dispatcher.forward(request, response);
			} else {

				List<AlumnoDTO> alumnos = null;
				bundle = null;

				int porcentajeAvance = 0;
				bundle = ResourceBundle.getBundle("config");
				porcentajeAvance = Integer.parseInt(bundle
						.getString("porcentaje"));

				alumnos = new AlumnoDaoImpl()
						.getAlumnosInscritosMenosdelPorcentaje(porcentajeAvance);

				session.setAttribute("inscritos", alumnos);

				RequestDispatcher dispatcher = request
						.getRequestDispatcher("pages/sicse/escolar/ListaAlumnosCartaCompromiso.jsp");
				dispatcher.forward(request, response);

			}

		}
	}
}
