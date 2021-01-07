package uz.suhrob.wallpaperapp.presentation.ui.search

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onDispose
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.other.PHOTO_PER_PAGE
import uz.suhrob.wallpaperapp.presentation.components.ImageItem
import uz.suhrob.wallpaperapp.presentation.components.LazyGridFor
import uz.suhrob.wallpaperapp.presentation.components.SearchBox
import uz.suhrob.wallpaperapp.repository.PhotoRepository
import uz.suhrob.wallpaperapp.repository.paging.PhotoPagingSource

@ExperimentalCoroutinesApi
@Composable
fun SearchScreen(
    repository: PhotoRepository,
    navController: NavController
) {
    val photos: MutableState<Flow<PagingData<List<Photo>>>?> = mutableStateOf(null)
    val query: MutableState<String> = mutableStateOf("")
    var searchJob: Job? = null
    Scaffold(topBar = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(color = MaterialTheme.colors.surface)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = { navController.popBackStack() })
                    .padding(8.dp)
            )
            SearchBox(
                value = query.value,
                onValueChange = {
                    query.value = it
                },
                textStyle = TextStyle(fontSize = 18.sp),
                actionSearch = {
                    searchJob?.cancel()
                    searchJob = Job()
                    searchJob?.let { job ->
                        CoroutineScope(IO + job).launch {
                            photos.value = Pager(PagingConfig(pageSize = PHOTO_PER_PAGE)) {
                                PhotoPagingSource(
                                    repository,
                                    isSearching = true,
                                    query = query.value
                                )
                            }.flow
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
            )
        }
    }) {
        photos.value?.let {
            val pagingPhotos = it.collectAsLazyPagingItems()
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
    onDispose {
        searchJob?.cancel()
    }
}