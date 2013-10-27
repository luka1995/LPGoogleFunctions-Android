/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class LPMapImageMarker {

	// Variables
	
	public static enum LPGoogleMapImageMarkerSize {
	    LPGoogleMapImageMarkerSizeTiny,
	    LPGoogleMapImageMarkerSizeMid,
	    LPGoogleMapImageMarkerSizeSmall,
	    LPGoogleMapImageMarkerSizeNormal
	};
	
	public LPGoogleMapImageMarkerSize size = LPGoogleMapImageMarkerSize.LPGoogleMapImageMarkerSizeNormal;
	public int color = 0x00000000;
	public String label = "A";
	public LPLocation location = null;
	
	// Class
	
	public LPMapImageMarker()
	{
		
	}
	
	public LPMapImageMarker clone()
	{
        try {
        	LPMapImageMarker object = new LPMapImageMarker();
        	
        	object.size = this.size;
        	object.color = this.color;
        	object.label = this.label;
        	object.location = this.location;
        	
        	return object;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
	
	// Functions
	
	public String getColorString()
	{
	    int red = ((this.color >> 16) & 0xFF);
	    int green = ((this.color >> 8) & 0xFF);
	    int blue = ((this.color >> 0) & 0xFF);
	    
	    String colorString = String.format("0x%02x%02x%02x", red, green, blue);
	    
	    return colorString;
	}

	public String getSizeString()
	{
	    switch (this.size) {
	        case LPGoogleMapImageMarkerSizeMid:
	            return "mid";
	        case LPGoogleMapImageMarkerSizeTiny:
	            return "tiny";
	        case LPGoogleMapImageMarkerSizeSmall:
	            return "small";
	        default:
	            return "";
	    }
	}

	public String getMarkerURLString()
	{
		DecimalFormatSymbols separator = new DecimalFormatSymbols();
		separator.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("##.######");
		df.setDecimalFormatSymbols(separator);
		
	    String string = String.format("size:%s|color:%s|label:%s|%s,%s", this.getSizeString(), this.getColorString(), this.label, df.format(this.location.latitude), df.format(this.location.longitude));

	    return string;
	}
}
