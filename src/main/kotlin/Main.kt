import controller.FileController
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.Androide
import models.Terminal
import monitor.Servidor

private const val PRODUCCION = 10
private const val MAX_MUESTRAS = 5
fun main() = runBlocking {

    limpiarTxt()

    val servidor = Servidor()

    val androides = listOf(Androide("R2D2", PRODUCCION, servidor), Androide("BB8", PRODUCCION, servidor))

    val terminales = listOf(Terminal("Luke", MAX_MUESTRAS, servidor), Terminal("Leia", MAX_MUESTRAS, servidor))

    producerConsumer(androides, terminales)

}

suspend fun producerConsumer(a: List<Androide>, t: List<Terminal>) = coroutineScope {
    a.forEach {
        launch {
            it.produceMuestras()
        }
    }

    t.forEach {
        launch {
            it.recogeMuestra()
        }
    }
}

private fun limpiarTxt() {

    val file = FileController.init()

    file.writeText("")
}