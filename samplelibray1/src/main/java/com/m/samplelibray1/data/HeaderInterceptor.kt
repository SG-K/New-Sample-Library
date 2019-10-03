package com.m.samplelibray1.data

import co.starfish.sharedpreference.PreferenceHelper
import com.m.samplelibray1.utils.Constants.ACCEPT
import com.m.samplelibray1.utils.Constants.AUTH
import com.m.samplelibray1.utils.Constants.CONTENT_TYPE
import com.m.samplelibray1.utils.Constants.HOST
import com.m.samplelibray1.utils.Constants.LANG
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class HeaderInterceptor(private val preferenceHelper: PreferenceHelper) : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        requestBuilder.addHeader(AUTH, "23AEE27A-3A81-4817-B6D8-624F125EFD55")
//        requestBuilder.addHeader(CONTENT_TYPE, "application/json; charset=utf-8")
        requestBuilder.addHeader(HOST, "rest1.jboomer.in")
//        requestBuilder.addHeader(ACCEPT, "application/json")
//        requestBuilder.addHeader(LANG, "en")
//        requestBuilder.addHeader(OS_VERSION, Integer.toString(Build.VERSION.SDK_INT))
//        requestBuilder.addHeader(CLIENT_ID, BuildConfig.CLIENT_ID)
//        requestBuilder.addHeader(CLIENT_SECRET, BuildConfig.CLIENT_SECRET)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}