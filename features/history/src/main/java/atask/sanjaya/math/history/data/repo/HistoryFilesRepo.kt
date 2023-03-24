package atask.sanjaya.math.history.data.repo

import atask.sanjaya.math.history.data.models.HistoryData
import kotlinx.coroutines.Deferred

interface HistoryFilesRepo {
    suspend fun getHistoriesFilesAsync(): Deferred<List<HistoryData>>
    suspend fun createHistoryFileAsync(data: HistoryData): Deferred<Boolean>
}