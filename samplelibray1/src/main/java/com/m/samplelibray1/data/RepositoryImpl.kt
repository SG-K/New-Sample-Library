package com.m.samplelibray1.data

import android.util.Log
import com.m.samplelibray1.domian.Repository
import com.m.samplelibray1.models.DashboardModel
import com.m.samplelibray1.models.SearchResponse
import io.reactivex.Single
import timber.log.Timber

class RepositoryImpl : Repository {



    override fun getDashboard(data: HashMap<String, String>,apiService: APIService): Single<DashboardModel> {
        return apiService.getSearchResults(data)
    }

    override fun getSerachList(msg: String): String {
        Log.v("jhdvbhjsdbvhjbvjhsd","got_inot_repo 1")
        Timber.v("jhdvbhjsdbvhjbvjhsd got_inot_repo %s",msg)
        return msg
    }
}