package com.m.samplelibray1.base.videmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.m.samplelibray1.SearchViewModel
import com.m.samplelibray1.base.BaseViewModel

class ViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
//
//        }
//        return SearchViewModel() as T
        return creator() as T
    }
}