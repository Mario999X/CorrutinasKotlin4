package models

import kotlinx.coroutines.delay
import monitor.Servidor

data class Androide(
    var nombre: String,
    val maxMuestras: Int,
    val servidor: Servidor
) {
    suspend fun produceMuestras() {
        for (i in 1..maxMuestras) {
            val muestra = Muestra(i, nombre)
            servidor.addMuestra(muestra)
            println(" ||Androide $nombre -> Muestra $muestra")
            delay(1500)
        }
    }
}
