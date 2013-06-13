<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/estilos.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="wrap">
		<div id="header">
			<jsp:include page="/pages/commons/header.jsp" />
		</div>
		<div id="nav"></div>

		<div id="main">
			Grupo: ${grupo.clave}<br>Asignatura: ${grupo.descripcion}<br> Profesor:
			${grupo.nombreProfesor}<br> Semestre: ${grupo.semestre}<br>

			<table width="100%" align="center" class="consulta">
				<tr bgcolor="#adc95c">
					<th>No.</th>
					<th>Matrícula</th>
					<th>Apellidos</th>
					<th>Nombres</th>
				</tr>

				<c:forEach var="alumno" items="${alumnos}" varStatus="row">

					<tr class="${ (row.count % 2) == 0 ? "row1" : "row2" }">
						<td>${row.count}</td>
						<td>${alumno.matricula}</td>
						<td>${alumno.apellidos}</td>
						<td>${alumno.nombres}</td>
					</tr>

				</c:forEach>
			</table>
		</div>
		<br>
		<div id="footer">
			<jsp:include page="/pages/commons/footer.jsp" />
		</div>

	</div>

</body>
</html>