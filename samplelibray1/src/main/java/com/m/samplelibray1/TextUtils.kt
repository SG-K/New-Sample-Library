package com.m.samplelibray1

import android.util.Log
import timber.log.Timber

public class TextUtils {


    companion object{
        public fun initiTimber(){
            if (BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
        }

        public fun printTimberLog(msg : String){

            Timber.v("startfish_log %s",msg)
            Log.v("startfish_log"," "+msg)
        }
    }



    fun daggerLinkObejct(){
        Log.v("you are giiid"," sdbvdksjbvjk");
    }



}