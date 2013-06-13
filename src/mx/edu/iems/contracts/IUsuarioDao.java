package mx.edu.iems.contracts;

import java.sql.SQLException;

import mx.edu.iems.dto.UsuarioDTO;

public interface IUsuarioDao {
//	public boolean esUsuarioCorrecto(UsuarioDTO u) throws SQLException;

	public String getDescifraPassword(UsuarioDTO u);
	
	public int getIdUsuario(UsuarioDTO u);
	
	public boolean isAdministrativo(UsuarioDTO u);
	
	public boolean esUsuarioCorrecto(UsuarioDTO u);
}
