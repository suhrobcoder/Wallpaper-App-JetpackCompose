package uz.suhrob.wallpaperapp.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import uz.suhrob.wallpaperapp.network.model.PhotoDto
import uz.suhrob.wallpaperapp.network.response.PhotoListResponse

private const val API_KEY = "563492ad6f917000010000013a6017a894b24084bfc610f9d1a0f390"

interface PexelsService {
    @Headers("Authorization: $API_KEY")
    @GET("search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): PhotoListResponse

    @Headers("Authorization: $API_KEY")
    @GET("curated")
    suspend fun curatedPhotos(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): PhotoListResponse

    @Headers("Authorization: $API_KEY")
    @GET("photos/{id}")
    suspend fun getPhoto(
        @Path("id") id: Int
    ): PhotoDto
}