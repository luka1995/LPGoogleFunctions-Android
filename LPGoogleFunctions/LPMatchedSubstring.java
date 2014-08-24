/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPMatchedSubstring {

	// Variables
	
	public int length = 0;
	public int offset = 0;
	
	// Class
	
	public LPMatchedSubstring()
	{
		
	}
	
	public LPMatchedSubstring(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("length")) {
				this.length = jsonObject.getInt("length");
			}
			
			if (jsonObject.has("offset")) {
				this.offset = jsonObject.getInt("offset");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPMatchedSubstring clone()
	{
		LPMatchedSubstring object = new LPMatchedSubstring();
    	
        try {
        	object.length = this.length;
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
			object.put("length", this.length);
			object.put("offset", this.offset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
