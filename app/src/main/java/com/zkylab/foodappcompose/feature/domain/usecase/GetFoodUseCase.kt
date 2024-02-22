package com.zkylab.foodappcompose.feature.domain.usecase

import com.zkylab.foodappcompose.core.util.Resource
import com.zkylab.foodappcompose.feature.domain.model.Food
import com.zkylab.foodappcompose.feature.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class GetFoodUseCase(
    private val repository: FoodRepository
) {
    operator fun invoke(): Flow<Resource<List<Food>>> {
        return repository.getFoods()
    }
}