package uz.suhrob.wallpaperapp.presentation.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import uz.suhrob.wallpaperapp.domain.model.Category
import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.other.PHOTO_PER_PAGE
import uz.suhrob.wallpaperapp.presentation.components.CategoryItem
import uz.suhrob.wallpaperapp.presentation.components.ImageItem
import uz.suhrob.wallpaperapp.presentation.components.LazyGridFor
import uz.suhrob.wallpaperapp.presentation.components.SearchBox
import uz.suhrob.wallpaperapp.repository.PhotoRepository
import uz.suhrob.wallpaperapp.repository.paging.PhotoPagingSource

@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(
    repository: PhotoRepository,
    navController: NavController
) {
    val photos: Flow<PagingData<List<Photo>>> = Pager(PagingConfig(pageSize = PHOTO_PER_PAGE)) {
        PhotoPagingSource(repository)
    }.flow
    val categories = Category.categories
    Scaffold {
        Column {
            SearchBox(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                onTextInputStarted = {
                    it.hideSoftwareKeyboard()
                    navController.navigate("search")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow {
                itemsIndexed(items = categories) { index, item ->
                    CategoryItem(
                        imageUrl = item.photoUrl,
                        title = item.title,
                        modifier = Modifier.padding(
                            start = if (index == 0) 16.dp else 4.dp,
                            end = if (index == categories.size - 1) 16.dp else 4.dp
                        )
                    ) {
                        navController.navigate("category/${item.title}")
                    }
                }
            }
            val lazyPhotos = photos.collectAsLazyPagingItems()
            LazyGridFor(
                pagingItems = lazyPhotos,
                spaceBetweenItems = 8.dp,
                hPadding = 16.dp,
                vPadding = 8.dp
            ) { item, _ ->
                ImageItem(imageUrl = item.smallUrl) {
                    navController.navigate("photo/${item.portraitUrl}")
                }
            }
        }
    }
}