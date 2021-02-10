package ru.apteka.screen.categorylist.model.data

import ru.apteka.base.commonapi.response.PhotoResponse

data class CatalogInfoResponse(
    val category: List<CatalogCategoryResponse>?,
    val goodGroup: List<GoodGroupResponse>?
)

data class CatalogCategoryResponse(
    val uid: String?,
    val name: String?,
    val url: String?,
    val subGroup: List<CatalogCategoryResponse>?,
    val itemsCount: Int?,
    val photo: PhotoResponse?
)

//looks same as CatalogCategoryResponse but with null photo
data class GoodGroupResponse(
    val uid: String?,
    val name: String?,
    val url: String?,
    val subGroup: List<GoodGroupResponse>?,
    val itemsCount: Int?
)