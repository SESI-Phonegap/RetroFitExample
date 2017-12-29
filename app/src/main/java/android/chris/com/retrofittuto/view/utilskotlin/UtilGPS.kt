package android.chris.com.retrofittuto.utilskotlin

import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log

/**
 * Service that allows to track the current location of the device
 *
 * @author mnaunay
 */
class UtilGPS
/**
 * Constructor
 *
 * @param context
 * Context
 */
(private val mContext: Context) : Service(), LocationListener {
    private var isGPSEnabled = false
    private var isNetworkEnabled = false
    private var canGetLocation = false
    /**
     * Get current location
     *
     * @return Location
     */
    @get:Synchronized
    var currentLocation: Location? = null
        private set
    private var locationManager: LocationManager? = null

    // First get location from Network Provider
    // if GPS Enabled get lat/long using GPS Services
    val location: Location?
        @Deprecated("")
        get() {
            try {
                if (canGetLocation()) {
                    if (isNetworkEnabled) {
                        locationManager!!.removeUpdates(this)
                        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)
                    }
                    if (isGPSEnabled) {
                        locationManager!!.removeUpdates(this)
                        locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)
                    }
                }
            } catch (e: Exception) {
                Log.e("GPSTracker::getLocation", e.message)
            }

            return currentLocation
        }

    /**
     * Start tracking location
     */
    fun startTracking() {
        try {
            locationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (null != locationManager) {
                isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
                isNetworkEnabled = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                canGetLocation = isGPSEnabled || isNetworkEnabled
                if (canGetLocation) {
                    // First get location from Network Provider
                    if (isNetworkEnabled) {
                        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)
                        Log.i("Request Location Net", "Request Location Network Provider")
                    }
                    // if GPS Enabled get lat/long using GPS Services
                    if (isGPSEnabled) {
                        locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)
                        locationManager!!.sendExtraCommand("gps", "delete_aiding_data", null)
                        Log.i("Request Location GPS", "Request Location GPS Provider")
                    }
                } else {
                    Log.e("Location not available", "Location services are not available")
                }
            }
        } catch (e: Exception) {
            Log.e("GPS:startTracking", e.message)
        }

    }

    /**
     * Stop tracking location
     */
    fun stopUsingGPS() {
        try {
            if (locationManager != null) {
                locationManager!!.removeUpdates(this@UtilGPS)
                locationManager = null
            }
            canGetLocation = false
        } catch (e: Exception) {
            Log.e("GPS::stopUsingGPS: ", e.message)
        }

    }

    /**
     * Method to check if best network provider
     *
     * @return boolean
     */
    fun canGetLocation(): Boolean {
        return canGetLocation
    }

    fun checkLocationService(): Boolean {
        var status = false
        try {
            val lm = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (null != lm) {
                val isGPS = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
                val isNetwork = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                if (isGPS || isNetwork) {
                    status = true
                }
            }
        } catch (ex: Exception) {
            Log.e("GPSTrackerService:", ex.message)
        }

        return status
    }

    override fun onLocationChanged(currenLocation: Location) {
        try {
            if (currentLocation == null || currenLocation.accuracy < MIN_ACCURACY_DISTANCE) {
                this.currentLocation = currenLocation
            }
        } catch (ex: Exception) {
            Log.e("GPSTrackerService:", ex.message)
        }

    }

    override fun onProviderDisabled(provider: String) {
        //  EMPTY
    }

    override fun onProviderEnabled(provider: String) {
        // EMPTY
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        //EMPTY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {
        private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 0 // 0 meters
        private val MIN_TIME_BW_UPDATES: Long = 0 // 0 minute
        private val MIN_ACCURACY_DISTANCE = 75 // Street >10 meters and <= 100 meters
    }
}