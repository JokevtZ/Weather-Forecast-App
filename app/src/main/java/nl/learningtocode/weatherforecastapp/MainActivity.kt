package nl.learningtocode.weatherforecastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import nl.learningtocode.weatherforecastapp.navigation.WeatherNavigation
import nl.learningtocode.weatherforecastapp.ui.theme.WeatherForecastAppTheme

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun WeatherApp(){

    WeatherForecastAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize())
        {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment =Alignment.CenterHorizontally )
            {
                WeatherNavigation()
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherForecastAppTheme {
    }
}