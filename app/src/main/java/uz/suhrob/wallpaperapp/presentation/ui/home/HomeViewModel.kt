package uz.suhrob.wallpaperapp.presentation.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.suhrob.wallpaperapp.domain.model.Category
import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.other.PHOTO_PER_PAGE
import uz.suhrob.wallpaperapp.other.STARTING_PAGE
import uz.suhrob.wallpaperapp.repository.PhotoRepository

class HomeViewModel @ViewModelInject constructor(
    repository: PhotoRepository
): ViewModel() {
    val photos: MutableState<List<Photo>> = mutableStateOf(listOf())
    val categories: MutableState<List<Category>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            val result = repository.curatedPhotos(PHOTO_PER_PAGE, STARTING_PAGE)
            photos.value = result
            categories.value = Category.categories
        }
    }
}