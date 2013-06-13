package mx.edu.iems.contracts;

import java.util.List;

import mx.edu.iems.dto.AlumnoTresSemestresDTO;

public interface IAlumnoTresSemestres {
	public List<AlumnoTresSemestresDTO> getActivosTresSemestres();
	public boolean isInscrito(String matricula, String semestre);
}
