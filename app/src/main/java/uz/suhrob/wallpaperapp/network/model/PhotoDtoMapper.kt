package uz.suhrob.wallpaperapp.network.model

import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.domain.util.DomainMapper
import uz.suhrob.wallpaperapp.network.response.PhotoSource

class PhotoDtoMapper : DomainMapper<PhotoDto, Photo> {
    override fun mapToDomainModel(model: PhotoDto): Photo =
        Photo(
            id = model.id,
            url = model.url,
            smallUrl = model.source.portrait.replace("h=1200&w=800", "h=600&w=400"),
            portraitUrl = model.source.portrait.replace("h=1200&w=800", "h=2160&w=1080")
        )

    override fun mapFromDomainModel(domainModel: Photo): PhotoDto =
        PhotoDto(
            id = domainModel.id,
            url = domainModel.url,
            source = PhotoSource(portrait = domainModel.portraitUrl)
        )

    fun toDomainList(models: List<PhotoDto>): List<Photo> {
        return models.map { mapToDomainModel(it) }
    }

    fun fromDomainList(domainModels: List<Photo>): List<PhotoDto> {
        return domainModels.map { mapFromDomainModel(it) }
    }
}