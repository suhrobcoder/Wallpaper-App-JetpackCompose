package uz.suhrob.wallpaperapp.repository

import uz.suhrob.wallpaperapp.domain.model.Photo

interface PhotoRepository {
    suspend fun search(query: String, perPage: Int, page: Int): List<Photo>

    suspend fun curatedPhotos(perPage: Int, page: Int): List<Photo>

    suspend fun getPhoto(id: Int): Photo
}