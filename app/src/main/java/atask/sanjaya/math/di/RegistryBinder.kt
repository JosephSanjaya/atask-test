package atask.sanjaya.math.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * A Dagger module that binds implementations of Route to their respective Routers.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RegistryBinder {
//    @Binds
//    abstract fun bindDashboardGetter(
//        impl: DashboardContentGetterImpl
//    ): DashboardContentGetter
}
