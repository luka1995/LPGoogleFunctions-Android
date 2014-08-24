/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LPVehicle {
	
	// Variables

	public static enum LPGoogleVehicleType
	{
		LPGoogleVehicleTypeRAIL,
		LPGoogleVehicleTypeMETRO_RAIL,
		LPGoogleVehicleTypeSUBWAY,
		LPGoogleVehicleTypeTRAM,
		LPGoogleVehicleTypeMONORAIL,
		LPGoogleVehicleTypeHEAVY_RAIL,
		LPGoogleVehicleTypeCOMMUTER_TRAIN,
		LPGoogleVehicleTypeHIGH_SPEED_TRAIN,
		LPGoogleVehicleTypeBUS,
		LPGoogleVehicleTypeINTERCITY_BUS,
		LPGoogleVehicleTypeTROLLEYBUS,
		LPGoogleVehicleTypeSHARE_TAXI,
		LPGoogleVehicleTypeFERRY,
		LPGoogleVehicleTypeCABLE_CAR,
		LPGoogleVehicleTypeGONDOLA_LIFT,
		LPGoogleVehicleTypeFUNICULAR,
		LPGoogleVehicleTypeOTHER
	};
	
	public static String VEHICLE_TYPE_RAIL = "RAIL";
	public static String VEHICLE_TYPE_METRO_RAIL = "METRO_RAIL";
	public static String VEHICLE_TYPE_SUBWAY = "SUBWAY";
	public static String VEHICLE_TYPE_TRAM = "TRAM";
	public static String VEHICLE_TYPE_MONORAIL = "MONORAIL";
	public static String VEHICLE_TYPE_HEAVY_RAIL = "HEAVY_RAIL";
	public static String VEHICLE_TYPE_COMMUTER_TRAIN = "COMMUTER_TRAIN";
	public static String VEHICLE_TYPE_HIGH_SPEED_TRAIN = "HIGH_SPEED_TRAIN";
	public static String VEHICLE_TYPE_BUS = "BUS";
	public static String VEHICLE_TYPE_INTERCITY_BUS = "INTERCITY_BUS";
	public static String VEHICLE_TYPE_TROLLEYBUS = "TROLLEYBUS";
	public static String VEHICLE_TYPE_SHARE_TAXI = "SHARE_TAXI";
	public static String VEHICLE_TYPE_FERRY = "FERRY";
	public static String VEHICLE_TYPE_CABLE_CAR = "CABLE_CAR";
	public static String VEHICLE_TYPE_GONDOLA_LIFT = "GONDOLA_LIFT";
	public static String VEHICLE_TYPE_FUNICULAR = "FUNICULAR";
	public static String VEHICLE_TYPE_OTHER = "OTHER";
	
	public String icon = null;
	public String name = null;
	public LPGoogleVehicleType type = LPGoogleVehicleType.LPGoogleVehicleTypeOTHER;
	
	// Class
	
	public LPVehicle()
	{
		
	}
	
	public LPVehicle(JSONObject jsonObject)
	{
		try {
			if (jsonObject.has("icon")) {
		        if (jsonObject.getString("icon").startsWith("//")) {
		            this.icon = String.format("http:%s", jsonObject.getString("icon"));
		        } else {
		            this.icon = jsonObject.getString("icon");
		        }
			}
			
			if (jsonObject.has("name")) {
				this.name = jsonObject.getString("name");
			}
			
			if (jsonObject.has("type")) {
				this.type = LPVehicle.getGoogleVehicleTypeFromString(jsonObject.getString("type"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public LPVehicle clone()
	{
		LPVehicle object = new LPVehicle();
    	
        try {
        	object.icon = this.icon;
        	object.name = this.name;
        	object.type = this.type;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return object;
	}
	
	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		
		try {
			if (this.icon != null) {
				object.put("icon", this.icon);
			}
			
			if (this.name != null) {
				object.put("name", this.name);
			}
			
			object.put("type", LPVehicle.getGoogleVehicleType(this.type));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
	
	// Functions
	
	public static LPGoogleVehicleType getGoogleVehicleTypeFromString(String type)
	{
	    if (type.equals(VEHICLE_TYPE_RAIL)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeRAIL;
	    } else if (type.equals(VEHICLE_TYPE_METRO_RAIL)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeMETRO_RAIL;
	    } else if (type.equals(VEHICLE_TYPE_SUBWAY)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeSUBWAY;
	    } else if (type.equals(VEHICLE_TYPE_TRAM)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeTRAM;
	    } else if (type.equals(VEHICLE_TYPE_MONORAIL)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeMONORAIL;
	    } else if (type.equals(VEHICLE_TYPE_HEAVY_RAIL)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeHEAVY_RAIL;
	    } else if (type.equals(VEHICLE_TYPE_COMMUTER_TRAIN)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeCOMMUTER_TRAIN;
	    } else if (type.equals(VEHICLE_TYPE_HIGH_SPEED_TRAIN)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeHIGH_SPEED_TRAIN;
	    } else if (type.equals(VEHICLE_TYPE_BUS)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeBUS;
	    } else if (type.equals(VEHICLE_TYPE_INTERCITY_BUS)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeINTERCITY_BUS;
	    } else if (type.equals(VEHICLE_TYPE_TROLLEYBUS)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeTROLLEYBUS;
	    } else if (type.equals(VEHICLE_TYPE_SHARE_TAXI)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeSHARE_TAXI;
	    } else if (type.equals(VEHICLE_TYPE_FERRY)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeFERRY;
	    } else if (type.equals(VEHICLE_TYPE_CABLE_CAR)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeCABLE_CAR;
	    } else if (type.equals(VEHICLE_TYPE_GONDOLA_LIFT)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeGONDOLA_LIFT;
	    } else if (type.equals(VEHICLE_TYPE_FUNICULAR)) {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeFUNICULAR;
	    } else {
	        return LPGoogleVehicleType.LPGoogleVehicleTypeOTHER;
	    }
	}

	public static String getGoogleVehicleType(LPGoogleVehicleType type)
	{
	    if (type==LPGoogleVehicleType.LPGoogleVehicleTypeRAIL) {
	        return VEHICLE_TYPE_RAIL;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeMETRO_RAIL) {
	        return VEHICLE_TYPE_METRO_RAIL;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeSUBWAY) {
	        return VEHICLE_TYPE_SUBWAY;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeTRAM) {
	        return VEHICLE_TYPE_TRAM;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeMONORAIL) {
	        return VEHICLE_TYPE_MONORAIL;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeHEAVY_RAIL) {
	        return VEHICLE_TYPE_HEAVY_RAIL;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeCOMMUTER_TRAIN) {
	        return VEHICLE_TYPE_COMMUTER_TRAIN;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeHIGH_SPEED_TRAIN) {
	        return VEHICLE_TYPE_HIGH_SPEED_TRAIN;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeBUS) {
	        return VEHICLE_TYPE_BUS;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeINTERCITY_BUS) {
	        return VEHICLE_TYPE_INTERCITY_BUS;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeTROLLEYBUS) {
	        return VEHICLE_TYPE_TROLLEYBUS;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeSHARE_TAXI) {
	        return VEHICLE_TYPE_SHARE_TAXI;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeFERRY) {
	        return VEHICLE_TYPE_FERRY;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeCABLE_CAR) {
	        return VEHICLE_TYPE_CABLE_CAR;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeGONDOLA_LIFT) {
	        return VEHICLE_TYPE_GONDOLA_LIFT;
	    } else if (type==LPGoogleVehicleType.LPGoogleVehicleTypeFUNICULAR) {
	        return VEHICLE_TYPE_FUNICULAR;
	    } else {
	        return VEHICLE_TYPE_OTHER;
	    }
	}
}
