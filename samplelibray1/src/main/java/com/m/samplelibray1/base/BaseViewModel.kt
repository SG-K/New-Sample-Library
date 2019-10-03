package com.m.samplelibray1.base

import android.content.Context
import androidx.lifecycle.ViewModel
import co.starfish.sharedpreference.PreferenceHelper
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.m.samplelibray1.BuildConfig
import com.m.samplelibray1.data.APIService
import com.m.samplelibray1.data.HeaderInterceptor
import com.m.samplelibray1.data.RepositoryImpl
import com.m.samplelibray1.domian.Repository
import com.m.samplelibray1.rx.SchedulersFacade
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

open class BaseViewModel  constructor() : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected fun getCompositeDisposable(): CompositeDisposable {
        return disposables
    }

    public override fun onCleared() {
        if (!disposables.isDisposed) {
            disposables.dispose()
            disposables.clear()
        }
    }

    var repository : Repository?= null
    var moshi_instance : Moshi?  = null
    var preferenceHelper : PreferenceHelper? = null
    var okHttpClient : OkHttpClient? = null
    var retofit : Retrofit? = null
    var apiService : APIService? = null

    fun provideOkHttpClient(preferenceHelper: PreferenceHelper, moshi: Moshi): OkHttpClient {
        okHttpClient?.let {
            return it
        }

        val logging = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        val okHttpBuilder = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(HeaderInterceptor(preferenceHelper))
            .addNetworkInterceptor(StethoInterceptor())
        okHttpClient = okHttpBuilder.build()
        return okHttpBuilder.build()
    }

    fun providePreferenceHelper(context: Context): PreferenceHelper {
        preferenceHelper?.let {
            return it
        }
        preferenceHelper = PreferenceHelper(context)
        return PreferenceHelper(context)
    }


    fun getRepo() : Repository? {
        if (repository == null){
            repository = RepositoryImpl() as Repository
        }
        return repository
    }



    fun getMoshi() : Moshi {
        moshi_instance?.let {
            return it
        }
        moshi_instance = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        retofit?.let {
            return it
        }
        val buildder = Retrofit.Builder()
            .baseUrl("https://rest.jboomer.in/API/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        retofit = buildder.build()
        return buildder.build()
    }

    fun provideApiService(retrofit: Retrofit): APIService {
        apiService?.let {
            return it
        }
        apiService = retrofit.create(APIService::class.java)
        return retrofit.create(APIService::class.java)
    }

}