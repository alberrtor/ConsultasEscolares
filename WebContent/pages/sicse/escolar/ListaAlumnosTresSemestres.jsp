<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<table width="100%" class="consulta">
				<tr bgcolor="#adc95c">
					<th rowspan="2">No.</th>
					<th rowspan="2">Matrícula</th>
					<th rowspan="2">Apellidos</th>
					<th rowspan="2">Nombres</th>
					<th colspan="2">${semestreantepenultimo} + I</th>
					<th colspan="2">${semestrepenultimo} + I</th>
					<th colspan="2">${semestreultimo} + I</th>
				</tr>

				<tr bgcolor="#adc95c">

					<th>Inscrito</th>
					<th>Porcentaje</th>
					<th>Inscrito</th>
					<th>Porcentaje</th>
					<th>Inscrito</th>
					<th>Porcentaje</th>
				</tr>

				<c:forEach var="alumno" items="${listaAlumnosTresSemestres}"
					varStatus="row">
					<tr class="${ (row.count % 2) == 0 ? "row1" : "row2" }">
						<td>${row.count}</td>
						<td>${alumno.matricula}</td>
						<td>${alumno.apellidos}</td>
						<td>${alumno.nombres}</td>
						<td>${alumno.inscritoantepenultimo}</td>
						<td>${alumno.porcentajeantepenultimo} %</td>
						<td>${alumno.inscritopenultimo}</td>
						<td>${alumno.porcentajepenultimo} %</td>
						<td>${alumno.inscritoultimo}</td>
						<td>${alumno.porcentajeultimo} %</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div id="footer">
			<jsp:include page="/pages/commons/footer.jsp" />
		</div>

	</div>

</body>
</html>