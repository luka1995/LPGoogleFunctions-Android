/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

// This code is distributed under the terms and conditions of the MIT license.

// Copyright (c) 2013 Luka Penger
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package LPGoogleFunctions;

import java.security.KeyStore;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.*;

import LPGoogleFunctions.LPDirections.*;
import LPGoogleFunctions.LPStep.*;
import LPGoogleFunctions.LPPrediction.*;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;

public class LPGoogleFunctions {

	// Variables

	public enum LPGoogleStatus {
	    LPGoogleStatusUnknownError,
	    LPGoogleStatusOK,
	    LPGoogleStatusNotFound,
	    LPGoogleStatusZeroResults,
	    LPGoogleStatusMaxWaypointsExceeded,
	    LPGoogleStatusInvalidRequest,
	    LPGoogleStatusOverQueryLimit,
	    LPGoogleStatusRequestDenied
	};

	public enum LPGoogleMapType{
	    LPGoogleMapTypeRoadmap,
	    LPGoogleMapTypeSatellite,
	    LPGoogleMapTypeHybrid,
	    LPGoogleMapTypeTerrain
	}

	public boolean sensor = true; /** Using GPS location sensor */
	public String languageCode = "en"; /** Language ISO code (default "en") **/
	public String googleAPIBrowserKey = "";
	
	// Const

	public static String mapTypeRoadmap = "roadmap";
	public static String mapTypeHybrid = "hybrid";
	public static String mapTypeSatellite = "satellite";
	public static String mapTypeTerrain = "terrain";
	
	public static String STATUS_OK = "OK";
	public static String STATUS_NOT_FOUND = "NOT_FOUND";
	public static String STATUS_ZERO_RESULTS = "ZERO_RESULTS";
	public static String STATUS_MAX_WAYPOINTS_EXCEEDED = "MAX_WAYPOINTS_EXCEEDED";
	public static String STATUS_INVALID_REQUEST = "INVALID_REQUEST";
	public static String STATUS_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
	public static String STATUS_REQUEST_DENIED = "REQUEST_DENIED";
	public static String STATUS_UNKNOWN_ERROR = "UNKNOWN_ERROR";

	public static String googleAPIDirectionsURL = "http://maps.googleapis.com/maps/api/directions/json?";
	public static String googleAPIStaticMapImageURL = "http://maps.googleapis.com/maps/api/staticmap?";
	public static String googleAPIStreetViewImageURL = "http://maps.googleapis.com/maps/api/streetview?";
	public static String googleAPIPlacesAutocompleteURL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
	public static String googleAPIPlaceDetailsURL = "https://maps.googleapis.com/maps/api/place/details/json?";
	public static String googleAPIGeocodingURL = "http://maps.googleapis.com/maps/api/geocode/json?";
	public static String googleAPIPlaceTextSearchURL = "https://maps.googleapis.com/maps/api/place/textsearch/json?";
	public static String googleAPIPlacePhotoURL = "https://maps.googleapis.com/maps/api/place/photo?";

	public AsyncHttpClient client = null;
	
    private int whichLoaded = 0;
    private ArrayList<LPPlaceDetails> placeDetailsArray = new ArrayList<LPPlaceDetails>();
    private boolean blockSend = false;

	// Class
	
	public LPGoogleFunctions() 
	{
		this.client = new AsyncHttpClient();
		this.setSSLSocketForClient(client);
	}
	
	// Functions
	
	public void setSSLSocketForClient(AsyncHttpClient client)
	{
		try {
	        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);

	        SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
	        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

	        client.setSSLSocketFactory(sf);
	    } catch (Exception e) {

	    }
	}
	
	public static String getMapType(LPGoogleMapType maptype)
	{
	    switch (maptype)
	    {
	        case LPGoogleMapTypeRoadmap:
	            return mapTypeRoadmap;
	        case LPGoogleMapTypeHybrid:
	            return mapTypeHybrid;
	        case LPGoogleMapTypeSatellite:
	            return mapTypeSatellite;
	        default:
	            return mapTypeTerrain;
	    }
	}
	
	public static LPGoogleStatus getGoogleStatusFromString(String status)
	{
	    if(status.equals(STATUS_OK))
	    {
	        return LPGoogleStatus.LPGoogleStatusOK;
	    } else if(status.equals(STATUS_NOT_FOUND)) {
	        return LPGoogleStatus.LPGoogleStatusNotFound;
	    } else if(status.equals(STATUS_ZERO_RESULTS)) {
	        return LPGoogleStatus.LPGoogleStatusZeroResults;
	    } else if(status.equals(STATUS_MAX_WAYPOINTS_EXCEEDED)) {
	        return LPGoogleStatus.LPGoogleStatusMaxWaypointsExceeded;
	    } else if(status.equals(STATUS_INVALID_REQUEST)) {
	        return LPGoogleStatus.LPGoogleStatusInvalidRequest;
	    } else if(status.equals(STATUS_OVER_QUERY_LIMIT)) {
	        return LPGoogleStatus.LPGoogleStatusOverQueryLimit;
	    } else if(status.equals(STATUS_REQUEST_DENIED)) {
	        return LPGoogleStatus.LPGoogleStatusRequestDenied;
	    } else {
	        return LPGoogleStatus.LPGoogleStatusUnknownError;
	    }
	}
	
	public static String getGoogleStatus(LPGoogleStatus status)
	{
	    switch (status)
	    {
	        case LPGoogleStatusOK:
	            return STATUS_OK;
	        case LPGoogleStatusInvalidRequest:
	            return STATUS_INVALID_REQUEST;
	        case LPGoogleStatusMaxWaypointsExceeded:
	            return STATUS_MAX_WAYPOINTS_EXCEEDED;
	        case LPGoogleStatusNotFound:
	            return STATUS_NOT_FOUND;
	        case LPGoogleStatusOverQueryLimit:
	            return STATUS_OVER_QUERY_LIMIT;
	        case LPGoogleStatusRequestDenied:
	            return STATUS_REQUEST_DENIED;
	        case LPGoogleStatusZeroResults:
	            return STATUS_ZERO_RESULTS;
	        default:
	            return STATUS_UNKNOWN_ERROR;
	    }
	}
	
	/**
	 * The Google Directions API is a service that calculates directions between locations using an HTTP request.
	 * You can search for directions for several modes of transportation, include transit, driving, walking or cycling. 
	 * Directions may specify origins, destinations and waypoints either as text strings (e.g. "Chicago, IL" or "Darwin, NT, Australia") or as latitude/longitude coordinates.
	 * The Directions API can return multi-part directions using a series of waypoints.
	 * @param origin - The latitude/longitude value from which you wish to calculate directions.
	 * @param destination - The latitude/longitude value from which you wish to calculate directions.
	 * @param travelMode - Specifies the mode of transport to use when calculating directions. If you set the mode to "transit" you must also specify either a departure time or an arrival time.
	 * @param avoid - Indicates that the calculated route(s) should avoid the indicated features.
	 * @param unit - Specifies the unit system to use when displaying results.
	 * @param alternatives - If set to true, specifies that the Directions service may provide more than one route alternative in the response. Note that providing route alternatives may increase the response time from the server.
	 * @param departureTime - Specifies the desired time of departure.
	 * @param arrivalTime - Specifies the desired time of arrival.
	 * @param waypoints - Specifies an array of waypoints. Waypoints alter a route by routing it through the specified location(s). A waypoint is specified as either a latitude/longitude coordinate or as an address which will be geocoded.
	 * @param responseHandler
	 * @Override public void willLoadDirections()
	 * @Override public void didLoadDirections(LPDirections directions)
	 * @Override public void errorLoadingDirections(LPGoogleStatus status)
	 */

	public void loadDirectionsForOrigin(LPLocation origin, LPLocation destination, final LPGoogleDirectionsTravelMode travelMode, LPGoogleDirectionsAvoid avoid, LPGoogleDirectionsUnit unit, boolean alternatives, Date departureTime, Date arrivalTime, ArrayList<LPWaypoint> waypoints, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null) responseHandler.willLoadDirections();

		RequestParams parameters = new RequestParams();

		DecimalFormatSymbols separator = new DecimalFormatSymbols();
		separator.setDecimalSeparator('.');
		DecimalFormat coordinateDecimalFormat = new DecimalFormat("##.######");
		coordinateDecimalFormat.setDecimalFormatSymbols(separator);
		
		if(origin != null) parameters.put("origin", String.format("%s,%s", coordinateDecimalFormat.format(origin.latitude), coordinateDecimalFormat.format(origin.longitude)));
		if(destination != null) parameters.put("destination", String.format("%s,%s", coordinateDecimalFormat.format(destination.latitude), coordinateDecimalFormat.format(destination.longitude)));
		parameters.put("sensor", sensor ? "true" : "false");
		parameters.put("language", languageCode);

	    //MODE
		if(travelMode != null)
		{
			parameters.put("mode", LPStep.getDirectionsTravelMode(travelMode));
		}

	    //AVOID
		if(avoid != null)
		{
			parameters.put("avoid", LPDirections.getDirectionsAvoid(avoid));
		}
		
	    //UNIT
		if(unit != null)
		{
			parameters.put("units", LPDirections.getDirectionsUnit(unit));
		}
		
	    //ALTERNATIVES
		parameters.put("alternatives", alternatives ? "true" : "false");

	    //departureTime
	    if(departureTime != null)
	    {
	        parameters.put("departure_time", String.format("%.0f", departureTime.getTime()));
	    }
	    
	    //departureTime
	    if(arrivalTime != null)
	    {
	    	parameters.put("arrival_time", String.format("%.0f", arrivalTime.getTime()));
	    }
	    
	    //WAYPOINTS
	    if(waypoints != null)
	    {
	        StringBuilder waypointsString = new StringBuilder();
	        
	        for(int i=0; i<waypoints.size(); i++)
	        {
	            LPWaypoint waypoint = waypoints.get(i);
	            
	            waypointsString.append(String.format("%s,%s", coordinateDecimalFormat.format(waypoint.location.latitude), coordinateDecimalFormat.format(waypoint.location.longitude)));
	        }
	        
	        parameters.put("waypoints", waypointsString.toString());
	    }
	    
    	this.client.get(googleAPIDirectionsURL, parameters, new AsyncHttpResponseHandler() {
    	    @Override
    	    public void onSuccess(String response) {
				try {
					JSONObject object = new JSONObject(response);

	    	    	LPDirections directions = new LPDirections(object);
	    	    	directions.requestTravelMode = travelMode;
	    	    	
	    	        LPGoogleStatus status = LPGoogleFunctions.getGoogleStatusFromString(directions.statusCode);

	    	        if(status==LPGoogleStatus.LPGoogleStatusOK)
	    	        {
	    	        	if(responseHandler != null) responseHandler.didLoadDirections(directions);
	    	        } else {
	    	        	if(responseHandler != null) responseHandler.errorLoadingDirections(status);
	    	        }
				} catch (JSONException e) {
					e.printStackTrace();
					
					if(responseHandler != null) responseHandler.errorLoadingDirections(LPGoogleStatus.LPGoogleStatusUnknownError);
				}
    	    }

    	    @Override
    	    public void onFailure(Throwable error) {
    	    	if(responseHandler != null) responseHandler.errorLoadingDirections(LPGoogleStatus.LPGoogleStatusUnknownError);
    	    }
    	});
	}

	/**
	 * The Google Maps Image APIs make it easy to embed a street view image into your image view.
	 * @param location - The location (such as 40.457375,-80.009353).
	 * @param imageWidth - Specifies width size of the image in pixels.
	 * @param imageHeight - Specifies height size of the image in pixels.
	 * @param heading - Indicates the compass heading of the camera. Accepted values are from 0 to 360 (both values indicating North, with 90 indicating East, and 180 South). 
	 * If no heading is specified, a value will be calculated that directs the camera towards the specified location, from the point at which the closest photograph was taken.
	 * @param fov - Determines the horizontal field of view of the image. The field of view is expressed in degrees, with a maximum allowed value of 120.
	 * When dealing with a fixed-size viewport, as with a Street View image of a set size, field of view in essence represents zoom, with smaller numbers indicating a higher level of zoom.
	 * @param pitch- Specifies the up or down angle of the camera relative to the Street View vehicle. This is often, but not always, flat horizontal.
	 * Positive values angle the camera up (with 90 degrees indicating straight up).
	 * Negative values angle the camera down (with -90 indicating straight down).
	 * @param responseHandler
	 * @Override public void willLoadStreetViewImage();
	 * @Override public void didLoadStreetViewImage(Bitmap bmp)
	 * @Override public void errorLoadingStreetViewImage(Throwable error);
	 */
	
	public void loadStreetViewImageForLocation(LPLocation location, int imageWidth, int imageHeight, float heading, float fov, float pitch, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null)	responseHandler.willLoadStreetViewImage();

		RequestParams parameters = new RequestParams();

		DecimalFormatSymbols separator = new DecimalFormatSymbols();
		separator.setDecimalSeparator('.');
		DecimalFormat coordinateDecimalFormat = new DecimalFormat("##.######");
		coordinateDecimalFormat.setDecimalFormatSymbols(separator);
		
		DecimalFormat twoDecimalFormat = new DecimalFormat(".##");
		twoDecimalFormat.setDecimalFormatSymbols(separator);
		
		parameters.put("key", this.googleAPIBrowserKey);
		parameters.put("sensor", sensor ? "true" : "false");
		parameters.put("size", String.format("%dx%d", imageWidth, imageHeight));
		if(location != null) parameters.put("location", String.format("%s,%s", coordinateDecimalFormat.format(location.latitude), coordinateDecimalFormat.format(location.longitude)));
		parameters.put("heading", twoDecimalFormat.format(heading));
		parameters.put("fov", twoDecimalFormat.format(fov));
		parameters.put("pitch", twoDecimalFormat.format(pitch));

		this.client.get(googleAPIStreetViewImageURL, parameters, new BinaryHttpResponseHandler() {
			@Override
			public void onSuccess(byte[] fileData) {
				try {					
					Bitmap bmp = BitmapFactory.decodeByteArray(fileData, 0, fileData.length);
                
					if(responseHandler != null) responseHandler.didLoadStreetViewImage(bmp);
				} catch (Exception e) {
					e.printStackTrace();
					
					if(responseHandler != null) responseHandler.errorLoadingStreetViewImage(e.getCause());
				}
			}
			
    	    @Override
    	    public void onFailure(Throwable error) {
    	    	if(responseHandler != null) responseHandler.errorLoadingStreetViewImage(error);
    	    }
		});
	}
	
	/**
	 * The Google Maps Image APIs make it easy to embed a street view image into your image view.
	 * @param address - The address (such as Chagrin Falls, OH).
	 * @param imageWidth - Specifies width size of the image in pixels.
	 * @param imageHeight - Specifies height size of the image in pixels.
	 * @param heading - Indicates the compass heading of the camera. Accepted values are from 0 to 360 (both values indicating North, with 90 indicating East, and 180 South). 
	 * If no heading is specified, a value will be calculated that directs the camera towards the specified location, from the point at which the closest photograph was taken.
	 * @param fov - Determines the horizontal field of view of the image. The field of view is expressed in degrees, with a maximum allowed value of 120.
	 * When dealing with a fixed-size viewport, as with a Street View image of a set size, field of view in essence represents zoom, with smaller numbers indicating a higher level of zoom.
	 * @param pitch- Specifies the up or down angle of the camera relative to the Street View vehicle. This is often, but not always, flat horizontal.
	 * Positive values angle the camera up (with 90 degrees indicating straight up).
	 * Negative values angle the camera down (with -90 indicating straight down).
	 * @param responseHandler
	 * @Override public void willLoadStreetViewImage();
	 * @Override public void didLoadStreetViewImage(Bitmap bmp)
	 * @Override public void errorLoadingStreetViewImage(Throwable error);
	 */
	
	public void loadStreetViewImageForAddress(String address, int imageWidth, int imageHeight, float heading, float fov, float pitch, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null)	responseHandler.willLoadStreetViewImage();

		RequestParams parameters = new RequestParams();

		DecimalFormatSymbols separator = new DecimalFormatSymbols();
		separator.setDecimalSeparator('.');
		DecimalFormat coordinateDecimalFormat = new DecimalFormat("##.######");
		coordinateDecimalFormat.setDecimalFormatSymbols(separator);
		
		DecimalFormat twoDecimalFormat = new DecimalFormat(".##");
		twoDecimalFormat.setDecimalFormatSymbols(separator);
		
		parameters.put("key", this.googleAPIBrowserKey);
		parameters.put("sensor", sensor ? "true" : "false");
		parameters.put("size", String.format("%dx%d", imageWidth, imageHeight));
		if(address != null) parameters.put("location", address);
		parameters.put("heading", twoDecimalFormat.format(heading));
		parameters.put("fov", twoDecimalFormat.format(fov));
		parameters.put("pitch", twoDecimalFormat.format(pitch));

		this.client.get(googleAPIStreetViewImageURL, parameters, new BinaryHttpResponseHandler() {
			@Override
			public void onSuccess(byte[] fileData) {
				try {					
					Bitmap bmp = BitmapFactory.decodeByteArray(fileData, 0, fileData.length);
                
					if(responseHandler != null) responseHandler.didLoadStreetViewImage(bmp);
				} catch (Exception e) {
					e.printStackTrace();
					
					if(responseHandler != null) responseHandler.errorLoadingStreetViewImage(e.getCause());
				}
			}
			
    	    @Override
    	    public void onFailure(Throwable error) {
    	    	if(responseHandler != null) responseHandler.errorLoadingStreetViewImage(error);
    	    }
		});
	}
	
	/**
	 * The Google Maps Image APIs make it easy to embed a static Google Maps image into your image view.
	 * @param location - The location (such as 40.457375,-80.009353).
	 * @param zoomLevel - (required if markers not present) defines the zoom level of the map, which determines the magnification level of the map.
	 * This parameter takes a numerical value corresponding to the zoom level of the region desired.
	 * @param imageWidth - Specifies width size of the image in pixels.
	 * @param imageHeight - Specifies height size of the image in pixels.
	 * @param imageScale - (optional) affects the number of pixels that are returned. scale=2 returns twice as many pixels as scale=1 while retaining the same coverage area and level of detail (i.e. the contents of the map don't change).
	 * This is useful when developing for high-resolution displays, or when generating a map for printing. The default value is 1.
	 * @param mapType - (optional) defines the type of map to construct. There are several possible maptype values, including roadmap, satellite, hybrid, and terrain. Use LPGoogleMapType.
	 * @param markers - (optional) define one or more markers to attach to the image at specified locations. This parameter takes a single marker definition with parameters separated by the pipe character (|).
	 * Multiple markers may be placed within the same markers parameter as long as they exhibit the same style; you may add additional markers of differing styles by adding additional markers parameters.
	 * Note that if you supply markers for a map, you do not need to specify the (normally required) center and zoom parameters. Use LPMapImageMarker.
	 * @param responseHandler
	 * @Override public void willLoadStaticMapImage()
	 * @Override public void didLoadStaticMapImage(Bitmap bmp)
	 * @Override public void errorLoadingStaticMapImage(Throwable error)
	 */
	
	public void loadStaticMapImageForLocation(LPLocation location, int zoomLevel, int imageWidth, int imageHeight, int imageScale, LPGoogleMapType mapType, ArrayList<LPMapImageMarker> markers, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null)	responseHandler.willLoadStaticMapImage();

		StringBuilder URL = new StringBuilder(googleAPIStaticMapImageURL);
	    
		DecimalFormatSymbols separator = new DecimalFormatSymbols();
		separator.setDecimalSeparator('.');
		DecimalFormat coordinateDecimalFormat = new DecimalFormat("##.######");
		coordinateDecimalFormat.setDecimalFormatSymbols(separator);
		
		if(location != null) URL.append(String.format("center=%s,%s&",coordinateDecimalFormat.format(location.latitude),coordinateDecimalFormat.format(location.longitude)));
		URL.append(String.format("sensor=%s&", this.sensor ? "true" : "false"));
		URL.append(String.format("zoom=%d&", zoomLevel));
		URL.append(String.format("scale=%d&", imageScale));
		URL.append(String.format("size=%dx%d&", imageWidth, imageHeight));
		URL.append(String.format("maptype=%s&", LPGoogleFunctions.getMapType(mapType)));
		
		if(markers != null)
		{
		    for(int i=0; i<markers.size(); i++)
		    {
		        LPMapImageMarker marker = markers.get(i);
		        
		        URL.append(String.format("markers=%s&", marker.getMarkerURLString()));
		    }
		}	

		this.client.get(URL.toString(), new BinaryHttpResponseHandler() {
			@Override
			public void onSuccess(byte[] fileData) {
				try {					
					Bitmap bmp = BitmapFactory.decodeByteArray(fileData, 0, fileData.length);
                
					if(responseHandler != null) responseHandler.didLoadStaticMapImage(bmp);
				} catch (Exception e) {
					e.printStackTrace();
					
					if(responseHandler != null) responseHandler.errorLoadingStaticMapImage(e.getCause());
				}
			}
			
    	    @Override
    	    public void onFailure(Throwable error) {
    	    	if(responseHandler != null) responseHandler.errorLoadingStaticMapImage(error);
    	    }
		});
	}

	/**
	 * The Google Maps Image APIs make it easy to embed a static Google Maps image into your image view.
	 * @param address - The address (such as Chagrin Falls, OH).
	 * @param zoomLevel - (required if markers not present) defines the zoom level of the map, which determines the magnification level of the map.
	 * This parameter takes a numerical value corresponding to the zoom level of the region desired.
	 * @param imageWidth - Specifies width size of the image in pixels.
	 * @param imageHeight - Specifies height size of the image in pixels.
	 * @param imageScale - (optional) affects the number of pixels that are returned. scale=2 returns twice as many pixels as scale=1 while retaining the same coverage area and level of detail (i.e. the contents of the map don't change).
	 * This is useful when developing for high-resolution displays, or when generating a map for printing. The default value is 1.
	 * @param mapType - (optional) defines the type of map to construct. There are several possible maptype values, including roadmap, satellite, hybrid, and terrain. Use LPGoogleMapType.
	 * @param markers - (optional) define one or more markers to attach to the image at specified locations. This parameter takes a single marker definition with parameters separated by the pipe character (|).
	 * Multiple markers may be placed within the same markers parameter as long as they exhibit the same style; you may add additional markers of differing styles by adding additional markers parameters.
	 * Note that if you supply markers for a map, you do not need to specify the (normally required) center and zoom parameters. Use LPMapImageMarker.
	 * @param responseHandler
	 * @Override public void willLoadStaticMapImage()
	 * @Override public void didLoadStaticMapImage(Bitmap bmp)
	 * @Override public void errorLoadingStaticMapImage(Throwable error)
	 */
	
	public void loadStaticMapImageForAddress(String address, int zoomLevel, int imageWidth, int imageHeight, int imageScale, LPGoogleMapType mapType, ArrayList<LPMapImageMarker> markers, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null)	responseHandler.willLoadStaticMapImage();
		
		StringBuilder URL = new StringBuilder(googleAPIStaticMapImageURL);
	    
		DecimalFormatSymbols separator = new DecimalFormatSymbols();
		separator.setDecimalSeparator('.');
		DecimalFormat coordinateDecimalFormat = new DecimalFormat("##.######");
		coordinateDecimalFormat.setDecimalFormatSymbols(separator);
		
		if(address != null) URL.append(String.format("center=%s&",address));
		URL.append(String.format("sensor=%s&", this.sensor ? "true" : "false"));
		URL.append(String.format("zoom=%d&", zoomLevel));
		URL.append(String.format("scale=%d&", imageScale));
		URL.append(String.format("size=%dx%d&", imageWidth, imageHeight));
		URL.append(String.format("maptype=%s&", LPGoogleFunctions.getMapType(mapType)));
		
		if(markers != null)
		{
		    for(int i=0; i<markers.size(); i++)
		    {
		        LPMapImageMarker marker = markers.get(i);
		        
		        URL.append(String.format("markers=%s&", marker.getMarkerURLString()));
		    }
		}	

		this.client.get(URL.toString(), new BinaryHttpResponseHandler() {
			@Override
			public void onSuccess(byte[] fileData) {
				try {					
					Bitmap bmp = BitmapFactory.decodeByteArray(fileData, 0, fileData.length);
                
					if(responseHandler != null) responseHandler.didLoadStaticMapImage(bmp);
				} catch (Exception e) {
					e.printStackTrace();
					
					if(responseHandler != null) responseHandler.errorLoadingStaticMapImage(e.getCause());
				}
			}
			
    	    @Override
    	    public void onFailure(Throwable error) {
    	    	if(responseHandler != null) responseHandler.errorLoadingStaticMapImage(error);
    	    }
		});
	}

	/**
	 * The Google Places Autocomplete API is a web service that returns Place information based on text search terms, and, optionally, geographic bounds.
	 * The API can be used to provide autocomplete functionality for text-based geographic searches, by returning Places such as businesses, addresses, and points of interest as a user types.
	 * @param input - The text string on which to search. The Place service will return candidate matches based on this string and order results based on their perceived relevance.
	 * @param offset - The character position in the input term at which the service uses text for predictions. For example, if the input is 'Googl' and the completion point is 3, the service will match on 'Goo'. The offset should generally be set to the position of the text caret. If no offset is supplied, the service will use the entire term.
	 * @param radius - The distance (in meters) within which to return Place results. Note that setting a radius biases results to the indicated area, but may not fully restrict results to the specified area.
	 * @param location - The point around which you wish to retrieve Place information. Must be specified as latitude,longitude.
	 * @param placeType - The types of Place results to return. If no type is specified, all types will be returned. See LPPrediction.h for types.
	 * @param countryRestriction - The country must be passed as a two character.
	 * @param responseHandler
	 * @Override public void willLoadPlacesAutocomplete()
	 * @Override public void didLoadPlacesAutocomplete(LPPlacesAutocomplete placesAutocomplete)
	 * @Override public void errorLoadingPlacesAutocomplete(LPGoogleStatus status)
	 */

	public void loadPlacesAutocompleteForInput(String input, int offset, int radius, LPLocation location, LPGooglePlaceType placeType, String countryRestriction, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null)	responseHandler.willLoadPlacesAutocomplete();
		
		RequestParams parameters = new RequestParams();

		DecimalFormatSymbols separator = new DecimalFormatSymbols();
		separator.setDecimalSeparator('.');
		DecimalFormat coordinateDecimalFormat = new DecimalFormat("##.######");
		coordinateDecimalFormat.setDecimalFormatSymbols(separator);

		parameters.put("key", this.googleAPIBrowserKey);
		if(input != null) parameters.put("input", input);
		parameters.put("types", LPPrediction.getStringFromGooglePlaceType(placeType));
		parameters.put("offset", String.format("%d", offset));
		parameters.put("radius", String.format("%d", radius));
		if(location != null) parameters.put("location", String.format("%s,%s", coordinateDecimalFormat.format(location.latitude), coordinateDecimalFormat.format(location.longitude)));
		parameters.put("sensor", this.sensor ? "true" : "false");
		parameters.put("language", this.languageCode);
	    
	    if(countryRestriction != null)
	    {
	    	parameters.put("components", String.format("country:%s", countryRestriction));
	    }

	    this.client.get(googleAPIPlacesAutocompleteURL, parameters, new AsyncHttpResponseHandler() {
    	    @Override
    	    public void onSuccess(String response) {
				try {
					JSONObject object = new JSONObject(response);

					LPPlacesAutocomplete placesAutocomplete = new LPPlacesAutocomplete(object);
					
	    	        LPGoogleStatus status = LPGoogleFunctions.getGoogleStatusFromString(placesAutocomplete.statusCode);

	    	        if(status==LPGoogleStatus.LPGoogleStatusOK)
	    	        {
	    	        	if(responseHandler != null) responseHandler.didLoadPlacesAutocomplete(placesAutocomplete);
	    	        } else {
	    	        	if(responseHandler != null) responseHandler.errorLoadingPlacesAutocomplete(status);
	    	        }
				} catch (JSONException e) {
					e.printStackTrace();
					
					if(responseHandler != null) responseHandler.errorLoadingPlacesAutocomplete(LPGoogleStatus.LPGoogleStatusUnknownError);
				}
    	    }

    	    @Override
    	    public void onFailure(Throwable error) {
    	    	if(responseHandler != null) responseHandler.errorLoadingPlacesAutocomplete(LPGoogleStatus.LPGoogleStatusUnknownError);
    	    }
    	});
	}
	
	/**
	 * Once you have a reference from a Place Search, you can request more details about a particular establishment or point of interest by initiating a Place Details request.
	 * A Place Details request returns more comprehensive information about the indicated place such as its complete address, phone number, user rating and reviews.
	 * @param reference - A textual identifier that uniquely identifies a place, returned from a Place search.
	 * @param responseHandler
	 * @Override public void willLoadPlaceDetails()
	 * @Override public void didLoadPlaceDetails(LPPlaceDetailsResults placeDetailsResults)
	 * @Override public void errorLoadingPlaceDetails(LPGoogleStatus status)
	 */

	public void loadPlaceDetailsForReference(String reference, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null)	responseHandler.willLoadPlaceDetails();
		
		RequestParams parameters = new RequestParams();

		parameters.put("key", this.googleAPIBrowserKey);
		if(reference != null) parameters.put("reference", reference);
		parameters.put("sensor", this.sensor ? "true" : "false");
		parameters.put("language", this.languageCode);

		this.client.get(googleAPIPlaceDetailsURL, parameters, new AsyncHttpResponseHandler() {
    	    @Override
    	    public void onSuccess(String response) {
				try {
					JSONObject object = new JSONObject(response);

					LPPlaceDetailsResults placeDetailsResults = new LPPlaceDetailsResults(object);

	    	        LPGoogleStatus status = LPGoogleFunctions.getGoogleStatusFromString(placeDetailsResults.statusCode);

	    	        if(status==LPGoogleStatus.LPGoogleStatusOK)
	    	        {
	    	        	if(responseHandler != null) responseHandler.didLoadPlaceDetails(placeDetailsResults);
	    	        } else {
	    	        	if(responseHandler != null) responseHandler.errorLoadingPlaceDetails(status);
	    	        }
				} catch (JSONException e) {
					e.printStackTrace();
					
					if(responseHandler != null) responseHandler.errorLoadingPlaceDetails(LPGoogleStatus.LPGoogleStatusUnknownError);
				}
    	    }

    	    @Override
    	    public void onFailure(Throwable error) {
    	    	if(responseHandler != null) responseHandler.errorLoadingPlaceDetails(LPGoogleStatus.LPGoogleStatusUnknownError);
    	    }
    	});
	}

	/**
	 * Geocoding is the process of converting addresses (like "1600 Amphitheatre Parkway, Mountain View, CA") into geographic coordinates 
	 * (like latitude 37.423021 and longitude -122.083739), which you can use to place markers or position the map.
	 * @param address - The address (such as Chagrin Falls, OH).
	 * @param filterComponents - A component filter for which you wish to obtain a geocode.
	 * The components filter will also be accepted as an optional parameter if an address is provided. See LPGeocodingFilter.h for components.
	 * @param responseHandler
	 * @Override public void willLoadGeocoding()
	 * @Override public void didLoadGeocoding(LPGeocodingResults geocodingResults)
	 * @Override public void errorLoadingGeocoding(LPGoogleStatus status)
	 */

	public void loadGeocodingForAddress(String address, ArrayList<LPGeocodingFilter> filterComponents, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null)	responseHandler.willLoadGeocoding();

		RequestParams parameters = new RequestParams();

		if(address != null) parameters.put("address", address);
		parameters.put("sensor", this.sensor ? "true" : "false");
		parameters.put("language", this.languageCode);
		
	    //COMPONENTS FILTER
	    if(filterComponents != null)
	    {
	    	if(filterComponents.size() > 0)
	    	{
		        StringBuilder comString = new StringBuilder("components=");
		        
		        for(int i=0; i<filterComponents.size(); i++)
		        {
		            LPGeocodingFilter filter = filterComponents.get(i);
		            
		            comString.append(String.format("%s:%s", LPGeocodingFilter.getGeocodingFilter(filter.filter), filter.value));
		        }
	    	}
	    }
		    
	    this.client.get(googleAPIGeocodingURL, parameters, new AsyncHttpResponseHandler() {
    	    @Override
    	    public void onSuccess(String response) {
				try {
					JSONObject object = new JSONObject(response);

					LPGeocodingResults geocodingResults = new LPGeocodingResults(object);

	    	        LPGoogleStatus status = LPGoogleFunctions.getGoogleStatusFromString(geocodingResults.statusCode);

	    	        if(status==LPGoogleStatus.LPGoogleStatusOK)
	    	        {
	    	        	if(responseHandler != null) responseHandler.didLoadGeocoding(geocodingResults);
	    	        } else {
	    	        	if(responseHandler != null) responseHandler.errorLoadingGeocoding(status);
	    	        }
				} catch (JSONException e) {
					e.printStackTrace();
					
					if(responseHandler != null) responseHandler.errorLoadingGeocoding(LPGoogleStatus.LPGoogleStatusUnknownError);
				}
    	    }

    	    @Override
    	    public void onFailure(Throwable error) {
    	    	if(responseHandler != null) responseHandler.errorLoadingGeocoding(LPGoogleStatus.LPGoogleStatusUnknownError);
    	    }
    	});
	}
	
	/**
	 * Geocoding is the process of converting addresses (like "1600 Amphitheatre Parkway, Mountain View, CA") into geographic coordinates 
	 * (like latitude 37.423021 and longitude -122.083739), which you can use to place markers or position the map.
	 * @param address - The location (such as 40.457375,-80.009353).
	 * @param filterComponents - A component filter for which you wish to obtain a geocode.
	 * The components filter will also be accepted as an optional parameter if an address is provided. See LPGeocodingFilter.h for components.
	 * @param responseHandler
	 * @Override public void willLoadGeocoding()
	 * @Override public void didLoadGeocoding(LPGeocodingResults geocodingResults)
	 * @Override public void errorLoadingGeocoding(LPGoogleStatus status)
	 */
	
	public void loadGeocodingForLocation(LPLocation location, ArrayList<LPGeocodingFilter> filterComponents, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null)	responseHandler.willLoadGeocoding();

		RequestParams parameters = new RequestParams();

		DecimalFormatSymbols separator = new DecimalFormatSymbols();
		separator.setDecimalSeparator('.');
		DecimalFormat coordinateDecimalFormat = new DecimalFormat("##.######");
		coordinateDecimalFormat.setDecimalFormatSymbols(separator);

		if(location != null) parameters.put("latlng", String.format("%s,%s", coordinateDecimalFormat.format(location.latitude), coordinateDecimalFormat.format(location.longitude)));
		parameters.put("sensor", this.sensor ? "true" : "false");
		parameters.put("language", this.languageCode);
		
	    //COMPONENTS FILTER
	    if(filterComponents != null)
	    {
	    	if(filterComponents.size() > 0)
	    	{
		        StringBuilder comString = new StringBuilder("components=");
		        
		        for(int i=0; i<filterComponents.size(); i++)
		        {
		            LPGeocodingFilter filter = filterComponents.get(i);
		            
		            comString.append(String.format("%s:%s", LPGeocodingFilter.getGeocodingFilter(filter.filter), filter.value));
		        }
	    	}
	    }
		    
	    this.client.get(googleAPIGeocodingURL, parameters, new AsyncHttpResponseHandler() {
    	    @Override
    	    public void onSuccess(String response) {
				try {
					JSONObject object = new JSONObject(response);

					LPGeocodingResults geocodingResults = new LPGeocodingResults(object);

	    	        LPGoogleStatus status = LPGoogleFunctions.getGoogleStatusFromString(geocodingResults.statusCode);

	    	        if(status==LPGoogleStatus.LPGoogleStatusOK)
	    	        {
	    	        	if(responseHandler != null) responseHandler.didLoadGeocoding(geocodingResults);
	    	        } else {
	    	        	if(responseHandler != null) responseHandler.errorLoadingGeocoding(status);
	    	        }
				} catch (JSONException e) {
					e.printStackTrace();
					
					if(responseHandler != null) responseHandler.errorLoadingGeocoding(LPGoogleStatus.LPGoogleStatusUnknownError);
				}
    	    }

    	    @Override
    	    public void onFailure(Throwable error) {
    	    	if(responseHandler != null) responseHandler.errorLoadingGeocoding(LPGoogleStatus.LPGoogleStatusUnknownError);
    	    }
    	});
	}
	
	/**
	 * Place Autocomplete + Place Details
	 * @param input - The text string on which to search. The Place service will return candidate matches based on this string and order results based on their perceived relevance.
	 * @param offset - The character position in the input term at which the service uses text for predictions. For example, if the input is 'Googl' and the completion point is 3, the service will match on 'Goo'. The offset should generally be set to the position of the text caret. If no offset is supplied, the service will use the entire term.
	 * @param radius - The distance (in meters) within which to return Place results. Note that setting a radius biases results to the indicated area, but may not fully restrict results to the specified area.
	 * @param location - The point around which you wish to retrieve Place information. Must be specified as latitude,longitude.
	 * @param placeType - The types of Place results to return. If no type is specified, all types will be returned. See LPPrediction.h for types.
	 * @param countryRestriction - The country must be passed as a two character.
	 * @param responseHandler
	 * @Override public void willLoadPlacesAutocompleteWithDetails()
	 * @Override public void didLoadPlacesAutocompleteWithDetails(ArrayList<LPPlaceDetails> placesWithDetails)
	 * @Override public void errorLoadingPlacesAutocompleteWithDetails(LPGoogleStatus status)
	 */

	public void loadPlacesAutocompleteWithDetailsForInput(String input, int offset, int radius, LPLocation location, LPGooglePlaceType placeType, String countryRestriction, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null) responseHandler.willLoadPlacesAutocompleteWithDetails();
		
		final LPGoogleFunctions self = this;

		this.whichLoaded = 0;
		this.blockSend = false;
		this.placeDetailsArray = new ArrayList<LPPlaceDetails>();
		
		self.loadPlacesAutocompleteForInput(input, offset, radius, location, placeType, countryRestriction, new LPGoogleFunctionsHandler() {
			@Override
			public void didLoadPlacesAutocomplete(final LPPlacesAutocomplete placesAutocomplete) {
			        
				for(int i=0; i<placesAutocomplete.predictions.size();i++)
		        {
		            String reference = placesAutocomplete.predictions.get(i).reference;
		            
		            self.loadPlaceDetailsForReference(reference, new LPGoogleFunctionsHandler() {
		            	@Override
		            	public void didLoadPlaceDetails(LPPlaceDetailsResults placeDetailsResults) {
		            		LPPlaceDetails placeWithDetails = placeDetailsResults.result.clone();
							
		            		placeDetailsArray.add(placeWithDetails);
							
							whichLoaded++;

				            if(whichLoaded >= placesAutocomplete.predictions.size())
				            {
			                    if(placeDetailsArray.size() > 0)
			                    {
			                        if(blockSend==false)
			                        {
			                            if(responseHandler != null) responseHandler.didLoadPlacesAutocompleteWithDetails(placeDetailsArray);
			                            
			                            blockSend = true;
			                        }
			                    } else {
			                        if(blockSend==false)
			                        {
			                        	if(responseHandler != null) responseHandler.errorLoadingPlacesAutocompleteWithDetails(LPGoogleStatus.LPGoogleStatusZeroResults);

			                            blockSend = true;
			                        }
			                    }
				            }
		            	}
		            	
		            	@Override
		            	public void errorLoadingPlaceDetails(LPGoogleStatus status) {
		            		whichLoaded++;

				            if(whichLoaded >= placesAutocomplete.predictions.size())
				            {
			                    if(placeDetailsArray.size() > 0)
			                    {
			                        if(blockSend==false)
			                        {
			                            if(responseHandler != null) responseHandler.didLoadPlacesAutocompleteWithDetails(placeDetailsArray);
			                            
			                            blockSend = true;
			                        }
			                    } else {
			                        if(blockSend==false)
			                        {
			                        	if(responseHandler != null) responseHandler.errorLoadingPlacesAutocompleteWithDetails(LPGoogleStatus.LPGoogleStatusZeroResults);

			                            blockSend = true;
			                        }
			                    }
				            }
		            	}
		            });
		        }
			}
			
			@Override
			public void errorLoadingPlacesAutocomplete(LPGoogleStatus status) {
				if(responseHandler != null) responseHandler.errorLoadingPlacesAutocompleteWithDetails(LPGoogleStatus.LPGoogleStatusUnknownError);
			}
		});
	}
	
	/**
	 * The Google Places API Text Search Service is a web service that returns information about a set of Places based on a string — for example "pizza in New York" or "shoe stores near Ottawa".
	 * @param query - The text string on which to search, for example: "restaurant".
	 * The Place service will return candidate matches based on this string and order the results based on their perceived relevance.
	 * @param location - The latitude/longitude around which to retrieve Place information. This must be specified as latitude,longitude.
	 * If you specify a location parameter, you must also specify a radius parameter.
	 * @param radius - Defines the distance (in meters) within which to bias Place results. The maximum allowed radius is 50 000 meters.
	 * Results inside of this region will be ranked higher than results outside of the search circle; however, prominent results from outside of the search radius may be included.
	 * @param responseHandler
	 * @Override public void willLoadPlaceTextSearch()
	 * @Override public void didLoadPlaceTextSearch()
	 * @override public void errorLoadingPlaceTextSearch(LPPlaceSearchResults placeDetailsResults)
	 */
	
	public void loadPlaceTextSearchForQuery(String query, LPLocation location, int radius, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null) responseHandler.willLoadPlaceTextSearch();
		
		RequestParams parameters = new RequestParams();

		parameters.put("key", this.googleAPIBrowserKey);
		parameters.put("query", query);
		parameters.put("sensor", this.sensor ? "true" : "false");
		parameters.put("language", this.languageCode);
		
	    if(location != null && radius > 0) 
	    {
			parameters.put("location", String.format("%s,%s", this.coordinateDecimalFormat().format(location.latitude), this.coordinateDecimalFormat().format(location.longitude)));
			parameters.put("radius", String.format("%d", radius));
	    }
	    
	    this.client.get(googleAPIPlaceTextSearchURL, parameters, new AsyncHttpResponseHandler() {
    	    @Override
    	    public void onSuccess(String response) {
    	    	
				try {
					JSONObject object = new JSONObject(response);

					LPPlaceSearchResults placeDetailsResults = new LPPlaceSearchResults(object);

	    	        LPGoogleStatus status = LPGoogleFunctions.getGoogleStatusFromString(placeDetailsResults.statusCode);

	    	        if(status==LPGoogleStatus.LPGoogleStatusOK)
	    	        {
	    	        	if(responseHandler != null) responseHandler.didLoadPlaceTextSearch(placeDetailsResults);
	    	        } else {
	    	        	if(responseHandler != null) responseHandler.errorLoadingPlaceTextSearch(status);
	    	        }
				} catch (JSONException e) {
					e.printStackTrace();
					
					if(responseHandler != null) responseHandler.errorLoadingPlaceTextSearch(LPGoogleStatus.LPGoogleStatusUnknownError);
				}
    	    }

    	    @Override
    	    public void onFailure(Throwable error) {
    	    	if(responseHandler != null) responseHandler.errorLoadingPlaceTextSearch(LPGoogleStatus.LPGoogleStatusUnknownError);
    	    }
    	});
	}
	
	/**
	 * The Places Photo service is a read-only API that allows you to easily add high quality photographic content to your application.
	 * The Photo service gives you access to the millions of photos stored in the Places and Google+ Local database.
	 * When you search for Places using either a Place Search or Place Details request, photo references will be returned for relevant photographic content.
	 * The Photo service lets you access the referenced photos, and resize the image to the optimal size for your application.
	 * @param reference - A string identifier that uniquely identifies a photo. Photo references are returned from either a Place Search or Place Details request.
	 * @param maxHeight - Max image height (accept an integer between 1 and 1600).
	 * @param maxWidth - Min image height (accept an integer between 1 and 1600).
	 * @param responseHandler
	 * @Override public void willLoadPlacePhoto() 
	 * @Override public void didLoadPlacePhoto()
	 * @Override public void errorLoadingPlacePhoto()
	 */
	
	public void loadPlacePhotoForReference(String reference, int maxHeight, int maxWidth, final LPGoogleFunctionsHandler responseHandler)
	{
		if(responseHandler != null) responseHandler.willLoadPlacePhoto();
		
		RequestParams parameters = new RequestParams();

		parameters.put("key", this.googleAPIBrowserKey);
		parameters.put("sensor", this.sensor ? "true" : "false");
		parameters.put("photoreference", reference);
		
	    if(maxHeight > 0 && maxHeight <= 1600) 
	    {
	    	parameters.put("maxheight", String.format("%d", maxHeight));
	    }
	    
	    if(maxWidth > 0 && maxWidth <= 1600) 
	    {
	    	parameters.put("maxwidth", String.format("%d", maxWidth));
	    }
	    
	    this.client.get(googleAPIPlacePhotoURL, new BinaryHttpResponseHandler() {
			@Override
			public void onSuccess(byte[] fileData) {
				try {					
					Bitmap bmp = BitmapFactory.decodeByteArray(fileData, 0, fileData.length);
                
					if(responseHandler != null) responseHandler.didLoadPlacePhoto(bmp);
				} catch (Exception e) {
					e.printStackTrace();
					
					if(responseHandler != null) responseHandler.errorLoadingPlacePhoto(e.getCause());
				}
			}
			
    	    @Override
    	    public void onFailure(Throwable error) {
    	    	if(responseHandler != null) responseHandler.errorLoadingPlacePhoto(error);
    	    }
		});
	}
	
	public DecimalFormat coordinateDecimalFormat()
	{
		DecimalFormatSymbols separator = new DecimalFormatSymbols();
		separator.setDecimalSeparator('.');
		DecimalFormat coordinateDecimalFormat = new DecimalFormat("##.######");
		coordinateDecimalFormat.setDecimalFormatSymbols(separator);
		
		return coordinateDecimalFormat;
	}
}