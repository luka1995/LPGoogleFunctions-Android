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

import LPGoogleFunctions.LPStep.*;

public class LPDirections {

	// Variables
	
	public static enum LPGoogleDirectionsAvoid {
	    LPGoogleDirectionsAvoidNone,
	    LPGoogleDirectionsAvoidTolls,
	    LPGoogleDirectionsAvoidHighways
	};

	public static enum LPGoogleDirectionsUnit {
	    LPGoogleDirectionsUnitMetric,
	    LPGoogleDirectionsUnitImperial
	};
	
	public static String googleDirectionsAvoidHighways = "highways";
	public static String googleDirectionsAvoidTolls = "avoid";
	
	public static String googleDirectionsUnitMetric = "metric";
	public static String googleDirectionsUnitImperial = "imperial";
		
	public ArrayList<LPRoute> routes = null;
	public String statusCode = null;
	public LPGoogleDirectionsTravelMode requestTravelMode = LPGoogleDirectionsTravelMode.LPGoogleDirectionsTravelModeBicycling;
	
	// Class
	
	public LPDirections()
	{
		
	}
	
	public LPDirections(JSONObject jsonObject)
	{
		try {
			if(jsonObject.has("routes"))
			{
				this.routes = new ArrayList<LPRoute>();
				JSONArray routes = jsonObject.getJSONArray("routes");
				for(int i=0; i<routes.length(); i++)
				{
					LPRoute route = new LPRoute(routes.getJSONObject(i));
					route.number = i;
					
					this.routes.add(route);
				}
			}
			
			if(jsonObject.has("status"))
			{
				this.statusCode = jsonObject.getString("status");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPDirections clone()
	{
        try {
        	LPDirections object = new LPDirections();
        	
        	object.routes = this.routes;
        	object.statusCode = this.statusCode;

        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
	
	// Functions
	
	public static String getDirectionsAvoid(LPGoogleDirectionsAvoid avoid)
	{    
	    switch (avoid) {
	        case LPGoogleDirectionsAvoidHighways:
	            return googleDirectionsAvoidHighways;
	        case LPGoogleDirectionsAvoidTolls:
	            return googleDirectionsAvoidTolls;
	        default:
	            return "";
	    }
	}

	public static String getDirectionsUnit(LPGoogleDirectionsUnit unit)
	{
	    switch (unit) {
	        case LPGoogleDirectionsUnitImperial:
	            return "imperial";
	        default:
	            return googleDirectionsUnitMetric;
	    }
	}
}
