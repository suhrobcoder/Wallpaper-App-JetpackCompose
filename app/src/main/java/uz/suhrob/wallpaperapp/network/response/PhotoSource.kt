package uz.suhrob.wallpaperapp.network.response

import com.google.gson.annotations.SerializedName


data class PhotoSource(
    @SerializedName("original")
    var original: String = "",

    @SerializedName("large2x")
    var large2x: String = "",

    @SerializedName("large")
    var large: String = "",

    @SerializedName("medium")
    var medium: String = "",

    @SerializedName("small")
    var small: String = "",

    @SerializedName("portrait")
    var portrait: String = "",

    @SerializedName("landscape")
    var landscape: String = "",

    @SerializedName("tiny")
    var tiny: String = "",
)