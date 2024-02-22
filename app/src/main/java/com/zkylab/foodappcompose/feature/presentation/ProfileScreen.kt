package com.zkylab.foodappcompose.feature.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zkylab.foodappcompose.R
import com.zkylab.foodappcompose.ui.theme.FoodAppComposeTheme

@Composable
fun ProfileScreen() {
    Column {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "profile picture",
                modifier = Modifier.padding(end = 16.dp)
            )
            Column {
                Text(text = "Andrian")
                Text(text = "0881023641280")
                Text(text = "Jalan Martadinata No. 14, Sumedang Jawa Barat")
            }
        }
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Keluar")
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePreview() {
    FoodAppComposeTheme {
        ProfileScreen()
    }
}