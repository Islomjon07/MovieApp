package com.example.movieapp.presentation.screens.main_screen.component

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DeletePopBackSplash(
    callBackPopBackSplash: () -> Unit,
) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current
    val coroutineScope = rememberCoroutineScope()

    val callback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                coroutineScope.launch {
                    callBackPopBackSplash()
                }
            }
        }
    }
    DisposableEffect(key1 = backDispatcher) {
        backDispatcher?.onBackPressedDispatcher?.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
}
