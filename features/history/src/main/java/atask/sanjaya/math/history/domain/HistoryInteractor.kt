package atask.sanjaya.math.history.domain

import atask.sanjaya.math.history.data.models.HistoryData
import atask.sanjaya.math.history.data.repo.HistoryDbRepo
import atask.sanjaya.math.history.data.repo.HistoryFilesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import javax.inject.Inject

class HistoryInteractor @Inject constructor(
    private val db: HistoryDbRepo,
    private val files: HistoryFilesRepo
) : HistoryUseCase {
    override suspend fun getHistoriesAsync(): Deferred<List<HistoryData>> {
        return CoroutineScope(currentCoroutineContext()).async {
            db.getHistoriesDbAsync().await() + files.getHistoriesFilesAsync().await()
        }
    }

    override suspend fun insertHistoryAsync(historyData: HistoryData): Deferred<Boolean> {
        return db.insertHistoryDbAsync(historyData)
    }

    override suspend fun createHistoryAsync(historyData: HistoryData): Deferred<Boolean> {
        return files.createHistoryFileAsync(historyData)
    }
}