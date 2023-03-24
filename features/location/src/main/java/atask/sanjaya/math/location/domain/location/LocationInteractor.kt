package atask.sanjaya.math.location.domain.location

import atask.sanjaya.math.location.data.SearchLocationRepo
import atask.sanjaya.math.location.data.local.FavoriteDao
import atask.sanjaya.math.location.data.models.LocationData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import javax.inject.Inject

class LocationInteractor @Inject constructor(
    private val repo: SearchLocationRepo,
    private val local: FavoriteDao
) : LocationUseCase {
    override suspend fun searchLocationAsync(cityName: String): Deferred<List<LocationData>> {
        return CoroutineScope(currentCoroutineContext()).async {
            val onFavorite = local.getAll()
            val response = repo.searchLocationAsync(cityName).await()
            response.map {
                it.copy(isFavorite = it in onFavorite)
            }
        }
    }

    override suspend fun setAsFavoriteAsync(locationData: LocationData) {
        local.insertAll(locationData)
    }

}