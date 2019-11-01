package abaloneZwei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSchnittstelle {
	
	private Connection con = null;
	private Statement stt = null;
	private String url = "jdbc:mysql://localhost:3306/abalone2";
	
	public DBSchnittstelle () {
		
		try {

			con = DriverManager.getConnection(url, "root", "");
			
			stt = con.createStatement();
			
			initDB();
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
		}
		
	}
	
	private void initDB () throws SQLException {
		
		stt.execute("CREATE TABLE IF NOT EXISTS profil (\n" + 
				"    id BIGINT AUTO_INCREMENT,\n" + 
				"    vorname VARCHAR(20) NOT NULL,\n" + 
				"    nachname VARCHAR(20) NOT NULL,\n" + 
				"    nutzername VARCHAR(30) NOT NULL,\n" + 
				"    passwort VARCHAR(40) NOT NULL,\n" + 
				"    email VARCHAR(25) NOT NULL,\n" + 
				"    PRIMARY KEY (id)\n" + 
				");");
		
		stt.execute("CREATE TABLE IF NOT EXISTS aktion (\n" + 
				"    id BIGINT AUTO_INCREMENT,\n" + 
				"    titel VARCHAR(20) NOT NULL,\n" + 
				"    beschreibung VARCHAR(200) NOT NULL,\n" + 
				"    kategorie BIGINT NOT NULL,\n" + 
				"    zeitraum VARCHAR(20) NOT NULL,\n" + 
				"    teilnehmerAnz INT NOT NULL,\n" + 
				"    PRIMARY KEY (id)\n" + 
				");");
		
		stt.execute("CREATE TABLE IF NOT EXISTS eventVonProfil (\n" + 
				"    profil_id BIGINT NOT NULL,\n" + 
				"    aktion_id BIGINT NOT NULL,\n" + 
				"    FOREIGN KEY (profil_id) REFERENCES profil(id),\n" + 
				"    FOREIGN KEY (aktion_id) REFERENCES aktion(id)\n" + 
				");");
		
//		stt.execute("CREATE TABLE IF NOT EXISTS kategorie (\n" + 
//				"    id BIGINT AUTO_INCREMENT,\n" + 
//				"    kategorie VARCHAR(20) NOT NULL,\n" + 
//				"    PRIMARY KEY (id)\n" + 
//				");");		
		
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
	
	public void eventAnlegen (String nutzername, String titel, int kategorie, String zeitraum, int teilnehmerAnz) {
		
		String eventAnlegen = "INSERT INTO aktion (titel, beschreibung, kategorie, zeitraum, teilnehmerAnz) VALUES ";
		String eventProfilZuweisen = "INSERT INTO eventVonProfil (profil_id, aktion_id) VALUES ";
		String getProfilID = "SELECT id FROM profil WHERE profil.nutzername = \"" + nutzername + "\"";
		// Wie findet man die ID von dem Event heraus, welches gerade erstellt wurde?
		// Vorerst mal über den Titel, der ist ja aber eigentlich kein Primärschlüssel
		String getAktionID = "SELECT id FROM aktion WHERE titel = \"" + titel + "\"";	
		
		try {
			
			// Get Profil-ID von dem Profil, mit dem das Event erstellt wurde
			
			ResultSet rs = stt.executeQuery(getProfilID);
			rs.next();
			String profil_id = rs.getString("id");
			
			// Event anlegen
			
			stt.execute(eventAnlegen + "(\"" + titel + "\",\"" + "Test" + "\",\"" + 
						kategorie + "\",\"" + zeitraum + "\",\"" + teilnehmerAnz + "\");");
			
			rs = stt.executeQuery(getAktionID); 
			rs.next();
			String aktion_id = rs.getString("id");
			
			stt.execute(eventProfilZuweisen + "(\"" + profil_id + "\",\"" + aktion_id + "\");");
			System.out.println("Event in DB angelegt und Profil zugewiesen.");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public Profil getProfil (String nutzername, String passwort) {
		
		String getProfileNutzernameUndPasswort = "SELECT * FROM profil WHERE profil.nutzername = \"" + nutzername + "\";";
		
		try {
		
			ResultSet rs = stt.executeQuery(getProfileNutzernameUndPasswort); 
			
			while (rs.next()) {
				
				String nutzernameResult = rs.getString("nutzername");
				String passwortResult = rs.getString("passwort");
				
				if (nutzernameResult.equals(nutzername)) {
					
					if (passwortResult.equals(passwort)) {
						
						return new Profil(rs.getString("vorname"), rs.getString("nachname"), rs.getString("nutzername"), rs.getString("passwort"), rs.getString("email"));
						
					}
					
				}
				
			}
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return null;
		
	}

}
