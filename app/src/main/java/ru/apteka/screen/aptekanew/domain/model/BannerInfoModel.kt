package ru.apteka.screen.aptekanew.domain.model

//Информация о баннере для отображения на сайте
data class BannerInfoModel(
    val photoPath: String? = null,       //Картинка
    val hint: String? = null,        //Хинт
    val url: String? = null         //Ссылка на которую ведёт баннер
)