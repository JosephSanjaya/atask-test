package atask.sanjaya.ui.domain

import atask.sanjaya.ui.data.locale.LocaleAuthRepo
import atask.sanjaya.ui.data.locale.LocaleResRepo
import atask.sanjaya.ui.data.locale.model.LocaleData
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class LocaleInteractor @Inject constructor(
    private val res: LocaleResRepo,
    private val auth: LocaleAuthRepo
) : LocaleUseCase {
    override suspend fun getStringResourcesAsync(): Deferred<List<LocaleData>> {
        val authToken = auth.doLoginAsync().await()
        return res.getStringResourcesAsync(authToken.token.orEmpty())
    }

}