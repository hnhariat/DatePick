package com.sun.toy.datepick.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import io.realm.RealmConfiguration
import java.lang.Exception
import kotlin.properties.Delegates

/**
 * Created by 1100160 on 2017. 6. 26..
 */

open class BaseFragment : Fragment(), IInitializer {
    var mView = null as? View
    var mRealm: Realm by Delegates.notNull<Realm>()
    var mRealmConfig: RealmConfiguration by Delegates.notNull<RealmConfiguration>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView == null) {
            Exception("mView must be initialized.")
        }
        initRealm()
        return mView;
    }
    override fun initView() {
    }

    override fun initControl() {
    }

    override fun init() {
    }

    fun initRealm() {
        mRealmConfig = RealmConfiguration.Builder().build()
        Realm.deleteRealm(mRealmConfig)

        mRealm = Realm.getInstance(mRealmConfig)
    }
}