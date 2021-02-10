package ru.apteka.base.commonapi.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PhotoResponse : Serializable {

    @SerializedName("original")
    var original: String? = null

    @SerializedName("medium")
    var medium: String? = null

    @SerializedName("small")
    var small: String? = null

    @SerializedName("preview")
    var preview: String? = null
}