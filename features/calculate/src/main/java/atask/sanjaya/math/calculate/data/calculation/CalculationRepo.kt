package atask.sanjaya.math.calculate.data.calculation

import atask.sanjaya.math.calculate.data.calculation.models.CalculationData
import kotlinx.coroutines.Deferred

interface CalculationRepo {
    fun calculateAsync(expr: String): Deferred<String>
}