/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;

public class LPLine {

	// Variables
	
	public ArrayList<LPAgencie> agencies = null;
	public int color = 0;
	public String name = null;
	public String shortName = null;
	public LPVehicle vehicle = null;
	
	// Class
	
	public LPLine()
	{

	}
	
	public LPLine(JSONObject jsonObject)
	{
		try {
			if(jsonObject.has("agencies"))
			{
				this.agencies = new ArrayList<LPAgencie>();
				JSONArray agencies = jsonObject.getJSONArray("agencies");
				for(int i=0; i<agencies.length(); i++)
				{
					LPAgencie agencie = new LPAgencie(agencies.getJSONObject(i));
					
					this.agencies.add(agencie);
				}
			}
			
			try {
				if(jsonObject.has("color"))
				{
					this.color = Color.parseColor(jsonObject.getString("color"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(jsonObject.has("name"))
			{
				this.name = jsonObject.getString("name");
			}
			
			if(jsonObject.has("short_name"))
			{
				this.shortName = jsonObject.getString("short_name");
			}
			
			if(jsonObject.has("vehicle"))
			{
				this.vehicle = new LPVehicle(jsonObject.getJSONObject("vehicle"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPLine clone()
	{
        try {
        	LPLine object = new LPLine();
        	
        	object.agencies = this.agencies;
        	object.color = this.color;
        	object.name = this.name;
        	object.shortName = this.shortName;
        	object.vehicle = this.vehicle;

        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
	
	// Functions
	
	public int getBUSnumber()
	{
	    Scanner scanner = new Scanner(this.shortName).useDelimiter("[^0-9]");
	    
	    int number = scanner.nextInt();
	    
	    return number;
	}
}
