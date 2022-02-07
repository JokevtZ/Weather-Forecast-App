package nl.learningtocode.weatherforecastapp.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import nl.learningtocode.weatherforecastapp.model.Favorite

@Dao
interface WeatherDao {
    @Query("SELECT * FROM  favorite_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite_tbl WHERE city =:city")
    suspend fun getFavoritesByID(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite_tbl")
    suspend fun deleteAllFavorite()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

}