<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="formulario">
	<br>Login Sistema<br><br>
	<form id="form1" name="form1" action="AutentificaUsuarioServlet"
		method="post">

		<table align="center" class="elemento">
			<tr>
				<td>Usuario:</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="radio" name="sistema" value="escolar" checked="checked" /> Escolar
				<input type="radio" name="sistema" value="semiescolar" /> Semiescolar
				</td>
			</tr>
		</table>
		<input type="submit" value="Enviar" />
	</form>
</div>

<p class="error">${mensaje}</p>