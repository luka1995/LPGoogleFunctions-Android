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
			if(jsonObject.has("length"))
			{
				this.length = jsonObject.getInt("length");
			}
			
			if(jsonObject.has("offset"))
			{
				this.offset = jsonObject.getInt("offset");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPMatchedSubstring clone()
	{
        try {
        	LPMatchedSubstring object = new LPMatchedSubstring();
        	
        	object.length = this.length;
        	object.offset = this.offset;
        	
        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
}
