/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPStop {

	// Variables
	
	public LPLocation location = null;
	public String name = null;
	
	// Class
	
	public LPStop()
	{
		
	}

	public LPStop(JSONObject jsonObject)
	{
		try {
			if(jsonObject.has("location"))
			{
				this.location = new LPLocation(jsonObject.getJSONObject("location"));
			}
			
			if(jsonObject.has("name"))
			{
				this.name = jsonObject.getString("name");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPStop clone()
	{
        try {
        	LPStop object = new LPStop();
        	
        	object.location = this.location;
        	object.name = this.name;

        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
}
