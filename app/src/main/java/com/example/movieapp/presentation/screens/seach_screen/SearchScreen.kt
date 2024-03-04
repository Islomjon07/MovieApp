package com.example.movieapp.presentation.screens.seach_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.data.cloud.utils.Constants.POSTER_PATH_URL
import com.example.movieapp.presentation.component.NoConnectionScreen
import com.example.movieapp.presentation.models.movie_list.MovieUi
import com.example.movieapp.presentation.screens.seach_screen.component.SearchScreenItem
import com.example.movieapp.presentation.theme.dp10
import com.example.movieapp.presentation.theme.dp11
import com.example.movieapp.presentation.theme.dp15
import com.example.movieapp.presentation.theme.dp25
import com.example.movieapp.presentation.theme.dp60
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    uiStateFlow: StateFlow<SearchUiState>,
    viewModel: SearchViewModel,
    onValueChange: (String) -> Unit,
    navigateToDetails: (Int) -> Unit,
    callBackPopBackState: () -> Unit,
    modifier: Modifier = Modifier
) {
    var queryState by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(dp10),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { callBackPopBackState() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back_svgrepo_com),
                            contentDescription = null,
                        )
                    }
                    Text(
                        modifier = modifier.padding(top = dp11),
                        text = stringResource(R.string.search),
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

                Spacer(modifier = modifier.height(16.dp))
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = dp15),
                    value = queryState,
                    onValueChange = {
                        queryState = it
                        onValueChange(it)
                    },
                    shape = RoundedCornerShape(dp25),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search__4_),
                            contentDescription = null,
                            tint = Color.Gray,
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Start Search",
                            color = Color.Gray,
                            minLines = 1
                        )
                    },
                )
            }
        },
    ) {
        when (val searchUiFlow = uiStateFlow.collectAsState().value) {
            is SearchUiState.Error -> NoConnectionScreen(callbackError = {})
            SearchUiState.Loading -> Unit
            is SearchUiState.Success -> {
                SearchLazyColumnScreen(
                    movieList = searchUiFlow.searchUiState,
                    navigateToDetails = navigateToDetails
                )
            }

            SearchUiState.Initial -> Unit
        }
    }
}

@Composable
private fun SearchLazyColumnScreen(
    movieList: ImmutableList<MovieUi>,
    navigateToDetails: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = dp60)
        ) {
            items(items = movieList, key = { it.movieId }) {
                SearchScreenItem(
                    navigateToDetails = navigateToDetails,
                    posterUrl = POSTER_PATH_URL + it.moviePosterPath,
                    movieId = it.movieId,
                    title = it.movieTitle,
                    voteAverage = it.movieVoteAverage.toString(),
                    releaseDate = it.movieReleaseDate,
                    runtime = it.movieVoteCount
                )
            }
        }
    }
}


