package abaloneZwei;

import java.util.ArrayList;
import java.util.Scanner;

import fehlermanagement.AppEventTeilnehmenException;
import fehlermanagement.AppException;

public class ConsoleUI {

	private static App app = new App();
	private static Scanner sc = new Scanner(System.in);
	private static Profil profil;
	
	private static final String REGEX_MENU_ANMELDEN_REGISTRIEREN = "[1-2]";
	private static final String REGEX_MENU_ANGEMELDET = "[1-5]";
	private static final String REGEX_MENU_PROFIL = "[1-3]";
	private static final String REGEX_MENU_EVENTS_ANGEZEIGT = "[1-2]";
	private static final String REGEX_MENU_EVENT = "[1-2]";
	private static final String REGEX_MENU_EIGENE_EVENTS_LISTE = "[1-2]";	
	
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
			
			innerloop:
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
				
				if(eingabe.equals("2")) {
					
					ArrayList<Event> eigeneEvents = app.eigeneEvents();
					int counter = 1;
					
					System.out.println();
					for (Event e : eigeneEvents) {
						
						System.out.println(counter++ + " | " + e.getTitel() + " | " + e.getKategorie() + " | " + e.getZeitraum() + " | " + e.getTeilnehmerAnzahl());
						
					}
					System.out.println();
					
					printEigeneEventsMenu();
					
					while (true) {
						
						System.out.print(">");
						eingabe = sc.nextLine();
						
						if (eingabe.matches(REGEX_MENU_EIGENE_EVENTS_LISTE))							
							break;		
						
					}
					
					if (eingabe.equals("1")) {
						
						System.out.println("Wählen Sie ein Event.");
						int eventNumber = 0;
						String regex = "[1-" + eigeneEvents.size() + "]";
						
						while (true) {
							
							System.out.print(">");
							eingabe = sc.nextLine();
							
							if (eingabe.matches(regex))
								break;
							
						}
						
						System.out.println("========================");										
						System.out.println(eigeneEvents.get(Integer.parseInt(eingabe) - 1).getTitel());										
						System.out.println("========================");
						System.out.println("Beschreibung:");
						System.out.println(eigeneEvents.get(Integer.parseInt(eingabe) - 1).getBeschreibung());
						System.out.println("Kategorie:");
						System.out.println(eigeneEvents.get(Integer.parseInt(eingabe) - 1).getKategorie());
						System.out.println("Zeitraum:");
						System.out.println(eigeneEvents.get(Integer.parseInt(eingabe) - 1).getZeitraum());
						System.out.println("========================");
						System.out.println("Aktuelle Teilnehmer:");
						System.out.println(app.getEventTeilnehmer(eigeneEvents.get(Integer.parseInt(eingabe) - 1).getID()) + "/" + eigeneEvents.get(Integer.parseInt(eingabe) - 1).getTeilnehmerAnzahl());
						System.out.println("========================");	
						
						printEigenesEventMenu();
						
					}
					
				}	
				
				if(eingabe.equals("3")) {
					
					profilMenu();
					
					while (true) {
					
						System.out.print(">");
						eingabe = sc.nextLine();
						
						if (eingabe.matches(REGEX_MENU_PROFIL)) {
							
							if (eingabe.equals("1"))
								System.out.println(app.getAktivesProfil().toString());
							
							// Passwort ändern
							if (eingabe.equals("2")) {
								
								System.out.println("Altes Passwort: ");
								String neuesPasswort = "";
								
								// Altes Passwort eingeben
								while (true) {
	
									System.out.print(">");
									eingabe = sc.nextLine();
									
									if (eingabe.equals(app.getAktivesProfil().getPasswort()))
										break;
									else
										System.out.println("Falsches Passwort.");
								
								}
								
								// Neues Passwort eingeben
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
								
								// Neues Passwort übernehmen
								app.getAktivesProfil().passwortÄndern(neuesPasswort);
								System.out.println("Ihr Passwort wurde erfolgreich geändert!");
								
							}
							
							// Abmelden
							if (eingabe.equals("3")) {
								
								app.abmelden();
								continue outerloop;
								
							}
							
							break;
							
						}
						
					}
					
				}
				
				// Feed anzeigen
				if(eingabe.equals("4")) {
					System.out.println(app.getAktivesProfil().getFeed());
				}
				
				// Alle Events des Systems anzeigen
				if(eingabe.equals("5")) {
					
					ArrayList<Event> alleEvents = app.getAlleEvents();
					
					while (true) {
						
						int counter = 1;
						System.out.println();
						for (Event e : alleEvents) {
							
							System.out.println(counter++ + " | " + e.getTitel() + " | " + e.getKategorie() + " | " + e.getZeitraum() + " | " + e.getTeilnehmerAnzahl());
							
						}
						System.out.println();
						
						printEventsAngezeigtMenu();
						
						System.out.print(">");
						eingabe = sc.nextLine();
						
						if (eingabe.matches(REGEX_MENU_EVENTS_ANGEZEIGT)) {
							
							// Gericht aus der Liste auswählen
							if (eingabe.equals("1")) {
								
								System.out.println("Wählen Sie ein Event.");
								int eventNumber = 0;
								String regex = "[1-" + app.getAnzahlEvents() + "]";
								
								while (true) {
									
									System.out.print(">");
									eingabe = sc.nextLine();
									
									if (eingabe.matches(regex)) {
										
										eventNumber = Integer.parseInt(eingabe);
										break;
										
									}
									
								}
						
									
								Event e = app.getEvent(Integer.parseInt(eingabe));
								
								System.out.println("========================");										
								System.out.println(e.getTitel());										
								System.out.println("========================");
								System.out.println("Beschreibung:");
								System.out.println(e.getBeschreibung());
								System.out.println("Kategorie:");
								System.out.println(e.getKategorie());
								System.out.println("Zeitraum:");
								System.out.println(e.getZeitraum());
								System.out.println("========================");
								System.out.println("Aktuelle Teilnehmer:");
								System.out.println(app.getEventTeilnehmer(alleEvents.get(eventNumber - 1).getID()) + "/" + e.getTeilnehmerAnzahl());
								System.out.println("========================");			
								
								printEventMenu();
								
								while (true) {
									
									System.out.print(">");
									eingabe = sc.nextLine();
									
									if (eingabe.matches(REGEX_MENU_EVENT)) {
										
										break;
										
									}
									
								}
								

								if (eingabe.equals("1")) {
									
									try {
										
										// An ausgewähltem Event teilnehmen -> eventID wird zur Bestimmung des Events übergeben
										app.anEventTeilnehmen(alleEvents.get(eventNumber - 1).getID());
									
									} catch (AppEventTeilnehmenException aete) {
										
										System.out.println("Event ist bereits voll.");
										
									}
									
								}
								

								if (eingabe.equals("2")) {
									
									break;
									
								}
								
							}
							
							if (eingabe.equals("2")) {
								
								continue innerloop;
								
							}
							
						}
						
					}
					
				}
	
			}
		
		}
		
	}
	
	private static void printEigenesEventMenu () {
		
		System.out.println("========================");
		
		System.out.println("  1 | Event bearbeiten");
		System.out.println("  2 | Zurück");
		
		System.out.println("========================");
		
	}
	
	private static void printEigeneEventsMenu () {
		
		System.out.println("========================");
		
		System.out.println("  1 | Event wählen");
		System.out.println("  2 | Zurück");
		
		System.out.println("========================");
		
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
	
	private static void printEventMenu () {
		
		System.out.println("========================");
		
		System.out.println("  1 | Teilnehmen!");
		System.out.println("  2 | Zurück");
		
		System.out.println("========================");
		
	}
	
	private static void printEventsAngezeigtMenu () {
		
		System.out.println("========================");
		
		System.out.println("  1 | Event wählen");
		System.out.println("  2 | Zurück");
		
		System.out.println("========================");
		
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
