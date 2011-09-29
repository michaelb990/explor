package com.explor;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class ExplorScreen extends Activity {
    
	private static final String TAG = ExplorScreen.class.getSimpleName();
	
	private static final int    MIN_NOTIFICATION_DIST = 2; // meters
	//private static final String LOCATION_PROVIDER     = LocationManager.NETWORK_PROVIDER;
	private static final String LOCATION_PROVIDER     = LocationManager.GPS_PROVIDER;
	
	private LocationManager mLocationMgr;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mLocationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }
    
    @Override
    public void onResume() {
        mLocationMgr.requestLocationUpdates(LOCATION_PROVIDER, 0, MIN_NOTIFICATION_DIST, mLocationListener);
    }
    
    @Override
    public void onPause() {
    	mLocationMgr.removeUpdates(mLocationListener);
    }

    private final LocationListener mLocationListener = new LocationListener() {
    	public void onLocationChanged(Location location) {
    		Log.i(TAG, location.getLatitude() + "  " + location.getLongitude());
    	}

    	public void onStatusChanged(String provider, int status, Bundle extras) {}

    	public void onProviderEnabled(String provider) {}

    	public void onProviderDisabled(String provider) {}
    };
}