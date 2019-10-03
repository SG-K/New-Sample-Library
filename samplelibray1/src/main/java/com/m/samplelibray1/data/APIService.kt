package com.m.samplelibray1.data

import com.m.samplelibray1.models.DashboardModel
import com.m.samplelibray1.models.SearchResponse
import io.reactivex.Single
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {
    @FormUrlEncoded
    @POST("BMV3/GetDashboard")
    fun getSearchResults(@FieldMap params: Map<String, String>): Single<DashboardModel>
}