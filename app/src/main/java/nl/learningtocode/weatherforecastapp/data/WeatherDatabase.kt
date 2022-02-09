package nl.learningtocode.weatherforecastapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.learningtocode.weatherforecastapp.model.Favorite
import nl.learningtocode.weatherforecastapp.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao
}