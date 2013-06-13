package mx.edu.iems.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import mx.edu.iems.conn.EscolarConnectionFactory;
import mx.edu.iems.conn.SemiescolarConnectionFactory;
import mx.edu.iems.contracts.ISemestreDao;
import mx.edu.iems.dto.SemestreDTO;


public class SemestreDaoImp implements ISemestreDao{
		
	public List<SemestreDTO> getAllSemestres() {
		List<SemestreDTO> semestres = new ArrayList<SemestreDTO>();
		String sql = "select distinct(semestre) as semestre from calendario order by semestre desc";
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = EscolarConnectionFactory
					.getInstance().getConnection();
			
			pst = con.prepareStatement(sql);
			rs=pst.executeQuery();
			SemestreDTO ningunSemestre = new SemestreDTO();
			ningunSemestre.setSemestre("-------");
			
			semestres.add(ningunSemestre);
			while(rs.next()){
				SemestreDTO semestre = new SemestreDTO();
				semestre.setSemestre(rs.getString("semestre"));
				
				semestres.add(semestre);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
		
		return semestres;
	}
}
