package nl.learningtocode.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.learningtocode.weatherforecastapp.screens.main.MainScreen
import nl.learningtocode.weatherforecastapp.screens.SplashScreen
import nl.learningtocode.weatherforecastapp.screens.main.MainViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = WeatherScreens.SPLASH_SCREEN.name )
    {
        composable(WeatherScreens.SPLASH_SCREEN.name)
        {
            SplashScreen(navController = navController)
        }

        composable(WeatherScreens.MAIN_SCREEN.name)
        {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController, mainViewModel)
        }
    }
}