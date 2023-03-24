package atask.sanjaya.math.location.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import atask.sanjaya.math.location.data.Get5DayForecastDataStore
import atask.sanjaya.math.location.data.Get5DayForecastRepo
import atask.sanjaya.math.location.data.SearchLocationDataStore
import atask.sanjaya.math.location.data.SearchLocationRepo
import atask.sanjaya.math.location.domain.location.LocationInteractor
import atask.sanjaya.math.location.domain.location.LocationUseCase
import atask.sanjaya.math.location.domain.pref.CurrentLocationInteractor
import atask.sanjaya.math.location.domain.pref.CurrentLocationUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationBinder {
    @Binds
    abstract fun bindSearchLocationDataSource(
        dataStore: SearchLocationDataStore
    ): SearchLocationRepo

    @Binds
    abstract fun bindLocationInteractor(
        interactor: LocationInteractor
    ): LocationUseCase

    @Binds
    abstract fun bindCurrentLocation(
        interactor: CurrentLocationInteractor
    ): CurrentLocationUseCase

    @Binds
    abstract fun bind5DayForecastRepo(
        dataStore: Get5DayForecastDataStore
    ): Get5DayForecastRepo
}