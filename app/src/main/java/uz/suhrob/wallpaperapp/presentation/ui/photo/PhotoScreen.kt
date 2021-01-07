package uz.suhrob.wallpaperapp.presentation.ui.photo

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uz.suhrob.wallpaperapp.R
import uz.suhrob.wallpaperapp.other.ImageStatus1
import uz.suhrob.wallpaperapp.other.loadPicture
import uz.suhrob.wallpaperapp.presentation.components.BottomBar
import uz.suhrob.wallpaperapp.presentation.components.BottomBarItem
import uz.suhrob.wallpaperapp.repository.PhotoRepository

@Composable
fun PhotoScreen(
    navController: NavController,
    photoUrl: String,
    share: () -> Unit,
    set: (Bitmap) -> Unit
) {
    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            val image by loadPicture(
                url = photoUrl,
                defaultImage = R.drawable.portrait_placeholder
            )
            image.bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primaryVariant.copy(alpha = 0.2F)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(onClick = { navController.popBackStack() })
                        .clip(RoundedCornerShape(percent = 50))
                        .padding(8.dp)
                )
            }
            BottomBar(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
            ) {
                BottomBarItem(
                    title = "Share",
                    icon = Icons.Filled.Share
                ) {
                    share()
                }
                BottomBarItem(
                    title = "Set as",
                    icon = vectorResource(id = R.drawable.ic_image)
                ) {
                    if (image.status == ImageStatus1.Status.LOADED) {
                        set(image.bitmap!!)
                    } else {
                        Toast.makeText(
                            AmbientContext.current,
                            "Image not loaded yet",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}