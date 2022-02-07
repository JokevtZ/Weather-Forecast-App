package nl.learningtocode.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import nl.learningtocode.weatherforecastapp.screens.about.AboutScreen
import nl.learningtocode.weatherforecastapp.screens.favorites.FavoritesScreen
import nl.learningtocode.weatherforecastapp.screens.main.MainScreen
import nl.learningtocode.weatherforecastapp.screens.splash.SplashScreen
import nl.learningtocode.weatherforecastapp.screens.main.MainViewModel
import nl.learningtocode.weatherforecastapp.screens.search.SearchScreen
import nl.learningtocode.weatherforecastapp.screens.settings.SettingsScreen

@ExperimentalComposeUiApi
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

        val route = WeatherScreens.MAIN_SCREEN.name
        composable("$route/{city}",
        arguments = listOf(
            navArgument(name = "city")
            {
                type = NavType.StringType
            }
        ))
        { navBack ->
            navBack.arguments?.getString("city").let {  city ->
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController, mainViewModel,
                city = city)
            }

        }

        composable(WeatherScreens.SEARCH_SCREEN.name)
        {
            SearchScreen(navController = navController)
        }

        composable(WeatherScreens.FAVORITE_SCREEN.name)
        {
            FavoritesScreen(navController = navController)
        }

        composable(WeatherScreens.SETTINGS_SCREEN.name)
        {
            SettingsScreen(navController = navController)
        }

        composable(WeatherScreens.ABOUT_SCREEN.name)
        {
            AboutScreen(navController = navController)
        }
    }
}