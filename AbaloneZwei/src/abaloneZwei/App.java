package abaloneZwei;

import java.util.ArrayList;

import fehlermanagement.AppEventTeilnehmenException;
import fehlermanagement.AppException;

public class App {
	
	private ArrayList<Profil> profile = new ArrayList<Profil>();
	private Profil aktivesProfil;
	private DBSchnittstelle dbSchnittstelle;
	
	public App() {
		
		profile.add(new Profil("admin", "admin", "admin", "admin@mail.de", "admin"));
		
		dbSchnittstelle = new DBSchnittstelle();
		
	}
	
	public void profilAnlegen (String vorname, String nachname, String nutzername, String email, String passwort) {
		
		String regexVornameNachname = "[A-Za-zÄÜÖäüö]+";
		String regexEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		
		try {
		
//Profildaten auf Korrektheit prüfen
			
			if (vorname == null || nachname == null || nutzername == null || email == null || passwort == null
				
				|| vorname.length() < 1 || nachname.length() < 1 || nutzername.length() < 1 || email.length() < 1 || passwort.length() < 1
			
				||!vorname.matches(regexVornameNachname) || !nachname.matches(regexVornameNachname)

				||!email.matches(regexEmail)) {
					
					throw new AppException("Falsche Eingabe.", getAktivesProfil() == null ? "" : getAktivesProfil().getNutzername());
						
				}
			
		} catch (AppException ae) {
			
			System.out.println("Fehler");
			return;
			
		}
		
//Profil anlegen
		
//		profile.add(new Profil(vorname, nachname, nutzername, email, passwort));
		dbSchnittstelle.profilAnlegen(vorname, nachname, nutzername, passwort, email); 
		System.out.println("Account erfolgreich angelegt!");
		
	}
	
	public void anmelden (String nutzername, String passwort) throws AppException {
		
			Profil profil = dbSchnittstelle.getProfil(nutzername, passwort);
			
			if (profil != null) {
				
				aktivesProfil = profil;
				return;
				
			}
			
//			for (int i = 0; i < profile.size(); i++) {
//				
//				//wenn nutzername vorhanden
//				if (profile.get(i).getNutzername().equals(nutzername)) {
//					
//					// und wenn passwort zu nutzernamen stimmt
//					if (profile.get(i).getPasswort().equals(passwort)) {
//						
//						aktivesProfil = profile.get(i);
//						return;
//					}
//				}	
//			}
			
			//wenn kein nutzername passt bzw passwort falsch ist wird exception geworfen
			throw new AppException("Anmeldefehler", getAktivesProfil() == null ? "" : getAktivesProfil().getNutzername());			
	}
	
	public void eventAnlegen (String titel, int kategorie, String zeitraum, int teilnehmerAnzahl) {
		
		dbSchnittstelle.eventAnlegen(aktivesProfil.getNutzername(), titel, kategorie, zeitraum, teilnehmerAnzahl);
		
	}
	
	public ArrayList<Event> getAlleEvents () {
		
		return dbSchnittstelle.getAlleEvents();
		
	}
	
	public int getAnzahlEvents () {
		
		return dbSchnittstelle.getAlleEvents().size();
		
	}
	
	public Event getEvent (int number) {
		
		return getAlleEvents().get(number - 1);
		
	}
	
	public ArrayList<Event> eigeneEvents () {
		
		return dbSchnittstelle.getEventsVonProfil(aktivesProfil.getNutzername());
		
	}
	
	public void eventAktualisieren (int id, String titel, int kategorie, String zeitraum, int teilnehmerAnzahl) {
		
		dbSchnittstelle.eventAktualisieren(id, titel, kategorie, zeitraum, teilnehmerAnzahl);
		
	}
	
	// An einem Event teilnehmen
	public void anEventTeilnehmen (int eventID) throws AppEventTeilnehmenException {
		
		dbSchnittstelle.teilnehmen(eventID, aktivesProfil.getNutzername());
		
	}
	
	// Gibt die aktuelle Teilnehmeranzahl eines Events zurück
	public int getEventTeilnehmer (int eventID) {
		
		return dbSchnittstelle.getEventTeilnehmer(eventID);
		
	}
	
	public void abmelden () {
		
		aktivesProfil = null;
		
	}
	
	public ArrayList<Profil> getProfile(){
		return profile;
	}
	
	public Profil getAktivesProfil() {
		return aktivesProfil;
	}

}
