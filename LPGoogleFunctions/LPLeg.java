/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LPLeg {

	// Variables
	
	public LPTime arrivalTime = null;
	public LPTime departureTime = null;
	public LPDistance distance = null;
	public LPDuration duration = null;
	public String endAddress = null;
	public LPLocation endLocation = null;
	public String startAddress = null;
	public LPLocation startLocation = null;
	public ArrayList<LPStep> steps = null;

	// Class
	
	public LPLeg()
	{
		
	}
	
	public LPLeg(JSONObject jsonObject) 
	{
		try {
			if(jsonObject.has("arrival_time"))
			{
				this.arrivalTime = new LPTime(jsonObject.getJSONObject("arrival_time"));
			}
			
			if(jsonObject.has("departure_time"))
			{
				this.departureTime = new LPTime(jsonObject.getJSONObject("departure_time"));
			}
			
			if(jsonObject.has("distance"))
			{
				this.distance = new LPDistance(jsonObject.getJSONObject("distance"));
			}

			if(jsonObject.has("duration"))
			{
				this.duration = new LPDuration(jsonObject.getJSONObject("duration"));
			}
			
			if(jsonObject.has("end_address"))
			{
				this.endAddress = jsonObject.getString("end_address");
			}

			if(jsonObject.has("end_location"))
			{
				this.endLocation = new LPLocation(jsonObject.getJSONObject("end_location"));
			}
			
			if(jsonObject.has("start_address"))
			{
				this.startAddress = jsonObject.getString("start_address");
			}
			
			if(jsonObject.has("start_location"))
			{
				this.startLocation = new LPLocation(jsonObject.getJSONObject("start_location"));
			}
			
			if(jsonObject.has("steps"))
			{
				this.steps = new ArrayList<LPStep>();
				JSONArray stepsArray = jsonObject.getJSONArray("steps");
				for(int i=0; i<stepsArray.length(); i++)
				{
					LPStep step = new LPStep(stepsArray.getJSONObject(i));
					
					this.steps.add(step);
				}
			}	        
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPLeg clone()
	{
		LPLeg object = new LPLeg();
    	
        try {
        	object.arrivalTime = this.arrivalTime;
        	object.departureTime = this.departureTime;
        	object.distance = this.distance;
        	object.duration = this.duration;
        	object.endAddress = this.endAddress;
        	object.endLocation = this.endLocation;
        	object.startAddress = this.startAddress;
        	object.startLocation = this.startLocation;
        	object.steps = this.steps;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if(this.arrivalTime != null)
			{
				object.put("arrival_time", this.arrivalTime.getJSONObject());
			}
			
			if(this.departureTime != null)
			{
				object.put("departure_time", this.departureTime.getJSONObject());
			}
			
			if(this.distance != null)
			{
				object.put("distance", this.distance.getJSONObject());
			}
			
			if(this.duration != null)
			{
				object.put("duration", this.duration.getJSONObject());
			}
			
			if(this.endAddress != null)
			{
				object.put("end_address", this.endAddress);
			}
			
			if(this.endLocation != null)
			{
				object.put("end_location", this.endLocation.getJSONObject());
			}
			
			if(this.startAddress != null)
			{
				object.put("start_address", this.startAddress);
			}
			
			if(this.startLocation != null)
			{
				object.put("start_location", this.startLocation.getJSONObject());
			}
			
			if(this.steps != null)
			{
				JSONArray stepsArray = new JSONArray();
				for(int i=0; i<this.steps.size(); i++)
				{
					JSONObject step = this.steps.get(i).getJSONObject();
					
					stepsArray.put(step);
				}
				object.put("steps", stepsArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
