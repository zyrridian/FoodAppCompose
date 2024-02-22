package com.zkylab.foodappcompose.feature.domain.model

import com.google.gson.annotations.SerializedName
import java.math.BigInteger
import java.util.Date

data class Food(
    val id: Int,
    val name: String,
    val code: String,
    val expiredDate: Date?,
    val singlePrice: BigInteger,
    val quantity: Int,
    val unit: String,
    val imageUrl: String,
)
