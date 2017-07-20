package com.sun.toy.datepick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.sun.toy.datepick.base.BaseFragment
import com.sun.toy.datepick.models.Group
import io.realm.Realm

/**
 * Created by 1100160 on 2017. 6. 29..
 */

class FrgDetail : BaseFragment(), View.OnClickListener {
    companion object {
        @JvmStatic
        fun newInstance(name: String): FrgDetail {
            var frg = FrgDetail()
            var bundle = Bundle()
            bundle.putString("name", name);
            frg.arguments = bundle
            return frg;
        }
    }

    private var btnSubscribe: Button? = null

    private var mName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mName = arguments.get("name") as String
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater?.inflate(R.layout.fragment_detail, null, false)
        initView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initView() {
        super.initView()
        btnSubscribe = mView?.findViewById(R.id.btn_subscribe) as Button

        btnSubscribe?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_subscribe) {
            mRealm?.beginTransaction()
            var group = mRealm?.createObject(Group::class.java)
            group.setName(mName)
            mRealm?.commitTransaction()
        }
    }
}