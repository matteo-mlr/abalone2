package abaloneZwei;

import java.io.Serializable;
import java.util.ArrayList;

import fehlermanagement.AppFalscheEingabeException;

public class Profil implements Serializable {

	private static final long serialVersionUID = 1L;
	private String vorname;
	private String nachname;
	private String nutzername;
	private String email;
	private String passwort;
	private int bewertung;
	private Verlauf verlaufTeilgenommen;
	private Verlauf verlaufVeranstaltet;
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public Profil (String vorname, String nachname, String nutzername, String email, String passwort) {
		
		setVorname(vorname);
		setNachname(nachname);
		setNutzername(nutzername);
		setEmail(email);
		setPasswort(passwort);
		bewertung = 0;
		verlaufTeilgenommen = new Verlauf();		
		verlaufVeranstaltet = new Verlauf();
		
	}
	
	public void eventAnlegen(String titel,String kategorie, String zeitraum,int teilnehmerAnzahl) {
		events.add(new Event(this, titel, kategorie, zeitraum, teilnehmerAnzahl));
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	private void setVorname (String vorname) {
		
		try {
		
			if (vorname == null) {
				
				throw new AppFalscheEingabeException("Ungültiger Vorname", this.nutzername);
				
			}
		
		} catch (AppFalscheEingabeException afee) {
		}
		
		this.vorname = vorname;
		
	}
	
	private void setNachname (String nachname) {
		
		try {
			
			if (nachname == null) {
				
				throw new AppFalscheEingabeException("Ungültiger Nachname", this.nutzername);
				
			}
		
		} catch (AppFalscheEingabeException afee) {
		}
		
		this.nachname = nachname;
		
	}
	
	private void setNutzername (String nutzername) {
		
		try {
			
			if (nutzername == null) {
				
				throw new AppFalscheEingabeException("Ungültiger Nutzername", this.nutzername);
				
			}
		
		} catch (AppFalscheEingabeException afee) {
		}
		
		this.nutzername = nutzername;
		
	}
	
	private void setEmail (String email) {
		
		try {
			
			if (email == null) {
				
				throw new AppFalscheEingabeException("Ungültige E-Mail", this.nutzername);
				
			}
		
		} catch (AppFalscheEingabeException afee) {
		}
		
		this.email = email;
		
	}

	private void setPasswort (String passwort) {
	
		try {
			
			if (passwort == null) {
				
				throw new AppFalscheEingabeException("Ungültiges Passwort", this.nutzername);
				
			}
		
		} catch (AppFalscheEingabeException afee) {
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
		
		StringBuilder sb = new StringBuilder(1000);
		
		sb.append("========================\n");
		sb.append("     Dein Profil\n");
		sb.append("========================\n");
		sb.append("\n");
		sb.append(this.vorname + " " + this.nachname + "\n");
		sb.append(this.nutzername + "\n\n");
		sb.append("========================\n\n");
		sb.append("Events veranstaltet: \n");
		sb.append(verlaufVeranstaltet.getAnzahl() + "\n");
		sb.append("Events teilgenommen: \n");
		sb.append(verlaufTeilgenommen.getAnzahl() + "\n");
		sb.append("========================\n");
		sb.append("Bewertung: \n");
		sb.append(this.bewertung + "\n");
		sb.append("========================\n");
		
		return sb.toString();
		
	}
	
	public void berechneBewertung () {
		
		int durchschnitt = 0;
		
		ArrayList<Event> events = verlaufVeranstaltet.getEvents();
		
		for (Event current : events) {
			
			durchschnitt += current.getBewertung();
			
		}
		
		this.bewertung = durchschnitt / events.size();
		
	}
	
	public void passwortÄndern (String neuesPasswort) {
		
		this.passwort = neuesPasswort;
		
	}

}
