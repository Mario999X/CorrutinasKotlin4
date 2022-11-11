package models

import kotlinx.coroutines.delay
import monitor.Servidor

data class Androide(
    var nombre: String,
    val servidor: Servidor
) {
    // run
    suspend fun produceMuestras(maxMuestras: Int) {
        for (i in 1..maxMuestras) {
            val muestra = Muestra(i, nombre)
            servidor.addMuestra(muestra)
            println(" ||Androide $nombre -> Muestra $muestra")
            delay(1500)
        }
    }
}
