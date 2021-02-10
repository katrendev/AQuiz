package ru.apteka.base.commonapi.clients

@JvmSuppressWildcards
interface AptekaRuApiClient {
    val catalogClient: CatalogClient
    val bannerClient: BannerClient
}