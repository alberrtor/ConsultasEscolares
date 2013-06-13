package mx.edu.iems.contracts;

import java.sql.SQLException;
import java.util.List;

import mx.edu.iems.dto.SemestreDTO;


public interface ISemestreDao {
	
	public List<SemestreDTO> getAllSemestres();
	
}
