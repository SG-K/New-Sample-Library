package com.m.samplehostapp1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.view.RxView
import com.m.samplelibray1.BuildConfig
import com.m.samplelibray1.FragmentSearch
import com.m.samplelibray1.SearchActivity
import com.m.samplelibray1.TextUtils
import com.m.samplelibray1.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        TextUtils.initiTimber()
        TextUtils.printTimberLog("samplee")


        RxView.clicks(tv_nav)
                    .throttleFirst(Constants.RX_CLICK_INTERVEL, TimeUnit.MILLISECONDS)
                    .subscribe({
                        TextUtils.printTimberLog("clicked activity")
                        SearchActivity.startActivity(WeakReference(this))
                    }, {

                    }).let {
//                        compositeDisposable.add(it)
                    }

//        tv_nav?.setOnClickListener {
//
////            FragmentSearch.newInstance(supportFragmentManager,R.id.frame_container_search)
//        }

    }
}
