package monitor

import kotlinx.coroutines.flow.MutableStateFlow
import models.Muestra
import kotlin.system.exitProcess

class Servidor {
    private val muestras = MutableStateFlow<List<Muestra>>(listOf())

    val muestrasDisponibles get() = muestras.value.size

    // Agregamos muestras
    suspend fun addMuestra(muestra: Muestra) {
        if (muestrasDisponibles == 8) {
            println("Servidor Lleno")
            // Forma medio viable de que termine el programa, teniendo en cuenta la velocidad de lectura de las terminales
            exitProcess(10)
            /* En caso de hacer que la salida del programa sea mas dinamica, puede hacerse gracias a un contador o
            * un Boolean, y aplicandole un delay.
            */
        }
        println("Servidor recibe: $muestra")
        muestras.value += muestra
    }

    // Sacamos muestras
    suspend fun getMuestra(): Muestra {
        val muestra = muestras.value.first()
        muestras.value = muestras.value.drop(1)
        println("Muestra $muestra retirada del servidor")
        return muestra
    }
}