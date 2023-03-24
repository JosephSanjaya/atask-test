package atask.sanjaya.math.calculate.data.recognizer

import android.net.Uri
import kotlinx.coroutines.Deferred

interface RecognizerRepo {
    suspend fun recognizeAsync(imageUri: Uri): Deferred<String>
}