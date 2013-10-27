/*
 * Luka Penger
 * Software & Hardware Development
 * http://lukapenger.eu
 */

package LPGoogleFunctions;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import LPGoogleFunctions.LPGoogleFunctions.*;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LPGoogleFunctionsHandler {

    protected static final int willLoadDirections_MESSAGE = 0;
    protected static final int errorLoadingDirections_MESSAGE = 1;
    protected static final int didLoadDirections_MESSAGE = 2;
    protected static final int willLoadStreetViewImage_MESSAGE = 3;
    protected static final int didLoadStreetViewImage_MESSAGE = 4;
    protected static final int errorLoadingStreetViewImage_MESSAGE = 5;
    protected static final int willLoadStaticMapImage_MESSAGE = 6;
    protected static final int didLoadStaticMapImage_MESSAGE = 7;
    protected static final int errorLoadingStaticMapImage_MESSAGE = 8;
    protected static final int willLoadPlacesAutocomplete_MESSAGE = 9;
    protected static final int didLoadPlacesAutocomplete_MESSAGE = 10;
    protected static final int errorLoadingPlacesAutocomplete_MESSAGE = 11;
    protected static final int willLoadPlaceDetails_MESSAGE = 12;
    protected static final int didLoadPlaceDetails_MESSAGE = 13;
    protected static final int errorLoadingPlaceDetails_MESSAGE = 14;
    protected static final int willLoadGeocoding_MESSAGE = 15;
    protected static final int didLoadGeocoding_MESSAGE = 16;
    protected static final int errorLoadingGeocoding_MESSAGE = 17;
    protected static final int willLoadPlacesAutocompleteWithDetails_MESSAGE = 18;
    protected static final int didLoadPlacesAutocompleteWithDetails_MESSAGE = 19;
    protected static final int errorLoadingPlacesAutocompleteWithDetails_MESSAGE = 20;
    protected static final int willLoadPlaceTextSearch_MESSAGE = 21;
    protected static final int didLoadPlaceTextSearch_MESSAGE = 22;
    protected static final int errorLoadingPlaceTextSearch_MESSAGE = 23;
    protected static final int willLoadPlacePhoto_MESSAGE = 24;
    protected static final int didLoadPlacePhoto_MESSAGE = 25;
    protected static final int errorLoadingPlacePhoto_MESSAGE = 26;
    
	private static final String LOG_TAG = "LPGoogleFunctionsHandler";
    
    private Handler handler;
    private Boolean useSynchronousMode = false;

    static class ResponderHandler extends Handler 
    {
        private final WeakReference<LPGoogleFunctionsHandler> mResponder;

        ResponderHandler(LPGoogleFunctionsHandler service) 
        {
            mResponder = new WeakReference<LPGoogleFunctionsHandler>(service);
        }

        public void handleMessage(Message msg) {
        	LPGoogleFunctionsHandler service = mResponder.get();
            if (service != null) {
                service.handleMessage(msg);
            }
        }    
    }

    public boolean getUseSynchronousMode()
    {
        return (useSynchronousMode);
    }
    
    /**
     * Set the response handler to use synchronous mode or not
     *
     * @param value true indicates that synchronous mode should be used
     */
    public void setUseSynchronousMode(Boolean value) 
    {
        useSynchronousMode = value;
    }
    
    /**
     * Creates a new AsyncHttpResponseHandler
     */
    public LPGoogleFunctionsHandler() 
    {
        // Set up a handler to post events back to the correct thread if possible
        if (Looper.myLooper() != null)
        {
            handler = new ResponderHandler(this);
        }
    }
    
    //
    // Callbacks to be overridden, typically anonymously
    //

    public void willLoadDirections()
    {
    }
    
    public void errorLoadingDirections(LPGoogleStatus status)
    {
    }
    
    public void didLoadDirections(LPDirections directions)
    {
    }

    public void willLoadStreetViewImage()
    {
    }
    
    public void didLoadStreetViewImage(Bitmap bmp)
    {
    }
    
    public void errorLoadingStreetViewImage(Throwable error)
    {
    }
    
    public void willLoadStaticMapImage()
    {
    }
    
    public void didLoadStaticMapImage(Bitmap bmp)
    {
    }
    
    public void errorLoadingStaticMapImage(Throwable error)
    {
    }
    
    public void willLoadPlacesAutocomplete()
    {
    }

    public void didLoadPlacesAutocomplete(LPPlacesAutocomplete placesAutocomplete)
    {
    }
    
    public void errorLoadingPlacesAutocomplete(LPGoogleStatus status)
    {
    }

    public void willLoadPlaceDetails()
    {
    }
    
    public void didLoadPlaceDetails(LPPlaceDetailsResults placeDetailsResults)
    {
    }
    
    public void errorLoadingPlaceDetails(LPGoogleStatus status)
    {
    }
    
    public void willLoadGeocoding()
    {
    }
    
    public void didLoadGeocoding(LPGeocodingResults geocodingResults)
    {
    }
    
    public void errorLoadingGeocoding(LPGoogleStatus status)
    {
    }
    
    public void willLoadPlacesAutocompleteWithDetails()
    {
    }
    
    public void didLoadPlacesAutocompleteWithDetails(ArrayList<LPPlaceDetails> placesWithDetails)
    {
    }
    
    public void errorLoadingPlacesAutocompleteWithDetails(LPGoogleStatus status)
    {
    }
    
    public void willLoadPlaceTextSearch()
    {
    }
    
    public void didLoadPlaceTextSearch(LPPlaceSearchResults placeDetailsResults)
    {
    }
    
    public void errorLoadingPlaceTextSearch(LPGoogleStatus status)
    {
    }
    
    public void willLoadPlacePhoto()
    {
    }
    
    public void didLoadPlacePhoto(Bitmap bmp)
    {
    }
    
    public void errorLoadingPlacePhoto(Throwable error)
    {
    }
    
    //
    // Pre-processing of messages (executes in background threadpool thread)
    //

    protected void sendWillLocadDirections()
    {
    	sendMessage(obtainMessage(willLoadDirections_MESSAGE, null));
    }
    
    protected void sendErrorLoadingDirectionsMessage(LPGoogleStatus status) 
    {
        sendMessage(obtainMessage(errorLoadingDirections_MESSAGE, new Object[]{status}));
    }

    protected void sendDidLoadDirectionsMessage(LPDirections directions)
    {
    	sendMessage(obtainMessage(didLoadDirections_MESSAGE, new Object[]{directions}));
    }
    
    protected void sendWillLoadStreetViewImageMesssage()
    {
    	sendMessage(obtainMessage(willLoadStreetViewImage_MESSAGE, null));
    }
    
    protected void sendDidLoadStreetViewImageMessage(Bitmap bmp)
    {
    	sendMessage(obtainMessage(didLoadStreetViewImage_MESSAGE, new Object[]{bmp}));
    }
    
    protected void sendErrorLoadingStreetViewImageMessage(Throwable error)
    {
    	sendMessage(obtainMessage(errorLoadingStreetViewImage_MESSAGE, new Object[]{error}));
    }
    
    protected void sendWillLoadStaticMapImageMessage()
    {
    	sendMessage(obtainMessage(willLoadStaticMapImage_MESSAGE, null));
    }
    
    protected void sendDidLoadStaticMapImageMessage(Bitmap bmp)
    {
    	sendMessage(obtainMessage(didLoadStaticMapImage_MESSAGE, new Object[]{bmp}));
    }
    
    protected void sendErrorLoadingStaticMapImageMessage(Throwable error)
    {
    	sendMessage(obtainMessage(errorLoadingStaticMapImage_MESSAGE, new Object[]{error}));
    }
    
    protected void sendWillLoadPlacesAutocompleteMessage()
    {
    	sendMessage(obtainMessage(willLoadPlacesAutocomplete_MESSAGE, null));
    }
    
    protected void sendDidLoadPlacesAutocompleteMessage(LPPlacesAutocomplete placesAutocomplete)
    {
    	sendMessage(obtainMessage(didLoadPlacesAutocomplete_MESSAGE, new Object[]{placesAutocomplete}));
    }
    
    protected void sendErrorLoadingPlacesAutocompleteMessage(LPGoogleStatus status)
    {
    	sendMessage(obtainMessage(errorLoadingPlacesAutocomplete_MESSAGE, new Object[]{status}));
    }

    protected void sendWillLoadPlaceDetailsMessage()
    {
    	sendMessage(obtainMessage(willLoadPlaceDetails_MESSAGE, null));
    }
    
    protected void sendDidLoadPlaceDetailsMessage(LPPlaceDetailsResults placeDetailsResults)
    {
    	sendMessage(obtainMessage(didLoadPlaceDetails_MESSAGE, new Object[]{placeDetailsResults}));
    }
    
    protected void sendErrorLoadingPlaceDetailsMessage(LPGoogleStatus status)
    {
    	sendMessage(obtainMessage(errorLoadingPlaceDetails_MESSAGE, new Object[]{status}));
    }
    
    protected void sendWillLoadGeocoding()
    {
    	sendMessage(obtainMessage(willLoadGeocoding_MESSAGE, null));
    }
    
    protected void sendDidLoadGeocoding(LPGeocodingResults geocodingResults)
    {
    	sendMessage(obtainMessage(didLoadGeocoding_MESSAGE, new Object[]{geocodingResults}));
    }
    
    protected void sendErrorLoadingGeocoding(LPGoogleStatus status)
    {
    	sendMessage(obtainMessage(errorLoadingGeocoding_MESSAGE, new Object[]{status}));
    }
    
    protected void sendWillLoadPlacesAutocompleteWithDetails()
    {
		sendMessage(obtainMessage(willLoadPlacesAutocompleteWithDetails_MESSAGE, null));
    }
    
    protected void sendDidLoadPlacesAutocompleteWithDetails(ArrayList<LPPlaceDetails> placesWithDetails)
    {
		sendMessage(obtainMessage(didLoadPlacesAutocompleteWithDetails_MESSAGE, new Object[]{placesWithDetails}));
    }
    
    protected void sendErrorLoadingPlacesAutocompleteWithDetails(LPGoogleStatus status)
    {
    	sendMessage(obtainMessage(errorLoadingPlacesAutocompleteWithDetails_MESSAGE, new Object[]{status}));
    }
    
    protected void sendWillLoadPlaceTextSearch()
    {
    	sendMessage(obtainMessage(willLoadPlaceTextSearch_MESSAGE, null));
    }
    
    protected void sendDidLoadPlaceTextSearch(LPPlaceSearchResults placeDetailsResults)
    {
    	sendMessage(obtainMessage(didLoadPlaceTextSearch_MESSAGE, new Object[]{placeDetailsResults}));
    }
    
    protected void sendErrorLoadingPlaceTextSearch(LPGoogleStatus status)
    {
    	sendMessage(obtainMessage(errorLoadingPlaceTextSearch_MESSAGE, new Object[]{status}));
    }
    
    protected void sendWillLoadPlacePhoto()
    {
    	sendMessage(obtainMessage(willLoadPlacePhoto_MESSAGE, null));
    }
    
    protected void sendDidLoadPlacePhoto(Bitmap bmp)
    {
    	sendMessage(obtainMessage(didLoadPlacePhoto_MESSAGE, new Object[]{bmp}));
    }
    
    protected void sendErrorLoadingPlacePhoto(Throwable error)
    {
    	sendMessage(obtainMessage(errorLoadingPlacePhoto_MESSAGE, new Object[]{error}));
    }
    
    @SuppressWarnings("unchecked")
	protected void handleMessage(Message msg) 
    {
        Object[] response;

        switch (msg.what) {
        	case willLoadDirections_MESSAGE:
        		willLoadDirections();
        		break;
            case errorLoadingDirections_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	errorLoadingDirections((LPGoogleStatus) response[0]);
                else
                    Log.e(LOG_TAG, "errorLoadingDirections_MESSAGE didn't got enough params");
                break;
            case didLoadDirections_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	didLoadDirections((LPDirections) response[0]);
                else
                    Log.e(LOG_TAG, "didLoadDirections_MESSAGE didn't got enough params");
                break;
            case willLoadStreetViewImage_MESSAGE:
            	willLoadStreetViewImage();
            	break;
            case didLoadStreetViewImage_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	didLoadStreetViewImage((Bitmap) response[0]);
                else
                    Log.e(LOG_TAG, "didLoadStreetViewImage_MESSAGE didn't got enough params");;
            	break;
            case errorLoadingStreetViewImage_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	errorLoadingStreetViewImage((Throwable) response[0]);
                else
                    Log.e(LOG_TAG, "errorLoadingStreetViewImage_MESSAGE didn't got enough params");
            	break;
            case willLoadStaticMapImage_MESSAGE:
            	willLoadStaticMapImage();
            	break;
            case didLoadStaticMapImage_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	didLoadStaticMapImage((Bitmap) response[0]);
                else
                    Log.e(LOG_TAG, "didLoadStaticMapImage_MESSAGE didn't got enough params");;
            	break;
            case errorLoadingStaticMapImage_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	errorLoadingStaticMapImage((Throwable) response[0]);
                else
                    Log.e(LOG_TAG, "errorLoadingStaticMapImage_MESSAGE didn't got enough params");
            	break;
            case willLoadPlacesAutocomplete_MESSAGE:
            	willLoadPlacesAutocomplete();
            	break;
            case didLoadPlacesAutocomplete_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	didLoadPlacesAutocomplete((LPPlacesAutocomplete) response[0]);
                else
                    Log.e(LOG_TAG, "didLoadPlacesAutocomplete_MESSAGE didn't got enough params");;
            	break;
            case errorLoadingPlacesAutocomplete_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	errorLoadingPlacesAutocomplete((LPGoogleStatus) response[0]);
                else
                    Log.e(LOG_TAG, "errorLoadingPlacesAutocomplete_MESSAGE didn't got enough params");
            	break;
            case willLoadPlaceDetails_MESSAGE:
            	willLoadPlaceDetails();
            	break;
            case didLoadPlaceDetails_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	didLoadPlaceDetails((LPPlaceDetailsResults) response[0]);
                else
                    Log.e(LOG_TAG, "didLoadPlaceDetails_MESSAGE didn't got enough params");;
            	break;
            case errorLoadingPlaceDetails_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	errorLoadingPlaceDetails((LPGoogleStatus) response[0]);
                else
                    Log.e(LOG_TAG, "errorLoadingPlaceDetails_MESSAGE didn't got enough params");
            	break;
            case willLoadGeocoding_MESSAGE:
            	willLoadGeocoding();
            	break;
            case didLoadGeocoding_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	didLoadGeocoding((LPGeocodingResults) response[0]);
                else
                    Log.e(LOG_TAG, "didLoadGeocoding_MESSAGE didn't got enough params");;
            	break;
            case errorLoadingGeocoding_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	errorLoadingGeocoding((LPGoogleStatus) response[0]);
                else
                    Log.e(LOG_TAG, "errorLoadingGeocoding_MESSAGE didn't got enough params");
            	break;
            case willLoadPlacesAutocompleteWithDetails_MESSAGE:
            	willLoadPlacesAutocompleteWithDetails();
            	break;
            case didLoadPlacesAutocompleteWithDetails_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	didLoadPlacesAutocompleteWithDetails((ArrayList<LPPlaceDetails>) response[0]);
                else
                    Log.e(LOG_TAG, "didLoadPlacesAutocompleteWithDetails_MESSAGE didn't got enough params");;
            	break;
            case errorLoadingPlacesAutocompleteWithDetails_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	errorLoadingPlacesAutocompleteWithDetails((LPGoogleStatus) response[0]);
                else
                    Log.e(LOG_TAG, "errorLoadingPlacesAutocompleteWithDetails_MESSAGE didn't got enough params");
            	break;
            case willLoadPlaceTextSearch_MESSAGE:
            	willLoadPlaceTextSearch();
            	break;
            case didLoadPlaceTextSearch_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	didLoadPlaceTextSearch((LPPlaceSearchResults) response[0]);
                else
                    Log.e(LOG_TAG, "didLoadPlaceTextSearch_MESSAGE didn't got enough params");;
            	break;
            case errorLoadingPlaceTextSearch_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	errorLoadingPlaceTextSearch((LPGoogleStatus) response[0]);
                else
                    Log.e(LOG_TAG, "errorLoadingPlaceTextSearch_MESSAGE didn't got enough params");
            	break;
            case willLoadPlacePhoto_MESSAGE:
            	willLoadPlacePhoto();
            	break;
            case didLoadPlacePhoto_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	didLoadPlacePhoto((Bitmap) response[0]);
                else
                    Log.e(LOG_TAG, "didLoadPlacePhoto_MESSAGE didn't got enough params");;
            	break;
            case errorLoadingPlacePhoto_MESSAGE:
                response = (Object[]) msg.obj;
                if (response != null && response.length == 1)
                	errorLoadingPlacePhoto((Throwable) response[0]);
                else
                    Log.e(LOG_TAG, "errorLoadingPlacePhoto_MESSAGE didn't got enough params");
            	break;
        }
    }

    protected void sendMessage(Message msg) 
    {
    	if (!Thread.currentThread().isInterrupted()) 
    	{ // do not send messages if request has been cancelled
            handler.sendMessage(msg);
        }
    }

    protected void postRunnable(Runnable r) 
    {
        if (r != null) 
        {
            handler.post(r);
        }
    }
    
    protected Message obtainMessage(int responseMessage, Object response) 
    {
        Message msg;
        if (handler != null) {
            msg = handler.obtainMessage(responseMessage, response);
        } else {
            msg = Message.obtain();
            if (msg != null) {
                msg.what = responseMessage;
                msg.obj = response;
            }
        }
        return msg;
    }
}