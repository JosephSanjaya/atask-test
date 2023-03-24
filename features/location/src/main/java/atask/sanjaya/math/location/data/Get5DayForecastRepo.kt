package atask.sanjaya.math.location.data

import atask.sanjaya.math.location.data.models.ForecastData
import kotlinx.coroutines.Deferred

interface Get5DayForecastRepo {
    fun getForecastAsync(lon: Double, lat: Double): Deferred<ForecastData>
}