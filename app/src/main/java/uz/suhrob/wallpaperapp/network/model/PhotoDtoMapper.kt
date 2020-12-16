package uz.suhrob.wallpaperapp.network.model

import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.domain.util.DomainMapper
import uz.suhrob.wallpaperapp.network.response.PhotoSource

class PhotoDtoMapper : DomainMapper<PhotoDto, Photo> {
    override fun mapToDomainModel(model: PhotoDto): Photo =
        Photo(
            id = model.id,
            url = model.url,
            portraitUrl = model.source.portrait
        )

    override fun mapFromDomainModel(domainModel: Photo): PhotoDto =
        PhotoDto(
            id = domainModel.id,
            url = domainModel.url,
            source = PhotoSource(portrait = domainModel.portraitUrl)
        )
}