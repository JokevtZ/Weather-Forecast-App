package nl.learningtocode.weatherforecastapp.network

import nl.learningtocode.weatherforecastapp.model.Weather
import nl.learningtocode.weatherforecastapp.utils.Constants.KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi{
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = KEY
    ): Weather
}