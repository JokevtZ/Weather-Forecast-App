package nl.learningtocode.weatherforecastapp.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nl.learningtocode.weatherforecastapp.data.DataOrException
import nl.learningtocode.weatherforecastapp.model.Weather
import nl.learningtocode.weatherforecastapp.repository.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel(){

    suspend fun getWeather(city: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city)
    }

}
