package mx.edu.iems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import mx.edu.iems.conn.EscolarConnectionFactory;
import mx.edu.iems.contracts.IAlumnoDao;
import mx.edu.iems.dto.AlumnoDTO;
import mx.edu.iems.dto.GrupoDTO;

public class AlumnoDaoImpl implements IAlumnoDao {
	
	public List<AlumnoDTO> getAlumnosInscritos(){
		List<AlumnoDTO> alumnos = new ArrayList<AlumnoDTO>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		ResourceBundle bundle = null;
		String semestre_que_termina = null;

		//Leemos los valores del archivo de configuracion y lo asignamos
		bundle = ResourceBundle.getBundle("config");
		semestre_que_termina = bundle.getString("semestre_que_termina");
		semestre_que_termina = semestre_que_termina + "%";

		try {
			con = EscolarConnectionFactory.getInstance().getConnection();
			sql = "select matricula, apellidos, nombres from alumnos where id_situacion=1 and matricula IN (select ag.matricula from alumnos_x_grupo ag, grupos g where ag.id_grupo=g.id_grupo and g.semestre like ? group by ag.matricula) order by matricula";

			pst = con.prepareStatement(sql);
			pst.setString(1, semestre_que_termina);
			rs = pst.executeQuery();

			while (rs.next()) {
				int porcentaje = 0;
				String sql1 = null;
				PreparedStatement pst1 = null;
				ResultSet rs1 = null;
				int inscritas = 0;

				sql1 = "select c.matricula, count(*) as inscritas from (select ag.matricula from alumnos_x_grupo ag, grupos g where ag.id_grupo=g.id_grupo and g.semestre like ? group by ag.matricula, g.descripcion order by ag.matricula) c where c.matricula=? group by c.matricula";
				pst1 = con.prepareStatement(sql1);
				pst1.setString(1, semestre_que_termina);
				pst1.setString(2, rs.getString("matricula"));
				rs1 = pst1.executeQuery();

				if (rs1.next())
					inscritas = rs1.getInt("inscritas");
				else {
					inscritas = 0;
				}
				pst1.close();
				rs1.close();

				String sql2 = null;
				PreparedStatement pst2 = null;
				ResultSet rs2 = null;
				int cubiertas = 0;

				sql2 = "SELECT ag.matricula, count(*) as cubre from alumnos_x_grupo ag, grupos g where ag.id_grupo=g.id_grupo and ag.evaluacion='C' and g.semestre like ? and ag.matricula=? group by ag.matricula";
				pst2 = con.prepareStatement(sql2);
				pst2.setString(1, semestre_que_termina);
				pst2.setString(2, rs.getString("matricula"));
				rs2 = pst2.executeQuery();
				
				if (rs2.next()){
					cubiertas = rs2.getInt("cubre");
				}else {
					cubiertas = 0;	
				}
				
				pst2.close();
				rs2.close();

				porcentaje = cubiertas * 100 / inscritas;
				if (porcentaje > 100)
					porcentaje = 100;

				AlumnoDTO alumno = new AlumnoDTO();
				alumno.setMatricula(rs.getString("matricula"));
				alumno.setApellidos(rs.getString("apellidos"));
				alumno.setNombres(rs.getString("nombres"));
				alumno.setPorcentaje(porcentaje);
				
				alumnos.add(alumno);
			}
		} catch (Exception e) {
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

	public List<AlumnoDTO> getAlumnosInscritosMenosdelPorcentaje(int porcentajeAvance){
		List<AlumnoDTO> alumnos = new ArrayList<AlumnoDTO>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		ResourceBundle bundle = null;
		String semestre_que_termina = null;

		//Leemos los valores del archivo de configuracion y lo asignamos
		bundle = ResourceBundle.getBundle("config");
		semestre_que_termina = bundle.getString("semestre_que_termina");
		semestre_que_termina = semestre_que_termina + "%";

		try {
			con = EscolarConnectionFactory.getInstance().getConnection();
			sql = "select matricula, apellidos, nombres from alumnos where id_situacion=1 and matricula IN (select ag.matricula from alumnos_x_grupo ag, grupos g where ag.id_grupo=g.id_grupo and g.semestre like ? group by ag.matricula) order by matricula";

			pst = con.prepareStatement(sql);
			pst.setString(1, semestre_que_termina);
			rs = pst.executeQuery();

			while (rs.next()) {
				int porcentaje = 0;
				String sql1 = null;
				PreparedStatement pst1 = null;
				ResultSet rs1 = null;
				int inscritas = 0;

				sql1 = "select c.matricula, count(*) as inscritas from (select ag.matricula from alumnos_x_grupo ag, grupos g where ag.id_grupo=g.id_grupo and g.semestre like ? group by ag.matricula, g.descripcion order by ag.matricula) c where c.matricula=? group by c.matricula";
				pst1 = con.prepareStatement(sql1);
				pst1.setString(1, semestre_que_termina);
				pst1.setString(2, rs.getString("matricula"));
				rs1 = pst1.executeQuery();

				if (rs1.next())
					inscritas = rs1.getInt("inscritas");
				else {
					inscritas = 0;
				}
				pst1.close();
				rs1.close();

				String sql2 = null;
				PreparedStatement pst2 = null;
				ResultSet rs2 = null;
				int cubiertas = 0;

				sql2 = "SELECT ag.matricula, count(*) as cubre from alumnos_x_grupo ag, grupos g where ag.id_grupo=g.id_grupo and ag.evaluacion='C' and g.semestre like ? and ag.matricula=? group by ag.matricula";
				pst2 = con.prepareStatement(sql2);
				pst2.setString(1, semestre_que_termina);
				pst2.setString(2, rs.getString("matricula"));
				rs2 = pst2.executeQuery();
				
				if (rs2.next()){
					cubiertas = rs2.getInt("cubre");
				}else {
					cubiertas = 0;	
				}
				
				pst2.close();
				rs2.close();

				porcentaje = cubiertas * 100 / inscritas;
				if (porcentaje > 100)
					porcentaje = 100;

				AlumnoDTO alumno = new AlumnoDTO();
				alumno.setMatricula(rs.getString("matricula"));
				alumno.setApellidos(rs.getString("apellidos"));
				alumno.setNombres(rs.getString("nombres"));
				alumno.setPorcentaje(porcentaje);
				
				if (porcentaje < porcentajeAvance)
					alumnos.add(alumno);
			}
		} catch (Exception e) {
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

}
