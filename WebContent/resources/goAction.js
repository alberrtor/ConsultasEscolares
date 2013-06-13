function goAction(mat, por, nomcompleto, yaesta){
	var matricula = mat;
	var porcentaje = por;
	
	var msg = "Confirmar entrega de Carta Compromiso para\n" + nomcompleto + "?\n";
	if (yaesta==0){
		if (confirm(msg)){
		document.forms["listaAlumnos"].method = "get";
		document.forms["listaAlumnos"].action = "ServletRegistraCartaCompromiso";
		document.forms["listaAlumnos"].matricula.value = matricula;
		document.forms["listaAlumnos"].porcentaje.value = porcentaje;
		document.forms["listaAlumnos"].submit();
		}
	}else{
		alert("El estudiante ya entrego Carta Compromiso !");
	}
}

