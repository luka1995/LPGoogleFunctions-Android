/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPGeometry {

	// Variables
	
	public LPLocation location = null;
	public LPBounds viewport = null;
	
	// Class
	
	public LPGeometry()
	{

	}
	
	public LPGeometry(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("location")) {
				this.location = new LPLocation(jsonObject.getJSONObject("location"));
			}
			
			if (jsonObject.has("viewport")) {
				this.viewport = new LPBounds(jsonObject.getJSONObject("viewport"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPGeometry clone()
	{
		LPGeometry object = new LPGeometry();
    	
        try {
        	object.location = this.location;
        	object.viewport = this.viewport;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.location != null) {
				object.put("location", this.location.getJSONObject());
			}
			
			if (this.viewport != null) {
				object.put("viewport", this.viewport.getJSONObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
