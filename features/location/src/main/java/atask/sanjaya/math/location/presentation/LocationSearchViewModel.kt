package atask.sanjaya.math.location.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import atask.sanjaya.network.presentation.BaseViewModel
import atask.sanjaya.math.location.data.models.LocationData
import atask.sanjaya.math.location.domain.location.LocationUseCase
import javax.inject.Inject

@HiltViewModel
class LocationSearchViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase
) : BaseViewModel() {
    val searchState by state("search", listOf<LocationData>())

    val setAsFavoriteResult by state("set-as-favorite", 0 to LocationData())
    val setAsFavoriteError by state("set-as-favorite", Throwable())

    fun searchLocation(cityName: String) = executeRoutine("search") {
        locationUseCase.searchLocationAsync(cityName).await()
    }

    @Suppress("DeferredResultUnused")
    fun setAsFavorite(position: Int, location: LocationData) =
        executeRoutine("set-as-favorite", useDefaultLoading = false, useDefaultError = false) {
            locationUseCase.setAsFavoriteAsync(location)
            position to location
        }
}