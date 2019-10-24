package abaloneZwei;

public class Rezension {
	
	private int bewertung;
	private String beschreibung;
	private Profil verfasser;
	private Event event;
	
	public Rezension (int bewertung, String beschreibung, Profil verfasser, Event event) {
		
		setBewertung(bewertung);
		setBeschreibung(beschreibung);
		setVerfasser(verfasser);
		setEvent(event);
		
	}
	
	private void setBewertung (int bewertung) {
		
		if (bewertung < 10 && bewertung > -1)
			this.bewertung = bewertung;
		
	}
	
	private void setBeschreibung (String beschreibung) {
		
		this.beschreibung = beschreibung;
		
	}
	
	private void setVerfasser (Profil verfasser) {
		
		this.verfasser = verfasser;
		
	}
	
	private void setEvent (Event event) {
		
		this.event = event;
		
	}
	
	public int getBewertung () {
		
		return bewertung;
		
	}

	public String getBeschreibung () {
		
		return beschreibung;
		
	}
	
}
