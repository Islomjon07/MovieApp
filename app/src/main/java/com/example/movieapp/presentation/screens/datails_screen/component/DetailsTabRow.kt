package com.example.movieapp.presentation.screens.datails_screen.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.movieapp.presentation.screens.datails_screen.DetailsScreenUiState
import com.example.movieapp.presentation.theme.Greeys
import com.example.movieapp.presentation.theme.dp12
import com.example.movieapp.presentation.theme.dp15
import com.example.movieapp.presentation.theme.dp4
import com.example.movieapp.presentation.theme.sp14
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsTabRow(
    uiState: DetailsScreenUiState.Success,
    modifier: Modifier = Modifier,
    screenHeight: Dp
) {
    val coroutineScope = rememberCoroutineScope()
    val movieList = listOf(
        uiState.movieDetails.title,
        uiState.movieDetails.runtime,
    )
    val pagerState = rememberPagerState {
        movieList.size
    }

    Column(
        modifier = modifier.height(screenHeight)
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = MaterialTheme.colorScheme.background,
            modifier = modifier
                .padding(horizontal = dp12)
                .fillMaxWidth(),
            indicator = { tabPosition ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPosition[pagerState.currentPage])
                        .height(dp4)
                        .background(
                            color = Greeys,
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            },
            divider = { Spacer(modifier = Modifier.height(4.dp)) },
        ) {
            movieList.forEachIndexed { index, _ ->
                val header = getPagerDetailsPosition(position = index)
                Tab(
                    selected = pagerState.currentPage == index,
                    text = {
                        Text(
                            text = header,
                            fontSize = sp14,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            Column {
                Text(
                    text = uiState.movieDetails.overview,
                    modifier = modifier.padding(dp15)
                )
            }
        }
    }
}


@Composable
fun getPagerDetailsPosition(position: Int): String = when (position) {
    0 -> "About Movie"
    1 -> "Reviews"
    else -> "Cast"
}