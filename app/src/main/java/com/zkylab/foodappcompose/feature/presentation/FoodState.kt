package com.zkylab.foodappcompose.feature.presentation

import com.zkylab.foodappcompose.feature.domain.model.Food

data class FoodState(
    val foodItems: List<Food> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)