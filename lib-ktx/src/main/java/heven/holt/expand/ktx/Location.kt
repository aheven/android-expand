package heven.holt.expand.ktx

import android.location.LocationManager
import androidx.core.content.getSystemService

inline val isLocationEnabled: Boolean
    get() = try {
        application.getSystemService<LocationManager>()?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true
    } catch (e: Exception) {
        false
    }
