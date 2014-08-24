/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPEvent {

	// Variables
	
	public String eventID = null;
	public String summary = null;
	public String URL = null;
	
	// Class
	
	public LPEvent()
	{
		
	}
	
	public LPEvent(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("event_id")) {
				this.eventID = jsonObject.getString("event_id");
			}
			
			if (jsonObject.has("summary")) {
				this.summary = jsonObject.getString("summary");
			}
			
			if (jsonObject.has("url")) {
				this.URL = jsonObject.getString("url");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPEvent clone()
	{
		LPEvent object = new LPEvent();
    	
        try {
        	object.eventID = this.eventID;
        	object.summary = this.summary;
        	object.URL = this.URL;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.eventID != null) {
				object.put("event_id", this.eventID);
			}
			
			if (this.summary != null) {
				object.put("summary", this.summary);
			}
			
			if (this.URL != null) {
				object.put("url", this.URL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
