package atask.sanjaya.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import atask.sanjaya.common.domain.config.ApiTokenConfigInteractor
import atask.sanjaya.common.domain.config.ApiTokenConfigUseCase

/**
 * Dagger module for binding common dependencies at the application level.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CommonBinder {
    /**
     * Binds an [ApiTokenConfigUseCase] implementation to the [ApiTokenConfigInteractor] dependency.
     *
     * @param interactor The interactor implementation to be used by the use case.
     * @return An instance of [ApiTokenConfigUseCase].
     */
    @Binds
    abstract fun bindApiTokenUseCase(
        interactor: ApiTokenConfigInteractor
    ): ApiTokenConfigUseCase
}
