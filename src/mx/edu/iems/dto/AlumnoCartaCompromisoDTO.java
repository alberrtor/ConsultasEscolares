package mx.edu.iems.dto;

public class AlumnoCartaCompromisoDTO {
	private String matricula;
	private String semestre;
	private String aprovechamiento;
	private String usuario;
	private String date;
	private boolean entregoacta;
	
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getAprovechamiento() {
		return aprovechamiento;
	}
	public void setAprovechamiento(String aprovechamiento) {
		this.aprovechamiento = aprovechamiento;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isEntregoacta() {
		return entregoacta;
	}
	public void setEntregoacta(boolean entregoacta) {
		this.entregoacta = entregoacta;
	}
	
	
}
