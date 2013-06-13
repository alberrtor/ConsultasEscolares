package mx.edu.iems.contracts;

import java.sql.SQLException;
import java.util.List;

import mx.edu.iems.dto.AlumnoDTO;

public interface IAlumnoDao {
	public List<AlumnoDTO> getAlumnosInscritos();
	
	public List<AlumnoDTO> getAlumnosInscritosMenosdelPorcentaje(int porcentajeAvance);
}
