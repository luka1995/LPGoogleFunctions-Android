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

public class LPPlacesAutocomplete {

	// Variables
	
	public ArrayList<LPPrediction> predictions = null;
	public String statusCode = null;
	
	// Class
	
	public LPPlacesAutocomplete()
	{
		
	}
	
	public LPPlacesAutocomplete(JSONObject jsonObject)
	{
		try {
			if(jsonObject.has("predictions"))
			{
				this.predictions = new ArrayList<LPPrediction>();
				JSONArray predictions = jsonObject.getJSONArray("predictions");
				for(int i=0; i<predictions.length(); i++)
				{
					LPPrediction prediction = new LPPrediction(predictions.getJSONObject(i));
					
					this.predictions.add(prediction);
				}
			}
			
			if(jsonObject.has("status"))
			{
				this.statusCode = jsonObject.getString("status");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPPlacesAutocomplete clone()
	{
        try {
        	LPPlacesAutocomplete object = new LPPlacesAutocomplete();
        	
        	object.predictions = this.predictions;
        	object.statusCode = this.statusCode;

        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
}
