package ru.apteka.screen.categorylist.data.conveter

import ru.apteka.base.commonapi.response.PhotoResponse
import ru.apteka.screen.categorylist.model.data.CatalogCategoryResponse
import ru.apteka.screen.categorylist.model.data.CatalogInfoResponse
import ru.apteka.screen.categorylist.model.data.GoodGroupResponse
import ru.apteka.screen.categorylist.model.domain.CatalogCategory
import ru.apteka.screen.categorylist.model.domain.CatalogInfo
import ru.apteka.screen.categorylist.model.domain.GoodGroup
import ru.apteka.screen.categorylist.model.domain.Photo

fun CatalogInfoResponse.toDomain() = CatalogInfo(
    category = category?.map { convertToDomain(it) },
    goodGroup = goodGroup?.map { convertToDomain(it) }
)

fun convertToDomain(catalogCategoryResponse: CatalogCategoryResponse, parent: CatalogCategory? = null): CatalogCategory {
    return with(catalogCategoryResponse) {
        CatalogCategory(
            uid = uid,
            name = name,
            url = url,
            subGroup = subGroup?.map { convertToDomain(it, this.toDomainParent()) },
            itemsCount = itemsCount,
            photo = photo?.toDomain(),
            parent = parent
        )
    }
}

fun CatalogCategoryResponse.toDomainParent() = CatalogCategory(
    uid = uid,
    name = name,
    url = url,
    subGroup = null,
    itemsCount = itemsCount,
    photo = photo?.toDomain()
)

fun GoodGroupResponse.toDomainParent() = GoodGroup(
    uid = uid,
    name = name,
    url = url,
    subGroup = null,
    itemsCount = itemsCount
)

fun convertToDomain(goodGroupResponse: GoodGroupResponse, parent: GoodGroup? = null): GoodGroup {
    return with(goodGroupResponse) {
        GoodGroup(
            uid = uid,
            name = name,
            url = url,
            subGroup = subGroup?.map { convertToDomain(it, this.toDomainParent()) },
            itemsCount = itemsCount,
            parent = parent
        )
    }
}

fun PhotoResponse.toDomain() =
    Photo(original, medium, small, preview)