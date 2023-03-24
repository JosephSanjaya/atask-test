package atask.sanjaya.math.location.data.web

import atask.sanjaya.network.domain.BaseUrlConfigUseCase
import atask.sanjaya.math.location.data.models.ForecastData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Get5DayForecastApi {
    @Headers("Domain-Name: ${BaseUrlConfigUseCase.OPEN_WEATHER_BASE_URL}")
    @GET("data/2.5/forecast")
    fun getForecast(
        @Query("appid") appId: String,
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
    ): Deferred<ForecastData>
}