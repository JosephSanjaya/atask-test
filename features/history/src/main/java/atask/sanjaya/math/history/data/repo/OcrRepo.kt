package atask.sanjaya.math.history.data.repo

import android.net.Uri
import com.google.mlkit.vision.text.Text
import kotlinx.coroutines.Deferred

interface OcrRepo {
    suspend operator fun invoke(uri: Uri): Deferred<Text>
}