package persistenz;

import java.io.*;

import abaloneZwei.App;
import abaloneZwei.Profil;
import fehlermanagement.AppException;


public class ProfilSerialisierung {

    private ObjectOutputStream out;
    private ObjectInputStream in;;

    public ProfilSerialisierung(){
        in = null; out = null;
    }

    public Profil lesen(String benutzername) throws  AppException {

        try {
            oeffneInputstream(benutzername);
        } catch (IOException e) {
            throw new AppException("Fehler beim Datei oeffnen.");
        }

        Object tmp;

        try {
            tmp = in.readObject();
        } catch (Exception e) {
            throw new AppException("Fehler beim Datei einlesen.");
        }

        try {
            schließen();
        } catch (IOException io) {
            throw new AppException("Fehler beim Schließen des Inputstreams");
        }


        if(tmp instanceof Profil)
            return (Profil)tmp;
        else
            throw new AppException("Eingelesene Datei ist kein Profil.");
    }

    private void oeffneInputstream(String benutzername) throws IOException {

        String dateipfad = "AbaloneZwei\\profilSpeicher\\" + benutzername+".ser";

        if(in != null){
            schließen();
            in = new ObjectInputStream(new FileInputStream(dateipfad));
        }

        in = new ObjectInputStream(new FileInputStream(dateipfad));


    }

    private void oeffneOutputstream(String benutzername) throws IOException {

        String dateipfad = "AbaloneZwei\\profilSpeicher\\" + benutzername+".ser";

        if(out != null){
            schließen();
            out = new ObjectOutputStream(new FileOutputStream(dateipfad));
        }

        out = new ObjectOutputStream(new FileOutputStream(dateipfad));
    }

    private void schließen() throws IOException{
        if(in != null)
            in.close();

        if(out != null)
            out.close();
    }

    public void serialisiere(Profil profil) throws AppException{

        if(profil == null) throw new AppException("profil ist null");

        String benutzername= profil.getNutzername();
        try {
            oeffneOutputstream(benutzername);
        } catch (IOException io) {
            throw new AppException("Fehler beim Datei öffnen.");
        }
        try {
            out.writeObject(profil);
        } catch (IOException io) {
            throw new AppException("Fehler beim Schreiben des Profils.");
        }

        try {
            schließen();
        } catch (IOException io) {
            throw new AppException("Fehler beim Schließen des Outputstreams");
        }



    }
}
