package ru.apteka.base.commonapi.response

import com.google.gson.annotations.SerializedName

//Информация о баннере для отображения на сайте
class BannerInfoModelResponse {

    @SerializedName("photoPath")
    val photoPath: String? = null       //Картинка

    @SerializedName("hint")
    val hint: String? = null        //Хинт

    @SerializedName("url")
    val url: String? = null         //Ссылка на которую ведёт баннер
}