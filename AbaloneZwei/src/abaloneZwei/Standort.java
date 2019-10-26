package abaloneZwei;

import fehlermanagement.AppStandortException;

public class Standort {
	
	private static final String REGEX_LATITUDE = "[0-90].[0-9](6)";
	private static final String REGEX_LONGITUDE = "[0-180].[0-9](6)";
	
	private String latitude;
	private String longitude;
	
	public Standort (String latitude, String longitude) {
		
		setLatitude(latitude);
		setLongitude(longitude);
		
	}
	
	private void setLatitude (String latitude) {
		
		try {
		
			if (!latitude.matches(REGEX_LATITUDE))
				throw new AppStandortException("Ungültige Koordinaten", "");
		
		} catch (AppStandortException ase) {
		}
		
		this.latitude = latitude;
		
	}
	
	private void setLongitude (String longitude) {
		
		try {
			
			if (!longitude.matches(REGEX_LONGITUDE))
				throw new AppStandortException("Ungültige Koordinaten", "");
			
		} catch (AppStandortException ase) {
		}
		
		this.longitude = longitude;
		
	}
	
	public String getLatitude () {
		
		return latitude;
		
	}

	public String getLongitude () {
		
		return longitude;
		
	}
	
}
