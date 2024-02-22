package com.zkylab.foodappcompose.feature.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.zkylab.foodappcompose.R
import com.zkylab.foodappcompose.feature.domain.model.Food
import com.zkylab.foodappcompose.ui.theme.FoodAppComposeTheme


data class MenuItem(
    val name: String,
    val price: String,
    val rating: Float,
    val imageResId: Int,
    val imageUrl: String
)

fun getMenuItems(): List<MenuItem> {
    return listOf(
        MenuItem(
            "Coca-Cola",
            "Rp7.000",
            4.8f,
            R.drawable.ic_launcher_foreground,
            "https://www.eatthis.com/wp-content/uploads/sites/4/2020/08/can-of-coke.jpg?quality=82&strip=1"
        ),
        MenuItem(
            "Fanta",
            "Rp7.000",
            4.5f,
            R.drawable.ic_launcher_background,
            "https://st2.depositphotos.com/1000647/6266/i/450/depositphotos_62669437-stock-photo-can-of-fanta-orange.jpg"
        ),
        MenuItem(
            "Oreo",
            "Rp2.000",
            4.0f,
            R.drawable.ic_launcher_foreground,
            "https://assets3.thrillist.com/v1/image/3064434/1200x630/flatten;crop_down;webp=auto;jpeg_quality=70"
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    viewModel: FoodViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            SearchBar(
                query = "Cari item",//text showed on SearchBar
//                onQueryChange = viewModel::onSearchTextChange, //update the value of searchText
//                onSearch = viewModel::onSearchTextChange, //the callback to be invoked when the input service triggers the ImeAction.Search action
//                active = isSearching, //whether the user is searching or not
//                onActiveChange
                onQueryChange = {}, //update the value of searchText
                onSearch = {}, //the callback to be invoked when the input service triggers the ImeAction.Search action
                active = false, //whether the user is searching or not
                onActiveChange = { }, //the callback to be invoked when this search bar's active state is changed
                leadingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
//                LazyColumn {
//                    items(countriesList) { country ->
//                        Text(
//                            text = country,
//                            modifier = Modifier.padding(
//                                start = 8.dp,
//                                top = 4.dp,
//                                end = 8.dp,
//                                bottom = 4.dp)
//                        )
//                    }
//                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "All",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 8.dp)
            )

            // Lazy Column
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
//                items(getMenuItems()) {
                items(state.foodItems) { food ->
                    MenuItem(food = food)
                }
            }

            // Pay Button
            Button(
                onClick = { /* Handle pay logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Pay")
                Spacer(modifier = Modifier.width(8.dp))
                Text("$100.00") // Replace with actual price
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
//fun MenuItem(item: MenuItem) {
fun MenuItem(food: Food) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Item Image
        val painter = rememberImagePainter(
            data = food.imageUrl,
            builder = {
//                transformations(CircleCrop()) // Optional: Apply transformations like CircleCrop
                crossfade(true) // Optional: Enable crossfade animation
            }
        )
        Image(
            painter = painter,
//            painter = painterResource(id = item.imageResId),
            contentDescription = null, // Set proper content description
            modifier = Modifier
                .size(100.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

//        Spacer(modifier = Modifier.width(16.dp))

        // Item Details
        Column {
            Text(
                food.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                "Rp${food.singlePrice}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 16.dp)
            )

            // Quantity Section
            var quantity by remember { mutableStateOf(1) }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                IconButton(
                    onClick = {
                        if (quantity > 1) {
                            quantity--
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.RemoveCircleOutline,
                        contentDescription = "Decrease Quantity"
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text("$quantity")

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {
                        quantity++
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircleOutline,
                        contentDescription = "Increase Quantity"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Rating and Cart Button
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("4.8", style = MaterialTheme.typography.bodySmall)


            }

            Spacer(modifier = Modifier.height(24.dp))

            FilledIconButton(onClick = {}) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "Add to Cart")
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MenuPreview() {
    FoodAppComposeTheme {
        MenuScreen()
    }
}