package uz.suhrob.wallpaperapp.presentation.ui.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.other.PHOTO_PER_PAGE
import uz.suhrob.wallpaperapp.other.STARTING_PAGE
import uz.suhrob.wallpaperapp.repository.PhotoRepository

class SearchViewModel @ViewModelInject constructor(
    private val repository: PhotoRepository
) : ViewModel() {
    val photos: MutableState<List<Photo>> = mutableStateOf(listOf())
    val query: MutableState<String> = mutableStateOf("")
    private var searchJob: Job? = null

    fun search(query: String) {
        searchJob?.cancel()
        searchJob = Job()
        searchJob?.let { job ->
            CoroutineScope(viewModelScope.coroutineContext + job).launch {
                val result = repository.search(query, PHOTO_PER_PAGE, STARTING_PAGE)
                photos.value = result
            }
        }
    }

    fun onQueryChange(newQuery: String) {
        query.value = newQuery
    }
}