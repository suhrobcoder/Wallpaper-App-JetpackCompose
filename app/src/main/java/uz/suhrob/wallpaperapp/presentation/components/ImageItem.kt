package uz.suhrob.wallpaperapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import uz.suhrob.wallpaperapp.R
import uz.suhrob.wallpaperapp.other.loadPicture

@ExperimentalCoroutinesApi
@Composable
fun ImageItem(
    imageUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 32.dp))
            .clickable(onClick = onClick)
    ) {
        val image by loadPicture(
            url = imageUrl,
            defaultImage = R.drawable.portrait_placeholder
        )
        image.bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}