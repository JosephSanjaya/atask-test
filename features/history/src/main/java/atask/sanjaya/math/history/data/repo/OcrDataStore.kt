package atask.sanjaya.math.history.data.repo

import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognizer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OcrDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val recognizer: TextRecognizer,
) : OcrRepo {
    override suspend fun invoke(uri: Uri): Deferred<Text> {
        return CoroutineScope(currentCoroutineContext()).async {
            recognizer.process(InputImage.fromFilePath(context, uri)).await()
        }
    }

}