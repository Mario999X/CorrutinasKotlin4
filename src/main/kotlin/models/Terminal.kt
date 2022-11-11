package models

import controller.FileController
import kotlinx.coroutines.delay
import monitor.Servidor

data class Terminal(
    var nombre: String,
    val maxMuestras: Int,
) {

    private var muestrasRecogidas = 0

    private var file = FileController.init()

    // run
    suspend fun recogeMuestra(servidor: Servidor) {
        while (muestrasRecogidas < maxMuestras) {
            if (servidor.muestrasDisponibles > 0) {
                val muestra = servidor.getMuestra()
                println("------------------------------------------------")
                println("La terminal: $nombre ha recogido la muestra: $muestra ")
                println("------------------------------------------------")
                muestrasRecogidas++
                if (muestra.porcentajePureza > 60) {
                    println("-La terminal: $nombre escribiendo en fichero...")
                    file.appendText("La terminal: $nombre ha recogido la muestra: $muestra \n")

                    println("\t --Informacion agregada de la terminal: $nombre")
                }
            }
            println("Servidor vacio, Terminal $nombre esperado...")
            delay((1500..3000).random().toLong())
        }
        println("\t \t -La terminal: $nombre ha llegado a su limite.")
    }
}
