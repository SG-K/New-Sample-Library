package com.m.samplelibray1

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.m.samplelibray1.base.BaseViewModel
import com.m.samplelibray1.models.DashboardModel
import com.m.samplelibray1.models.ListCardsItem
import com.m.samplelibray1.rx.SchedulersFacade
import com.m.samplelibray1.utils.Constants.LOG_TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchViewModel  constructor(

) : BaseViewModel() {

    var colorWhite : String = "W"
    var colorAccent : String = "A"

    var filteredTvShowsLiveData = MutableLiveData<ArrayList<ListCardsItem>>()

    fun getResults(context: Context){
        val hashMap_daata : HashMap<String,String> = HashMap()
        hashMap_daata["AccountID"] = "168946779229808"
        hashMap_daata["BusinessID"] = "238176711548042"
        hashMap_daata["PageNumber"] = "1"
        getRepo()?.getDashboard(hashMap_daata,provideApiService(
            provideRetrofit(provideOkHttpClient(
                providePreferenceHelper(context),getMoshi()),
                getMoshi())))
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                Timber.v("request sucess")
                it?.let {dashboardModel ->
                    if (dashboardModel.error == null){
                        Log.v("Start_fish_log","views sucess "+dashboardModel.listCards?.size)
                        Timber.v("%s request sucess views %s",LOG_TAG,dashboardModel.listCards?.size)
                        Timber.v("%s request sucess total %s",LOG_TAG,dashboardModel)
                        filteredTvShowsLiveData.postValue(dashboardModel.listCards as ArrayList)
                    }else{
                        Log.v("Start_fish_log","views fail "+dashboardModel.error)
                        Timber.v("%s request fail %s",LOG_TAG,dashboardModel.error);
                    }
                }
            },{
                Timber.v("request fail %s",it.message)
            }).let {
                it?.let {
                    getCompositeDisposable().add(it)
                }
            }
    }


}