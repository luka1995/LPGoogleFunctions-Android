/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPTime {

	// Variables
	
	public String text = null;
	public String timeZone = null;
	public float value = 0;

	// Class
	
	public LPTime()
	{
		
	}

	public LPTime(JSONObject jsonObject) 
	{
		try {
			if (jsonObject.has("text")) {
				this.text = jsonObject.getString("text");
			}
			
			if (jsonObject.has("time_zone")) {
				this.timeZone = jsonObject.getString("time_zone");
			}
			
			if (jsonObject.has("value")) {
				this.value = (float)jsonObject.getDouble("value");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPTime clone()
	{
		LPTime object = new LPTime();
    	
        try {
        	object.text = this.text;
        	object.timeZone = this.timeZone;
        	object.value = this.value;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.text != null) {
				object.put("text", this.text);
			}
			
			if (this.timeZone != null) {
				object.put("time_zone", this.timeZone);
			}
			
			object.put("value", this.value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
