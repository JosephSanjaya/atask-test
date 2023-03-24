package atask.sanjaya.math.history.data.repo

import android.content.Context
import atask.sanjaya.math.history.data.local.HistoryDao
import atask.sanjaya.math.history.data.models.HistoryData
import com.google.crypto.tink.Aead
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject
import kotlin.random.Random

class HistoryDbDataStore @Inject constructor(
    private val dao: HistoryDao
) : HistoryDbRepo {

    override suspend fun getHistoriesDbAsync(): Deferred<List<HistoryData>> {
        return CoroutineScope(currentCoroutineContext()).async {
            dao.getAll()
        }
    }

    override suspend fun insertHistoryDbAsync(data: HistoryData): Deferred<Boolean> {
        return CoroutineScope(currentCoroutineContext()).async {
            dao.insertAll(data)
            true
        }
    }
}