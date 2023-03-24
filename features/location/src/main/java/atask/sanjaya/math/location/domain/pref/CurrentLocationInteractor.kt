package atask.sanjaya.math.location.domain.pref

import android.content.SharedPreferences
import atask.sanjaya.common.utils.toJson
import atask.sanjaya.common.utils.toObject
import atask.sanjaya.math.location.data.models.LocationData
import javax.inject.Inject

class CurrentLocationInteractor @Inject constructor(
    private val prefRepo: SharedPreferences
) : CurrentLocationUseCase {
    override fun getCurrentLocation(): LocationData? {
        return prefRepo.getString(CurrentLocationUseCase.CURRENT_LOCATION_KEY, "")?.toObject()
    }

    override suspend fun setCurrentLocation(locationData: LocationData) {
        prefRepo.edit()
            .putString(CurrentLocationUseCase.CURRENT_LOCATION_KEY, locationData.toJson())
            .apply()
    }
}