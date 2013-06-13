<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/estilos.css" />
<script language="javascript" src="${pageContext.request.contextPath}/resources/funciones.js"></script>

<title>Insert title here</title>
</head>
<body onload="sf();">
	<div id="wrap">
		<div id="header">
			<jsp:include page="pages/commons/header.jsp"/>
		</div>
		
		<div id="nav">
			
		</div>
		
		<div id="main">
			<jsp:include page="pages/sicse/login.jsp"/>
		</div>
		
		<div id="footer">
			<jsp:include page="pages/commons/footer.jsp"/>
		</div>
	
	</div>
</body>
</html>