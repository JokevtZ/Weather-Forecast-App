package nl.learningtocode.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.learningtocode.weatherforecastapp.screens.MainScreen
import nl.learningtocode.weatherforecastapp.screens.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SPLASH_SCREEN.name )
    {
        composable(WeatherScreens.SPLASH_SCREEN.name)
        {
            SplashScreen(navController = navController)
        }

        composable(WeatherScreens.MAIN_SCREEN.name)
        {
            MainScreen(navController = navController)
        }
    }
}