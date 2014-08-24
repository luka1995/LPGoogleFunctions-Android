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

public class LPDistanceMatrix {
	
	// Variables
	
	public static enum LPGoogleDistanceMatrixAvoid {
		LPGoogleDistanceMatrixAvoidNone,
	    LPGoogleDistanceMatrixAvoidTolls,
	    LPGoogleDistanceMatrixAvoidHighways
	};
	
	public static enum LPGoogleDistanceMatrixUnit {
		LPGoogleDistanceMatrixUnitMetric,
	    LPGoogleDistanceMatrixUnitImperial
	};
	
	public static enum LPGoogleDistanceMatrixTravelMode {
		LPGoogleDistanceMatrixModeDriving,
	    LPGoogleDistanceMatrixModeWalking,
	    LPGoogleDistanceMatrixModeBicycling
	};
	
	public String statusCode = null;
	public String errorMessage = null;
	public LPGoogleDistanceMatrixTravelMode requestTravelMode = LPGoogleDistanceMatrixTravelMode.LPGoogleDistanceMatrixModeDriving;
	public ArrayList<String> destinationAddresses = null;
	public ArrayList<String> originAddresses = null;
	public ArrayList<ArrayList<LPDistanceMatrixElement>> rows = null;

	public static String googleDistanceMatrixTravelModeDriving = "driving";
	public static String googleDistanceMatrixTravelModeBicycling = "bicycling";
	public static String googleDistanceMatrixTravelModeWalking = "walking";
	
	// Class
		
	public LPDistanceMatrix()
	{
			
	}
		
	public LPDistanceMatrix(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("status")) {
				this.statusCode = jsonObject.getString("status");
			}
			
			if (jsonObject.has("error_message")) {
				this.errorMessage = jsonObject.getString("error_message");
			}
			
			if (jsonObject.has("destination_addresses")) {
				this.destinationAddresses = new ArrayList<String>();
				JSONArray addresses = jsonObject.getJSONArray("destination_addresses");
				
				for (int i=0; i<addresses.length(); i++) {
					String string = addresses.getString(i);

					this.destinationAddresses.add(string);
				}
			}
			
			if (jsonObject.has("origin_addresses")) {
				this.originAddresses = new ArrayList<String>();
				JSONArray addresses = jsonObject.getJSONArray("origin_addresses");
				
				for (int i=0; i<addresses.length(); i++) {
					String string = addresses.getString(i);

					this.originAddresses.add(string);
				}
			}
			
			if (jsonObject.has("rows")) {
				this.rows = new ArrayList<ArrayList<LPDistanceMatrixElement>>();
				JSONArray rows = jsonObject.getJSONArray("rows");
				
				for (int i=0; i<rows.length(); i++) {
					ArrayList<LPDistanceMatrixElement> elements = new ArrayList<LPDistanceMatrixElement>();
					
					for (int a=0; a<jsonObject.getJSONArray("rows").getJSONObject(i).getJSONArray("elements").length(); a++) {
						LPDistanceMatrixElement element = new LPDistanceMatrixElement(jsonObject.getJSONArray("rows").getJSONObject(i).getJSONArray("elements").getJSONObject(a));
						
						elements.add(element);
					}

					this.rows.add(elements);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPDistanceMatrix clone()
	{
		LPDistanceMatrix object = new LPDistanceMatrix();
    	
        try {
        	object.statusCode = this.statusCode;
        	object.errorMessage = this.errorMessage;
        	object.requestTravelMode = this.requestTravelMode;
        	object.destinationAddresses = this.destinationAddresses;
        	object.originAddresses = this.originAddresses;
        	object.rows = this.rows;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.statusCode != null) {
				object.put("statusCode", this.statusCode);
			}

			if (this.errorMessage != null) {
				object.put("errorMessage", this.errorMessage);
			}
			
			if (this.requestTravelMode != null) {
				object.put("requestTravelMode", LPDistanceMatrix.getDistanceMatrixTravelMode(this.requestTravelMode));
			}
			
			if (this.destinationAddresses != null) {
				JSONArray array = new JSONArray();
				
				for (int i=0; i<this.destinationAddresses.size(); i++) {
					String string = this.destinationAddresses.get(i).toString();
					
					array.put(string);
				}
				
				object.put("destinationAddresses", array);
			}
			
			if (this.originAddresses != null) {
				JSONArray array = new JSONArray();
				
				for (int i=0; i<this.originAddresses.size(); i++) {
					String string = this.originAddresses.get(i).toString();
					
					array.put(string);
				}
				
				object.put("originAddresses", array);
			}
			
			if (this.rows != null) {
				JSONArray array = new JSONArray();
				
				for (int i=0; i<this.rows.size(); i++) {
					JSONArray rowsArray = new JSONArray();
					
					for (int a=0; a<this.rows.get(i).size(); a++) {
		                LPDistanceMatrixElement element = this.rows.get(i).get(a);

		                rowsArray.put(element.getJSONObject());
		            }

					array.put(rowsArray);
				}
				
				object.put("rows", array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
	
	// Functions
	
	public static String getDistanceMatrixTravelMode(LPGoogleDistanceMatrixTravelMode travelMode)
	{    
	    switch (travelMode) {
	    	case LPGoogleDistanceMatrixModeDriving:
	    		return googleDistanceMatrixTravelModeDriving;
	        case LPGoogleDistanceMatrixModeBicycling:
	            return googleDistanceMatrixTravelModeBicycling;
	        default:
	            return googleDistanceMatrixTravelModeWalking;
	    }
	}

	public static String getDistanceMatrixAvoid(LPGoogleDistanceMatrixAvoid avoid)
	{
	    switch (avoid) {
	        case LPGoogleDistanceMatrixAvoidHighways:
	            return "highways";
	        case LPGoogleDistanceMatrixAvoidTolls:
	        	return "avoid";
	        default:
	            return "";
	    }
	}
	
	public static String getDistanceMatrixUnit(LPGoogleDistanceMatrixUnit unit)
	{
		switch (unit) {
        	case LPGoogleDistanceMatrixUnitImperial:
        		return "imperial";
        	default:
        		return "metric";
		}
    }
}
