package atask.sanjaya.ui.di

import android.content.Context
import androidx.room.Room
import atask.sanjaya.ui.data.locale.LocaleResRepo
import atask.sanjaya.ui.data.locale.local.LocaleDao
import atask.sanjaya.ui.data.locale.local.LocaleDatabase
import atask.sanjaya.ui.data.locale.web.LocaleAuthApi
import atask.sanjaya.ui.data.locale.web.LocaleResApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UiModules {

    @Provides
    @Singleton
    fun provideLocaleResApi(
        retrofit: Retrofit
    ): LocaleResApi = retrofit.create(LocaleResApi::class.java)

    @Provides
    @Singleton
    fun provideLocaleAuthApi(
        retrofit: Retrofit
    ): LocaleAuthApi = retrofit.create(LocaleAuthApi::class.java)

    @Provides
    @Singleton
    fun provideLocaleDatabase(
        @ApplicationContext context: Context
    ): LocaleDatabase = Room.databaseBuilder(
        context,
        LocaleDatabase::class.java, LocaleDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideLocaleDao(
        database: LocaleDatabase
    ): LocaleDao = database.getLocaleDao()
}