package utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image

object ImageUtils {

    private val client = HttpClient()

    private val inMemoryCache = mutableMapOf<String, ByteArray>()

    suspend fun getImageBitmapByUrl(url: String): ImageBitmap {
        val bytes = inMemoryCache.getOrPut(url) {
            client.get(url).readBytes()
        }
        val bitmap = withContext(Dispatchers.Default) {
            Image.makeFromEncoded(bytes).toComposeImageBitmap()
        }
        return bitmap
    }
}
