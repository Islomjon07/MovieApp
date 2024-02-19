package com.example.movieapp.presentation.screens.main_screen

import androidx.activity.addCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.data.cloud.utils.Constants.EMPTY_STRING
import com.example.movieapp.presentation.component.LoadedScreen
import com.example.movieapp.presentation.component.NoConnectionScreen
import com.example.movieapp.presentation.component.SetSystemBarsColor
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
import com.example.movieapp.presentation.theme.sp18
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    uiStateFlow: StateFlow<MainScreenUiState>,
    viewModel: MainScreenViewModel,
    callBackPopBackSplash: () -> Unit
) {

    val backstackDispatcher = LocalOnBackPressedDispatcherOwner.current

    backstackDispatcher?.onBackPressedDispatcher?.addCallback {
        callBackPopBackSplash()
    }
    when (val mainScreenUiFlow = uiStateFlow.collectAsState().value) {
        is MainScreenUiState.Loading -> LoadedScreen()
        is MainScreenUiState.Success -> {
            LoadedScreen(uiState = mainScreenUiFlow)
        }

        is MainScreenUiState.Error -> NoConnectionScreen(
            callbackErrorTopRated = viewModel::fetchTopRatedMovie
        )
    }
    SetSystemBarsColor(
        setSystemBarsColor = MaterialTheme.colorScheme.background,
        setNavigationBarColor = MaterialTheme.colorScheme.background,
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoadedScreen(
    modifier: Modifier = Modifier,
    uiState: MainScreenUiState.Success,
) {
    val movieList = listOf(
        uiState.movieTopRated,
        uiState.moviePopular,
        uiState.movieNowPlaying,
        uiState.movieUpcoming
    )
    var cleanTitleState1 by remember {
        mutableStateOf(EMPTY_STRING)
    }
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
            OutlinedTextField(
                modifier = modifier
                    .padding(top = dp20)
                    .height(dp42)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 16.dp))
                    .background(color = Greeys),
                value = cleanTitleState1,
                onValueChange = { cleanTitleState1 = it },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.Gray,
                    )
                },
                placeholder = {
                    Text(
                        text = "Search",
                        color = GreeysWH,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )
            Spacer(modifier = modifier.padding(top = dp18))
            MoviesLazyColumn(uiState.movieTopRated)
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
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(items = movie, key = { it.movieId }) {
                            MainScreenItemTwo(
                                imageUrl = it.moviePosterPath,
                                image = painterResource(id = R.drawable.movie_svgrepo_com),
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

