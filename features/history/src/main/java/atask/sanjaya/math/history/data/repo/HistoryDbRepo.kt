package atask.sanjaya.math.history.data.repo

import atask.sanjaya.math.history.data.models.HistoryData
import kotlinx.coroutines.Deferred

interface HistoryDbRepo {
    suspend fun getHistoriesDbAsync(): Deferred<List<HistoryData>>
    suspend fun insertHistoryDbAsync(data: HistoryData): Deferred<Boolean>
}