package uz.suhrob.wallpaperapp.presentation.ui.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.other.PHOTO_PER_PAGE
import uz.suhrob.wallpaperapp.repository.PhotoRepository
import uz.suhrob.wallpaperapp.repository.paging.PhotoPagingSource

class SearchViewModel @ViewModelInject constructor(
    private val repository: PhotoRepository
) : ViewModel() {
    val photos: MutableState<Flow<PagingData<List<Photo>>>?> = mutableStateOf(null)
    val query: MutableState<String> = mutableStateOf("")
    private var searchJob: Job? = null

    fun search(query: String) {
        searchJob?.cancel()
        searchJob = Job()
        searchJob?.let { job ->
            CoroutineScope(viewModelScope.coroutineContext + job).launch {
                photos.value = Pager(PagingConfig(pageSize = PHOTO_PER_PAGE)) {
                    PhotoPagingSource(repository, isSearching = true, query = query)
                }.flow
            }
        }
    }

    fun onQueryChange(newQuery: String) {
        query.value = newQuery
    }
}