package nl.learningtocode.weatherforecastapp.widgets

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


//@Preview
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon : ImageVector? = null,
    isMainScreen : Boolean = true,
    elevation : Dp = 0.dp,
    navController : NavController,
    onAddActionClicked : () -> Unit = {},
    onButtonClicked : () -> Unit = {})
{

    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value){
        ShowSettingsDropdownDialog(showDialog = showDialog, navController = navController)
    }

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
                  }
        },
        navigationIcon = {
                         if (icon != null){
                             Icon(imageVector = icon,
                                 contentDescription = "null",
                                tint = MaterialTheme.colors.onSecondary,
                                 modifier = Modifier.clickable
                                 {
                                     onButtonClicked.invoke()
                                 }
                             )
                         }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation)


}

@Composable
fun ShowSettingsDropdownDialog(
    showDialog: MutableState<Boolean>, 
    navController: NavController) {

    var expanded by remember { mutableStateOf(true) }
    val items = listOf("Favorite", "Settings", "About")

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
                            "Favorite" -> Icons.Default.FavoriteBorder
                            "Settings" -> Icons.Default.Settings
                            else -> Icons.Default.Info
                        },
                        contentDescription = null,
                        tint = Color.Gray)
                    Text(text = text, modifier = Modifier.clickable
                    {
                    },
                    fontWeight = FontWeight.W300)
                }
            }
        }
    }
}
