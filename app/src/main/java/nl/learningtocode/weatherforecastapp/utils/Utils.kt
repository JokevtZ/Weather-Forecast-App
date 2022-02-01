package nl.learningtocode.weatherforecastapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun formatDate(timestamp: Int): String{
    val simpleDateFormat = SimpleDateFormat("EEE, d MMM")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return simpleDateFormat.format(date)
}

@SuppressLint("SimpleDateFormat")
fun simpleDateFormat(timestamp: Int): String {
    val simpleDateFormat = SimpleDateFormat("hh:mm:aa")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return simpleDateFormat.format(date)
}

fun formatDecimals(item : Double): String{
    return " % 0f".format(item)
}