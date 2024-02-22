package com.zkylab.foodappcompose.feature.data.repository

import com.zkylab.foodappcompose.core.util.Resource
import com.zkylab.foodappcompose.feature.data.remote.FoodApi
import com.zkylab.foodappcompose.feature.data.remote.dto.toFood
import com.zkylab.foodappcompose.feature.domain.model.Food
import com.zkylab.foodappcompose.feature.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodRepositoryImpl(
    private val api: FoodApi
) : FoodRepository {
    override fun getFoods(): Flow<Resource<List<Food>>> = flow {
        emit(Resource.Loading())
        val foods = api.getFoods().data.map { it.toFood() }
        emit(Resource.Loading(data = foods))
        emit(Resource.Success(foods))
    }
}