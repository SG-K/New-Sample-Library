package com.m.samplelibray1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding2.view.RxView
import com.m.samplelibray1.base.BaseActivity
import com.m.samplelibray1.base.videmodel.ViewModelFactory
import com.m.samplelibray1.utils.Constants
import kotlinx.android.synthetic.main.activity_lay.*
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

class SearchActivity : BaseActivity<SearchViewModel>() {
    override fun getInjectViewModel(): SearchViewModel {
        return ViewModelProviders.of(this, ViewModelFactory { SearchViewModel() }).get(SearchViewModel::class.java)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_lay
    }

    override fun setupViews() {

        TextUtils.printTimberLog("entered into activity")
        RxView.clicks(btn)
            .throttleFirst(Constants.RX_CLICK_INTERVEL, TimeUnit.MILLISECONDS)
            .subscribe({
                TextUtils.printTimberLog("clicked fragment 1")
                FragmentSearch.newInstance(supportFragmentManager, R.id.frame_container_search_lib)
            }, {

            }).let {
                compositeDisposable.add(it)
            }
    }


    companion object {
        public fun startActivity(activityWeakReference: WeakReference<Activity>) {
            val intent = Intent(activityWeakReference.get(), SearchActivity::class.java)
            activityWeakReference.get()?.startActivity(intent)
        }
    }

}