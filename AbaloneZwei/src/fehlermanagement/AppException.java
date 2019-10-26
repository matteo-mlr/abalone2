package fehlermanagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppException extends Exception {

	private static final long serialVersionUID = 1L;
	private transient DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm");
	private transient LocalDateTime now = LocalDateTime.now();
	
	public AppException (String fehler, String user) {
		
		super(fehler);
		addToLog(user);
		
	}
	
	private void addToLog(String user) {

		PrintWriter pWriter = null;
		
		try {

			pWriter = new PrintWriter(new BufferedWriter(new FileWriter("error.log", true)));
			pWriter.println(dtf.format(now) + " " + getMessage() + " | User: " + user);

		} catch (IOException ioe) {
			
			return;

		} finally {

			if (pWriter != null) {

				pWriter.flush();
				pWriter.close();

			}

		}

	}

}
