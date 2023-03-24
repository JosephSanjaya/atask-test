package atask.sanjaya.math.calculate.data.recognizer

import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognizer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RecognizerDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val client: TextRecognizer
) : RecognizerRepo {
    override suspend fun recognizeAsync(imageUri: Uri): Deferred<String> {
        return CoroutineScope(currentCoroutineContext()).async {
            val inputStream = context.contentResolver.openInputStream(imageUri)
            // Create an InputImage object from the input stream
            val buffer = inputStream?.readBytes()
            val image = InputImage.fromByteArray(
                buffer!!, /* image width */
                640, /* image height */
                480, /* image rotation */
                0,
                InputImage.IMAGE_FORMAT_NV21
            )
            withContext(Dispatchers.IO) {
                inputStream.close()
            }
            client.process(image).await().text
        }
    }
}