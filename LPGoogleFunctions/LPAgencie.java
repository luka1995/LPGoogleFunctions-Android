/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPAgencie {

	// Variables
	
	public String name = null;
	public String URL = null;
	
	// Class
	
	public LPAgencie()
	{

	}
	
	public LPAgencie(JSONObject jsonObject) 
	{
		try {
			if(jsonObject.has("name"))
			{
				this.name = jsonObject.getString("name");
			}
			
			if(jsonObject.has("url"))
			{
				this.URL = jsonObject.getString("url");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPAgencie clone()
	{
		LPAgencie object = new LPAgencie();
    	
        try {
        	object.name = this.name;
        	object.URL = this.URL;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if(this.name != null)
			{
				object.put("name", this.name);
			}
			
			if(this.URL != null)
			{
				object.put("url", this.URL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
