package com.example.trendingmovies.presentation.common

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class SnackbarDelegate(
    private var snackbarHostState: SnackbarHostState?,
    private var coroutineScope: CoroutineScope?
) {

    fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        coroutineScope?.launch {
            snackbarHostState?.showSnackbar(
                message,
                actionLabel,
                duration = duration
            )
        }
    }
}