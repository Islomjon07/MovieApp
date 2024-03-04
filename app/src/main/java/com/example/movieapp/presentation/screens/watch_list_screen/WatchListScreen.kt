package com.example.movieapp.presentation.screens.watch_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.data.cloud.utils.Constants
import com.example.movieapp.data.cloud.utils.Constants.POSTER_PATH_URL
import com.example.movieapp.presentation.component.LoadingScreen
import com.example.movieapp.presentation.component.NoConnectionScreen
import com.example.movieapp.presentation.models.details.DetailsUiModel
import com.example.movieapp.presentation.screens.seach_screen.component.SearchScreenItem
import com.example.movieapp.presentation.theme.dp10
import com.example.movieapp.presentation.theme.dp11
import com.example.movieapp.presentation.theme.dp4
import com.example.movieapp.presentation.theme.dp60
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WatchListScreen(
    uiStateFlow: StateFlow<WatchListScreenUiState>,
    navigateToDetails: (Int) -> Unit,
    callBackPopBackStake: () -> Unit,
) {
    when (val watchListUiFlow = uiStateFlow.collectAsState().value) {
        is WatchListScreenUiState.Error -> NoConnectionScreen(callbackError = {})
        is WatchListScreenUiState.Loading -> LoadingScreen()
        is WatchListScreenUiState.Success -> {
            WatchListScreenColumnScreen(
                movieList = watchListUiFlow.movie,
                navigateToDetails = navigateToDetails,
                callBackPopBackStake = callBackPopBackStake
            )
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun WatchListScreenColumnScreen(
    movieList: List<DetailsUiModel>,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    callBackPopBackStake: () -> Unit,
) {
    Scaffold(
        topBar = {
            Column(
                modifier = modifier.background(MaterialTheme.colorScheme.background)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(dp10),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { callBackPopBackStake() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back_svgrepo_com),
                            contentDescription = null,
                        )
                    }
                    Text(
                        modifier = modifier.padding(top = dp11),
                        text = stringResource(R.string.saved),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold,
                    )
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_info_outline_24),
                            contentDescription = null,
                        )
                    }
                }
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn(
                modifier = Modifier.padding(bottom = dp60)
            ) {
                items(items = movieList, key = { it.id }) {
                    SearchScreenItem(
                        navigateToDetails = navigateToDetails,
                        posterUrl = POSTER_PATH_URL + it.posterPath,
                        movieId = it.id,
                        title = it.title,
                        voteAverage = it.voteAverage.toString(),
                        releaseDate = it.releaseDate,
                        runtime = it.voteCount
                    )
                }
            }
        }
    }
}






