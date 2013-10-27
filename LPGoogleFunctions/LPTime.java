/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class LPTime {

	// Variables
	
	public String text = null;
	public String timeZone = null;
	public float value = 0;
	public Date formattedTime = null;
	
	// Class
	
	public LPTime()
	{
		
	}

	public LPTime(JSONObject jsonObject) 
	{
		try {
			if(jsonObject.has("text"))
			{
				this.text = jsonObject.getString("text");
			}
			
			if(jsonObject.has("time_zone"))
			{
				this.timeZone = jsonObject.getString("time_zone");
			}
			
			if(jsonObject.has("value"))
			{
				this.value = (float)jsonObject.getDouble("value");
				
				this.formattedTime = new Date((long)this.value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPTime clone()
	{
        try {
        	LPTime object = new LPTime();
        	
        	object.text = this.text;
        	object.timeZone = this.timeZone;
        	object.value = this.value;
        	object.formattedTime = this.formattedTime;

        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
}
