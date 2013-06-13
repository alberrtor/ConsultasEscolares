<%@page import="java.util.ResourceBundle"%>
<%@page import="mx.edu.iems.dto.AlumnoCartaCompromisoDTO"%>
<%@page import="java.util.List"%>
<%@page import="mx.edu.iems.dto.AlumnoDTO"%>
<%@page import="mx.edu.iems.dao.AlumnoCartaCompromisoDaoImpl"%>
<%@page import="mx.edu.iems.contracts.IAlumnoCartaCompromisoDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<%
	HttpSession sesion = request.getSession();
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/estilos.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/goAction.js"></script>

<title>Insert title here</title>
</head>
<body>

	<div id="wrap">
		<div id="header">
			<jsp:include page="/pages/commons/header.jsp" />
		</div>

		<div id="nav">
			<jsp:include page="/pages/commons/menu.jsp" />
		</div>

		<div id="main">
			<form id="listaAlumnos" action="">
				<table width="100%" class="consulta">
					<tr bgcolor="#adc95c">
						<th>No.</th>
						<th>Matrícula</th>
						<th>Apellidos</th>
						<th>Nombres</th>
						<th>Aprovechamiento</th>
					</tr>


					<%
						String semestre_que_termina = null;
						ResourceBundle bundle = null;

						bundle = ResourceBundle.getBundle("config");
						semestre_que_termina = bundle.getString("semestre_que_termina");

						int i = 1;
						String color = "";
						IAlumnoCartaCompromisoDao dao = new AlumnoCartaCompromisoDaoImpl();
						for (AlumnoDTO alumno : (List<AlumnoDTO>) sesion
								.getAttribute("inscritos")) {
							
							int yaentrego = 0;

							AlumnoCartaCompromisoDTO aluct = new AlumnoCartaCompromisoDTO();
							aluct.setMatricula(alumno.getMatricula());
							aluct.setSemestre(semestre_que_termina);
							if (dao.isInDataBase(aluct)){
								yaentrego =1;
								color = "#FF8000";
							}else if (i % 2 == 0)
								color = "#e5eecc";
							else
								color = "#f3f7e7";
							
							String nombre_completo = alumno.getNombres() + " " + alumno.getApellidos();
					%>
						
					<tr bgcolor="<%=color%>"
						ondblclick="goAction('<%=alumno.getMatricula()%>', '<%=alumno.getPorcentaje()%>', '<%=nombre_completo%>', '<%=yaentrego%>')"
						class="row">

						<td><%=i%></td>
						<td><%=alumno.getMatricula()%></td>
						<td><%=alumno.getApellidos()%></td>
						<td><%=alumno.getNombres()%></td>
						<td><%=alumno.getPorcentaje()%></td>
					</tr>

					<%
						i++;

						}
					%>
				</table>
				<input type="hidden" name="matricula" /> <input type="hidden"
					name="porcentaje" />

			</form>

		</div>

		<div id="footer">
			<jsp:include page="/pages/commons/footer.jsp" />
		</div>

	</div>

</body>
</html>