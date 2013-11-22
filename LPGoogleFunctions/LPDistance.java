/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPDistance {

	// Variables
	
	public String text = null;
	public int value = 0;
	
	// Class
	
	public LPDistance()
	{
		
	}
	
	public LPDistance(JSONObject jsonObject)
	{
		try {
			if(jsonObject.has("text"))
			{
				this.text = jsonObject.getString("text");
			}
			
			if(jsonObject.has("value"))
			{
				this.value = jsonObject.getInt("value");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPDistance clone()
	{
		LPDistance object = new LPDistance();
    	
        try {
        	object.text = this.text;
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
			if(this.text != null)
			{
				object.put("text", this.text);
			}
			
			object.put("value", this.value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
