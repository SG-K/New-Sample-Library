package com.m.samplelibray1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.m.samplelibray1.base.BaseFragment
import com.m.samplelibray1.base.videmodel.ViewModelFactory

class FragmentSearchDetails : BaseFragment<SearchViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_details
    }

    override fun getInjectViewModel(): SearchViewModel? {
        return activity?.run {
            ViewModelProviders.of(this, ViewModelFactory { SearchViewModel() }).get(SearchViewModel::class.java)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TextUtils.printTimberLog("got into fragment 2")
//        TextUtils.printTimberLog(viewModel?.colorAccent?:"failed")
        viewModel?.colorAccent = "A3"
    }

    companion object {
        fun newInstance(supportFragmentManagerinstance: FragmentManager?,
                        view_id : Int) {
            val arguments = Bundle()

            val expertsCommonFragment = FragmentSearchDetails()
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