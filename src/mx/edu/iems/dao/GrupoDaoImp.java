package mx.edu.iems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.iems.conn.EscolarConnectionFactory;
import mx.edu.iems.conn.SemiescolarConnectionFactory;
import mx.edu.iems.contracts.IGrupoDao;
import mx.edu.iems.dto.AlumnoDTO;
import mx.edu.iems.dto.GrupoDTO;

public class GrupoDaoImp implements IGrupoDao {
	
	public List<GrupoDTO> getAllGrupos(String semestre){
		List<GrupoDTO> grupos = new ArrayList<GrupoDTO>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = EscolarConnectionFactory
					.getInstance().getConnection();

			String sql = "select id_grupo, clave, descripcion from grupos where semestre=? order by clave, descripcion";

			pst = con.prepareStatement(sql);
			pst.setString(1, semestre);
			rs = pst.executeQuery();
			while (rs.next()) {
				GrupoDTO grupo = new GrupoDTO();
				grupo.setIdGrupo(rs.getInt("id_grupo"));
				grupo.setClave(rs.getString("clave"));
				grupo.setDescripcion(rs.getString("descripcion"));

				grupos.add(grupo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (con != null)
					con.close();

				if (pst != null)

					pst.close();

				if (rs != null)

					rs.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}

		}

		return grupos;
	}

	public List<AlumnoDTO> getAlumnosPorGrupo(int idGrupo){
		List<AlumnoDTO> alumnos = new ArrayList<AlumnoDTO>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = EscolarConnectionFactory
					.getInstance().getConnection();

			String sql = "select a.matricula, a.apellidos, a.nombres from alumnos a, alumnos_x_grupo b where a.matricula=b.matricula and a.id_situacion=1 and b.id_grupo=? order by a.apellidos";

			pst = con.prepareStatement(sql);
			pst.setInt(1, idGrupo);
			rs = pst.executeQuery();

			while (rs.next()) {
				AlumnoDTO alumno = new AlumnoDTO();
				alumno.setMatricula(rs.getString("matricula"));
				alumno.setApellidos(rs.getString("apellidos"));
				alumno.setNombres(rs.getString("nombres"));

				alumnos.add(alumno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)

					con.close();

				if (pst != null)

					pst.close();

				if (rs != null)

					rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

		return alumnos;
	}

	@Override
	public GrupoDTO getGrupobyIdGrupo(int idGrupo) {
		GrupoDTO g = new GrupoDTO();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;

		try {
			con = EscolarConnectionFactory
					.getInstance().getConnection();
			String sql = "select id_grupo, semestre, clave, descripcion, id_profesor from grupos where id_grupo=?";

			pst = con.prepareStatement(sql);
			pst.setInt(1, idGrupo);
			rs = pst.executeQuery();

			if (rs != null) {
				rs.next();
				g.setIdGrupo(rs.getInt("id_grupo"));
				g.setClave(rs.getString("clave"));
				g.setDescripcion(rs.getString("descripcion"));
				g.setSemestre(rs.getString("semestre"));

				String sql2 = "select nombres, apellidos from profesores where id_profesor=?";
				pst2 = con.prepareStatement(sql2);
				pst2.setInt(1, rs.getInt("id_profesor"));
				rs2 = pst2.executeQuery();

				if (rs2 != null) {
					rs2.next();
					String nombreProfesor = rs2.getString("nombres") + " "
							+ rs2.getString("apellidos");
					g.setNombreProfesor(nombreProfesor);

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (con != null)
					con.close();
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

		}

		return g;
	}

}
