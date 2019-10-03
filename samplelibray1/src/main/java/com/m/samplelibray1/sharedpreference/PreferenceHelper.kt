package co.starfish.sharedpreference

import android.content.Context
import com.m.samplelibray1.sharedpreference.SharedPreferenceHelper
import com.m.samplelibray1.utils.Constants

class PreferenceHelper  constructor(context: Context){

    private val sharedPreferenceHelper: SharedPreferenceHelper

    init {
        SharedPreferenceHelper.initialize(context)
        this.sharedPreferenceHelper = SharedPreferenceHelper.getInstance()
    }

    var isUserVerified: Boolean
        get() = sharedPreferenceHelper.getBoolean(Constants.IS_VERIFIED, false)
        set(is_verified) = sharedPreferenceHelper.setBoolean(Constants.IS_VERIFIED, is_verified)


    fun clearData(){
        sharedPreferenceHelper.clearPrefs()
    }

}