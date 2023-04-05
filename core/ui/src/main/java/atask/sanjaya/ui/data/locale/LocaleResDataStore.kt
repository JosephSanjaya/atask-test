package atask.sanjaya.ui.data.locale

import android.content.Context
import android.content.SharedPreferences
import atask.sanjaya.ui.data.locale.local.LocaleDao
import atask.sanjaya.ui.data.locale.model.LocaleData
import atask.sanjaya.ui.data.locale.web.LocaleResApi
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.restring.Restring
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import splitties.init.injectAsAppCtx
import java.util.*
import javax.inject.Inject

class LocaleResDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: LocaleResApi,
    private val dao: LocaleDao,
    private val pref: SharedPreferences
) : LocaleResRepo {
    override suspend fun getStringResourcesAsync(token: String): Deferred<List<LocaleData>> {
        return CoroutineScope(currentCoroutineContext()).async {
            val data = api.getResourceAsync(token).await()
            dao.saveAll(*(data.list.orEmpty().filterNotNull().toTypedArray()))
            mapResources(data.list.orEmpty().filterNotNull())
        }
    }


    private fun mapResources(data: List<LocaleData>): List<LocaleData> {
        val indonesia = data.map { locale ->
            val string = locale.id
            locale.key to (string).orEmpty()
                .replace("\\n", System.getProperty("line.separator").orEmpty())
        }
            .toTypedArray()
        val english = data.map { locale ->
            val string = locale.en
            locale.key to (string).orEmpty()
                .replace("\\n", System.getProperty("line.separator").orEmpty())
        }
            .toTypedArray()
        Restring.putStrings(
            Locale("id", "ID"),
            mapOf(*indonesia)
        )
        Restring.putStrings(
            Locale.ENGLISH,
            mapOf(*english)
        )
        Restring.wrapContext(context).also { ctx ->
            ctx.injectAsAppCtx()
        }
        AppLocale.desiredLocale =
            Locale.forLanguageTag(pref.getString("current-locale", "").orEmpty())
        return data
    }
}