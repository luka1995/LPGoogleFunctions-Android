/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPLocation {

	// Variables
	
	public double latitude = 0;
	public double longitude = 0;
	
	// Class
	
	public LPLocation()
	{
		
	}
	
	public LPLocation(double latitude, double longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public LPLocation(JSONObject jsonObject)
	{
		try {
			if(jsonObject.has("lat"))
			{
				this.latitude = jsonObject.getDouble("lat");
			}
			
			if(jsonObject.has("lng"))
			{
				this.longitude = jsonObject.getDouble("lng");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPLocation clone()
	{
        try {
        	LPLocation object = new LPLocation();
        	
        	object.latitude = this.latitude;
        	object.longitude = this.longitude;
        	
        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
}
