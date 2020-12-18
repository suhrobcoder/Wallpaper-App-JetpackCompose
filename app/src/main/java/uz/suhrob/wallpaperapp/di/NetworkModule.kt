package uz.suhrob.wallpaperapp.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.suhrob.wallpaperapp.network.PexelsService
import uz.suhrob.wallpaperapp.network.model.PhotoDtoMapper
import uz.suhrob.wallpaperapp.other.BASE_URL
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun providePexelsService(
        retrofit: Retrofit
    ): PexelsService = retrofit.create(PexelsService::class.java)

    @Singleton
    @Provides
    fun providePhotoDtoMapper(): PhotoDtoMapper = PhotoDtoMapper()
}