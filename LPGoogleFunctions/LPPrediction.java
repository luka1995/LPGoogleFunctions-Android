/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LPPrediction {

	// Variables
	
	public static enum LPGooglePlaceType
	{
		LPGooglePlaceTypeGeocode,
		LPGooglePlaceTypeEstablishment,
		LPGooglePlaceTypeLocality,
		LPGooglePlaceTypeSublocality,
		LPGooglePlaceTypePostalCode,
		LPGooglePlaceTypeCountry,
		LPGooglePlaceTypeAdministrativeArea1,
		LPGooglePlaceTypeAdministrativeArea2,
		LPGooglePlaceTypeAdministrativeArea3,
		LPGooglePlaceTypeUnknown
	};
	
	public static String PLACE_TYPE_GEOCODE = "geocode";
	public static String PLACE_TYPE_ESTABLISHMENT = "establishment";
	public static String PLACE_TYPE_LOCALITY = "locality";
	public static String PLACE_TYPE_SUBLOCALITY = "sublocality";
	public static String PLACE_TYPE_POSTAL_CODE = "postal_code";
	public static String PLACE_TYPE_COUNTRY = "country";
	public static String PLACE_TYPE_ADMINISTRATIVE_AREA1 = "administrative_area1";
	public static String PLACE_TYPE_ADMINISTRATIVE_AREA2 = "administrative_area2";
	public static String PLACE_TYPE_ADMINISTRATIVE_AREA3 = "administrative_area3";

	public String name = null;
	public String ID = null;
	public int number = 0;
	public String reference = null;
	public ArrayList<String> types = null;
	public ArrayList<LPTerm> terms = null;
	public ArrayList<LPMatchedSubstring> matchedSubstrings = null;
	
	// Class
	
	public LPPrediction()
	{
		
	}
	
	public LPPrediction(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("name")) {
				this.name = jsonObject.getString("name");
			}
			
			if (jsonObject.has("id")) {
				this.ID = jsonObject.getString("id");
			}
				
			if (jsonObject.has("number")) {
				this.number = jsonObject.getInt("number");
			}
			
			if (jsonObject.has("reference")) {
				this.reference = jsonObject.getString("reference");
			}
			
			if (jsonObject.has("types")) {
				this.types = new ArrayList<String>();
				JSONArray types = jsonObject.getJSONArray("types");
				
				for (int i=0; i<types.length(); i++) {
					String type = types.getString(i);
					
					this.types.add(type);
				}
			}
			
			if (jsonObject.has("matched_substrings")) {
				this.matchedSubstrings = new ArrayList<LPMatchedSubstring>();
				JSONArray matchedSubstrings = jsonObject.getJSONArray("matched_substrings");
				
				for (int i=0; i<matchedSubstrings.length(); i++) {
					LPMatchedSubstring matchedSubstring = new LPMatchedSubstring(matchedSubstrings.getJSONObject(i));
					
					this.matchedSubstrings.add(matchedSubstring);
				}
			}
			
			if (jsonObject.has("terms")) {
				this.terms = new ArrayList<LPTerm>();
				JSONArray terms = jsonObject.getJSONArray("terms");
				
				for (int i=0; i<terms.length(); i++) {
					LPTerm term = new LPTerm(terms.getJSONObject(i));
					
					this.terms.add(term);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPPrediction clone()
	{
		LPPrediction object = new LPPrediction();
    	
        try {
        	object.name = this.name;
        	object.ID = this.ID;
        	object.number = this.number;
        	object.reference = this.reference;
        	object.types = this.types;
        	object.terms = this.terms;
        	object.matchedSubstrings = this.matchedSubstrings;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}	
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.name != null) {
				object.put("name", this.name);
			}
			
			if (this.ID != null) {
				object.put("id", this.ID);
			}
			
			object.put("number", this.number);
			
			if (this.reference != null) {
				object.put("reference", this.reference);
			}
			
			if (this.types != null) {
				object.put("types", new JSONArray(this.types));
			}
			
			if (this.matchedSubstrings != null) {
				JSONArray matchedSubstringsArray = new JSONArray();
				
				for (int i=0; i<this.matchedSubstrings.size(); i++) {
					JSONObject substring = this.matchedSubstrings.get(i).getJSONObject();
					
					matchedSubstringsArray.put(substring);
				}
				
				object.put("matched_substrings", matchedSubstringsArray);
			}
			
			if (this.terms != null) {
				JSONArray termsArray = new JSONArray();
				
				for (int i=0; i<this.terms.size(); i++) {
					JSONObject term = this.terms.get(i).getJSONObject();
					
					termsArray.put(term);
				}
				
				object.put("terms", termsArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
	
	// Functions
	
	public static LPGooglePlaceType getGooglePlaceTypeFromString(String type)
	{
	    if (type.equals(PLACE_TYPE_GEOCODE)) {
	        return LPGooglePlaceType.LPGooglePlaceTypeGeocode;
	    } else if (type.equals(PLACE_TYPE_ESTABLISHMENT)) {
	        return LPGooglePlaceType.LPGooglePlaceTypeEstablishment;
	    } else if (type.equals(PLACE_TYPE_LOCALITY)) {
	        return LPGooglePlaceType.LPGooglePlaceTypeLocality;
	    } else if (type.equals(PLACE_TYPE_SUBLOCALITY)) {
	        return LPGooglePlaceType.LPGooglePlaceTypeSublocality;
	    } else if (type.equals(PLACE_TYPE_POSTAL_CODE)) {
	        return LPGooglePlaceType.LPGooglePlaceTypePostalCode;
	    } else if (type.equals(PLACE_TYPE_COUNTRY)) {
	        return LPGooglePlaceType.LPGooglePlaceTypeCountry;
	    } else if (type.equals(PLACE_TYPE_ADMINISTRATIVE_AREA1)) {
	        return LPGooglePlaceType.LPGooglePlaceTypeAdministrativeArea1;
	    } else if (type.equals(PLACE_TYPE_ADMINISTRATIVE_AREA2)) {
	        return LPGooglePlaceType.LPGooglePlaceTypeAdministrativeArea2;
	    } else if (type.equals(PLACE_TYPE_ADMINISTRATIVE_AREA3)) {
	        return LPGooglePlaceType.LPGooglePlaceTypeAdministrativeArea3;
	    } else {
	        return LPGooglePlaceType.LPGooglePlaceTypeUnknown;
	    }
	}

	public static String getStringFromGooglePlaceType(LPGooglePlaceType type)
	{
	    if (type==LPGooglePlaceType.LPGooglePlaceTypeGeocode) {
	        return PLACE_TYPE_GEOCODE;
	    } else if (type.equals(LPGooglePlaceType.LPGooglePlaceTypeEstablishment)) {
	        return PLACE_TYPE_ESTABLISHMENT;
	    } else if (type.equals(LPGooglePlaceType.LPGooglePlaceTypeLocality)) {
	        return PLACE_TYPE_LOCALITY;
	    } else if (type.equals(LPGooglePlaceType.LPGooglePlaceTypeSublocality)) {
	        return PLACE_TYPE_SUBLOCALITY;
	    } else if (type.equals(LPGooglePlaceType.LPGooglePlaceTypePostalCode)) {
	        return PLACE_TYPE_POSTAL_CODE;
	    } else if (type.equals(LPGooglePlaceType.LPGooglePlaceTypeCountry)) {
	        return PLACE_TYPE_COUNTRY;
	    } else if (type.equals(LPGooglePlaceType.LPGooglePlaceTypeAdministrativeArea1)) {
	        return PLACE_TYPE_ADMINISTRATIVE_AREA1;
	    } else if (type.equals(LPGooglePlaceType.LPGooglePlaceTypeAdministrativeArea2)) {
	        return PLACE_TYPE_ADMINISTRATIVE_AREA2;
	    } else if (type.equals(LPGooglePlaceType.LPGooglePlaceTypeAdministrativeArea3)) {
	        return PLACE_TYPE_ADMINISTRATIVE_AREA3;
	    } else {
	        return "";
	    }
	}
}
