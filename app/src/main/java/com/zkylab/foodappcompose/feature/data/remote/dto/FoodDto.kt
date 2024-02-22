package com.zkylab.foodappcompose.feature.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.zkylab.foodappcompose.feature.domain.model.Food
import java.math.BigInteger
import java.util.Date

data class FoodDto(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: List<FoodItem>
)

data class FoodItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("expired_date")
    val expiredDate: Date?,
    @SerializedName("single_price")
    val singlePrice: BigInteger,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("image_url")
    val imageUrl: String,
) {

}

fun FoodItem.toFood(): Food {
    return Food(
        id = id,
        name = name,
        code = code,
        expiredDate = expiredDate,
        singlePrice = singlePrice,
        quantity = quantity,
        unit = unit,
        imageUrl = imageUrl
    )
}
