package mx.edu.iems.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.edu.iems.contracts.IAlumnoTresSemestres;
import mx.edu.iems.dao.AlumnoTresSemestresDaoImpl;
import mx.edu.iems.model.ReadProperties;

/**
 * Servlet implementation class ServletAlumnosTresSemestres
 */
@WebServlet("/ServletAlumnosTresSemestres")
public class ServletAlumnosTresSemestres extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ServletAlumnosTresSemestres() {
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAlumnoTresSemestres dao = new AlumnoTresSemestresDaoImpl();
		HttpSession session = request.getSession();
		if (session.getAttribute("listaAlumnosTresSemestres") == null){
			String semestreantepenultimo = ReadProperties.getVariable("semestre_antepenultimo");
			String semestrepenultimo = ReadProperties.getVariable("semestre_penultimo");
			String semestreultimo = ReadProperties.getVariable("semestre_ultimo");
			
			session.setAttribute("semestreantepenultimo", semestreantepenultimo);
			session.setAttribute("semestrepenultimo", semestrepenultimo);
			session.setAttribute("semestreultimo", semestreultimo);
			
			session.setAttribute("listaAlumnosTresSemestres", dao.getActivosTresSemestres());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/sicse/escolar/ListaAlumnosTresSemestres.jsp");
		dispatcher.forward(request, response);
	}


}
