package nl.learningtocode.weatherforecastapp.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.learningtocode.weatherforecastapp.model.Favorite
import nl.learningtocode.weatherforecastapp.navigation.WeatherScreens
import nl.learningtocode.weatherforecastapp.screens.favorites.FavoriteViewModel


@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon : ImageVector? = null,
    isMainScreen : Boolean = true,
    elevation : Dp = 0.dp,
    navController : NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClicked : () -> Unit = {},
    onButtonClicked : () -> Unit = {})
{

    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value){
        ShowSettingsDropdownDialog(showDialog = showDialog, navController = navController)
    }

    val showIt = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    TopAppBar(
        title = {
                Text(text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(fontWeight = FontWeight.Bold,
                    fontSize = 15.sp)
                )
        },
        actions = {
                  if (isMainScreen){
                      IconButton(onClick = {
                          onAddActionClicked.invoke()
                      })
                      {
                          Icon(imageVector = Icons.Default.Search,
                              contentDescription = "Search Icon" )
                      }
                      IconButton(onClick = {
                          showDialog.value = true
                      }) {
                          Icon(imageVector = Icons.Rounded.MoreVert,
                              contentDescription = "More Icon" )
                      }
                  } else Box {}
        },
        navigationIcon = {
            if (icon != null) {
                Icon(imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable
                    {
                        onButtonClicked.invoke()
                    }
                )
            }
            if (isMainScreen) {
                val isAlreadyOnFavoriteList = favoriteViewModel
                    .favList.collectAsState().value.filter { item ->
                        (item.city == title.split(" - ")[0])
                    }
                if (isAlreadyOnFavoriteList.isNullOrEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite Icon",
                        modifier = Modifier
                            .scale(0.9f)
                            .clickable {
                                val dataList = title.split(" - ")
                                favoriteViewModel
                                    .insertFavorite(
                                        Favorite(
                                            city = dataList[0],
                                            country = dataList[1]
                                        )
                                    ).run { showIt.value = true }
                            },
                        tint = Color.Red.copy(alpha = 0.6f)
                    )
                }
            } else {
                showIt.value = false
                Box {} }

            ShowToast(context = context, showIt = showIt, title = title)
        },
        backgroundColor = Color.Transparent,
        elevation = elevation)


}

@Composable
fun ShowToast(context: Context, showIt: MutableState<Boolean>, title: String) {
    val dataList = title.split(" - ")[0]
    if (showIt.value){
        Toast.makeText(context, "$dataList added to Favorites", Toast.LENGTH_SHORT).show()
    }

}

@Composable
fun ShowSettingsDropdownDialog(
    showDialog: MutableState<Boolean>, 
    navController: NavController) {

    var expanded by remember { mutableStateOf(true) }
    val items = listOf("Favorites", "Settings", "About")

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 45.dp, right = 20.dp))
    {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White))
        {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialog.value = false
                }) {
                    Icon(
                        imageVector = when (text) {
                            "Favorites" -> Icons.Default.FavoriteBorder
                            "Settings" -> Icons.Default.Settings
                            else -> Icons.Default.Info
                        },
                        contentDescription = null,
                        tint = Color.Gray
                    )
                    Text(text = text, modifier = Modifier.clickable
                    {
                        navController.navigate(
                            when (text){
                                "Favorites" -> WeatherScreens.FAVORITE_SCREEN.name
                                "Settings" -> WeatherScreens.SETTINGS_SCREEN.name
                                else -> WeatherScreens.ABOUT_SCREEN.name
                            }
                        )
                    },
                    fontWeight = FontWeight.W300)
                }
            }
        }
    }
}
