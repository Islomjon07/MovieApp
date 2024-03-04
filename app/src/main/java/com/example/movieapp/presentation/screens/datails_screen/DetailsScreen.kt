package com.example.movieapp.presentation.screens.datails_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.data.cloud.utils.Constants.POSTER_PATH_URL
import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.presentation.component.LoadingScreen
import com.example.movieapp.presentation.models.details.DetailsUiModel
import com.example.movieapp.presentation.screens.datails_screen.component.DetailsTabRow
import com.example.movieapp.presentation.theme.VoteAvaregeColor
import com.example.movieapp.presentation.theme.YellowStar
import com.example.movieapp.presentation.theme.dp10
import com.example.movieapp.presentation.theme.dp11
import com.example.movieapp.presentation.theme.dp15
import com.example.movieapp.presentation.theme.dp20
import com.example.movieapp.presentation.theme.dp24
import com.example.movieapp.presentation.theme.dp28
import com.example.movieapp.presentation.theme.dp3
import com.example.movieapp.presentation.theme.dp30
import com.example.movieapp.presentation.theme.dp4
import com.example.movieapp.presentation.theme.dp54
import com.example.movieapp.presentation.theme.dp70
import com.example.movieapp.presentation.theme.dp8
import com.example.movieapp.presentation.theme.dp95
import com.example.movieapp.presentation.theme.sp18
import kotlinx.coroutines.flow.StateFlow


@Composable
fun DetailsScreen(
    onGetMovieInfo: () -> Unit,
    uiStateFlow: StateFlow<DetailsScreenUiState>,
    callBackPopBackDetail: () -> Unit,
    onSaveMovieToCache: (DetailsDomainModel) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        onGetMovieInfo()
    }
    when (val detailsUiFlow = uiStateFlow.collectAsState().value) {
        is DetailsScreenUiState.Error -> {}
        DetailsScreenUiState.Loading -> LoadingScreen()
        is DetailsScreenUiState.Success -> {
            LoadedDetailsScreen(
                model = detailsUiFlow,
                callBackPopBackDetail = callBackPopBackDetail,
                onSaveMovieToCache = onSaveMovieToCache,
                movieModel = detailsUiFlow.movieDetails,
                isSaved = detailsUiFlow.isSaved
            )
        }
    }
}

@Composable
fun LoadedDetailsScreen(
    modifier: Modifier = Modifier,
    model: DetailsScreenUiState.Success,
    callBackPopBackDetail: () -> Unit,
    isSaved: Boolean,
    onSaveMovieToCache: (DetailsDomainModel) -> Unit,
    movieModel: DetailsDomainModel
) {
    val date = model.movieDetails.releaseDate
    val year = date.take(4).toIntOrNull()
    val verticalScrollState = rememberScrollState()

    BoxWithConstraints(
        modifier = modifier
    ) {
        val screenHeight = maxHeight
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(state = verticalScrollState),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(dp10),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    callBackPopBackDetail()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_svgrepo_com),
                        contentDescription = null,
                    )
                }
                Text(
                    modifier = modifier.padding(top = dp11),
                    text = "Detail",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                IsSavedMoviePainter(onSaveMovieToCache, movieModel, isSaved = isSaved)
            }
            Box {
                Card(
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(bottomEnd = dp20, bottomStart = dp20)
                ) {
                    AsyncImage(
                        modifier = modifier,
                        model = POSTER_PATH_URL + model.movieDetails.backdropPath,
                        contentDescription = null
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 160.dp, start = dp30)
                ) {
                    Card(
                        modifier = modifier
                            .width(dp95)
                            .height(130.dp),
                        shape = RoundedCornerShape(dp10),
                    ) {
                        AsyncImage(
                            modifier = modifier,
                            model = POSTER_PATH_URL + model.movieDetails.posterPath,
                            contentDescription = null,
                            placeholder = painterResource(id = R.drawable.movie_svgrepo_com),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = model.movieDetails.title,
                        modifier = modifier.padding(top = dp70, start = dp15),
                        fontSize = sp18,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 27.sp,
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Card(
                        modifier = modifier
                            .padding(end = dp10, top = dp28)
                            .width(dp54)
                            .height(dp24),
                        shape = RoundedCornerShape(dp8),
                        colors = CardDefaults.cardColors(VoteAvaregeColor)
                    ) {
                        Row(
                            modifier = modifier.padding(dp4)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.star_icons),
                                contentDescription = null,
                                tint = YellowStar
                            )
                            Text(
                                modifier = modifier.padding(start = dp3),
                                text = model.movieDetails.voteAverage.toString(),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = YellowStar
                            )
                        }
                    }
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp20),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.calendarblank),
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    modifier = modifier.padding(start = dp4),
                    text = "$year  |  ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                Icon(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    modifier = modifier.padding(start = dp4),
                    text = "${model.movieDetails.runtime} Minutes  |  ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                Icon(
                    painter = painterResource(id = R.drawable.ticket_),
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    modifier = modifier.padding(start = dp4),
                    text = "Action",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
            DetailsTabRow(
                uiState = model,
                modifier = modifier.padding(top = dp10),
                screenHeight = screenHeight
            )
        }
    }
}

@Composable
private fun IsSavedMoviePainter(
    onSaveMovieToCache: (DetailsDomainModel) -> Unit,
    movieModel: DetailsDomainModel,
    isSaved: Boolean
) {
    IconButton(onClick = {
        onSaveMovieToCache(movieModel)
    }) {
        Icon(
            painter = painterResource(
                id = if (isSaved)
                    R.drawable.save_svgrepo_icons
                else R.drawable.un_saved_svgrepo_icons
            ),
            contentDescription = null,
        )
    }
}


@Preview
@Composable
fun LoadedDetailsScreenPreview() {
    MaterialTheme {
//        LoadedDetailsScreen(model = DetailsScreenUiState.Success(
//            movieDetails = DetailsUiModel.unknown
//        ), callBackPopBackDetail = {},
//            onSaveMovieToCache = {},
//            movieModel = DetailsUiModel.unknown
//        )
//    }
    }
}
   
