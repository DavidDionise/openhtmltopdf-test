import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.util.stream.Collectors

fun main() {
    PdfGenerator().pdfFromHtml("website/index.html")
}

class PdfGenerator {
    fun pdfFromHtml(fileName: String) {
        val outputStream = javaClass.getResource("output.pdf").file
        val websiteUri = "file://${javaClass.getResource("website").path}/website"
        val input = fileName.readContents()
        val builder = PdfRendererBuilder()
        builder.withHtmlContent(input, "$websiteUri")
        builder.toStream(FileOutputStream(outputStream))
        builder.run()
    }
}

fun String.readContents(): String {
    val imageResponseStream = ClassLoader.getSystemResourceAsStream(this)
    return BufferedReader(InputStreamReader(imageResponseStream))
        .lines().collect(Collectors.joining("\n"))
}