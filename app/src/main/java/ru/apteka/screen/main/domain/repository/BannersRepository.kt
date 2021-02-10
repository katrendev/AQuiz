package ru.apteka.screen.main.domain.repository

import io.reactivex.Single
import ru.apteka.screen.aptekanew.domain.model.BannerInfoModel

interface BannersRepository {
    fun getBanner(bannerSectionUrl: String, optionalParametr: String?, count: Int?): Single<List<BannerInfoModel>>
}