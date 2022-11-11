package monitor

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import models.Muestra
import kotlin.system.exitProcess

class Servidor {
    private val muestras = MutableStateFlow<List<Muestra>>(listOf())

    val muestrasDisponibles get() = muestras.value.size

    var contador = 1

    // Agregamos muestras
    suspend fun addMuestra(muestra: Muestra) {
        if (muestrasDisponibles == 8) {
            println("Servidor Lleno")
            delay(1500)
            contador++
        }
        println("Servidor recibe: $muestra")
        muestras.value += muestra
        contador++
        //println(contador)
        if (contador == 30) {
            // Por si se le pone un numero superior de produccion, con esto me aseguro que el programa termina antes
            exitProcess(1)
        }
    }

    // Sacamos muestras
    fun getMuestra(): Muestra {
        val muestra = muestras.value.first()
        muestras.value = muestras.value.drop(1)
        println("Muestra $muestra retirada del servidor")
        return muestra
    }
}