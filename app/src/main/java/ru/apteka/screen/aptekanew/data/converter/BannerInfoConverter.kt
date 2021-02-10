package ru.apteka.screen.categoryadditional.data.converter

import ru.apteka.base.commonapi.response.BannerInfoModelResponse
import ru.apteka.screen.aptekanew.domain.model.BannerInfoModel

fun BannerInfoModelResponse.toDomain() =
    BannerInfoModel(
        photoPath = photoPath,
        hint = hint,
        url = url
    )

