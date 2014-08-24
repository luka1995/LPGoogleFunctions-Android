/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPDuration {

	// Variables
	
	public String text = null;
	public int value = 0;
	
	// Class
	
	public LPDuration()
	{
		
	}
	
	public LPDuration(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("text")) {
				this.text = jsonObject.getString("text");
			}
			
			if (jsonObject.has("value")) {
				this.value = jsonObject.getInt("value");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPDuration clone()
	{
		LPDuration object = new LPDuration();
    	
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
			if (this.text != null) {
				object.put("text", this.text);
			}
			
			object.put("value", this.value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
