package uz.suhrob.wallpaperapp.presentation.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.suhrob.wallpaperapp.domain.model.Category
import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.other.PHOTO_PER_PAGE
import uz.suhrob.wallpaperapp.other.STARTING_PAGE
import uz.suhrob.wallpaperapp.repository.PhotoRepository
import uz.suhrob.wallpaperapp.repository.paging.PhotoPagingSource

class HomeViewModel @ViewModelInject constructor(
    repository: PhotoRepository
): ViewModel() {
    val photos: Flow<PagingData<List<Photo>>> = Pager(PagingConfig(pageSize = PHOTO_PER_PAGE)) {
        PhotoPagingSource(repository)
    }.flow
    val categories: MutableState<List<Category>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            categories.value = Category.categories
        }
    }
}