package nl.learningtocode.weatherforecastapp.repository

import android.util.Log
import nl.learningtocode.weatherforecastapp.data.DataOrException
import nl.learningtocode.weatherforecastapp.model.Weather
import nl.learningtocode.weatherforecastapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi){

    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {

        val response = try {
            api.getWeather(query = cityQuery)

        }catch(e: Exception){
            Log.d("EXCEPTION", "getWeather : $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getWeather: $response")
        return DataOrException(data = response)
    }

}