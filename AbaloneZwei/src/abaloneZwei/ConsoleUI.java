package abaloneZwei;

import java.util.Scanner;

public class ConsoleUI {

	private static App app = new App();
	private static Scanner sc = new Scanner(System.in);
	
	
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
			registrieren();
		if(eingabe.equals("2"))
			anmelden();
		
		
		
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

			
			boolean wiederhole = true;
			
			while (wiederhole) {
			
				System.out.println("Nutzername");
				System.out.print("> ");
				String nutzername = sc.nextLine();
				
				System.out.println("Passwort");
				System.out.print("> ");
				String passwort = sc.nextLine();
				
				if (app.getProfile().contains(nutzername)) {
					
					int counter = 0;
					
					for (int i = 0; i < app.getProfile().size(); i++) {
						
						//wenn nutzername vorhanden
						if (app.getProfile().get(i).getNutzername().equals(nutzername)) {
							
							// und wenn passwort zu nutzernamen stimmt
							if (app.getProfile().get(i).getPasswort().equals(passwort)) {
								
								app.anmelden(nutzername, passwort);
								wiederhole = false;
								break;
							}
						}	
					}
					
					System.out.println("Der Nutzername ist falsch.");	
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
