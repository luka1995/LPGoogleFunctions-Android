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

public class LPPlacePhoto {

	// Variables
	
	public ArrayList<String> htmlAttributions = null;
	public int height = 0;
	public int width = 0;
	public String photoReference = null;
	
	// Class
	
	public LPPlacePhoto()
	{

	}
	
	public LPPlacePhoto(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("html_attributions")) {
				this.htmlAttributions = new ArrayList<String>();
				JSONArray htmlAttributions = jsonObject.getJSONArray("html_attributions");
				
				for (int i=0; i<htmlAttributions.length(); i++) {
					String att = htmlAttributions.getString(i);
					
					this.htmlAttributions.add(att);
				}
			}
			
			if (jsonObject.has("height")) {
				this.height = jsonObject.getInt("height");
			}
			
			if (jsonObject.has("width")) {
				this.width = jsonObject.getInt("width");
			}
			
			if (jsonObject.has("photo_reference")) {
				this.photoReference = jsonObject.getString("photo_reference");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPPlacePhoto clone()
	{
		LPPlacePhoto object = new LPPlacePhoto();
    	
        try {
        	object.htmlAttributions = this.htmlAttributions;
        	object.height = this.height;
        	object.width = this.width;
        	object.photoReference = this.photoReference;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.htmlAttributions != null) {
				object.put("html_attributions", new JSONArray(this.htmlAttributions));
			}
			
			object.put("height", this.height);
			object.put("width", this.width);
			
			if (this.photoReference != null) {
				object.put("photo_reference", this.photoReference);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
