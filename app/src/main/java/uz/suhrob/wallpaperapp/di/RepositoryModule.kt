package uz.suhrob.wallpaperapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import uz.suhrob.wallpaperapp.network.PexelsService
import uz.suhrob.wallpaperapp.network.model.PhotoDtoMapper
import uz.suhrob.wallpaperapp.repository.PhotoRepository
import uz.suhrob.wallpaperapp.repository.PhotoRepositoryImpl
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun providePhotoRepository(
        pexelsService: PexelsService,
        mapper: PhotoDtoMapper
    ): PhotoRepository = PhotoRepositoryImpl(pexelsService, mapper)
}