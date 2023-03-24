package atask.sanjaya.math.location.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import atask.sanjaya.math.location.data.local.FavoriteDao
import atask.sanjaya.math.location.data.local.LocationDatabase
import atask.sanjaya.math.location.data.web.Get5DayForecastApi
import atask.sanjaya.math.location.data.web.SearchLocationApi
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocationModules {

    @Provides
    @Singleton
    fun provideSearchLocationApi(
        retrofit: Retrofit
    ): SearchLocationApi = retrofit.create(SearchLocationApi::class.java)


    @Provides
    @Singleton
    fun provide5DayForecastApi(
        retrofit: Retrofit
    ): Get5DayForecastApi = retrofit.create(Get5DayForecastApi::class.java)

    @Provides
    @Singleton
    fun providesLocationDb(
        @ApplicationContext context: Context
    ): LocationDatabase = Room.databaseBuilder(
        context,
        LocationDatabase::class.java, LocationDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providesFavoriteDao(
        db: LocationDatabase
    ): FavoriteDao = db.favoriteDao()

}