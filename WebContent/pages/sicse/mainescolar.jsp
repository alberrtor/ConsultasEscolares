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
			
			<table class="consulta">
				<tr>
					<td>
							<a href="http://web/cescolar/inscritoscurso.php">Estudiantes Inscritos y/o Reinscritos en la modalidad de Curso en el semestre 2012-2013 B
							</a>
					</td>
				</tr>
				<tr>
					<td>
							<a href="ServletRegistraCartaCompromiso">Estudiantes que
								deberán entregar Carta compromiso</a>
					</td>
				</tr>
				<tr>
					<td>
							<a href="ServletAlumnosTresSemestres">Porcentaje de
								aprovechamiento de tres semestres anteriores</a>			
					</td>
				</tr>
			</table>
		
		</div>

		<div id="footer">
			<jsp:include page="/pages/commons/footer.jsp" />
		</div>

	</div>

</body>
</html>