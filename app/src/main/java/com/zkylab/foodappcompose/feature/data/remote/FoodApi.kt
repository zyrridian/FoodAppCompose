package com.zkylab.foodappcompose.feature.data.remote

import com.zkylab.foodappcompose.feature.data.remote.dto.FoodDto
import retrofit2.http.GET

interface FoodApi  {

    @GET("v1/foods")
    suspend fun getFoods(): FoodDto

}