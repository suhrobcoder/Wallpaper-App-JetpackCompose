package uz.suhrob.wallpaperapp.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import uz.suhrob.wallpaperapp.presentation.components.ImageItem
import uz.suhrob.wallpaperapp.presentation.components.LazyGridFor
import uz.suhrob.wallpaperapp.presentation.components.SearchBox
import uz.suhrob.wallpaperapp.presentation.theme.WallpaperAppTheme

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                WallpaperAppTheme {
                    Scaffold(topBar = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.background(color = MaterialTheme.colors.surface)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable(onClick = { findNavController().popBackStack() })
                                    .padding(8.dp)
                            )
                            SearchBox(
                                value = viewModel.query.value,
                                onValueChange = {
                                    viewModel.onQueryChange(it)
                                },
                                textStyle = TextStyle(fontSize = 18.sp),
                                actionSearch = {
                                    viewModel.search(viewModel.query.value)
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }) {
                        LazyGridFor(
                            items = viewModel.photos.value,
                            spaceBetweenItems = 8.dp,
                            hPadding = 16.dp,
                            vPadding = 8.dp
                        ) { item, _ ->
                            ImageItem(imageUrl = item.portraitUrl) {
                                findNavController().navigate(
                                    SearchFragmentDirections.actionSearchFragmentToPhotoFragment(
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