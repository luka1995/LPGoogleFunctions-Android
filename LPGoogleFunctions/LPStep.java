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
		LPGoogleDirectionsTravelModeDriving,
		LPGoogleDirectionsTravelModeWalking,
		LPGoogleDirectionsTravelModeBicycling,
		LPGoogleDirectionsTravelModeTransit
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
        try {
        	LPStep object = new LPStep();
        	
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

        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
	
	// Functions
	
	public static LPGoogleDirectionsTravelMode getDirectionsTravelModeFromString(String string)
	{
	    if(string.equals(googleTravelModeDriving) || string.equals(googleTravelModeDriving))
	    {
	        return LPGoogleDirectionsTravelMode.LPGoogleDirectionsTravelModeDriving;
	    } else if(string.equals(googleTravelModeBicycling) || string.equals(googleTravelModeBicycling)) {
	        return LPGoogleDirectionsTravelMode.LPGoogleDirectionsTravelModeBicycling;
	    } else if(string.equals(googleTravelModeTransit) || string.equals(googleTravelModeTransit)) {
	        return LPGoogleDirectionsTravelMode.LPGoogleDirectionsTravelModeTransit;
	    } else {
	        return LPGoogleDirectionsTravelMode.LPGoogleDirectionsTravelModeWalking;
	    }
	}
	
	public static String getDirectionsTravelMode(LPGoogleDirectionsTravelMode travelMode)
	{
	    switch (travelMode) {
        case LPGoogleDirectionsTravelModeDriving:
            return googleTravelModeDriving;
        case LPGoogleDirectionsTravelModeBicycling:
            return googleTravelModeBicycling;
        case LPGoogleDirectionsTravelModeTransit:
            return googleTravelModeTransit;
        default:
            return googleTravelModeWalking;
	    }
	}
}
