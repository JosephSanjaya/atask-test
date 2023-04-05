package atask.sanjaya.ui.data.locale

import atask.sanjaya.ui.data.locale.model.LocaleToken
import kotlinx.coroutines.Deferred

interface LocaleAuthRepo {
    fun doLoginAsync(): Deferred<LocaleToken>
}