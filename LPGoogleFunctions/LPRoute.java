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

public class LPRoute {

	// Variables
	
	public int number = 0;
	public LPBounds bounds = null;
	public String copyrights = null;
	public ArrayList<LPLeg> legs = null;
	public LPPolyline overviewPolyline = null;
	public String summary = null;
	public ArrayList<LPWaypoint> waypoints = null;
	public ArrayList<String> warnings = null;
	
	// Class
	
	public LPRoute()
	{
		
	}

	public LPRoute(JSONObject jsonObject)
	{
		try {
			if(jsonObject.has("warnings"))
			{
				this.warnings = new ArrayList<String>();
				JSONArray warnings = jsonObject.getJSONArray("warnings");
				for(int i=0; i<warnings.length(); i++)
				{
					String warning = warnings.getString(i);
					
					this.warnings.add(warning);
				}
			}
			
			if(jsonObject.has("bounds"))
			{
				this.bounds = new LPBounds(jsonObject.getJSONObject("bounds"));
			}
			
			if(jsonObject.has("copyrights"))
			{
				this.copyrights = jsonObject.getString("copyrights");
			}
			
			if(jsonObject.has("legs"))
			{
				this.legs = new ArrayList<LPLeg>();
				JSONArray legs = jsonObject.getJSONArray("legs");
				for(int i=0; i<legs.length(); i++)
				{
					LPLeg leg = new LPLeg(legs.getJSONObject(i));

					this.legs.add(leg);
				}
			}
			
			if(jsonObject.has("overview_polyline"))
			{
				this.overviewPolyline = new LPPolyline(jsonObject.getJSONObject("overview_polyline"));
			}
			
			if(jsonObject.has("summary"))
			{
				this.summary = jsonObject.getString("summary");
			}
			
			if(jsonObject.has("via_waypoint"))
			{
		        this.waypoints = new ArrayList<LPWaypoint>();
				JSONArray waypoints = jsonObject.getJSONArray("via_waypoint");
				for(int i=0; i<waypoints.length(); i++)
				{
					LPWaypoint waypoint = new LPWaypoint(waypoints.getJSONObject(i));

					this.waypoints.add(waypoint);
				}
			}
			
			if(jsonObject.has("number"))
			{
				this.number = jsonObject.getInt("number");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPRoute clone()
	{
		LPRoute object = new LPRoute();
    	
        try {
        	object.number = this.number;
        	object.bounds = this.bounds;
        	object.copyrights = this.copyrights;
        	object.legs = this.legs;
        	object.overviewPolyline = this.overviewPolyline;
        	object.summary = this.summary;
        	object.waypoints = this.waypoints;
        	object.warnings = this.warnings;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if(this.warnings != null)
			{
				JSONArray warningsArray = new JSONArray();
				for(int i=0; i<this.warnings.size(); i++)
				{
					String warning = this.warnings.get(i).toString();
					
					warningsArray.put(warning);
				}
				object.put("warnings", warningsArray);
			}
				
			if(this.bounds != null)
			{
				object.put("bounds", this.bounds.getJSONObject());
			}
			
			if(this.copyrights != null)
			{
				object.put("copyrights", this.copyrights);
			}
			
			if(this.legs != null)
			{
				JSONArray legsArray = new JSONArray();
				for(int i=0; i<this.legs.size(); i++)
				{
					JSONObject leg = this.legs.get(i).getJSONObject();
					
					legsArray.put(leg);
				}
				object.put("legs", legsArray);
			}
			
			if(this.overviewPolyline != null)
			{
				object.put("overview_polyline", this.overviewPolyline.getJSONObject());
			}
			
			if(this.summary != null)
			{
				object.put("summary", this.summary);
			}
			
			if(this.waypoints != null)
			{
				JSONArray waypointsArray = new JSONArray();
				for(int i=0; i<this.waypoints.size(); i++)
				{
					JSONObject waypoint = this.waypoints.get(i).getJSONObject();
					
					waypointsArray.put(waypoint);
				}
				object.put("via_waypoint", waypointsArray);
			}
			
			object.put("number", this.number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
	
	// Functions
	
	public int getRouteDuration()
	{
	    int value = 0;
	    
	    if(this.legs != null)
	    {
		    for(int a=0; a<this.legs.size(); a++)
		    {
		        LPLeg leg = this.legs.get(a);

		        if(leg != null)
		        {
			        for(int b=0; b<leg.steps.size(); b++)
			        {
			            LPStep step = leg.steps.get(b);
			            
			            if(step != null)
			            {
				            value += step.duration.value;
				            
				            if(step.subSteps != null)
				            {
				            	for(int c=0; c<step.subSteps.size(); c++)
					            {
					                LPStep substep = step.subSteps.get(c);
					                
					                if(substep != null)
					                {
					                	value += substep.duration.value;
					                }
					            }
				            }
			            }
			        }
		        }
		    }
	    }
	    
	    return value;
	}

	public int getRouteDistance()
	{
	    int value = 0;
	    
	    if(this.legs != null)
	    {
		    for(int a=0; a<this.legs.size(); a++)
		    {
		        LPLeg leg = this.legs.get(a);
		        
		        if(leg != null)
		        {
			        for(int b=0; b<leg.steps.size(); b++)
			        {
			            LPStep step = leg.steps.get(b);
			            
			            if(step != null)
			            {
			            	value += step.distance.value;
				            
			            	if(step.subSteps != null)
			            	{
					            for(int c=0; c<step.subSteps.size(); c++)
					            {
					                LPStep substep = step.subSteps.get(c);
					                
					                if(substep != null)
					                {
					                	value += substep.distance.value;
					                }
					            }
			            	}
			            }
			        }
		        }
		    }
	    }
	    
	    return value;
	}
}
