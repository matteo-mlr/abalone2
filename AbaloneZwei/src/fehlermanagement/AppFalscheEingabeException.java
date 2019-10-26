package fehlermanagement;

public class AppFalscheEingabeException extends AppException {

	private static final long serialVersionUID = 1L;

	public AppFalscheEingabeException(String fehler, String user) {
		
		super(fehler, user);
		
	}

}
