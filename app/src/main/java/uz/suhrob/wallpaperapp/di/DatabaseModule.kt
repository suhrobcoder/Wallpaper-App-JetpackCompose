package uz.suhrob.wallpaperapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.suhrob.wallpaperapp.database.PhotoDatabase
import uz.suhrob.wallpaperapp.database.dao.PhotoDao
import uz.suhrob.wallpaperapp.database.mapper.PhotoEntityMapper
import uz.suhrob.wallpaperapp.other.DATABASE_NAME
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun providePhotoDatabase(
        @ApplicationContext context: Context
    ): PhotoDatabase =
        Room.databaseBuilder(context, PhotoDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providePhotoDao(
        database: PhotoDatabase
    ): PhotoDao = database.getPhotoDao()

    @Singleton
    @Provides
    fun providePhotoEntityMapper() = PhotoEntityMapper()
}