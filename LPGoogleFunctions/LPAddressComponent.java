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
			if(jsonObject.has("long_name"))
			{
				this.longName = jsonObject.getString("long_name");
			}
			
			if(jsonObject.has("short_name"))
			{
				this.shortName = jsonObject.getString("short_name");
			}
			
			if(jsonObject.has("types"))
			{
				this.types = new ArrayList<String>();
				JSONArray types = jsonObject.getJSONArray("types");
				for(int i=0; i<types.length(); i++)
				{
					String type = types.getString(i);
					
					this.types.add(type);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPAddressComponent clone()
	{
        try {
        	LPAddressComponent object = new LPAddressComponent();
        	
        	object.longName = this.longName;
        	object.shortName = this.shortName;
        	object.types = this.types;
        	
        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
}
