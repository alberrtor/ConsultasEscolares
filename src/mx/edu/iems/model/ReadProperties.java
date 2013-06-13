package mx.edu.iems.model;

import java.util.ResourceBundle;

public class ReadProperties {
	private static ResourceBundle bundle;
	
	static {
		//Lee el archivo config.properties
		bundle = ResourceBundle.getBundle("config");
	}
	
	public static String getVariable(String nombreVariable){
		return bundle.getString(nombreVariable);
	}

}
