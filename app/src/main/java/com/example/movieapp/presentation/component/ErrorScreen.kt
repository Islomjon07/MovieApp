package com.example.movieapp.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.presentation.theme.TaskerBlue
import com.example.movieapp.presentation.theme.dp24
import com.example.movieapp.presentation.theme.dp8

@Composable
fun NoConnectionScreen(
    callbackError: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MovieLottieAnim(
                rawFile = R.raw.animation_internet,
                modifier = modifier.size(200.dp)
            )
            Text(
                text = stringResource(R.string.seti),
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Button(
                onClick = {
                    callbackError()
                },
                colors = ButtonDefaults.buttonColors(TaskerBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dp24)
                    .padding(top = dp8)
            ) {
                Text(
                    text = stringResource(R.string.povt),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun Previews() {
    NoConnectionScreen(callbackError = {})
}

