<%@page import="mx.edu.iems.dto.SemestreDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>Módulo de consultas de la modalidad Semiescolar</h4>


<form name="formSemestre" method="get">
	Seleccione Semestre: <select name="semestre" onchange="enableSelect()">
		<c:forEach var="sem" items="${semestres}" varStatus="row">
			<%
				String s = null;
			%>
			<c:if test="${sem.semestre==semestre}">
				<%
					s = "selected";
				%>
			</c:if>
			<option value="${sem.semestre}" <%=s %>>${sem.semestre}</option>

		</c:forEach></select>
		
</form>

<br>
<div class=formulario>
	<table align="center" witdh="100%" class="consulta">
		<!-- ***************************** -->
		<tr class="row1">
			<td width="15%"><ul type="square"><li>Lista de estudiantes por grupo</ul></td>
			<td width="85%">
				<form name="form" method="get"
					action="ControllerServletSemiescolar">
					<input type="hidden" name="accion" value="1">
					Grupo: <select style="width: 600px;" name="idGrupo">
						<c:forEach var="grupo" items="${grupos}" varStatus="row">
							<option value="${grupo.idGrupo}">${grupo.clave}
								${grupo.descripcion}</option>
						</c:forEach>
					</select>&nbsp;<input type="submit" value="enviar" />
				</form>
			</td>
		</tr>

		<!-- ***************************** -->
		<tr class="row2">
			<td width="100%" colspan="2"><ul type="square"><li><a href="ControllerServletSemiescolar?accion=2">Estudiantes inscritos semestre ${semestre}</a></ul></td>
		</tr>
		
	</table>
</div>
<br>
<br>
<br>
