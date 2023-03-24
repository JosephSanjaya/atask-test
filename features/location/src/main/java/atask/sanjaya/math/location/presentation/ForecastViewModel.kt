package atask.sanjaya.math.location.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import atask.sanjaya.network.presentation.BaseViewModel
import atask.sanjaya.math.location.data.Get5DayForecastRepo
import atask.sanjaya.math.location.data.models.ForecastData
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val forecastRepo: Get5DayForecastRepo
) : BaseViewModel() {

    val forecast by state<ForecastData>("forecast-result")

    fun getForecast(lon: Double, lat: Double) = executeRoutine("forecast-result") {
        forecastRepo.getForecastAsync(lon, lat).await()
    }
}