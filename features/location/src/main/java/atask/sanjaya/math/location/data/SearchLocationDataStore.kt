package atask.sanjaya.math.location.data

import atask.sanjaya.common.domain.config.ApiTokenConfigUseCase
import atask.sanjaya.math.location.data.models.LocationData
import atask.sanjaya.math.location.data.web.SearchLocationApi
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class SearchLocationDataStore @Inject constructor(
    private val api: SearchLocationApi,
    private val tokenConfigUseCase: ApiTokenConfigUseCase
) : SearchLocationRepo {
    override fun searchLocationAsync(city: String): Deferred<List<LocationData>> {
        return api.searchLocationAsync(tokenConfigUseCase.getApiNinjaApiKey(), city)
    }
}