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

public class LPPlaceDetailsResults {

	// Variables
	
	public ArrayList<String> htmlAttributions = null;
	public LPPlaceDetails result = null;
	public String statusCode = null;
	
	// Class
	
	public LPPlaceDetailsResults()
	{
		
	}
	
	public LPPlaceDetailsResults(JSONObject jsonObject)
	{
		try {
			if(jsonObject.has("html_attributions"))
			{
				this.htmlAttributions = new ArrayList<String>();
				JSONArray htmlAttributions = jsonObject.getJSONArray("html_attributions");
				for(int i=0; i<htmlAttributions.length(); i++)
				{
					String att = htmlAttributions.getString(i);
					
					this.htmlAttributions.add(att);
				}
			}
			
			if(jsonObject.has("result"))
			{
				this.result = new LPPlaceDetails(jsonObject.getJSONObject("result"));
			}
			
			if(jsonObject.has("status"))
			{
				this.statusCode = jsonObject.getString("status");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPPlaceDetailsResults clone()
	{
        try {
        	LPPlaceDetailsResults object = new LPPlaceDetailsResults();
        	
        	object.htmlAttributions = this.htmlAttributions;
        	object.result = this.result;
        	object.statusCode = this.statusCode;

        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
}
