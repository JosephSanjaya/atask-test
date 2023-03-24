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

class HistoryFilesDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
    private val aead: Aead
) : HistoryFilesRepo {

    override suspend fun getHistoriesFilesAsync(): Deferred<List<HistoryData>> {
        return CoroutineScope(currentCoroutineContext()).async {
            val directory = context.filesDir
            val encryptedFiles = directory.listFiles { _, name -> name.endsWith(".encrypted") }
            encryptedFiles?.mapNotNull { file ->
                runCatching {
                    val ciphertext = file.readBytes()
                    val plaintext = aead.decrypt(ciphertext, null)
                    gson.fromJson(String(plaintext, Charsets.UTF_8), HistoryData::class.java)
                }.getOrNull()
            }.orEmpty()
        }
    }

    override suspend fun createHistoryFileAsync(data: HistoryData): Deferred<Boolean> {
        return CoroutineScope(currentCoroutineContext()).async {
            // Create the encrypted file
            val directory = context.filesDir
            val fileName =
                "${LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()}.encrypted"
            val file = File(directory, fileName)
            val jsonObject = gson.toJson(data.copy(id = Random.Default.nextInt()))
            val plaintext = jsonObject.toString().toByteArray(Charsets.UTF_8)
            val ciphertext = aead.encrypt(plaintext, null)
            file.writeBytes(ciphertext)
            true
        }
    }
}