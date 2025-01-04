package com.example.data.source

import com.example.data.response.CityResponse
import com.example.data.response.DetailWeatherByCity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestService {
    @GET("geo/1.0/direct?")
    suspend fun getCitiesSearch(@Query("q") cityName: String, @Query("limit") limit: Int?): Response<List<CityResponse>>

    @GET("data/3.0/onecall?")
    suspend fun getDetailWeatherByCity(@Query("lat") latitude: Double, @Query("lon") longitude: Double): Response<DetailWeatherByCity>
}