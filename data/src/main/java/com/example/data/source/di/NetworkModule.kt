package com.example.data.source.di

import org.koin.dsl.module
import com.example.data.source.interceptor.SupportInterceptor
import com.example.data.utils.ConnectionUtils
import com.example.data.utils.ConnectionUtilsImpl
import com.example.data.utils.RetrofitUtils
import org.koin.android.ext.koin.androidContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<ConnectionUtils> {
        ConnectionUtilsImpl(androidContext())
    }

    factory { SupportInterceptor() }

    single { RetrofitUtils.provideOkHttpClient(get()) }
    single { RetrofitUtils.provideApi(get()) }
    //single { RetrofitUtils.provideRetrofit(get()) }
}



