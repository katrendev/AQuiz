package ru.apteka.screen.categorylist.domain

import ru.apteka.screen.categorylist.data.conveter.toDomain

class CategoryListInteractor(
    private val categoryListRepository: CategoryListRepository
) {
    fun getCategoryTree() =
        categoryListRepository.getCatalogAllItems()
            .map {
                it.toDomain()
            }
}