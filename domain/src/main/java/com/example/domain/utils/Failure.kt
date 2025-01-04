package com.example.domain.utils

sealed class Failure {

    /** When service return 401 or 403 this will force the client to log out of the app.*/
    open class AppNotAvailable(val message: String) : Failure()

    open class ErrorRequestClient(val message: String) : Failure()

    /** When service return 401 or 403 this will force the client to log out of the app.*/
    open class UnauthorizedOrForbidden(val message: String?) : Failure()

    /** Error 403 .*/
    open class PermissionsDenied() : Failure()

    /** Error 404 .*/
    open class ResourcesNotFound() : Failure()

    /** Error 505 .*/
    open class Error505() : Failure()

    /** Weird and strange error that we don´t know the cause.*/
    object None : Failure()

    /** When suddenly the connection is lost.*/
    object NetworkConnectionLostSuddenly : Failure()

    /** When there is no internet network detected.*/
    object NoNetworkDetected : Failure()

    object SSLError : Failure()

    /** When service is taking to long on return the response.*/
    object TimeOut : Failure()

    /** When exists a problem with Shared Preferences.*/
    object PreferenceDataNotFound : Failure()

    /** Extend this class for feature specific failures.*/
    open class ServiceUncaughtFailure(val uncaughtFailureMessage: String) : Failure()

    /** Extend this class for feature specific SERVICE ERROR BODY RESPONSE.*/
    open class ServerBodyError(val code: Int, val message: String) : Failure()

    /** Extend this class for feature specific SERVICE ERROR BODY RESPONSE.*/
    open class ServerBodyErrorForLogin(
        val code: String,
        val status: String,
        val url: String,
        val title: String,
        val message: String
    ) : Failure()

    /** Extend this class for feature specific DATA -> DOMAIN MAPPERS exceptions.*/
    open class DataToDomainMapperFailure(val mapperException: String?) : Failure()

    /** Extend this class for feature specific DOMAIN -> PRESENTATION MAPPERS exceptions.*/
    open class DomainToPresentationMapperFailure(val mapperException: String?) : Failure()

}