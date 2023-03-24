package atask.sanjaya.math.location.data

import atask.sanjaya.math.location.data.models.LocationData
import kotlinx.coroutines.Deferred

interface SearchLocationRepo {
    fun searchLocationAsync(city: String): Deferred<List<LocationData>>
}