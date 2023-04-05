package atask.sanjaya.ui.di

import atask.sanjaya.ui.data.locale.LocaleAuthDataStore
import atask.sanjaya.ui.data.locale.LocaleAuthRepo
import atask.sanjaya.ui.data.locale.LocaleResDataStore
import atask.sanjaya.ui.data.locale.LocaleResRepo
import atask.sanjaya.ui.domain.LocaleInteractor
import atask.sanjaya.ui.domain.LocaleUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UiBinder {
    @Binds
    abstract fun bindLocaleResRepo(
        impl: LocaleResDataStore
    ): LocaleResRepo

    @Binds
    abstract fun bindLocaleAuthRepo(
        impl: LocaleAuthDataStore
    ): LocaleAuthRepo

    @Binds
    abstract fun bindLocaleUseCase(
        interactor: LocaleInteractor
    ): LocaleUseCase
}