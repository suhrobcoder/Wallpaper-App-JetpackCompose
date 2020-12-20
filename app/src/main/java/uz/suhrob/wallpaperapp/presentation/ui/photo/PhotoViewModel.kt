package uz.suhrob.wallpaperapp.presentation.ui.photo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import uz.suhrob.wallpaperapp.repository.PhotoRepository

class PhotoViewModel @ViewModelInject constructor(
    private val repository: PhotoRepository
) : ViewModel() {
    val photoUrl: MutableState<String> = mutableStateOf("")
}