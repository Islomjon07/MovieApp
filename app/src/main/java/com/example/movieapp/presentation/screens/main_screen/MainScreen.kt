package com.example.movieapp.presentation.screens.main_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.presentation.component.LoadingScreen
import com.example.movieapp.presentation.component.NoConnectionScreen
import com.example.movieapp.presentation.component.SetSystemBarsColor
import com.example.movieapp.presentation.screens.main_screen.component.DeletePopBackSplash
import com.example.movieapp.presentation.screens.main_screen.component.MainScreenItemTwo
import com.example.movieapp.presentation.screens.main_screen.component.MoviesLazyColumn
import com.example.movieapp.presentation.theme.Greeys
import com.example.movieapp.presentation.theme.GreeysWH
import com.example.movieapp.presentation.theme.dp10
import com.example.movieapp.presentation.theme.dp14
import com.example.movieapp.presentation.theme.dp15
import com.example.movieapp.presentation.theme.dp18
import com.example.movieapp.presentation.theme.dp20
import com.example.movieapp.presentation.theme.dp42
import com.example.movieapp.presentation.theme.dp6
import com.example.movieapp.presentation.theme.dp60
import com.example.movieapp.presentation.theme.dp8
import com.example.movieapp.presentation.theme.sp18
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    uiStateFlow: StateFlow<MainScreenUiState>,
    viewModel: MainScreenViewModel,
    callBackPopBackSplash: () -> Unit,
    onNavigateToInfo: (Int) -> Unit,
    onNavigateToSearch: () -> Unit
) {

    when (val mainScreenUiFlow = uiStateFlow.collectAsState().value) {
        is MainScreenUiState.Loading -> LoadingScreen()
        is MainScreenUiState.Success -> {
            LoadedScreen(
                uiState = mainScreenUiFlow,
                onNavigateToInfo = onNavigateToInfo,
                onNavigateToSearch = onNavigateToSearch
            )
        }

        is MainScreenUiState.Error -> NoConnectionScreen(
            callbackError = viewModel::fetchTopRatedMovie
        )
    }
    SetSystemBarsColor(
        setSystemBarsColor = MaterialTheme.colorScheme.background,
        setNavigationBarColor = MaterialTheme.colorScheme.background,
    )
    DeletePopBackSplash(callBackPopBackSplash = callBackPopBackSplash)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoadedScreen(
    modifier: Modifier = Modifier,
    uiState: MainScreenUiState.Success,
    onNavigateToInfo: (Int) -> Unit,
    onNavigateToSearch: () -> Unit
) {
    val movieList = listOf(
        uiState.movieNowPlaying,
        uiState.movieUpcoming,
        uiState.movieTopRated,
        uiState.moviePopular,
    )
    val pagerState = rememberPagerState { movieList.size }
    val coroutineScope = rememberCoroutineScope()

    BoxWithConstraints(
        modifier = modifier
    ) {
        val verticalScrollState = rememberScrollState()
        val screenHeight = maxHeight
        Column(
            modifier = modifier
                .padding(horizontal = dp15)
                .fillMaxSize()
                .verticalScroll(state = verticalScrollState),
        ) {
            Text(
                modifier = modifier.padding(top = dp14),
                text = stringResource(R.string.what_do_you),
                fontSize = sp18,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Card(
                modifier = modifier
                    .padding(top = dp20)
                    .height(dp42)
                    .fillMaxWidth()
                    .clickable {
                        onNavigateToSearch()
                    }
                    .clip(shape = RoundedCornerShape(size = 16.dp))
                    .background(color = Greeys),
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(dp8),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = modifier.padding(top = dp6),
                        text = "Search",
                        color = GreeysWH,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                    )
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.Gray,
                    )
                }
            }

            Spacer(modifier = modifier.padding(top = dp18))
            MoviesLazyColumn(
                uiState.moviePopular,
                onNavigateToInfo = onNavigateToInfo
            )
            Column(
                modifier = modifier.height(screenHeight)
            ) {
                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    modifier = modifier
                        .padding(horizontal = dp10)
                        .fillMaxWidth(),
                    indicator = { tabPosition ->
                        Box(
                            modifier = modifier
                                .tabIndicatorOffset(tabPosition[pagerState.currentPage])
                                .height(3.dp)
                                .background(
                                    color = Greeys,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        )
                    },
                    divider = { Spacer(modifier = modifier.height(4.dp)) },
                    edgePadding = 0.dp
                ) {
                    movieList.forEachIndexed { index, _ ->
                        val header = getPagerHeaderByPosition(position = index)
                        Tab(
                            selected = pagerState.currentPage == index,
                            text = {
                                Text(text = header)
                            },
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.scrollToPage(index)
                                }
                            },
                        )
                    }
                }
                Spacer(modifier = modifier.padding(top = dp15))
                HorizontalPager(
                    state = pagerState,
                    userScrollEnabled = true
                ) { page ->
                    val movie = movieList[page]
                    LazyVerticalGrid(
                        modifier = modifier.padding(bottom = dp60),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(items = movie, key = { it.movieId }) {
                            MainScreenItemTwo(
                                imageUrl = it.moviePosterPath,
                                image = painterResource(id = R.drawable.movie_svgrepo_com),
                                movieID = it.movieId,
                                onNavigateToInfo = onNavigateToInfo
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun getPagerHeaderByPosition(position: Int): String = when (position) {
    0 -> "Now playing"
    1 -> "Upcoming"
    2 -> "Top rated"
    else -> "Popular"
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        uiStateFlow = MutableStateFlow<MainScreenUiState>(value = MainScreenUiState.Loading),
        viewModel = hiltViewModel(),
        callBackPopBackSplash = {},
        onNavigateToInfo = {}
    ) {}
}