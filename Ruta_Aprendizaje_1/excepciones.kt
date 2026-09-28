//Excepciones y cancelación
fun main() {
    val numberOfPeople = 0
    val numberOfPizzas = 20
    println("Slices per person: ${numberOfPizzas / numberOfPeople}")
}


//Excepciones dentro de corrutinas
suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }  // lanza AssertionError
    "${forecast.await()} ${temperature.await()}"
}


//Manejo con try-catch

val temperature = async {
    try {
        getTemperature()
    } catch (e: AssertionError) {
        "{ No temperature found }"
    }
}

//cancelacion de corrutinas

suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }
    
    delay(200)
    temperature.cancel()   // cancela solo esta corrutina

    "${forecast.await()}"
}