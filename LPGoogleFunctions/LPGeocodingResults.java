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

public class LPGeocodingResults {

	// Variables
	
	public ArrayList<LPPlaceDetails> results = null;
	public String statusCode = null;
	
	// Class
	
	public LPGeocodingResults()
	{
		
	}
	
	public LPGeocodingResults(JSONObject jsonObject)
	{
		try {
			if(jsonObject.has("results"))
			{
				this.results = new ArrayList<LPPlaceDetails>();
				JSONArray results = jsonObject.getJSONArray("results");
				
				for (int i=0; i<results.length(); i++) {
					LPPlaceDetails placeDetails = new LPPlaceDetails(results.getJSONObject(i));
					
					this.results.add(placeDetails);
				}
			}
			
			if (jsonObject.has("status")) {
				this.statusCode = jsonObject.getString("status");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPGeocodingResults clone()
	{
		LPGeocodingResults object = new LPGeocodingResults();
    	
        try {
        	object.results = this.results;
        	object.statusCode = this.statusCode;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
}
