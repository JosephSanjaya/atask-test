package atask.sanjaya.math.calculate.data.calculation.api

import kotlinx.coroutines.Deferred
import retrofit2.http.POST
import retrofit2.http.Query

interface CalculationApi {
    @POST
    fun calculateAsync(@Query("expr") expr: String): Deferred<String>
}