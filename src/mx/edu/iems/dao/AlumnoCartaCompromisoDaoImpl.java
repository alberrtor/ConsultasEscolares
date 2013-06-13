package mx.edu.iems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import mx.edu.iems.conn.PaginaWebConnectionFactory;
import mx.edu.iems.contracts.IAlumnoCartaCompromisoDao;
import mx.edu.iems.dto.AlumnoCartaCompromisoDTO;

public class AlumnoCartaCompromisoDaoImpl implements IAlumnoCartaCompromisoDao {
	

	public boolean add(AlumnoCartaCompromisoDTO alumnocp) {
		Connection con = null;
		PreparedStatement pst = null;
		String sql = null;
		int bandera = 0;
		try {
			con = PaginaWebConnectionFactory.getInstance().getConnection();

			sql = "insert into cartacompromiso values (?,?,?,?,?,?)";

			pst = con.prepareStatement(sql);
			pst.setString(1, alumnocp.getMatricula());
			pst.setString(2, alumnocp.getSemestre());
			pst.setInt(3, Integer.parseInt(alumnocp.getAprovechamiento()));
			pst.setString(4, alumnocp.getUsuario());
			pst.setString(5, alumnocp.getDate());
			pst.setBoolean(6, alumnocp.isEntregoacta());

			bandera = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();

				if (pst != null)

					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (bandera > 0)
			return true;
		else
			return false;

	}

	public boolean isInDataBase(AlumnoCartaCompromisoDTO alumnocp) {
		Connection con = null;
		PreparedStatement pst = null;
		String sql = null;
		ResultSet rs = null;
		int bandera = 0;
		// Leemos el semestre que termina del archivo de configuración
		// config.properties
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		String semestre_que_termina = bundle.getString("semestre_que_termina");

		try {
			con = PaginaWebConnectionFactory.getInstance().getConnection();

			sql = "select matricula from cartacompromiso where matricula=? and semestre=?";

			pst = con.prepareStatement(sql);
			pst.setString(1, alumnocp.getMatricula());
			pst.setString(2, alumnocp.getSemestre());
			rs = pst.executeQuery();
			
			if (rs.next()){
				bandera = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();

				if (pst != null)

					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (bandera == 1)
			return true;
		else
			return false;

	}
}
