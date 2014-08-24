/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class LPPolyline {

	// Variables
	
	public String pointsString = null;
	public ArrayList<LPLocation> pointsArray = null;
	
	// Class
	
	public LPPolyline()
	{
		
	}
		
	public LPPolyline(JSONObject jsonObject) 
	{
		try {
			if (jsonObject.has("points")) {
				this.pointsString = jsonObject.getString("points");
				
				this.pointsArray = decodePolylineOfGoogleMaps(this.pointsString);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<LPLocation> decodePolylineOfGoogleMaps(String encodedPolyline)
	{
		int length = encodedPolyline.length();
		int index = 0;
		
		ArrayList<LPLocation> points = new ArrayList<LPLocation>();
		
		float lat = 0.0f;
		float lng = 0.0f;

		while (index < length) {
			// Temorary variable to hold each ASCII byte.
		    int b = 0;
		    
		    // The encoded polyline consists of a latitude value followed by a
		    // longitude value. They should always come in pair. Read the
		    // latitude value first.
		    int shift = 0;
		    int result = 0;
		        
		    do {
	            // If index exceded lenght of encoding, finish 'chunk'
	            if (index >= length) { 
	                b = 0;
	            } else {
	                
	                // The '[encodedPolyline characterAtIndex:index++]' statement resturns the ASCII
	                // code for the characted at index. Subtract 63 to get the original
	                // value. (63 was added to ensure proper ASCII characters are displayed
	                // in the encoded plyline string, wich id 'human' readable)
	                b = encodedPolyline.charAt(index++) - 63;
	            }
		            
	            // AND the bits of the byte with 0x1f to get the original 5-bit 'chunk'.
	            // Then left shift the bits by the required amount, wich increases
	            // by 5 bits each time.
	            // OR the value into results, wich sums up the individual 5-bit chunks
	            // into the original value. Since the 5-bit chunks were reserved in
	            // order during encoding, reading them in this way ensures proper
	            // summation.
	            result |= (b & 0x1f) << shift;
	            shift += 5;
		            
		        } while (b >= 0x20); // Continue while the read byte is >= 0x20 since the last 'chunk'
		        // was nor OR'd with 0x20 during the conversion process. (Signals the end).
		        
		        // check if negative, and convert. (All negative values have the last bit set)
		        float dlat;
				if ((result & 1) == 1)
					dlat = ~(result >> 1);
				else
					dlat = (result >> 1);
		        
		        //Compute actual latitude since value is offset from previous value.
		        lat += dlat;
		        
		        // The next value will correspond to the longitude for this point.
		        shift = 0;
		        result = 0;
		        
		        do {
		            // If index exceded lenght of encoding, finish 'chunk'
		            if (index >= length) { 
		                b = 0;
		            } else {
		                b = encodedPolyline.charAt(index++) - 63;
		            }
		            
		            result |= (b & 0x1f) << shift;
		            shift += 5;
		            
		        } while (b >= 0x20);
		        
		        float dlng;
				if ((result & 1) == 1)
					dlng = ~(result >> 1);
				else
					dlng = (result >> 1);
		        lng += dlng;
		        
		        // The actual latitude and longitude values were multiplied by
		        // 1e5 before encoding so that they could be converted to a 32-bit
		        //integer representation. (With a decimal accuracy of 5 places)
		        // Convert back to original value.

		        LPLocation location = new LPLocation((lat * 1e-5),(lng * 1e-5));
		        
		        points.add(location);
		    }
		    
		return points;
	}
	
	public LPPolyline clone()
	{
		LPPolyline object = new LPPolyline();
    	
        try {
        	object.pointsString = this.pointsString;
        	object.pointsArray = this.pointsArray;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.pointsString != null) {
				object.put("points", this.pointsString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
