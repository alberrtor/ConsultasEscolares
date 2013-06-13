package mx.edu.iems.contracts;

import mx.edu.iems.dto.AlumnoCartaCompromisoDTO;

public interface IAlumnoCartaCompromisoDao {
	boolean isInDataBase(AlumnoCartaCompromisoDTO alumnocp);
	boolean add(AlumnoCartaCompromisoDTO alumnocp);
}
