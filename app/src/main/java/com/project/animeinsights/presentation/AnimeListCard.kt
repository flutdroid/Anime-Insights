package com.project.animeinsights.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.project.animeinsights.R
import com.project.animeinsights.data.models.AnimeData
import com.project.animeinsights.data.models.Data
import com.project.animeinsights.presentation.ui.theme.ubuntu

@Composable
fun AnimeListCard(data: Data, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp), shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = data.images.jpg.image_url,
                placeholder = painterResource(R.drawable.ic_loading),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    ),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    data.title ?: "",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 4.dp),
                    maxLines = 1,
                    fontFamily = ubuntu
                )
                Text(
                    data.type ?: "Type",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 4.dp),
                    maxLines = 1,
                    fontFamily = ubuntu

                )

                Text(
                    data.duration ?: "0",
                    fontSize = 18.sp,
                    maxLines = 1,
                    fontFamily = ubuntu
                )
                Text(
                    data.status ?: "0",
                    fontSize = 18.sp,
                    maxLines = 1,
                    fontFamily = ubuntu

                )
            }
        }
    }
}

