package nl.learningtocode.weatherforecastapp.screens.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.learningtocode.weatherforecastapp.data.DataOrException
import nl.learningtocode.weatherforecastapp.model.Weather
import nl.learningtocode.weatherforecastapp.model.WeatherItem
import nl.learningtocode.weatherforecastapp.navigation.WeatherScreens
import nl.learningtocode.weatherforecastapp.screens.settings.SettingsViewModel
import nl.learningtocode.weatherforecastapp.utils.formatDate
import nl.learningtocode.weatherforecastapp.utils.formatDecimals
import nl.learningtocode.weatherforecastapp.widgets.*

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    city: String?
){
    val unitFromDb = settingsViewModel.unitList.collectAsState().value

    var unit by remember {
        mutableStateOf("imperial")
    }

    var isImperial by remember {
        mutableStateOf(false)
    }

    if (!unitFromDb.isNullOrEmpty()){
        unit = unitFromDb[0].unit.split(" ")[0].lowercase()
        isImperial = unit == "imperial"

        val weatherData = produceState< DataOrException <Weather, Boolean, Exception>>(initialValue = DataOrException(loading = true))
        {
            value = mainViewModel.getWeatherData(city = city.toString(),
                    units = unit)
        }.value

        if (weatherData.loading == true) {
            CircularProgressIndicator()
        }else if (weatherData.data != null) {
            MainScaffold(weather = weatherData.data!!,
                navController = navController, isImperial = isImperial)
        }
    }

}

@Composable
fun MainScaffold(weather: Weather, navController: NavController, isImperial: Boolean){
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + " - ${weather.city.country}",
            navController = navController,
            onAddActionClicked = {
                                 navController.navigate(WeatherScreens.SEARCH_SCREEN.name)
            },
            elevation = 5.dp){
            Log.d(TAG, "MainScaffold: ButtonClicked")
        }
    })
    {
        MainContent(data = weather, isImperial = isImperial)
    }
}

@Composable
fun MainContent(data: Weather, isImperial: Boolean){

    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Column(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = formatDate(weatherItem.dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(6.dp)
        )
        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
            shape =  CircleShape,
            color = Color(0xFF7C61F0)
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                //Image
            )
            {
                WeatherStateImage(imageUrl)

                Text(text = formatDecimals(weatherItem.temp.day) + "º",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold)
                    Text(text = weatherItem.weather[0].main,
                        fontStyle = FontStyle.Italic
                    )
            }
        }
        HumidityWindPressureRow(weather = weatherItem, isImperial = isImperial)
        Divider()
        SunsetSunriseRow(weather = weatherItem)
        Text(text = "Week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = Color(0xFFACACF0),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(modifier = Modifier
                .padding(2.dp),
            contentPadding = PaddingValues(1.dp))
            {
                items(items = data.list) {item: WeatherItem ->
                    WeatherDetailRow(weather = item)
                }
            }
        }
    }
}

