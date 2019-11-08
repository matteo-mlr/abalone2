package abaloneZwei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBSchnittstelle {
	
	private Connection con = null;
	private Statement stt = null;
//	private String url = "jdbc:mysql://localhost:3306/abalone2";
	private String url = "jdbc:mysql://matteomueller.net:3306/cu-muellermatteo01_abalone2";
	
	public DBSchnittstelle () {
		
		try {

			con = DriverManager.getConnection(url +"?user=cu-mu_nachthuhn" + "&password=nachthuhnauftoast!!11&serverTimezone=UTC");
			
			stt = con.createStatement();
			
//			initDB();
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
		}
		
	}
	
//	private void initDB () throws SQLException {
//		
//		stt.execute("CREATE TABLE IF NOT EXISTS profil (\n" + 
//				"    id BIGINT AUTO_INCREMENT,\n" + 
//				"    vorname VARCHAR(20) NOT NULL,\n" + 
//				"    nachname VARCHAR(20) NOT NULL,\n" + 
//				"    nutzername VARCHAR(30) NOT NULL,\n" + 
//				"    passwort VARCHAR(40) NOT NULL,\n" + 
//				"    email VARCHAR(25) NOT NULL,\n" + 
//				"    PRIMARY KEY (id)\n" + 
//				");");
//		
//		stt.execute("CREATE TABLE IF NOT EXISTS aktion (\n" + 
//				"    id BIGINT AUTO_INCREMENT,\n" + 
//				"    titel VARCHAR(20) NOT NULL,\n" + 
//				"    beschreibung VARCHAR(200) NOT NULL,\n" + 
//				"    kategorie BIGINT NOT NULL,\n" + 
//				"    zeitraum VARCHAR(20) NOT NULL,\n" + 
//				"    teilnehmerAnz INT NOT NULL,\n" + 
//				"    PRIMARY KEY (id)\n" + 
//				");");
//		
//		stt.execute("CREATE TABLE IF NOT EXISTS eventVonProfil (\n" + 
//				"    profil_id BIGINT NOT NULL,\n" + 
//				"    aktion_id BIGINT NOT NULL,\n" + 
//				"    FOREIGN KEY (profil_id) REFERENCES profil(id),\n" + 
//				"    FOREIGN KEY (aktion_id) REFERENCES aktion(id)\n" + 
//				");");
//		
//	}
	
	public void profilAnlegen (String vorname, String nachname, String nutzername, String passwort, String email) {
		
		String profilAnlegen = "INSERT INTO profil (vorname, nachname, nutzername, passwort, email) VALUES ";
		
		try {
		
			stt.execute(profilAnlegen + "(\"" + vorname + "\",\"" + nachname + "\",\"" + nutzername + "\",\"" + passwort + "\",\"" + email + "\");");
		
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
	
	public ArrayList<Event> getAlleEvents () {
		
		String getAlleEvents = "SELECT * FROM aktion";
		ArrayList<Event> alleEvents = new ArrayList<>();
		
		try {
		
		ResultSet rs = stt.executeQuery(getAlleEvents);
		
		while (rs.next()) {
			
			String titel = rs.getString("titel");
			String kategorie = "" + rs.getInt("kategorie");
			String zeitraum = rs.getString("zeitraum");
			int teilnehmerAnz = rs.getInt("teilnehmerAnz");
			
			alleEvents.add(new Event(null, titel, kategorie, zeitraum, teilnehmerAnz));
			
		}
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return alleEvents;
		
	}
	
	public void teilnehmen (String titel, String nutzername) {
		
		String aktuelleTeilnehmer = "SELECT COUNT(profil_id) FROM profilNimmtTeilAn WHERE aktion_id IN (SELECT id FROM aktion WHERE titel = \"" + titel + "\")";
		String getEvent = "SELECT * FROM aktion WHERE titel = \"" + titel + "\"";
		String getProfilID = "SELECT id FROM profil WHERE nutzername = \"" + nutzername + "\""; 
		
		try {
			
			ResultSet rs = stt.executeQuery(aktuelleTeilnehmer);
			int aktuelleTeilnehmerAnzahl = 0;
			int teilnehmerAnzahl = 0;
			int eventID = 0;
			int profilID = 0;
			
			while (rs.next()) {
				
				aktuelleTeilnehmerAnzahl = rs.getRow();
				
			}
			
			rs = stt.executeQuery(getEvent);
			
			while (rs.next()) {
				
				teilnehmerAnzahl = rs.getInt("teilnehmerAnz");
				eventID = rs.getInt("id");
				
			}
			
			rs = stt.executeQuery(getProfilID);
			
			while (rs.next()) {
				
				profilID = rs.getInt("id");
				
			}
			
			if (aktuelleTeilnehmerAnzahl < teilnehmerAnzahl) {
				
				String updateTeilnehmerAnz = "INSERT INTO profilNimmtTeilAn (profil_id, aktion_id) VALUES (" + profilID + ", " + eventID + ");";
				stt.execute(updateTeilnehmerAnz);
			
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
