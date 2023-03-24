package atask.sanjaya.math.calculate.data.calculation

import atask.sanjaya.math.calculate.data.calculation.api.CalculationApi
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class CalculationDataStore @Inject constructor(
    private val api: CalculationApi
) : CalculationRepo {
    override fun calculateAsync(expr: String): Deferred<String> {
        return api.calculateAsync(expr)
    }
}