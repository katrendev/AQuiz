package ru.apteka.base.commonapi.clients

class AptekaRuApiClientImpl(
    override val catalogClient: CatalogClient,
    override val bannerClient: BannerClient,
) : AptekaRuApiClient