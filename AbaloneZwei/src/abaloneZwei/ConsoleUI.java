package abaloneZwei;

import java.util.Scanner;

import fehlermanagement.AppException;

public class ConsoleUI {

	private static App app = new App();
	private static Scanner sc = new Scanner(System.in);
	private static Profil profil;
	
	
	private static final String REGEX_MENU = "[1-2]";
	
	
	
	public static void main(String[] args) {

		
		printStartMenu();
		
		String eingabe;
		
		while (true) {
			
			System.out.print(">");
			eingabe = sc.nextLine();
		
			if (eingabe.matches(REGEX_MENU))
				break;
		}
		
		if(eingabe.equals("1"))
			anmelden();
		if(eingabe.equals("2")) {
			registrieren();
			anmelden();
		}
		
		while(true) {

			printEventMenu();

			while (true) {

				System.out.print(">");
				eingabe = sc.nextLine();

				if (eingabe.matches(REGEX_MENU))
					break;
			}

			if(eingabe.equals("1"))
				eventAnlegen();
			if(eingabe.equals("2"))
				anmelden();

		}
	}
		
	private static void eventAnlegen() {
		
		System.out.println("========================");
		System.out.println("   neues Event anlegen  ");
		System.out.println("========================");
		
		System.out.println("Titel");
		System.out.print("> ");
		String titel = sc.nextLine();
		
		System.out.println("Kategorie");
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
	}

	private static void printEventMenu() {
		
		System.out.println("========================");
		
		System.out.println("  1 | neues Event");
		System.out.println("  2 | meine Events anzeigen");
		
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
