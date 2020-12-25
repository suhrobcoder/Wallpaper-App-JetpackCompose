package uz.suhrob.wallpaperapp.other

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.AmbientContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun loadPicture(
    url: String,
    @DrawableRes defaultImage: Int
): MutableState<ImageStatus1> {
    val imageStatus: MutableState<ImageStatus1> = mutableStateOf(ImageStatus1.notLoaded())
    Glide.with(AmbientContext.current)
        .asBitmap()
        .load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                if (imageStatus.value.status == ImageStatus1.Status.NOT_LOADED) {
                    imageStatus.value = ImageStatus1.defaultImageLoaded(bitmap = resource)
                }
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    Glide.with(AmbientContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                imageStatus.value = ImageStatus1.loaded(bitmap = resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    return imageStatus
}

data class ImageStatus1(val bitmap: Bitmap?, val status: Status) {
    enum class Status {
        NOT_LOADED, DEFAULT_IMAGE_LOADED, LOADED
    }

    companion object {
        fun notLoaded() = ImageStatus1(null, Status.NOT_LOADED)

        fun defaultImageLoaded(bitmap: Bitmap) = ImageStatus1(bitmap, Status.DEFAULT_IMAGE_LOADED)

        fun loaded(bitmap: Bitmap) = ImageStatus1(bitmap, Status.LOADED)
    }
}