package nl.learningtocode.weatherforecastapp.screens.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.CheckboxDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import nl.learningtocode.weatherforecastapp.R
import nl.learningtocode.weatherforecastapp.data.DataOrException
import nl.learningtocode.weatherforecastapp.model.Weather
import nl.learningtocode.weatherforecastapp.model.WeatherItem
import nl.learningtocode.weatherforecastapp.utils.formatDate
import nl.learningtocode.weatherforecastapp.utils.formatDecimals
import nl.learningtocode.weatherforecastapp.utils.simpleDateFormat
import nl.learningtocode.weatherforecastapp.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()){

    val weatherData = produceState< DataOrException <Weather, Boolean, Exception>>(initialValue = DataOrException(loading = true))
    {
        value = mainViewModel.getWeatherData(city = "Lisse")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    }else if (weatherData.data != null) {
        MainScaffold(weather = weatherData.data!!, navController = navController)
    }
}

@Composable
fun MainScaffold(weather : Weather, navController : NavController){
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + " - ${weather.city.country}",
            navController = navController,
            elevation = 5.dp){
            Log.d(TAG, "MainScaffold: ButtonClicked")
        }
    })
    {
        MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: Weather){

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
            color = Color(0xFFFFC400)
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
        HumidityWindPressureRow(weather = weatherItem)
        Divider()
        SunsetSunriseRow(weather = weatherItem)
    }
}

@Composable
fun SunsetSunriseRow(weather: WeatherItem) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise Icon",
                modifier = Modifier
                    .size(25.dp),
            )
            Text(
                text = simpleDateFormat(weather.sunrise),
                style = MaterialTheme.typography.caption
            )
        }

        Row() {
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset Icon",
                modifier = Modifier
                    .size(25.dp),
            )
            Text(
                text = simpleDateFormat(weather.sunset),
                style = MaterialTheme.typography.caption
            )
        }
    }

}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier
            .padding(4.dp))
        {
            Icon(painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity Icon",
                modifier = Modifier
                    .size(20.dp))
            Text(text = "${weather.humidity}%",
            style = MaterialTheme.typography.caption)
        }

        Row() {
            Icon(painter = painterResource(id = R.drawable.pressure),
                contentDescription = "Wind Icon",
                modifier = Modifier
                    .size(20.dp))
            Text(text = "${weather.pressure} bar",
                style = MaterialTheme.typography.caption)
        }

        Row() {
            Icon(painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind Icon",
                modifier = Modifier
                    .size(20.dp))
            Text(text = "${weather.humidity} km/h",
                style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {

    Image(painter = rememberImagePainter(imageUrl),
        contentDescription = "icon image",
        modifier = Modifier
            .size(80.dp))
}
