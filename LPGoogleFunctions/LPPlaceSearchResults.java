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

public class LPPlaceSearchResults {

	// Variables
	
	public ArrayList<LPPlaceDetails> results = null;
	public String statusCode = null;
	public ArrayList<String> htmlAttributions = null;
	public String nextPageToken = null;
	
	// Class
	
	public LPPlaceSearchResults()
	{
		
	}
	
	public LPPlaceSearchResults(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("results")) {
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
			
			if (jsonObject.has("html_attributions")) {
				this.htmlAttributions = new ArrayList<String>();
				JSONArray htmlAttributions = jsonObject.getJSONArray("html_attributions");
				
				for (int i=0; i<htmlAttributions.length(); i++) {
					String att = htmlAttributions.getString(i);
					
					this.htmlAttributions.add(att);
				}
			}
			
			if (jsonObject.has("next_page_token")) {
				this.nextPageToken = jsonObject.getString("next_page_token");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPPlaceSearchResults clone()
	{
		LPPlaceSearchResults object = new LPPlaceSearchResults();
    	
        try {
        	object.results = this.results;
        	object.statusCode = this.statusCode;
        	object.htmlAttributions = this.htmlAttributions;
        	object.nextPageToken = this.nextPageToken;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
}
