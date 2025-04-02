package com.project.animeinsights.presentation

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.project.animeinsights.R
import com.project.animeinsights.common.Resource
import com.project.animeinsights.presentation.ui.theme.ubuntu

@Composable
fun AnimeMainScreen(navController: NavController) {
    val animeViewModel: AnimeViewModel = hiltViewModel()
    val animeListState = animeViewModel.state.value
    val activity = (LocalContext.current as? Activity)
    var animeTextFieldValue by remember { mutableStateOf("") }
    val animeFilterList =
        animeListState.animeList?.data?.filter {
            it.title.contains(
                animeTextFieldValue,
                ignoreCase = true
            )
        }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier.clickable {
                    activity?.finish()
                })
            OutlinedTextField(animeTextFieldValue, onValueChange = {
                animeTextFieldValue = it
            }, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.06f)
                .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(12.dp),
                placeholder = {
                    Text(
                        "Search Anime",
                        fontFamily = ubuntu,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            )
        }
        if (animeListState.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        if (animeListState.isFailure.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    animeListState.isFailure,
                    fontSize = 32.sp,
                    fontFamily = ubuntu,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            animeListState.animeList?.let {
                items(animeFilterList ?: emptyList()) { result ->
                    AnimeListCard(data = result) {
                        navController.navigate(
                            Screen.AnimeDetailScreen.route + "/${result.mal_id}"
                        )
                    }
                }
            }
        }
    }
}