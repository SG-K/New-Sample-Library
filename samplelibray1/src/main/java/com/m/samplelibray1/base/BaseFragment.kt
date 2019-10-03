package com.m.samplelibray1.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.m.samplelibray1.data.RepositoryImpl
import com.m.samplelibray1.domian.Repository
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment <VM : BaseViewModel> : Fragment() {
    protected var viewModel: VM? = null
    protected val compositeDisposable = CompositeDisposable()
    private var mActivity: BaseActivity<*>? = null
    var snackbar: Snackbar? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.viewModel = getInjectViewModel()
        if (context is BaseActivity<*>) {
            this.mActivity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    abstract fun getInjectViewModel(): VM?

    fun isViewLive() = isAdded && view != null

    protected open fun initViews() {

    }

    override fun onDetach() {
        mActivity = null
        viewModel?.onCleared()
        compositeDisposable.clear()
        super.onDetach()
    }

    fun getBaseActivity(): BaseActivity<*>? {
        return mActivity
    }






}