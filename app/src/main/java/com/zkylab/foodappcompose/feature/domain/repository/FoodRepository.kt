package com.zkylab.foodappcompose.feature.domain.repository

import com.zkylab.foodappcompose.core.util.Resource
import com.zkylab.foodappcompose.feature.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getFoods(): Flow<Resource<List<Food>>>
}