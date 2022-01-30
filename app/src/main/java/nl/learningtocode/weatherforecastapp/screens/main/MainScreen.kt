package nl.learningtocode.weatherforecastapp.screens.main

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.learningtocode.weatherforecastapp.data.DataOrException
import nl.learningtocode.weatherforecastapp.model.Weather

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()){
    ShowData(mainViewModel)
}

@Composable
fun ShowData(mainViewModel: MainViewModel){

    val weatherData = produceState< DataOrException < Weather, Boolean, Exception>>(initialValue = DataOrException(loading = true))
    {
        value = mainViewModel.data.value
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    }else if (weatherData.loading != null) {
        Text(text = "Main Screen ${weatherData.data!!.name}")
    }


}