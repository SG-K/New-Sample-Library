package com.m.samplelibray1.domian

import com.m.samplelibray1.data.APIService
import com.m.samplelibray1.models.DashboardModel
import com.m.samplelibray1.models.SearchResponse
import io.reactivex.Single

interface Repository {

    fun getSerachList(msg : String): String

    fun getDashboard(data : HashMap<String,String>,apiService: APIService): Single<DashboardModel>

}