package uz.suhrob.wallpaperapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoSource(
    @SerialName("original")
    var original: String = "",

    @SerialName("large2x")
    var large2x: String = "",

    @SerialName("large")
    var large: String = "",

    @SerialName("medium")
    var medium: String = "",

    @SerialName("small")
    var small: String = "",

    @SerialName("portrait")
    var portrait: String = "",

    @SerialName("landscape")
    var landscape: String = "",

    @SerialName("tiny")
    var tiny: String = "",
)