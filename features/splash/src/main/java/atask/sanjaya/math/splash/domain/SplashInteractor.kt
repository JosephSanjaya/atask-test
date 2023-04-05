package atask.sanjaya.math.splash.domain

import atask.sanjaya.ui.domain.LocaleUseCase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SplashInteractor @Inject constructor(
    private val remote: FirebaseRemoteConfig,
    private val res: LocaleUseCase
) : SplashUseCase {
    override suspend fun initSplash(): Boolean {
        remote.fetchAndActivate().await()
        res.getStringResourcesAsync().await()
        return true
    }
}