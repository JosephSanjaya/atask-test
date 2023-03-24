package atask.sanjaya.math.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import atask.sanjaya.math.location.presentation.router.LocationRouter
import atask.sanjaya.math.router.location.LocationRouterImpl
import atask.sanjaya.math.router.splash.SplashExternalRouterImpl
import atask.sanjaya.math.splash.presentation.navigation.SplashExternalRoute

/**
 * A Dagger module that binds implementations of Route to their respective Routers.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RouterBinder {
    /**
     * Binds [SplashExternalRouterImpl] to [SplashExternalRoute].
     *
     * @param impl The implementation of [SplashExternalRoute] to bind.
     * @return The bound [SplashExternalRoute].
     */
    @Binds
    abstract fun bindSplashExternal(
        impl: SplashExternalRouterImpl
    ): SplashExternalRoute

    @Binds
    abstract fun bindLocRouter(
        impl: LocationRouterImpl
    ): LocationRouter
}
