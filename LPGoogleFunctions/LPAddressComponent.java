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

public class LPAddressComponent {

	// Variables
	
	public String longName;
	public String shortName;
	public ArrayList<String> types;
	
	// Class
	
	public LPAddressComponent()
	{

	}
	
	public LPAddressComponent(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("long_name")) {
				this.longName = jsonObject.getString("long_name");
			}
			
			if (jsonObject.has("short_name")) {
				this.shortName = jsonObject.getString("short_name");
			}
			
			if (jsonObject.has("types")) {
				this.types = new ArrayList<String>();
				JSONArray typesArray = jsonObject.getJSONArray("types");
				
				for (int i=0; i<typesArray.length(); i++) {
					String type = typesArray.getString(i);

					this.types.add(type);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPAddressComponent clone()
	{
		LPAddressComponent object = new LPAddressComponent();
    	
        try {
        	object.longName = this.longName;
        	object.shortName = this.shortName;
        	object.types = this.types;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.longName != null) {
				object.put("long_name", this.longName);
			}
			
			if (this.shortName != null) {
				object.put("short_name", this.shortName);
			}
			
			if (this.types != null) {
				JSONArray typesArray = new JSONArray();
				
				for (int i=0; i<this.types.size(); i++) {
					String string = this.types.get(i).toString();
					
					typesArray.put(string);
				}
				
				object.put("types", typesArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
