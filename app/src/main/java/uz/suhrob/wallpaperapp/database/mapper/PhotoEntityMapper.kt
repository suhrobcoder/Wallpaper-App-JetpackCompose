package uz.suhrob.wallpaperapp.database.mapper

import uz.suhrob.wallpaperapp.database.entity.PhotoEntity
import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.domain.util.DomainMapper

class PhotoEntityMapper : DomainMapper<PhotoEntity, Photo> {
    override fun mapToDomainModel(model: PhotoEntity): Photo {
        return Photo(
            id = model.id,
            url = model.url,
            portraitUrl = model.portraitUrl
        )
    }

    override fun mapFromDomainModel(domainModel: Photo): PhotoEntity {
        return PhotoEntity(
            id = domainModel.id,
            url = domainModel.url,
            portraitUrl = domainModel.portraitUrl
        )
    }

    fun mapToList(list: List<PhotoEntity>): List<Photo> {
        return list.map { mapToDomainModel(it) }
    }

    fun mapFromList(list: List<Photo>): List<PhotoEntity> {
        return list.map { mapFromDomainModel(it) }
    }
}