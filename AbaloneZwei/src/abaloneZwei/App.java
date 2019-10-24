package abaloneZwei;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	
	private static ArrayList<Profil> profile;
	
	public static void main (String[] args) {
		
		profile = new ArrayList<>();
	
		Scanner sc = new Scanner(System.in);
		
		profilAnlegen(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine());
		
	}
	
	private static void profilAnlegen (String vorname, String nachname, String nutzername, String email, String passwort) {
		
		if (vorname == null && nachname == null && nutzername == null && email == null && passwort == null) {
		
			throw new RuntimeException("Fehler");
		
		}
		
		profile.add(new Profil(vorname, nachname, nutzername, email, passwort));
		
	}

}
