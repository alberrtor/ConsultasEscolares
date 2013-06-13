<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/estilos.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	//funcion que nos permite seleccionar un semestre 
	function enableSelect() {
		var semestre = null;
		semestre = document.formSemestre.semestre[document.formSemestre.semestre.selectedIndex].value;

		//document.formSemestre.idGrupo.disabled = false;
		window.location = "ControllerServletSemiescolar?accion=0&semestre="
				+ semestre;

	}
</script>
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
			<jsp:include page="/pages/sicse/semiescolar/content.jsp" />
		</div>

		<div id="footer">
			<jsp:include page="/pages/commons/footer.jsp" />
		</div>

	</div>
</body>
</html>