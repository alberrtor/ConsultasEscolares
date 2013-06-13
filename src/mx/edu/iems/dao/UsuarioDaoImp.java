package mx.edu.iems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mx.edu.iems.conn.EscolarConnectionFactory;
import mx.edu.iems.conn.SemiescolarConnectionFactory;
import mx.edu.iems.contracts.IUsuarioDao;
import mx.edu.iems.dto.UsuarioDTO;

public class UsuarioDaoImp implements IUsuarioDao {
	/**
	 * Método que checa si el login y el password son correctos
	 * 
	 * @param u
	 *            Usuario
	 * @return True si el usuario es correcto y false en caso contrario
	 * @throws SQLException
	 */
	public boolean esUsuarioCorrecto(UsuarioDTO u) {
		Connection co = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String passwordEncriptado = null;
		String sql = null;

		try {
			co = EscolarConnectionFactory
					.getInstance().getConnection();
					

			sql = "SELECT usuario_id, link_id, usuario, contrasena FROM usuarios where usuario=?";

			pst = co.prepareStatement(sql);
			pst.setString(1, u.getUsuario());
			rs = pst.executeQuery();
			passwordEncriptado = getDescifraPassword(u);
			

			if (rs.next()) {
				if (passwordEncriptado.equals(rs.getString("contrasena"))) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (co != null)
					co.close();
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return false;
	}

	/**
	 * Metodo que convierte el password a la forma en como se almacena en la
	 * base de datos
	 * 
	 * @param u
	 *            Usuario
	 * @return Regresa el password codificado
	 */
	public String getDescifraPassword(UsuarioDTO u) {
		String pas = ""; // el valor de la variable pass nunca debe ir a null
		int ascii = 0, valor = 0;
		char a = 0;

		for (int i = 0; i < u.getPassword().length(); i++) {
			ascii = (int) u.getPassword().charAt(i);
			valor = (int) (ascii ^ 1);
			a = (char) valor;
			pas = pas + a;
		}

		return pas;
	}

	/**
	 * Regresa el idUsuari
	 * 
	 * @param u
	 *            Usuario
	 * @return Regresa el idUsuario, regresa -1 si no existe el usuario
	 * @throws SQLException
	 */
	public int getIdUsuario(UsuarioDTO u){
		Connection co = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		int idUsuario = -1;
		String sql = null;

		try {
			co = EscolarConnectionFactory
					.getInstance().getConnection();
			sql = "SELECT link_id, usuario, contrasena FROM usuarios where usuario=?";

			pst = co.prepareStatement(sql);
			pst.setString(1, u.getUsuario());
			rs = pst.executeQuery();

			rs.next();
			idUsuario = Integer.parseInt(rs.getString("link_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (co != null)
					co.close();
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return idUsuario;
	}

	public boolean isAdministrativo(UsuarioDTO u) {
		Connection co = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String bandera = null;;
		String sql = null;

		try {
			co = EscolarConnectionFactory
					.getInstance().getConnection();

			sql = "SELECT link_id, usuario, contrasena FROM usuarios where usuario=?";

			pst = co.prepareStatement(sql);
			pst.setString(1, u.getUsuario());
			rs = pst.executeQuery();

			rs.next();
			bandera = rs.getString("link_id");
				

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (co != null)
					co.close();
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (bandera == null)
			return true;
		else
			return false;

	}

}
