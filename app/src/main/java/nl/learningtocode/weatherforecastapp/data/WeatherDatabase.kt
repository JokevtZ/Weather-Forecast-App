package nl.learningtocode.weatherforecastapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.learningtocode.weatherforecastapp.data.dao.WeatherDao
import nl.learningtocode.weatherforecastapp.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao
}