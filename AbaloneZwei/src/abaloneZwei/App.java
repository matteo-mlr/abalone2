package abaloneZwei;

import java.util.ArrayList;
import java.util.Scanner;

import fehlermanagement.AppException;

public class App {
	
	private static ArrayList<Profil> profile;
	
	public static void main (String[] args) {
		
		profile = new ArrayList<>();
	
		Scanner sc = new Scanner(System.in);
		
		profilAnlegen(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine());
		
	}
	
	private static void profilAnlegen (String vorname, String nachname, String nutzername, String email, String passwort) {
		
		try {
		
			if (vorname == null || nachname == null || nutzername == null || email == null || passwort == null ||
				vorname.length() < 1 || nachname.length() < 1 || nutzername.length() < 1 || email.length() < 1 || passwort.length() < 1) {
			
				throw new AppException("Falsche Eingabe.");
			
			}
		
		} catch (AppException ae) {
			
			System.out.println("Fehler");
			return;
			
		}
		
		profile.add(new Profil(vorname, nachname, nutzername, email, passwort));
		
	}

}
