package uz.suhrob.wallpaperapp.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uz.suhrob.wallpaperapp.network.response.PhotoSource

@Serializable
data class PhotoDto(
    @SerialName("id")
    var id: Int = 0,

    @SerialName("width")
    var width: Int = 0,

    @SerialName("height")
    var height: Int = 0,

    @SerialName("url")
    var url: String = "",

    @SerialName("photographer")
    var photographer: String = "",

    @SerialName("photographer_url")
    var photographerUrl: String = "",

    @SerialName("photographer_id")
    var photographerId: Int = 0,

    @SerialName("src")
    var source: PhotoSource = PhotoSource(),

    @SerialName("liked")
    var liked: Boolean = false
)