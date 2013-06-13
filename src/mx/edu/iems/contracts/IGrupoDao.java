package mx.edu.iems.contracts;

import java.sql.SQLException;
import java.util.List;

import mx.edu.iems.dto.AlumnoDTO;
import mx.edu.iems.dto.GrupoDTO;

public interface IGrupoDao {
	public List<GrupoDTO> getAllGrupos(String semestre);
	
	public List<AlumnoDTO> getAlumnosPorGrupo(int idGrupo);
	
	public GrupoDTO getGrupobyIdGrupo(int idGrupo);
}
