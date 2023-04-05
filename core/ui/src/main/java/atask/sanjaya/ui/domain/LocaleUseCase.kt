package atask.sanjaya.ui.domain

import atask.sanjaya.ui.data.locale.model.LocaleData
import kotlinx.coroutines.Deferred

interface LocaleUseCase {
    suspend fun getStringResourcesAsync(): Deferred<List<LocaleData>>
}