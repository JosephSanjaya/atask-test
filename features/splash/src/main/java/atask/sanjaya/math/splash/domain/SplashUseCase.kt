package atask.sanjaya.math.splash.domain

interface SplashUseCase {
    suspend fun initSplash(): Boolean
}