package uz.suhrob.wallpaperapp.presentation.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import uz.suhrob.wallpaperapp.presentation.components.ImageItem
import uz.suhrob.wallpaperapp.presentation.components.LazyGridFor
import uz.suhrob.wallpaperapp.presentation.theme.WallpaperAppTheme

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private val viewModel by viewModels<CategoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString("category")?.let {
            viewModel.category.value = it
            viewModel.loadPhotos(it)
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
                    Scaffold(
                        topBar = {
                            CategoryTopAppBar(title = viewModel.category.value) {
                                findNavController().popBackStack()
                            }
                        }
                    ) {
                        LazyGridFor(
                            items = viewModel.photos.value,
                            spaceBetweenItems = 8.dp,
                            hPadding = 16.dp,
                            vPadding = 8.dp
                        ) { item, _ ->
                            ImageItem(imageUrl = item.portraitUrl) {
                                findNavController().navigate(
                                    CategoryFragmentDirections.actionCategoryFragmentToPhotoFragment(
                                        item.portraitUrl
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryTopAppBar(
    title: String,
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                modifier = Modifier.clickable(onClick = onNavigationIconClick)
            )
        },
        elevation = 0.dp
    )
}