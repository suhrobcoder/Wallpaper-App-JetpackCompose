package uz.suhrob.wallpaperapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import uz.suhrob.wallpaperapp.R
import uz.suhrob.wallpaperapp.other.loadPicture

@ExperimentalCoroutinesApi
@Composable
fun CategoryItem(
    imageUrl: String,
    title: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(
                top = 4.dp,
                bottom = 4.dp
            )
            .preferredHeight(64.dp)
            .preferredWidth(128.dp)
            .clip(RoundedCornerShape(size = 16.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        val image by loadPicture(
            url = imageUrl,
            defaultImage = R.drawable.album_placeholder
        )
        image.bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = title,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}