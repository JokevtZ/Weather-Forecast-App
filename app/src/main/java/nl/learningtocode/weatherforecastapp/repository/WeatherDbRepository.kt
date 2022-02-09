package nl.learningtocode.weatherforecastapp.repository

import kotlinx.coroutines.flow.Flow
import nl.learningtocode.weatherforecastapp.data.WeatherDao
import nl.learningtocode.weatherforecastapp.model.Favorite
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorite() = weatherDao.deleteAllFavorite()
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)
    suspend fun getFavoriteById(city: String): Favorite = weatherDao.getFavoritesByID(city)
}