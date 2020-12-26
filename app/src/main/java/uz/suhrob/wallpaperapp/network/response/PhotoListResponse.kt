package uz.suhrob.wallpaperapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uz.suhrob.wallpaperapp.network.model.PhotoDto

@Serializable
data class PhotoListResponse(
    @SerialName("total_results")
    val totalResults: Int,

    @SerialName("page")
    val page: Int,

    @SerialName("per_page")
    val perPage: Int,

    @SerialName("photos")
    val photos: List<PhotoDto>,

    @SerialName("next_page")
    val nextPage: String
)