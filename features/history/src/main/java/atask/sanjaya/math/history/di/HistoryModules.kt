package atask.sanjaya.math.history.di

import android.content.Context
import androidx.room.Room
import atask.sanjaya.math.history.data.local.HistoryDao
import atask.sanjaya.math.history.data.local.HistoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class HistoryModules {

    @Provides
    @Singleton
    fun providesLocationDb(
        @ApplicationContext context: Context
    ): HistoryDatabase = Room.databaseBuilder(
        context,
        HistoryDatabase::class.java, HistoryDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providesFavoriteDao(
        db: HistoryDatabase
    ): HistoryDao = db.historyDao()

}