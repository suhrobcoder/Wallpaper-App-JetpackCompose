package uz.suhrob.wallpaperapp.repository

import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.network.PexelsService
import uz.suhrob.wallpaperapp.network.model.PhotoDtoMapper

class PhotoRepositoryImpl(
    private val pexelsService: PexelsService,
    private val mapper: PhotoDtoMapper
) : PhotoRepository {
    override suspend fun search(query: String, perPage: Int, page: Int): List<Photo> {
        return mapper.toDomainList(pexelsService.searchPhotos(query, perPage, page).photos)
    }

    override suspend fun curatedPhotos(perPage: Int, page: Int): List<Photo> {
        return mapper.toDomainList(pexelsService.curatedPhotos(perPage, page).photos)
    }

    override suspend fun getPhoto(id: Int): Photo {
        return mapper.mapToDomainModel(pexelsService.getPhoto(id))
    }
}