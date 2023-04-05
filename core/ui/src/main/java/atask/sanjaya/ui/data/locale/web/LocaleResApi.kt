package atask.sanjaya.ui.data.locale.web

import atask.sanjaya.ui.data.locale.model.LocaleResponseData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header

interface LocaleResApi {
    @GET("api/v1/db/data/noco/p_o9fnlntul93a0h/dummy-sheet/views/dummy-sheet?limit=25000&offset=0")
    fun getResourceAsync(
        @Header("xc-auth") token: String
    ): Deferred<LocaleResponseData>
}