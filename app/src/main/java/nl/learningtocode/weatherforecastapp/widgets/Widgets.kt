package nl.learningtocode.weatherforecastapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import nl.learningtocode.weatherforecastapp.R
import nl.learningtocode.weatherforecastapp.model.WeatherItem
import nl.learningtocode.weatherforecastapp.utils.formatDate
import nl.learningtocode.weatherforecastapp.utils.formatDecimals
import nl.learningtocode.weatherforecastapp.utils.simpleDateFormat

@Composable
fun WeatherDetailRow(weather:WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(
            topEnd = CornerSize(6.dp)
        ),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            // weather list of days
            Text(
                text = formatDate(
                    weather.dt
                )
                    .split(",")[0],
                modifier = Modifier.padding(start = 5.dp)
            )
            //weather icon
            WeatherStateImage(imageUrl = imageUrl)
            // weather description
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFF9F8EE6)
            )
            {
                Text(
                    text = weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            Text(text = buildAnnotatedString
            {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue.copy(alpha = 0.7f),
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(formatDecimals(weather.temp.max) + "º")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Gray.copy(alpha = 0.9f),
                    )
                ) {
                    append(formatDecimals(weather.temp.min) + "º")
                }
            })
        }
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
fun HumidityWindPressureRow(weather: WeatherItem, isImperial: Boolean) {
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
            Text(text = "${weather.pressure} " +
                    if (isImperial)"psi"
                    else "bar",
                style = MaterialTheme.typography.caption)
        }

        Row() {
            Icon(painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind Icon",
                modifier = Modifier
                    .size(20.dp))
            Text(text = "${weather.speed} " +
                if (isImperial)"mph"
                else "m/s",
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
