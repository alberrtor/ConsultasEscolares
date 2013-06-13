package mx.edu.iems.conn;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * 
 * Esta clase realiza la Conexion a una base de datos de postgresql, se tiene
 * que tener la libreria de postgres postgresql-8.1-412.jdbc3.jar
 * 
 * @author Alberto Romero Rubí
 */
public class SemiescolarConnectionFactory {
	// Los atributos se declaran estaticos para que se puedan llamar de los
	// métodos
	// estáticos.

	private String JDBC_DRIVER;
	private String JDBC_URL_SEMIESCOLAR;
	private String JDBC_USER;
	private String JDBC_PASS;
	private Connection conn;
	private ResourceBundle bundle;
	private static SemiescolarConnectionFactory semiescolarConnectionFactory;

	// Iniciamos los atributos de la clase
	private SemiescolarConnectionFactory() {
		// Leemos el archivo de configuracion llamado config.properties
		bundle = ResourceBundle.getBundle("config");
		// Leemos los valores del archivo de configuracion y lo asignamos
		JDBC_DRIVER = bundle.getString("driver");
		JDBC_URL_SEMIESCOLAR = bundle.getString("url_base_semiescolar");
		JDBC_USER = bundle.getString("user");
		JDBC_PASS = bundle.getString("pass");

		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() throws SQLException{
		conn = DriverManager.getConnection(JDBC_URL_SEMIESCOLAR, JDBC_USER, JDBC_PASS);

		return conn;
	}

	public static SemiescolarConnectionFactory getInstance(){
		if (semiescolarConnectionFactory == null){
			semiescolarConnectionFactory = new SemiescolarConnectionFactory();
		}
		
		return semiescolarConnectionFactory;
	}
	
}