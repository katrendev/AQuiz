package ru.apteka.base.commonapi.factory

interface ApiFactory {
    fun <T> createClient(service: Class<T>): T
}