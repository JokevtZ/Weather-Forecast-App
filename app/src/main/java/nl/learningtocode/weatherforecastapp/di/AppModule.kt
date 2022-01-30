package nl.learningtocode.weatherforecastapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.learningtocode.weatherforecastapp.network.WeatherApi
import nl.learningtocode.weatherforecastapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Retrofit dependency injection
@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}