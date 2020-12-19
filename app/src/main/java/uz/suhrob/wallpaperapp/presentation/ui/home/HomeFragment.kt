package uz.suhrob.wallpaperapp.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowForIndexed
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import uz.suhrob.wallpaperapp.presentation.components.CategoryItem
import uz.suhrob.wallpaperapp.presentation.components.ImageItem
import uz.suhrob.wallpaperapp.presentation.components.LazyGridFor
import uz.suhrob.wallpaperapp.presentation.components.SearchBox
import uz.suhrob.wallpaperapp.presentation.theme.WallpaperAppTheme

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                WallpaperAppTheme {
                    Scaffold {
                        Column {
                            SearchBox(
                                value = "",
                                onValueChange = {},
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                onTextInputStarted = {
                                    it.hideSoftwareKeyboard()
                                    findNavController().navigate(
                                        HomeFragmentDirections.actionHomeFragmentToSearchFragment()
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            val categories = viewModel.categories.value
                            LazyRowForIndexed(items = categories) { index, item ->
                                CategoryItem(
                                    imageUrl = item.photoUrl,
                                    title = item.title,
                                    modifier = Modifier.padding(
                                        start = if (index == 0) 16.dp else 4.dp,
                                        end = if (index == categories.size - 1) 16.dp else 4.dp
                                    )
                                ) {
                                    findNavController().navigate(
                                        HomeFragmentDirections.actionHomeFragmentToCategoryFragment(
                                            item.title
                                        )
                                    )
                                }
                            }
                            LazyGridFor(
                                items = viewModel.photos.value,
                                spaceBetweenItems = 8.dp,
                                hPadding = 16.dp,
                                vPadding = 8.dp
                            ) { item, _ ->
                                ImageItem(imageUrl = item.portraitUrl) {
                                    findNavController().navigate(
                                        HomeFragmentDirections.actionHomeFragmentToPhotoFragment(
                                            item.id
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
}