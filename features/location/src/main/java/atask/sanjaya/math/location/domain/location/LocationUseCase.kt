package atask.sanjaya.math.location.domain.location

import atask.sanjaya.math.location.data.models.LocationData
import kotlinx.coroutines.Deferred

interface LocationUseCase {
    suspend fun searchLocationAsync(cityName: String): Deferred<List<LocationData>>
    suspend fun setAsFavoriteAsync(locationData: LocationData)
}