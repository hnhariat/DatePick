package com.sun.toy.datepick.base

import android.app.Application
import com.google.firebase.FirebaseApp

/**
 * Created by 1100160 on 2017. 6. 26..
 */
class BaseApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
    }
}