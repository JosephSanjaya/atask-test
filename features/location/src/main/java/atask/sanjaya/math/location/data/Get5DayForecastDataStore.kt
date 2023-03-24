package atask.sanjaya.math.location.data

import atask.sanjaya.common.domain.config.ApiTokenConfigUseCase
import atask.sanjaya.math.location.data.models.ForecastData
import atask.sanjaya.math.location.data.web.Get5DayForecastApi
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class Get5DayForecastDataStore @Inject constructor(
    private val api: Get5DayForecastApi,
    private val tokenConfigUseCase: ApiTokenConfigUseCase
): Get5DayForecastRepo {
    override fun getForecastAsync(lon: Double, lat: Double): Deferred<ForecastData> {
        return api.getForecast(tokenConfigUseCase.getOpenWeatherApiKey(), lon, lat)
    }
}