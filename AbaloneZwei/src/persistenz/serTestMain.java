package persistenz;

import abaloneZwei.Profil;
import fehlermanagement.AppException;

import java.io.FileWriter;
import java.io.IOException;

public class serTestMain {

    public static void main(String[] args) throws IOException {
        Profil p1 = new Profil("arno", "dübel", "dübela",
                "arno@hartz4.de","arbeit");
        Profil p2;

        ProfilSerialisierung percy = new ProfilSerialisierung();
        try {
            p2 = percy.lesen("dübela");
            System.out.println(p2.getNutzername());
        } catch (AppException ae) {
            System.out.println("offenbar fail");
        }


    }
}
