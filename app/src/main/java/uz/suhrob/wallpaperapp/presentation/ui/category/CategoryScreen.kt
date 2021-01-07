package uz.suhrob.wallpaperapp.presentation.ui.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.other.PHOTO_PER_PAGE
import uz.suhrob.wallpaperapp.presentation.components.ImageItem
import uz.suhrob.wallpaperapp.presentation.components.LazyGridFor
import uz.suhrob.wallpaperapp.repository.PhotoRepository
import uz.suhrob.wallpaperapp.repository.paging.PhotoPagingSource

@ExperimentalCoroutinesApi
@Composable
fun CategoryScreen(
    repository: PhotoRepository,
    navController: NavController,
    category: String
) {
    val photos: Flow<PagingData<List<Photo>>> = Pager(PagingConfig(pageSize = PHOTO_PER_PAGE)) {
        PhotoPagingSource(repository)
    }.flow
    Scaffold(
        topBar = {
            CategoryTopAppBar(title = category) {
                navController.popBackStack()
            }
        }
    ) {
        val pagingPhotos = photos.collectAsLazyPagingItems()
        LazyGridFor(
            pagingItems = pagingPhotos,
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

@Composable
fun CategoryTopAppBar(
    title: String,
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = { onNavigationIconClick() })
                    .clip(RoundedCornerShape(percent = 50))
                    .padding(8.dp)
            )
        },
        elevation = 0.dp
    )
}