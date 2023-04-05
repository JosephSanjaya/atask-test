package atask.sanjaya.ui.data.locale.web

import atask.sanjaya.ui.data.locale.model.LocaleToken
import atask.sanjaya.ui.data.locale.model.LocaleTokenBody
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface LocaleAuthApi {
    @POST("api/v1/auth/user/signin")
    fun doLoginAsync(
        @Body body: LocaleTokenBody
    ): Deferred<LocaleToken>
}