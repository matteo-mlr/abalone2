package abaloneZwei;

import java.util.ArrayList;
import java.util.Scanner;

import fehlermanagement.AppException;

public class App {
	
	private static ArrayList<Profil> profile;
	
	public static void main (String[] args) {
		
		profile = new ArrayList<>();
		
		profilAnlegen("Rainer","Wahnsinn","nachthuhn","birke@wald.de","********");
		profilAnlegen("Christoph","Hammel","djikstra","eiche@wald.de","********");
		profilAnlegen("Simone","Talberger","fränk","buche@wald.de","********");
		
		for (int i = 0; i < profile.size(); i++) {
			System.out.println(profile.get(i).toString());
		}
		
		Event e = new Event(profile.get(0), "Chilli Sin Carne", "Fleisch", "19:00 - 20:00 Uhr", 2);
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println(e.toString());
		
		
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
