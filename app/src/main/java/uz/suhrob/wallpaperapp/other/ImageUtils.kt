package uz.suhrob.wallpaperapp.other

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun loadPicture(
    url: String,
    @DrawableRes defaultImage: Int
): StateFlow<Bitmap?> {
    val bitmapState: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val target = object: CustomTarget<Bitmap>() {
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            bitmapState.value = resource
        }

        override fun onLoadCleared(placeholder: Drawable?) {}
    }
    Glide.with(AmbientContext.current)
        .asBitmap()
        .load(defaultImage)
        .into(target)
    Glide.with(AmbientContext.current)
        .asBitmap()
        .load(url)
        .into(target)
    return bitmapState
}