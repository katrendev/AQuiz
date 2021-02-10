package ru.apteka.screen.categorylist.model.domain

import java.io.Serializable

data class CatalogInfo(
    val category: List<CatalogCategory>?,
    val goodGroup: List<GoodGroup>?
)

data class CatalogCategory(
    val uid: String?,
    val name: String?,
    val url: String?,
    val subGroup: List<CatalogCategory>?,
    val itemsCount: Int?,
    val photo: Photo?,
    val parent: CatalogCategory? = null
): Serializable {
    val imageUrl: String? = photo?.run {
        original ?: medium ?: small ?: preview
    }

    val searchUrl: String? = parent?.url?.let { "$it/$url" } ?: url

    fun height(): Int {
        return when (parent) {
            null -> 0
            else -> parent.height() + 1
        }
    }
}

//looks same as CatalogCategoryResponse but with null photo
data class GoodGroup(
    val uid: String?,
    val name: String?,
    val url: String?,
    val subGroup: List<GoodGroup>?,
    val itemsCount: Int?,
    val parent: GoodGroup? = null
)

data class Photo(
    var original: String? = null,
    var medium: String? = null,
    var small: String? = null,
    var preview: String? = null
): Serializable