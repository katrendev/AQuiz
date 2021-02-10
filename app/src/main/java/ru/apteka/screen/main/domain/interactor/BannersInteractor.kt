package ru.apteka.screen.main.domain.interactor

import io.reactivex.Single
import ru.apteka.base.data.ISharedPreferenceManager
import ru.apteka.screen.aptekanew.domain.model.BannerInfoModel
import ru.apteka.screen.main.domain.repository.BannersRepository


interface BannersInteractor {
    fun getBanner(bannerSectionUrl: String, optionalParametr: String? = null, count: Int? = null): Single<List<BannerInfoModel>>
}

class BannersInteractorImpl(
    private val prefs: ISharedPreferenceManager,
    private val bannersRepository: BannersRepository
) : BannersInteractor {

    override fun getBanner(bannerSectionUrl: String, optionalParametr: String?, count: Int?): Single<List<BannerInfoModel>> {
        return bannersRepository.getBanner(bannerSectionUrl, optionalParametr, count)
    }
}