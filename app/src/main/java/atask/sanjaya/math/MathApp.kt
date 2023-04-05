package atask.sanjaya.math

import androidx.multidex.MultiDexApplication
import atask.sanjaya.math.splash.presentation.AppLocaleProvider
import dagger.hilt.android.HiltAndroidApp
import dev.b3nedikt.restring.Restring
import dev.b3nedikt.reword.RewordInterceptor
import dev.b3nedikt.viewpump.ViewPump
import javax.inject.Inject

/**
 * The MathApp class is the main application class for the MathApp application.
 * It extends the MultiDexApplication class and is annotated with the @AndroidEntryPoint annotation.
 * This annotation is used to enable dependency injection using Hilt.
 */
@HiltAndroidApp
class MathApp : MultiDexApplication() {

    @Inject
    lateinit var localeProvider: AppLocaleProvider
    override fun onCreate() {
        super.onCreate()
        Restring.init(this)
        ViewPump.init(RewordInterceptor)
        Restring.localeProvider = localeProvider
    }
}
