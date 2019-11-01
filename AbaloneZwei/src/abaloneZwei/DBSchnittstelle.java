package abaloneZwei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBSchnittstelle {
	
	private Connection con = null;
	private Statement stt = null;
	private String url = "jdbc:mysql://localhost:3306/abalone2";
	
	public DBSchnittstelle () {
		
		try {

			con = DriverManager.getConnection(url, "root", "");
			
			stt = con.createStatement();
			
			stt.execute("CREATE TABLE IF NOT EXISTS profil (\n" + 
					"    id BIGINT AUTO_INCREMENT,\n" + 
					"    vorname VARCHAR(20) NOT NULL,\n" + 
					"    nachname VARCHAR(20) NOT NULL,\n" + 
					"    nutzername VARCHAR(30) NOT NULL,\n" + 
					"    passwort VARCHAR(40) NOT NULL,\n" + 
					"    email VARCHAR(25) NOT NULL,\n" + 
					"    PRIMARY KEY (id)\n" + 
					");");
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
		}
		
	}
	
	public void profilAnlegen (String vorname, String nachname, String nutzername, String passwort, String email) {
		
		String profilAnlegen = "INSERT INTO profil (vorname, nachname, nutzername, passwort, email) VALUES ";
		
		try {
		
			stt.execute(profilAnlegen + "(\"" + vorname + "\",\"" + nachname + "\",\"" + nutzername + "\",\"" + passwort + "\",\"" + email + "\");");
			System.out.println("Profil in DB angelegt.");
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
