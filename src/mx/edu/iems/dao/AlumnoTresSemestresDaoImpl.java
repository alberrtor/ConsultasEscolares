package mx.edu.iems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import mx.edu.iems.conn.EscolarConnectionFactory;
import mx.edu.iems.contracts.IAlumnoTresSemestres;
import mx.edu.iems.dto.AlumnoDTO;
import mx.edu.iems.dto.AlumnoTresSemestresDTO;
import mx.edu.iems.model.ReadProperties;

public class AlumnoTresSemestresDaoImpl implements IAlumnoTresSemestres {

	private String semestre_antepenultimo = null;
	private String semestre_penultimo = null;
	private String semestre_ultimo = null;
		
	public AlumnoTresSemestresDaoImpl() {
		semestre_antepenultimo = ReadProperties
				.getVariable("semestre_antepenultimo");
		semestre_penultimo = ReadProperties
				.getVariable("semestre_penultimo");
		semestre_ultimo = ReadProperties
				.getVariable("semestre_ultimo");
	}

	
	public List<AlumnoTresSemestresDTO> getActivosTresSemestres() {
		List<AlumnoTresSemestresDTO> alumnostressemestres = new ArrayList<AlumnoTresSemestresDTO>();
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			con = EscolarConnectionFactory.getInstance().getConnection();
			
			sql = "select matricula, apellidos, nombres from alumnos where id_situacion=1 order by matricula, apellidos, nombres";
			
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()){
				AlumnoTresSemestresDTO dto = new AlumnoTresSemestresDTO();
				
				String matricula = rs.getString("matricula");
				String apellidos = rs.getString("apellidos");
				String nombres = rs.getString("nombres");
				
				dto.setMatricula(matricula);
				dto.setApellidos(apellidos);
				dto.setNombres(nombres);
				
				if (isInscrito(matricula, semestre_antepenultimo)){
					dto.setInscritoantepenultimo(true);
					dto.setPorcentajeantepenultimo(getPorcentajeAprovechamiento(matricula, semestre_antepenultimo));
				}else{
					dto.setInscritoantepenultimo(false);
					dto.setPorcentajeantepenultimo(-1);
				}
				
				if (isInscrito(matricula, semestre_penultimo)){
					dto.setInscritopenultimo(true);
					dto.setPorcentajepenultimo(getPorcentajeAprovechamiento(matricula, semestre_penultimo));
				}else{
					dto.setInscritopenultimo(false);
					dto.setPorcentajepenultimo(-1);
				}
				
				if (isInscrito(matricula, semestre_ultimo)){
					dto.setInscritoultimo(true);
					dto.setPorcentajeultimo(getPorcentajeAprovechamiento(matricula, semestre_ultimo));
				}else{
					dto.setInscritoultimo(false);
					dto.setPorcentajeultimo(-1);
				}
				
				alumnostressemestres.add(dto);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			
			try {
				if (con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return alumnostressemestres;
	}

	public boolean isInscrito(String matricula, String semestre) {
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		
		int bandera = 0;

		try {
			con = EscolarConnectionFactory.getInstance().getConnection();

			sql = "select ag.matricula from alumnos_x_grupo ag, grupos g where ag.id_grupo=g.id_grupo and g.semestre like ? and ag.matricula=?";

			pst = con.prepareStatement(sql);
			// Para que incluya el intersemestre
			semestre = semestre + "%";

			pst.setString(1, semestre);
			pst.setString(2, matricula);

			rs = pst.executeQuery();

			if (rs.next())
				bandera = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if (con != null)
					con.close();
				if (pst != null)
					pst.close();
				if (rs !=null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (bandera == 1)
			return true;
		else
			return false;
	}
	/**
	 * 
	 * @param matricula
	 * @param semestre, contempla semestre e intersemestre, pero solo se pasa como parametro el semestre
	 * @return
	 */
	public int getPorcentajeAprovechamiento(String matricula, String semestre){
		
		int porcentaje = 0;
		int inscritas = 0;
		int cubiertas = 0;
		
		Connection con = null;
		
		String sql1 = null;
		PreparedStatement pst1 = null;
		ResultSet rs1 = null;
		
		String sql2 = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;

		//Para contemplar el semestre e intersemestre
		semestre = semestre + "%";
		
		try {
			
			con = EscolarConnectionFactory.getInstance().getConnection();
			
			sql1 = "select c.matricula, count(*) as inscritas from (select ag.matricula from alumnos_x_grupo ag, grupos g where ag.id_grupo=g.id_grupo and g.semestre like ? group by ag.matricula, g.descripcion order by ag.matricula) c where c.matricula=? group by c.matricula";
			pst1 = con.prepareStatement(sql1);
			pst1.setString(1, semestre);
			pst1.setString(2, matricula);
			rs1 = pst1.executeQuery();

			if (rs1.next())
				inscritas = rs1.getInt("inscritas");
			else {
				inscritas = 0;
			}
	
			sql2 = "SELECT ag.matricula, count(*) as cubre from alumnos_x_grupo ag, grupos g where ag.id_grupo=g.id_grupo and ag.evaluacion='C' and g.semestre like ? and ag.matricula=? group by ag.matricula";
			pst2 = con.prepareStatement(sql2);
			pst2.setString(1, semestre);
			pst2.setString(2, matricula);
			rs2 = pst2.executeQuery();
			
			if (rs2.next()){
				cubiertas = rs2.getInt("cubre");
			}else {
				cubiertas = 0;	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			try {
			
				if (con != null)
					con.close();
				if (pst1 != null)
					pst1.close();
				if (rs1 !=null)
					rs1.close();
				if (pst2 != null)
					pst2.close();
				if (rs2 !=null)
					rs2.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		porcentaje = cubiertas * 100 / inscritas;
		
		if (porcentaje > 100)
			porcentaje = 100;
		
		return porcentaje;
		
	}

	public static void main(String args[]){
		AlumnoTresSemestresDaoImpl dao = new AlumnoTresSemestresDaoImpl();
		
		int i=1;
		for (AlumnoTresSemestresDTO alumno : dao.getActivosTresSemestres()){
			System.out.println(i);
			i++;
		}
	}
	
}
