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

    val androides = listOf(Androide("R2D2", servidor), Androide("BB8", servidor))

    val terminales = listOf(Terminal("Luke", MAX_MUESTRAS), Terminal("Leia", MAX_MUESTRAS))

    producerConsumer(androides, terminales, servidor)

}

suspend fun producerConsumer(a: List<Androide>, t: List<Terminal>, s: Servidor) = coroutineScope {
    a.forEach {
        launch {
            it.produceMuestras(PRODUCCION)
        }
    }

    t.forEach {
        launch {
            it.recogeMuestra(s)
        }
    }
}

private fun limpiarTxt() {

    val file = FileController.init()

    file.writeText("")
}