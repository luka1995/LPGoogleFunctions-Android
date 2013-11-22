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

import LPGoogleFunctions.LPStep.LPGoogleDirectionsTravelMode;
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
	public LPGoogleDirectionsTravelMode requestTravelMode = LPGoogleDirectionsTravelMode.bicycling;
	
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
        	object.requestTravelMode = this.requestTravelMode;
        	
        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if(this.routes != null)
			{
				JSONArray routesArray = new JSONArray();
				for(int i=0; i<this.routes.size(); i++)
				{
					JSONObject route = this.routes.get(i).getJSONObject();
					
					routesArray.put(route);
				}
				object.put("routes", routesArray);
			}
			
			if(this.statusCode != null)
			{
				object.put("status", this.statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
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
