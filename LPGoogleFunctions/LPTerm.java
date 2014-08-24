/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPTerm {

	// Variable
	
	public String value = null;
	public int offset = 0;
	
	// Class
	
	public LPTerm()
	{
		
	}
	
	public LPTerm(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("value")) {
				this.value = jsonObject.getString("value");
			}
			
			if (jsonObject.has("offset")) {
				this.offset = jsonObject.getInt("offset");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPTerm clone()
	{
		LPTerm object = new LPTerm();
    	
        try {
        	object.value = this.value;
        	object.offset = this.offset;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			object.put("value", this.value);
			object.put("offset", this.offset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
