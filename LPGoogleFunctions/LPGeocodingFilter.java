/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

public class LPGeocodingFilter {

	// Variables
	
	public static enum LPGeocodingFilterMode {
	    LPGeocodingFilterRoute,
	    LPGeocodingFilterLocality,
	    LPGeocodingFilterAdministrative_area,
	    LPGeocodingFilterPostal_code,
	    LPGeocodingFilterCountry
	};
	
	public static String geocodingFilterRoute = "route";
	public static String geocodingFilterPostal_code = "postal_code";
	public static String geocodingFilterLocality = "locality";
	public static String geocodingFilterAdministrative_area = "administrative_area";
	public static String geocodingFilterCountry = "country";
	
	public LPGeocodingFilterMode filter = null;
	public String value = null;
	
	// Class
	
	public LPGeocodingFilter()
	{
		
	}
	
	public LPGeocodingFilter(LPGeocodingFilterMode filter, String value)
	{
		this.filter = filter;
		this.value = value;
	}
	
	public LPGeocodingFilter clone()
	{
		LPGeocodingFilter object = new LPGeocodingFilter();
    	
        try {
        	object.filter = this.filter;
        	object.value = this.value;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}

	// Functions
	
	public static String getGeocodingFilter(LPGeocodingFilterMode filter)
	{
	    if(filter==LPGeocodingFilterMode.LPGeocodingFilterRoute)
	    {
	        return geocodingFilterRoute;
	    } else if(filter==LPGeocodingFilterMode.LPGeocodingFilterPostal_code) {
	        return geocodingFilterPostal_code;
	    } else if(filter==LPGeocodingFilterMode.LPGeocodingFilterLocality) {
	        return geocodingFilterLocality;
	    } else if(filter==LPGeocodingFilterMode.LPGeocodingFilterAdministrative_area) {
	        return geocodingFilterAdministrative_area;
	    } else {
	        return geocodingFilterCountry;
	    }
	}
}
