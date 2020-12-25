package uz.suhrob.wallpaperapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
    }
}

@Composable
fun ErrorState(
    onRefresh: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        OutlinedButton(onClick = onRefresh) {
            Text(
                text = "Retry",
                style = TextStyle(color = MaterialTheme.colors.onPrimary, fontSize = 20.sp)
            )
        }
    }
}