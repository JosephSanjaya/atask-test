package atask.sanjaya.math.splash.presentation

import android.content.SharedPreferences
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.restring.LocaleProvider
import java.util.*
import javax.inject.Inject

/*
 * Designed and developed by Joseph Sanjaya, S.T., M.Kom. on 9/7/2022
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */
class AppLocaleProvider @Inject constructor(
    private val pref: SharedPreferences
) : LocaleProvider {
    override val isInitial
        get() = AppLocale.isInitial

    override var currentLocale: Locale
        get() = Locale.forLanguageTag(pref.getString("current-locale", "").orEmpty())
        set(value) {
            AppLocale.desiredLocale = value
        }
}
