package nl.learningtocode.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.learningtocode.weatherforecastapp.screens.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SPLASH_SCREEN.name )
    {
        composable(WeatherScreens.SPLASH_SCREEN.name)
        {
            WeatherSplashScreen(navController = navController)
        }
    }
}