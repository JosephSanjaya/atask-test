package atask.sanjaya.ui.data.locale

import atask.sanjaya.ui.data.locale.model.LocaleData
import atask.sanjaya.ui.data.locale.model.LocaleResponseData
import kotlinx.coroutines.Deferred

interface LocaleResRepo {
    suspend fun getStringResourcesAsync(token: String): Deferred<List<LocaleData>>
}