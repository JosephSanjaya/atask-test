package atask.sanjaya.math.splash.di

import atask.sanjaya.math.splash.domain.SplashInteractor
import atask.sanjaya.math.splash.domain.SplashUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SplashBinder {
    @Binds
    abstract fun bindSplashUseCase(
        interctor: SplashInteractor
    ): SplashUseCase
}