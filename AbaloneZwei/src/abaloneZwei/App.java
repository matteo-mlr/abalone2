package abaloneZwei;

import java.util.ArrayList;
import java.util.Scanner;
import fehlermanagement.AppException;

public class App {
	
	private ArrayList<Profil> profile = new ArrayList<Profil>();
	private Profil akitvesProfil;
	
	public App() {
		
		profile.add(new Profil("admin", "admin", "admin", "admin@mail.de", "admin"));
	}
	
	public void profilAnlegen (String vorname, String nachname, String nutzername, String email, String passwort) {
		
		String regexVornameNachname = "[a-zA-Z]+";
		String regexEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		String regexPasswort = "([A-Za-z0-9])+";
		
		try {
		
//Profildaten auf Korrektheit prüfen
			
			if (vorname == null || nachname == null || nutzername == null || email == null || passwort == null
				
				|| vorname.length() < 1 || nachname.length() < 1 || nutzername.length() < 1 || email.length() < 1 || passwort.length() < 1
			
				||!vorname.matches(regexVornameNachname) || !nachname.matches(regexVornameNachname)

				||!email.matches(regexEmail)
						
				||!passwort.matches(regexPasswort)) {
					
					throw new AppException("Falsche Eingabe.");
						
				}
			
		} catch (AppException ae) {
			
			System.out.println("Fehler");
			return;
			
		}
		
//Profil anlegen
		
		profile.add(new Profil(vorname, nachname, nutzername, email, passwort));
		System.out.println("Account erfolgreich angelegt!");
		
	}
	
	public void anmelden (String nutzername, String passwort) throws AppException {
			
			for (int i = 0; i < profile.size(); i++) {
				
				//wenn nutzername vorhanden
				if (profile.get(i).getNutzername().equals(nutzername)) {
					
					// und wenn passwort zu nutzernamen stimmt
					if (profile.get(i).getPasswort().equals(passwort)) {
						
						akitvesProfil = profile.get(i);
						return;
					}
				}	
			}
			
			//wenn kein nutzername passt bzw passwort falsch ist wird exception geworfen
			throw new AppException("Anmeldefehler");			
	}
	
	public ArrayList<Profil> getProfile(){
		return profile;
	}
	
	public Profil getAktivesProfil() {
		return akitvesProfil;
	}

}
