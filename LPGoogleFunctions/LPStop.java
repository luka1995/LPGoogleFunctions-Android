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
		LPStop object = new LPStop();
    	
        try {
        	object.location = this.location;
        	object.name = this.name;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if(this.location != null)
			{
				object.put("location", this.location.getJSONObject());
			}
			
			if(this.name != null)
			{
				object.put("name", this.name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
