package abaloneZwei;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import fehlermanagement.AppException;

public class ConsoleUI {

	private static App app = new App();
	private static Scanner sc = new Scanner(System.in);
	private static Profil profil;
	
	private static final String REGEX_MENU_ANMELDEN_REGISTRIEREN = "[1-2]";
	private static final String REGEX_MENU_ANGEMELDET = "[1-5]";
	private static final String REGEX_MENU_PROFIL = "[1-3]";
	
	
	
	public static void main(String[] args) {
		
		String eingabe;
		
		outerloop:
		while (true) {
		
			printStartMenu();
				
			while (true) {
				
				System.out.print(">");
				eingabe = sc.nextLine();
			
				if (eingabe.matches(REGEX_MENU_ANMELDEN_REGISTRIEREN))
					break;
			}
			
			if(eingabe.equals("1"))
				anmelden();
			if(eingabe.equals("2")) {
				registrieren();
				anmelden();
			}
			
			while(true) {
	
				printMenu();
	
				while (true) {
	
					System.out.print(">");
					eingabe = sc.nextLine();
	
					if (eingabe.matches(REGEX_MENU_ANGEMELDET))
						break;
				}
	
				if(eingabe.equals("1"))
					eventAnlegen();
				if(eingabe.equals("2"))
					eventsAnzeigen();
				if(eingabe.equals("3")) {
					
					profilMenu();
					
					while (true) {
					
						System.out.print(">");
						eingabe = sc.nextLine();
						
						if (eingabe.matches(REGEX_MENU_PROFIL)) {
							
							if (eingabe.equals("1"))
								System.out.println(app.getAktivesProfil().toString());
							
							if (eingabe.equals("2")) {
								
								System.out.println("Altes Passwort: ");
								String neuesPasswort = "";
								
								while (true) {
	
									System.out.print(">");
									eingabe = sc.nextLine();
									
									if (eingabe.equals(app.getAktivesProfil().getPasswort()))
										break;
									else
										System.out.println("Falsches Passwort.");
								
								}
								
								while (true) {
									
									System.out.println("Neues Passwort: ");
									System.out.print(">");
									neuesPasswort = sc.nextLine();
									
									System.out.println("Neues Passwort wiederholen: ");
									System.out.print(">");
									eingabe = sc.nextLine();
									
									if (neuesPasswort.equals(eingabe))
										break;
									else
										System.out.println("Versuchen Sie es erneut.");
									
								}
								
								app.getAktivesProfil().passwortÄndern(neuesPasswort);
								System.out.println("Ihr Passwort wurde erfolgreich geändert!");
								
							}
							
							if (eingabe.equals("3")) {
								
								app.abmelden();
								continue outerloop;
								
							}
							
							break;
							
						}
						
					}
					
				}
				
				if(eingabe.equals("4")) {
					System.out.println(app.getAktivesProfil().getFeed());
				}
				
				if(eingabe.equals("5")) {
					
					ArrayList<Event> alleEvents = app.getAlleEvents();
					
					for (Event e : alleEvents) {
						
						System.out.println(e.getTitel() + " | " + e.getKategorie() + " | " + e.getZeitraum() + " | " + e.getTeilnehmerAnzahl());
						
					}
					
				}
	
			}
		
		}
		
	}
		
	
	private static void eventsAnzeigen() {
		
		ArrayList<Event> events = app.getAktivesProfil().getMeineEvents();
		
		if (events.size() == 0) {
			
			System.out.println("Sie haben noch keine Events.");
			return;
			
		}
		
		for (int i = 0; i < events.size(); i++) {
			System.out.println((i+1) + ". " + events.get(i).getTitel());
			
		}
		
		
	}
	
	private static void eventAnlegen() {
		
		System.out.println("========================");
		System.out.println("   neues Event anlegen  ");
		System.out.println("========================");
		
		System.out.println("Titel");
		System.out.print("> ");
		String titel = sc.nextLine();
		
		System.out.println("Kategorie(Nummer wegen Enum)");
		System.out.print("> ");
		String kategorie = sc.nextLine();
		
		System.out.println("Zeitraum");
		System.out.print("> ");
		String zeitraum = sc.nextLine();
		
		System.out.println("Teilnehmer Anzahl");
		System.out.print("> ");
		String teilnehmerAnzahlString = sc.nextLine();
		int teilnehmerAnzahl = Integer.parseInt(teilnehmerAnzahlString);
		
		app.getAktivesProfil().eventAnlegen(titel, kategorie, zeitraum, teilnehmerAnzahl);
		app.eventAnlegen(titel, Integer.parseInt(kategorie), zeitraum, teilnehmerAnzahl);
		
	}

	private static void printMenu() {
		
		System.out.println("========================");
		
		System.out.println("  1 | Neues Event");
		System.out.println("  2 | Meine Events anzeigen");
		System.out.println("  3 | Mein Profil");
		System.out.println("  4 | Feed anzeigen");
		System.out.println("  5 | Alle Events anzeigen");
		
		System.out.println("========================");
		
	}
	
	private static void profilMenu () {
		
		System.out.println("========================");
		
		System.out.println("  1 | Profil anzeigen");
		System.out.println("  2 | Passwort ändern");
		System.out.println("  3 | Abmelden");
		
		System.out.println("========================");
		
	}
	
	private static void printStartMenu() {
		
		System.out.println("========================");
		System.out.println("       ABALONE  2");
		System.out.println("========================");
		
		System.out.println("  1 | Anmelden");
		System.out.println("  2 | Registrieren");
		
		System.out.println("========================");
		
	}
	
	private static void anmelden() {

		System.out.println("========================");
		System.out.println("        Anmelden        ");
		System.out.println("========================");
		
		boolean wiederhole = true;

		while (wiederhole) {

			System.out.println("Nutzername");
			System.out.print("> ");
			String nutzername = sc.nextLine();

			System.out.println("Passwort");
			System.out.print("> ");
			String passwort = sc.nextLine();

			try {
				app.anmelden(nutzername, passwort);
				wiederhole = false;
			}

			catch(AppException e) {
				System.out.println("Nutzername oder Passwort falsch");
			}
		}
	}
	
	private static void registrieren() {
			
//Profildaten eingeben
		
		System.out.println("========================");
		System.out.println("     Profil anlegen");
		System.out.println("========================");
		
		System.out.println("Vorname");
		System.out.print("> ");
		String vorname = sc.nextLine();
		
		System.out.println("Nachname");
		System.out.print("> ");
		String nachname = sc.nextLine();
		
		System.out.println("Nutzername");
		System.out.print("> ");
		String nutzername = sc.nextLine();
		
		System.out.println("E-Mail Adresse");
		System.out.print("> ");
		String email = sc.nextLine();
			
//passwort zweimal zur bestätigung eingeben
		
		String passwort = "";
		
		while (true) {
		
			System.out.println("Passwort eingeben");
			System.out.print("> ");
			passwort = sc.nextLine();
			
			System.out.println("Passwort bestätigen");
			System.out.print("> ");
			String passwort2 = sc.nextLine();
			
			if (passwort.equals(passwort2))
				break;
			else
				System.out.println("Passwörter stimmen nicht überein.");
		}
		
//profil anlegen
		
		app.profilAnlegen(vorname, nachname, nutzername, email, passwort);
		
	}
	
	
}
