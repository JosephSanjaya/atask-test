package atask.sanjaya.math.splash.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.AndroidEntryPoint
import atask.sanjaya.ui.utils.repeatOnStarted
import atask.sanjaya.math.splash.R
import atask.sanjaya.math.splash.presentation.navigation.SplashExternalRoute
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

/**
 * A fragment that displays a splash screen while loading data.
 */
@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    /**
     * External route used to navigate to the main dashboard screen.
     */
    @Inject
    lateinit var router: SplashExternalRoute
    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repeatOnStarted {
            viewModel.splashState
                .distinctUntilChanged()
                .collect {
                    router.goToDashboard(requireContext())
                }
        }
        viewModel.initSplash()
    }
}
