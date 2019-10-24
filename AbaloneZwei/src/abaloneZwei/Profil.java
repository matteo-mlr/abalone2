package abaloneZwei;

import java.io.Serializable;

public class Profil implements Serializable {
	
	private String vorname;
	private String nachname;
	private String nutzername;
	private String email;
	private String passwort;
	
	public Profil (String vorname, String nachname, String nutzername, String email, String passwort) {
		
		setVorname(vorname);
		setNachname(nachname);
		setNutzername(nutzername);
		setEmail(email);
		setPasswort(passwort);
		
	}
	
	private void setVorname (String vorname) {
		
		if (vorname == null) {
			
			// Fehlermanagement
			return;
			
		}
		
		this.vorname = vorname;
		
	}
	
	private void setNachname (String nachname) {
		
		if (nachname == null) {
			
			// Fehlermanagement
			return;
			
		}
		
		this.nachname = nachname;
		
	}
	
	private void setNutzername (String nutzername) {
		
		if (nutzername == null) {
			
			// Fehlermanagement
			return;
			
		}
		
		this.nutzername = nutzername;
		
	}
	
	private void setEmail (String email) {
		
		if (email == null) {
			
			// Fehlermanagement
			return;
			
		}
		
		this.email = email;
		
	}

	private void setPasswort (String passwort) {
	
		if (passwort == null) {
			
			// Fehlermanagement
			return;
			
		}
		
		this.passwort = passwort;
	
	}
	
	public String getPasswort () {
		
		return passwort;
		
	}
	
	public String getNutzername () {
		
		return nutzername;
		
	}

	@Override
	public String toString() {
		return "[ "+ vorname + " " +  nachname  + " ]";
	}
	
}
