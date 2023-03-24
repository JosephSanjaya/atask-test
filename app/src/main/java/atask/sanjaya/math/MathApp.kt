package atask.sanjaya.math

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * The MathApp class is the main application class for the MathApp application.
 * It extends the MultiDexApplication class and is annotated with the @AndroidEntryPoint annotation.
 * This annotation is used to enable dependency injection using Hilt.
 */
@HiltAndroidApp
class MathApp : MultiDexApplication()
