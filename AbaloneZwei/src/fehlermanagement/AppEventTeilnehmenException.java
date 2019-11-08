package fehlermanagement;

public class AppEventTeilnehmenException extends AppException {
	
	private static final long serialVersionUID = 1L;

	public AppEventTeilnehmenException(String fehler, String user) {
		
		super(fehler, user);
		
	}

}
