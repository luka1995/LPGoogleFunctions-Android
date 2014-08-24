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

public class LPPlaceDetails {

	// Variables
	
	public static enum LPGooglePriceLevel {
	    LPGooglePriceLevelFree,
	    LPGooglePriceLevelInexpensive,
	    LPGooglePriceLevelModerate,
	    LPGooglePriceLevelExpensive,
	    LPGooglePriceLevelVeryExpensive
	};
	
	public ArrayList<LPAddressComponent> addressComponents = null;
	public String adrAddress = null;
	public String formattedAddress = null;
	public LPGeometry geometry = null;
	public String icon = null;
	public String ID = null;
	public String name = null;
	public String reference = null;
	public ArrayList<String> types = null;
	public String URL = null;
	public String vicinity = null;
	public ArrayList<LPPlacePhoto> photos = null;
	public int priceLevel = 0;
	public float rating = 0;
	public ArrayList<LPEvent> events = null;
	
	// Class
	
	public LPPlaceDetails()
	{
		
	}
	
	public LPPlaceDetails(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("address_components")) {
				this.addressComponents = new ArrayList<LPAddressComponent>();
				JSONArray components = jsonObject.getJSONArray("address_components");

				for (int i=0; i<components.length(); i++) {
					LPAddressComponent component = new LPAddressComponent(components.getJSONObject(i));
					
					this.addressComponents.add(component);
				}
			}
			
			if (jsonObject.has("adr_address")) {
				this.adrAddress = jsonObject.getString("adr_address");
			}
			
			if (jsonObject.has("formatted_address")) {
				this.formattedAddress = jsonObject.getString("formatted_address");
			}
			
			if (jsonObject.has("geometry")) {
				this.geometry = new LPGeometry(jsonObject.getJSONObject("geometry"));
			}
			
			if (jsonObject.has("icon")) {
				this.icon = jsonObject.getString("icon");
			}
			
			if (jsonObject.has("id")) {
				this.ID = jsonObject.getString("id");
			}
			
			if (jsonObject.has("name")) {
				this.name = jsonObject.getString("name");
			}
			
			if (jsonObject.has("reference")) {
				this.reference = jsonObject.getString("reference");
			}
			
			if (jsonObject.has("types")) {
				this.types = new ArrayList<String>();
				JSONArray types = jsonObject.getJSONArray("types");
				
				for (int i=0; i<types.length(); i++) {
					String type = types.getString(i);
					
					this.types.add(type);
				}
			}
			
			if (jsonObject.has("url")) {
				this.URL = jsonObject.getString("url");
			}
			
			if (jsonObject.has("vicinity")) {
				this.vicinity = jsonObject.getString("vicinity");
			}
			
			if (jsonObject.has("photos")) {
				this.photos = new ArrayList<LPPlacePhoto>();
				JSONArray photos = jsonObject.getJSONArray("photos");
				
				for (int i=0; i<photos.length(); i++) {
					LPPlacePhoto photo = new LPPlacePhoto(photos.getJSONObject(i));
					
					this.photos.add(photo);
				}
			}
			
			if (jsonObject.has("price_level")) {
				this.priceLevel = jsonObject.getInt("price_level");
			}
			
			if (jsonObject.has("rating")) {
				this.rating = (float)jsonObject.getDouble("rating");
			}
			
			if (jsonObject.has("events")) {
				this.events = new ArrayList<LPEvent>();
				JSONArray events = jsonObject.getJSONArray("events");
				
				for (int i=0; i<events.length(); i++) {
					LPEvent event = new LPEvent(events.getJSONObject(i));
					
					this.events.add(event);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPPlaceDetails clone()
	{
		LPPlaceDetails object = new LPPlaceDetails();
    	
        try {
        	object.addressComponents = this.addressComponents;
        	object.adrAddress = this.adrAddress;
        	object.formattedAddress = this.formattedAddress;
        	object.geometry = this.geometry;
        	object.icon = this.icon;
        	object.ID = this.ID;
        	object.name = this.name;
        	object.reference = this.reference;
        	object.types = this.types;
        	object.URL = this.URL;
        	object.vicinity = this.vicinity;
        	object.photos = this.photos;
        	object.priceLevel = this.priceLevel;
        	object.rating = this.rating;
        	object.events = this.events;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.addressComponents != null) {
				JSONArray addressComponentsArray = new JSONArray();
				
				for (int i=0; i<this.addressComponents.size(); i++) {
					JSONObject component = this.addressComponents.get(i).getJSONObject();
					
					addressComponentsArray.put(component);
				}
				
				object.put("address_components", addressComponentsArray);
			}
			
			object.put("adr_address", this.adrAddress);
			object.put("formatted_address", this.formattedAddress);
			if(this.geometry != null) object.put("geometry", this.geometry.getJSONObject());
			object.put("icon", this.icon);
			object.put("id", this.ID);
			object.put("name", this.name);
			object.put("reference", this.reference);
			
			if (this.types != null) {
				JSONArray typesArray = new JSONArray();
				
				for (int i=0; i<this.types.size(); i++) {
					String string = this.types.get(i).toString();
					
					typesArray.put(string);
				}
				
				object.put("types", typesArray);
			}
			
			if (this.URL != null) {
				object.put("url", this.URL);
			}
			
			if (this.vicinity != null) {
				object.put("vicinity", this.vicinity);
			}
			
			if (this.photos != null) {
				JSONArray photosArray = new JSONArray();
				
				for (int i=0; i<this.photos.size(); i++) {
					JSONObject photo = this.photos.get(i).getJSONObject();
					
					photosArray.put(photo);
				}
				
				object.put("photos", photosArray);
			}
			
			object.put("price_level", this.priceLevel);
			
			object.put("rating", this.rating);
			
			if (this.events != null) {
				JSONArray eventsArray = new JSONArray();
				
				for (int i=0; i<this.events.size(); i++) {
					JSONObject event = this.events.get(i).getJSONObject();
					
					eventsArray.put(event);
				}
				
				object.put("events", eventsArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
