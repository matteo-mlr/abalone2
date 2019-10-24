package abaloneZwei;

public class Event {

	private Profil ersteller;
	private String titel;
	private String beschreibung;
	private String kategorie;
	private String zeitraum;  //nur temporär String
	private Profil[] teilnehmer;
	private int teilnehmerAnzahl;
	private Rezension[] rezensionen;
	private int bewertung;
	//private Standort standort;
	//private bilder img[];
	//private kategorieIcon img;
	
	public Event(Profil ersteller, String titel,String kategorie, String zeitraum,int teilnehmerAnzahl) {
		
		setErsteller(ersteller);
		setTitel(titel);
		setKategorie(kategorie);
		setZeitraum(zeitraum);
		setTeilnehmerAnzahl(teilnehmerAnzahl);
		
		setTeilnehmer(new Profil[teilnehmerAnzahl]);
		
	}

	public Profil getErsteller() {
		return ersteller;
	}

	private void setErsteller(Profil ersteller) {
		this.ersteller = ersteller;
	}

	public String getTitel() {
		return titel;
	}

	private void setTitel(String titel) {
		this.titel = titel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	private void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getKategorie() {
		return kategorie;
	}

	private void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	public String getZeitraum() {
		return zeitraum;
	}

	private void setZeitraum(String zeitraum) {
		this.zeitraum = zeitraum;
	}

	public Profil[] getTeilnehmer() {
		return teilnehmer;
	}

	private void setTeilnehmer(Profil[] teilnehmer) {
		this.teilnehmer = teilnehmer;
	}

	public int getTeilnehmerAnzahl() {
		return teilnehmerAnzahl;
	}

	private void setTeilnehmerAnzahl(int teilnehmerAnzahl) {
		this.teilnehmerAnzahl = teilnehmerAnzahl;
	}

	public Rezension[] getRezensionen() {
		return rezensionen;
	}

	private void setRezensionen(Rezension[] rezensionen) {
		this.rezensionen = rezensionen;
	}
	
	@Override
	public String toString() {
		return 	"Titel: " + titel + "\n" +
				"Kategorie: " + kategorie + "\n" +
				"Uhrzeit: " + zeitraum + "\n" +
				"Für: " + teilnehmerAnzahl + " Personen";
	}
	
	public void berechneBewertung () {
		
		int durchschnitt = 0;
		
		for (Rezension current : rezensionen) {
			
			durchschnitt += current.getBewertung();
			
		}
		
		durchschnitt /= rezensionen.length;
		
		this.bewertung = durchschnitt;
		
	}
	
	public int getBewertung () {
		
		return bewertung;
		
	}
 	

}
