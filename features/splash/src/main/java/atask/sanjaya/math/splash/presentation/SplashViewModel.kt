package atask.sanjaya.math.splash.presentation

import atask.sanjaya.math.splash.domain.SplashUseCase
import atask.sanjaya.network.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: SplashUseCase
) : BaseViewModel() {

    val splashState by state<Boolean?>("splash")

    fun initSplash() = executeRoutine("splash") {
        useCase.initSplash()
    }
}