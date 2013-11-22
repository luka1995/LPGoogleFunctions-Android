/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPWaypoint {

	// Variables
	
	public LPLocation location;
	public int stepIndex = 0;
	public double stepInterpolation = 0;
	
	// Class
	
	public LPWaypoint()
	{
		
	}
	
	public LPWaypoint(JSONObject jsonObject) 
	{
		try {
			if(jsonObject.has("location"))
			{
				this.location = new LPLocation(jsonObject.getJSONObject("location"));
			}
			
			if(jsonObject.has("step_index"))
			{
				this.stepIndex = jsonObject.getInt("step_index");
			}
			
			if(jsonObject.has("step_interpolation"))
			{
				this.stepInterpolation = jsonObject.getDouble("step_interpolation");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPWaypoint clone()
	{
		LPWaypoint object = new LPWaypoint();
    	
        try {
        	object.location = this.location;
        	object.stepIndex = this.stepIndex;
        	object.stepInterpolation = this.stepInterpolation;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if(this.location != null)
			{
				object.put("location", this.location.getJSONObject());
			}
			
			object.put("step_index", this.stepIndex);
			object.put("stepInterpolation", this.stepInterpolation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
