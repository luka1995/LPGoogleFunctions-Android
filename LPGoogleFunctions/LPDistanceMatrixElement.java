/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPDistanceMatrixElement {

	// Variables

	public LPDistance distance = null;
	public LPDuration duration = null;
	public String statusCode = null;
	
	// Class
	
	public LPDistanceMatrixElement()
	{
		
	}
	
	public LPDistanceMatrixElement(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("distance")) {
				this.distance = new LPDistance(jsonObject.getJSONObject("distance"));
			}
			
			if (jsonObject.has("duration")) {
				this.duration = new LPDuration(jsonObject.getJSONObject("duration"));
			}
			
			if (jsonObject.has("status")) {
				this.statusCode = jsonObject.getString("status");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPDistanceMatrixElement clone()
	{
		LPDistanceMatrixElement object = new LPDistanceMatrixElement();
    	
        try {
        	object.distance = this.distance;
        	object.duration = this.duration;
        	object.statusCode = this.statusCode;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.distance != null) {
				object.put("distance", this.distance.getJSONObject());
			}
			
			if (this.duration != null) {
				object.put("duration", this.duration.getJSONObject());
			}
			
			if (this.statusCode != null) {
				object.put("statusCode", this.statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
