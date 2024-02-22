package com.zkylab.foodappcompose.feature.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zkylab.foodappcompose.core.util.Resource
import com.zkylab.foodappcompose.feature.domain.usecase.GetFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val getFoodUseCase: GetFoodUseCase
) : ViewModel() {

    private val _state = mutableStateOf<FoodState>(FoodState())
    val state: State<FoodState> = _state

    init {
        getFoods()
    }

    private fun getFoods() {
        getFoodUseCase().onEach { result ->
            when (result) {

                is Resource.Success -> {
                    _state.value = FoodState(foodItems = result.data ?: emptyList())
                }

                is Resource.Loading -> {
                    _state.value = FoodState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value =
                        FoodState(error = result.message ?: "An unexpected error occurred")
                }

            }
        }.launchIn(viewModelScope)
    }

}