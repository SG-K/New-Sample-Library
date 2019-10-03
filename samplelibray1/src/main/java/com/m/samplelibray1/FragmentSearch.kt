package com.m.samplelibray1

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.m.samplelibray1.base.BaseFragment
import com.m.samplelibray1.base.videmodel.ViewModelFactory
import com.m.samplelibray1.utils.Constants.LOG_TAG
import kotlinx.android.synthetic.main.activity_search.*
import timber.log.Timber

class FragmentSearch : BaseFragment<SearchViewModel>() {

    override fun getInjectViewModel(): SearchViewModel? {
        return activity?.run {
            ViewModelProviders.of(this, ViewModelFactory { SearchViewModel() }).get(SearchViewModel::class.java)
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }



    override fun initViews() {
        super.initViews()
        TextUtils.printTimberLog("got into fragment 1")
//        TextUtils.printTimberLog(viewModel?.colorAccent ?: "failed")
        viewModel?.colorAccent = "A2"

        observersIntia()

        im_logo?.setOnClickListener {
            arguments?.getInt("id")?.let { idd ->
                activity?.supportFragmentManager?.let {
                    TextUtils.printTimberLog("clicked fragment 2")
                    viewModel?.getResults(activity!!)
                    FragmentSearchDetails.newInstance(it, idd)
                }
            }
        }
    }

    private fun observersIntia() {
        viewModel?.filteredTvShowsLiveData?.observe(this, Observer {
            Timber.v("%s fragmet cards size %s",LOG_TAG,it?.size);
        })
    }

    companion object {
        fun newInstance(
            supportFragmentManagerinstance: FragmentManager?,
            view_id: Int
        ) {
            val arguments = Bundle()
            arguments.putInt("id", view_id)
            val expertsCommonFragment = FragmentSearch()
            expertsCommonFragment.arguments = arguments

            supportFragmentManagerinstance?.beginTransaction()
                ?.add(
                    view_id, expertsCommonFragment,
                    FragmentSearch::class.java.simpleName
                )
                ?.addToBackStack(FragmentSearch::class.java.simpleName)
                ?.commit()
        }
    }

}