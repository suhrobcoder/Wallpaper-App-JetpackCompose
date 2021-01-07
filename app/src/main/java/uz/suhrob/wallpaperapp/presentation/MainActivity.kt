package uz.suhrob.wallpaperapp.presentation

import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import uz.suhrob.wallpaperapp.network.PexelsService
import uz.suhrob.wallpaperapp.network.model.PhotoDtoMapper
import uz.suhrob.wallpaperapp.other.BASE_URL
import uz.suhrob.wallpaperapp.presentation.theme.WallpaperAppTheme
import uz.suhrob.wallpaperapp.presentation.ui.category.CategoryScreen
import uz.suhrob.wallpaperapp.presentation.ui.home.HomeScreen
import uz.suhrob.wallpaperapp.presentation.ui.photo.PhotoScreen
import uz.suhrob.wallpaperapp.presentation.ui.search.SearchScreen
import uz.suhrob.wallpaperapp.repository.PhotoRepository
import uz.suhrob.wallpaperapp.repository.PhotoRepositoryImpl
import java.io.IOException

@ExperimentalSerializationApi
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val service = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(MediaType.get("application/json")))
            .build()
            .create(PexelsService::class.java)
        val repository: PhotoRepository = PhotoRepositoryImpl(service, PhotoDtoMapper())
        setContent {
            WallpaperAppTheme {
                Surface(modifier = Modifier.background(color = MaterialTheme.colors.onSurface)) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(repository = repository, navController = navController)
                        }
                        composable(
                            "category/{category}",
                            arguments = listOf(navArgument(name = "category") { defaultValue = "" })
                        ) {
                            it.arguments?.getString("category")?.let { category ->
                                CategoryScreen(
                                    repository = repository,
                                    navController = navController,
                                    category = category
                                )
                            }
                        }
                        composable("search") {
                            SearchScreen(repository = repository, navController = navController)
                        }
                        composable(
                            "photo/photoUrl",
                            arguments = listOf(navArgument(name = "photoUrl") { defaultValue = "" })
                        ) {
                            it.arguments?.getString("photoUrl")?.let { photoUrl ->
                                PhotoScreen(
                                    navController = navController,
                                    photoUrl = photoUrl,
                                    context = applicationContext,
                                    share = { share(photoUrl) },
                                    set = { bitmap -> setPhotoAsWallpaper(bitmap) })
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setPhotoAsWallpaper(bitmap: Bitmap) {
        val wallpaperManager = WallpaperManager.getInstance(applicationContext)
        try {
            wallpaperManager.setBitmap(bitmap)
            Toast.makeText(applicationContext, "Setting Wallpaper successfully", Toast.LENGTH_SHORT)
                .show()
        } catch (e: IOException) {
            Toast.makeText(applicationContext, "Setting Wallpaper failed", Toast.LENGTH_SHORT)
                .show()
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