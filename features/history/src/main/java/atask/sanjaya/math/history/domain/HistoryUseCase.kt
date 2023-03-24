package atask.sanjaya.math.history.domain

import atask.sanjaya.math.history.data.models.HistoryData
import kotlinx.coroutines.Deferred

interface HistoryUseCase {
    suspend fun getHistoriesAsync(): Deferred<List<HistoryData>>
    suspend fun insertHistoryAsync(historyData: HistoryData): Deferred<Boolean>
    suspend fun createHistoryAsync(historyData: HistoryData): Deferred<Boolean>
}