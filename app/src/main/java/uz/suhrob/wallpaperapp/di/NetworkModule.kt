package uz.suhrob.wallpaperapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import uz.suhrob.wallpaperapp.network.PexelsService
import uz.suhrob.wallpaperapp.network.model.PhotoDtoMapper
import uz.suhrob.wallpaperapp.other.BASE_URL
import javax.inject.Singleton

@ExperimentalSerializationApi
@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json {
            ignoreUnknownKeys = true
        }.asConverterFactory(MediaType.get("application/json")))
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