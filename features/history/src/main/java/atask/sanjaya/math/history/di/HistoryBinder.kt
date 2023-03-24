package atask.sanjaya.math.history.di

import atask.sanjaya.math.history.data.repo.HistoryDbDataStore
import atask.sanjaya.math.history.data.repo.HistoryDbRepo
import atask.sanjaya.math.history.data.repo.HistoryFilesDataStore
import atask.sanjaya.math.history.data.repo.HistoryFilesRepo
import atask.sanjaya.math.history.domain.HistoryInteractor
import atask.sanjaya.math.history.domain.HistoryUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryBinder {
    @Binds
    abstract fun bindHistoryDbDataStore(
        dataStore: HistoryDbDataStore
    ): HistoryDbRepo

    @Binds
    abstract fun bindHistoryFilesDataStore(
        dataStore: HistoryFilesDataStore
    ): HistoryFilesRepo

    @Binds
    abstract fun bindHistoryFilesInteractor(
        interactor: HistoryInteractor
    ): HistoryUseCase
}