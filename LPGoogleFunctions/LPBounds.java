/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPBounds {

	// Variables
	
	public LPLocation northeast = null;
	public LPLocation southwest = null;

	// Class
	
	public LPBounds()
	{
		
	}
	
	public LPBounds(JSONObject jsonObject) 
	{
		try {
			if(jsonObject.has("northeast"))
			{
				this.northeast = new LPLocation(jsonObject.getJSONObject("northeast"));
			}
			
			if(jsonObject.has("southwest"))
			{
				this.southwest = new LPLocation(jsonObject.getJSONObject("southwest"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPBounds clone()
	{
        try {
        	LPBounds object = new LPBounds();
        	
        	object.northeast = this.northeast;
        	object.southwest = this.southwest;

        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
}
