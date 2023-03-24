package atask.sanjaya.math.location.presentation.router

import android.os.Bundle
import atask.sanjaya.math.location.data.models.LocationData

interface LocationRouter {
    fun goToLocationSearch()
    fun goToLocationForecast(location: LocationData)
    fun getLocationDataFromArgs(arguments: Bundle?): LocationData
    fun selectMainLocation()
}