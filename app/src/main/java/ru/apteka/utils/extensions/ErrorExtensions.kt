package ru.apteka.utils.extensions

import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

fun Throwable.isNoContent(): Boolean {
    return (this as? HttpException)?.code() == HttpURLConnection.HTTP_NO_CONTENT
}




