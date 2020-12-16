package uz.suhrob.wallpaperapp.network.response

import com.google.gson.annotations.SerializedName
import uz.suhrob.wallpaperapp.network.model.PhotoDto

data class PhotoListResponse(
    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("page")
    val page: Int,

    @SerializedName("per_page")
    val perPage: Int,

    @SerializedName("photos")
    val photos: List<PhotoDto>,

    @SerializedName("next_page")
    val nextPage: String
)