package uz.suhrob.wallpaperapp.presentation.ui.category

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
import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.other.PHOTO_PER_PAGE
import uz.suhrob.wallpaperapp.repository.PhotoRepository
import uz.suhrob.wallpaperapp.repository.paging.PhotoPagingSource

class CategoryViewModel @ViewModelInject constructor(
    private val repository: PhotoRepository
): ViewModel() {
    var photos: MutableState<Flow<PagingData<List<Photo>>>?> = mutableStateOf(null)

    val category: MutableState<String> = mutableStateOf("")

    fun loadPhotos(category: String) = viewModelScope.launch {
        photos.value = Pager(PagingConfig(pageSize = PHOTO_PER_PAGE)) {
            PhotoPagingSource(repository, isSearching = true, query = category)
        }.flow
    }
}