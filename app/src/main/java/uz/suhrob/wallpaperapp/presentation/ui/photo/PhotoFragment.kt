package uz.suhrob.wallpaperapp.presentation.ui.photo

import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import uz.suhrob.wallpaperapp.R
import uz.suhrob.wallpaperapp.other.ImageStatus1
import uz.suhrob.wallpaperapp.other.loadPicture
import uz.suhrob.wallpaperapp.presentation.components.BottomBar
import uz.suhrob.wallpaperapp.presentation.components.BottomBarItem
import uz.suhrob.wallpaperapp.presentation.theme.WallpaperAppTheme
import java.io.IOException

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class PhotoFragment : Fragment() {
    private val viewModel by viewModels<PhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString("photo_url")?.let {
            viewModel.photoUrl.value = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                WallpaperAppTheme {
                    Scaffold {
                        Box(modifier = Modifier.fillMaxSize()) {
                            val image by loadPicture(
                                url = viewModel.photoUrl.value,
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
                                        .clickable(onClick = { findNavController().popBackStack() })
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
                                    share(viewModel.photoUrl.value)
                                }
                                BottomBarItem(
                                    title = "Set as",
                                    icon = vectorResource(id = R.drawable.ic_image)
                                ) {
                                    if (image.status == ImageStatus1.Status.LOADED) {
                                        setPhotoAsWallpaper(image.bitmap!!)
                                    } else {
                                        Toast.makeText(
                                            requireContext(),
                                            "Image not loaded yet",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setPhotoAsWallpaper(bitmap: Bitmap) {
        val wallpaperManager = WallpaperManager.getInstance(requireContext())
        try {
            wallpaperManager.setBitmap(bitmap)
            Toast.makeText(requireContext(), "Setting Wallpaper successfully", Toast.LENGTH_SHORT)
                .show()
        } catch (e: IOException) {
            Toast.makeText(requireContext(), "Setting Wallpaper failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun share(url: String) {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Share image")
            putExtra(Intent.EXTRA_TEXT, url)
            startActivity(Intent.createChooser(this, "Share via"))
        }
    }
}