/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPTransitDetails {

	// Variables
	
	public LPStop arrivalStop = null;
	public LPTime arrivalTime = null;
	public LPStop departureStop = null;
	public LPTime departureTime = null;
	public String headsign = null;
	public LPLine line = null;
	public int numStops = 0;
	
	// Class
	
	public LPTransitDetails()
	{
		
	}
	
	public LPTransitDetails(JSONObject jsonObject)
	{
		try {
			if(jsonObject.has("arrival_stop"))
			{
				this.arrivalStop = new LPStop(jsonObject.getJSONObject("arrival_stop"));
			}
			
			if(jsonObject.has("arrival_time"))
			{
				this.arrivalTime = new LPTime(jsonObject.getJSONObject("arrival_time"));
			}
			
			if(jsonObject.has("departure_stop"))
			{
				this.departureStop = new LPStop(jsonObject.getJSONObject("departure_stop"));
			}
			
			if(jsonObject.has("departure_time"))
			{
				this.departureTime = new LPTime(jsonObject.getJSONObject("departure_time"));
			}
			
			if(jsonObject.has("headsign"))
			{
				this.headsign = jsonObject.getString("headsign");
			}
			
			if(jsonObject.has("line"))
			{
				this.line = new LPLine(jsonObject.getJSONObject("line"));
			}
			
			if(jsonObject.has("num_stops"))
			{
				this.numStops = jsonObject.getInt("num_stops");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPTransitDetails clone()
	{
        try {
        	LPTransitDetails object = new LPTransitDetails();
        	
        	object.arrivalStop = this.arrivalStop;
        	object.arrivalTime = this.arrivalTime;
        	object.departureStop = this.departureStop;
        	object.departureTime = this.departureTime;
        	object.headsign = this.headsign;
        	object.line = this.line;
        	object.numStops = this.numStops;

        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
}
