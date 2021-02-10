package ru.apteka.screen.main.data

import io.reactivex.Single
import ru.apteka.base.commonapi.clients.AptekaRuApiClient
import ru.apteka.screen.categoryadditional.data.converter.toDomain
import ru.apteka.screen.aptekanew.domain.model.BannerInfoModel
import ru.apteka.screen.main.domain.repository.BannersRepository

class BannersRepositoryImpl(
    private val apiClient: AptekaRuApiClient
) : BannersRepository {

    override fun getBanner(bannerSectionUrl: String, optionalParametr: String?, count: Int?): Single<List<BannerInfoModel>> {
        return apiClient.bannerClient.getBanner(bannerSectionUrl, optionalParametr, count).map {
            it.map { it.toDomain() }
        }
    }
}