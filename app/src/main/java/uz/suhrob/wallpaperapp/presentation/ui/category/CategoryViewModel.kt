package uz.suhrob.wallpaperapp.presentation.ui.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.other.PHOTO_PER_PAGE
import uz.suhrob.wallpaperapp.other.STARTING_PAGE
import uz.suhrob.wallpaperapp.repository.PhotoRepository

class CategoryViewModel @ViewModelInject constructor(
    private val repository: PhotoRepository
): ViewModel() {
    val photos: MutableState<List<Photo>> = mutableStateOf(listOf())

    val category: MutableState<String> = mutableStateOf("")

    fun loadPhotos(category: String) = viewModelScope.launch {
        val result = repository.search(category, PHOTO_PER_PAGE, STARTING_PAGE)
        photos.value = result
    }
}