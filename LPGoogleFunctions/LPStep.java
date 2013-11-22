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

public class LPStep {

	// Variables
	
	public static enum LPGoogleDirectionsTravelMode
	{
		driving,
		walking,
		bicycling,
		transit
	};
	
	public String maneuver;
	public LPDistance distance;
	public LPDuration duration;
	public LPLocation endLocation;
	public String htmlInstructions;
	public LPPolyline polyline;
	public LPLocation startLocation;
	public LPGoogleDirectionsTravelMode travelMode;
	public ArrayList<LPStep> subSteps;
	public LPTransitDetails transitDetails;
	public boolean isBicikeLJStationStart;
	public boolean isBicikeLJStationEnd;
	
	public static String googleTravelModeDriving = "driving";
	public static String googleTravelModeBicycling = "bicycling";
	public static String googleTravelModeTransit = "transit";
	public static String googleTravelModeWalking = "walking";
	
	// Class
	
	public LPStep()
	{
		
	}
	
	public LPStep(JSONObject jsonObject)
	{
		try {
			if(jsonObject.has("maneuver"))
			{
				this.maneuver = jsonObject.getString("maneuver");
			}
			
			if(jsonObject.has("distance"))
			{
				this.distance = new LPDistance(jsonObject.getJSONObject("distance"));
			}
			
			if(jsonObject.has("duration"))
			{
				this.duration = new LPDuration(jsonObject.getJSONObject("duration"));
			}
			
			if(jsonObject.has("end_location"))
			{
				this.endLocation = new LPLocation(jsonObject.getJSONObject("end_location"));
			}
			
			if(jsonObject.has("html_instructions"))
			{
				this.htmlInstructions = jsonObject.getString("html_instructions");
			}
			
			if(jsonObject.has("polyline"))
			{
				this.polyline = new LPPolyline(jsonObject.getJSONObject("polyline"));
			}
			
			if(jsonObject.has("start_location"))
			{
				this.startLocation = new LPLocation(jsonObject.getJSONObject("start_location"));
			}

			if(jsonObject.has("travel_mode"))
			{
				this.travelMode = LPStep.getDirectionsTravelModeFromString(jsonObject.getString("travel_mode"));
			}
			
			if(jsonObject.has("steps"))
			{
				this.subSteps = new ArrayList<LPStep>();
				JSONArray subSteps = jsonObject.getJSONArray("steps");
				for(int i=0; i<subSteps.length(); i++)
				{
					LPStep step = new LPStep(subSteps.getJSONObject(i));
					
					this.subSteps.add(step);
				}
			}
			
			if(jsonObject.has("transit_details"))
			{
				this.transitDetails = new LPTransitDetails(jsonObject.getJSONObject("transit_details"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPStep clone()
	{
		LPStep object = new LPStep();
    	
        try {
        	object.maneuver = this.maneuver;
        	object.distance = this.distance;
        	object.duration = this.duration;
        	object.endLocation = this.endLocation;
        	object.htmlInstructions = this.htmlInstructions;
        	object.polyline = this.polyline;
        	object.startLocation = this.startLocation;
        	object.travelMode = this.travelMode;
        	object.subSteps = this.subSteps;
        	object.transitDetails = this.transitDetails;
        	object.isBicikeLJStationStart = this.isBicikeLJStationStart;
        	object.isBicikeLJStationEnd = this.isBicikeLJStationEnd;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if(this.maneuver != null)
			{
				object.put("maneuver", this.maneuver);
			}
			
			if(this.distance != null)
			{
				object.put("distance", this.distance.getJSONObject());
			}
			
			if(this.duration != null)
			{
				object.put("duration", this.duration.getJSONObject());
			}
			
			if(this.endLocation != null)
			{
				object.put("end_location", this.endLocation.getJSONObject());
			}
			
			if(this.htmlInstructions != null)
			{
				object.put("html_instructions", this.htmlInstructions);
			}
			
			if(this.polyline != null)
			{
				object.put("polyline", this.polyline.getJSONObject());
			}
			
			if(this.startLocation != null)
			{
				object.put("start_location", this.startLocation.getJSONObject());
			}
			
			if(this.subSteps != null)
			{
				JSONArray substepsArray = new JSONArray();
				for(int i=0; i<this.subSteps.size(); i++)
				{
					LPStep step = this.subSteps.get(i);
					
					substepsArray.put(step.getJSONObject());
				}
				object.put("steps", substepsArray);
			}
			
			if(this.transitDetails != null)
			{
				object.put("transit_details", this.transitDetails.getJSONObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
	
	// Functions
	
	public static LPGoogleDirectionsTravelMode getDirectionsTravelModeFromString(String string)
	{
	    if(string.equalsIgnoreCase(googleTravelModeDriving))
	    {
	        return LPGoogleDirectionsTravelMode.driving;
	    } else if(string.equalsIgnoreCase(googleTravelModeBicycling)) {
	        return LPGoogleDirectionsTravelMode.bicycling;
	    } else if(string.equalsIgnoreCase(googleTravelModeTransit)) {
	        return LPGoogleDirectionsTravelMode.transit;
	    } else {
	        return LPGoogleDirectionsTravelMode.walking;
	    }
	}
	
	public static String getDirectionsTravelMode(LPGoogleDirectionsTravelMode travelMode)
	{
	    switch (travelMode)
	    {
        	case driving:
        		return googleTravelModeDriving;
        	case bicycling:
        		return googleTravelModeBicycling;
        	case transit:
        		return googleTravelModeTransit;
        	default:
        		return googleTravelModeWalking;
	    }
	}
}
