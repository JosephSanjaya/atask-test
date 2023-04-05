package atask.sanjaya.ui.data.locale

import atask.sanjaya.ui.data.locale.model.LocaleToken
import atask.sanjaya.ui.data.locale.model.LocaleTokenBody
import atask.sanjaya.ui.data.locale.web.LocaleAuthApi
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class LocaleAuthDataStore @Inject constructor(
    private val api: LocaleAuthApi
) : LocaleAuthRepo {
    override fun doLoginAsync(): Deferred<LocaleToken> {
        val body = LocaleTokenBody(
            "customer.mart@yopmail.com",
            "PasswordCustomer123*"
        )
        return api.doLoginAsync(body)
    }
}