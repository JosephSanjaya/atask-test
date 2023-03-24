package atask.sanjaya.math.location.domain.pref

import atask.sanjaya.math.location.data.models.LocationData

interface CurrentLocationUseCase {

    fun getCurrentLocation(): LocationData?
    suspend fun setCurrentLocation(locationData: LocationData)

    companion object {
        const val CURRENT_LOCATION_KEY = "current-location"
    }
}