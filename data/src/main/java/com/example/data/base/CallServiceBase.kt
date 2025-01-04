package com.example.data.base

import android.util.Log
import com.example.data.response.ApiResponse
import com.example.data.utils.ConnectionUtils
import com.example.domain.utils.Failure
import com.example.domain.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

abstract class CallServiceBase() {

    abstract fun getNetworkUtils(): ConnectionUtils

    /**
     * Use this for unit(void) responses returns.
     */
    protected suspend inline fun <T> callService(crossinline retrofitCall: suspend () -> Response<T>): Either<Failure, T> {
        return when (getNetworkUtils().isNetworkAvailable()) {
            true -> {
                try {
                    withContext(Dispatchers.IO) {
                        val response = retrofitCall.invoke()
                        if (response.code() == 200) {
                            return@withContext Either.Success(response.body()!!)
                        } else {
                            return@withContext Either.Error(
                                getErrorMessageFromServer(
                                    response.code(), null
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    return Either.Error(parseException(e))
                }
            }

            false -> Either.Error(Failure.NoNetworkDetected)
        }
    }

    /**
     * Parse Server Error to [Failure.ServerBodyError] if [errorBody].
     * @return [Failure] object.
     */
    suspend fun getErrorMessageFromServer(errorCode: Int?, errorBody: String?): Failure {
        return if (!errorBody.isNullOrBlank()) {
            return withContext(Dispatchers.IO) {
                return@withContext when (errorCode) {
                    400 -> {
                        Failure.ErrorRequestClient(errorBody)
                    }

                    401 -> {
                        Failure.UnauthorizedOrForbidden(errorBody)
                    }

                    403 -> {
                        Failure.PermissionsDenied()
                    }

                    404 -> {
                        Failure.ResourcesNotFound()
                    }

                    423 -> {
                        Failure.AppNotAvailable(errorBody)
                    }

                    505 -> {
                        Failure.Error505()
                    }

                    else -> {
                        Failure.ServerBodyError(errorCode ?: 500, errorBody)
                    }
                }
            }
        } else {
            //No error body was found.
            Failure.None
        }
    }

    fun parseException(throwable: Throwable): Failure {
        return when (throwable) {
            is SocketTimeoutException -> Failure.TimeOut
            is SSLException -> Failure.NetworkConnectionLostSuddenly
            is SSLHandshakeException -> Failure.SSLError
            else -> Failure.ServiceUncaughtFailure(
                throwable.message ?: "Service response doesn't match with response object."
            )
        }
    }

}