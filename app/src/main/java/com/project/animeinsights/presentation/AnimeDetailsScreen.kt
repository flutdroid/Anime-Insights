package com.project.animeinsights.presentation

import android.icu.text.DateFormat
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

import com.project.animeinsights.R
import com.project.animeinsights.data.models.Data
import com.project.animeinsights.presentation.ui.theme.ubuntu
import java.util.Date

@Composable
fun AnimeDetailScreen(navController: NavController) {
    val animeViewModel: AnimeViewModel = hiltViewModel()
    val animeState = animeViewModel.animeDetailsState.value
    val animeDetailsData = animeState.animeDetails?.data
    if (animeState.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
    if (animeState.isFailure.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                animeState.isFailure,
                fontSize = 32.sp,
                fontFamily = ubuntu,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
    }
    animeDetailsData?.let {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Image(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .clickable {
                            navController.popBackStack()
                        })
                if (it.trailer.embed_url != null) {
                    val context = LocalContext.current
                    val youtubeUrl = it.trailer.embed_url
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight(0.4f)
                            .padding(vertical = 12.dp)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        AndroidView(
                            factory = {
                                WebView(context).apply {
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                    )
                                    settings.javaScriptEnabled = true
                                    settings.mediaPlaybackRequiresUserGesture =
                                        false // Auto play videos
                                    webViewClient = WebViewClient()
                                    webChromeClient = WebChromeClient()
                                    setBackgroundColor(0xFF000000.toInt())
                                    loadUrl(youtubeUrl)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillParentMaxHeight(0.4f)
                                .padding(vertical = 12.dp)
                                .clip(RoundedCornerShape(12.dp))

                        )
                    }

                } else {
                    AsyncImage(
                        model = it.images.jpg.image_url,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .fillParentMaxHeight(0.4f),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        it.title,
                        fontSize = 24.sp,
                        fontFamily = ubuntu,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        it.title_japanese,
                        fontSize = 24.sp,
                        fontFamily = ubuntu,
                        fontWeight = FontWeight.Bold
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = Color.Magenta)
                        )
                        Text(it.score.toString(), fontSize = 20.sp, fontFamily = ubuntu)
                        Text(
                            " (" + it.scored_by.toString() + " ratings)",
                            fontSize = 20.sp,
                            fontFamily = ubuntu
                        )

                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            it.type,
                            fontSize = 20.sp,
                            fontFamily = ubuntu,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            " . ",
                            fontSize = 20.sp,
                            fontFamily = ubuntu,
                            fontWeight = FontWeight.Bold
                        )
                        Text(it.status, fontSize = 20.sp, fontFamily = ubuntu)
                    }
                    Text(
                        "Episodes . ${it.aired.prop.from.day}," +
                                "${it.aired.prop.from.month}," +
                                " ${it.aired.prop.from.year}" +
                                " to " +
                                "${it.aired.prop.to.day}," +
                                "${it.aired.prop.to.month}," +
                                "${it.aired.prop.to.year}",
                        fontSize = 20.sp,
                        fontFamily = ubuntu
                    )
                    Text(
                        "Synopsis",
                        fontSize = 24.sp,
                        fontFamily = ubuntu,
                        fontWeight = FontWeight.Bold
                    )
                    Text(it.synopsis, fontSize = 20.sp, fontFamily = ubuntu)

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(0.5f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Origin",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = ubuntu
                                )
                                Text(it.source, fontSize = 20.sp, fontFamily = ubuntu)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Duration",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    fontFamily = ubuntu
                                )
                                Text("Origin", fontSize = 20.sp, fontFamily = ubuntu)
                            }
                        }
                        Column(modifier = Modifier.weight(0.5f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Studio",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    fontFamily = ubuntu
                                )
                                Text("Origin", fontSize = 20.sp, fontFamily = ubuntu)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "Genres",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    fontFamily = ubuntu
                                )
                                Text("Origin", fontSize = 20.sp, fontFamily = ubuntu)
                            }
                        }
                    }
                }
            }
        }
    }
}