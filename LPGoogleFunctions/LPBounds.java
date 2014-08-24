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
	
	public LPBounds(LPLocation northeast, LPLocation southwest)
	{
		this.northeast = northeast;
		this.southwest = southwest;
	}
	
	public LPBounds(JSONObject jsonObject) 
	{
		try {
			if (jsonObject.has("northeast")) {
				this.northeast = new LPLocation(jsonObject.getJSONObject("northeast"));
			}
			
			if (jsonObject.has("southwest")) {
				this.southwest = new LPLocation(jsonObject.getJSONObject("southwest"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPBounds clone()
	{
		LPBounds object = new LPBounds();
    	
        try {
        	object.northeast = this.northeast;
        	object.southwest = this.southwest;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.northeast != null) {
				object.put("northeast", this.northeast.getJSONObject());
			}
			
			if (this.southwest != null) {
				object.put("southwest", this.southwest.getJSONObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
