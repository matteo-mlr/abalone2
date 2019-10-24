package abaloneZwei;

import java.util.ArrayList;
import java.util.Scanner;
import fehlermanagement.AppException;

public class App {
	
	private static ArrayList<Profil> profile;
	
	public static void main (String[] args) {
		
		profile = new ArrayList<>();
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("========================");
		System.out.println("       ABALONE  2");
		System.out.println("========================");
		
		System.out.println("  1 | Anmelden");
		System.out.println("  2 | Registrieren");
		
		System.out.println("========================");
		
		boolean falscheEingabe = true;
		String choice = "";
		
		while (falscheEingabe) {
		
			System.out.print(">");
			
			choice = sc.nextLine();
			String regexChoice = "[1-2]";
			
			if (choice.matches(regexChoice))
				falscheEingabe = false;
		
		}
		
		if (choice.equals("1")) {
		
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
			
			boolean nichtKorrekt = true;
			
			String passwort = "";
			
			while (nichtKorrekt) {
			
				System.out.println("Passwort");
				System.out.print("> ");
				passwort = sc.nextLine();
				
				System.out.println("Passwort bestätigen");
				System.out.print("> ");
				String passwort2 = sc.nextLine();
				
				if (passwort.equals(passwort2))
					nichtKorrekt = false;
				else
					System.out.println("Passwörter stimmen nicht überein.");
			
			}
			
			profilAnlegen(vorname, nachname, nutzername, email, passwort);
		
		} else {
			
			boolean falscheEingabeAnmelden = true;
			
			while (falscheEingabeAnmelden) {
			
				System.out.println("Nutzername");
				System.out.print("> ");
				String nutzername = sc.nextLine();
				
				System.out.println("Passwort");
				System.out.print("> ");
				String passwort = sc.nextLine();
				
				if (profile.contains(nutzername)) {
					
					int counter = 0;
					
					for (Profil profil : profile) {
						
						if (!profil.getNutzername().equals(nutzername)) {
							
							counter++;
							
						} else {
							
							if (profile.get(counter).getPasswort().equals(passwort)) {
								
								falscheEingabeAnmelden = false;
								anmelden(nutzername, passwort);
								
							}
							
						}
							
					}
					
				} else {
					
					System.out.println("Der Nutzername ist falsch.");
					
				}
			
			}
			
		}
		
	}
	
	private static void profilAnlegen (String vorname, String nachname, String nutzername, String email, String passwort) {
		
		String regexVornameNachname = "[a-zA-Z]+";
		String regexEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		String regexPasswort = "([A-Z][a-z][0-9])+";
		
		try {
		
			if (vorname == null || nachname == null || nutzername == null || email == null || passwort == null ||
				vorname.length() < 1 || nachname.length() < 1 || nutzername.length() < 1 || email.length() < 1 || passwort.length() < 1) {
			
				if (!vorname.matches(regexVornameNachname) || !nachname.matches(regexVornameNachname)) {

					if (!email.matches(regexEmail)) {
						
						if (!passwort.matches(regexPasswort)) {
					
							throw new AppException("Falsche Eingabe.");
						
						}
					
					}
					
				}
				
			
			}
		
		} catch (AppException ae) {
			
			System.out.println("Fehler");
			return;
			
		}
		
		profile.add(new Profil(vorname, nachname, nutzername, email, passwort));
		System.out.println("Account erfolgreich angelegt!");
		
	}
	
	private static void anmelden (String nutzername, String passwort) {
		
		
		
	}

}
