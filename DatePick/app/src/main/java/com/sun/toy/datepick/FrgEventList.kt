package com.sun.toy.datepick

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sun.toy.datepick.base.BaseFragment
import com.sun.toy.datepick.base.OnClickDeliverer
import java.util.*

/**
 * Created by 1100160 on 2017. 6. 26..
 */

class FrgEventList : BaseFragment(), OnClickDeliverer {

    private var rcv: RecyclerView? = null
    private var adapterEvent: AdapterEvent? = null

    companion object {
        @JvmStatic
        fun newInstance(): FrgEventList {
            var frgEventList = FrgEventList()
            return frgEventList;
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater?.inflate(R.layout.fragment_event_list, null, false) as View
        initView();
        loadEventList()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun loadEventList() {
        var list = ArrayList<Event>()

        var cal = Calendar.getInstance() as Calendar
        cal.set(2017, Calendar.JULY, 21, 14, 10, 10)
        list.add(Event(cal.timeInMillis, "첫번째"))
        cal.set(2017, Calendar.JULY, 31, 9, 10, 10)
        list.add(Event(cal.timeInMillis, "두번째"))

        adapterEvent!!.apply { setList(list) }
    }


    override fun initView() {
        super.initView()
        rcv = mView?.findViewById(R.id.rcv) as RecyclerView
        adapterEvent = AdapterEvent().apply {
            setOnClickDeliverer(this@FrgEventList)
        }
        rcv!!.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterEvent
        }
    }

    override fun onClickDeliver(view: View) {
        var pos = rcv?.layoutManager?.getPosition(view)
        if (pos != null) {
            adapterEvent!!.item(pos).apply {
                Intent().apply {
                    setAction(Intent.ACTION_EDIT);
                    setType("vnd.android.cursor.item/event");
                    putExtra("title", name);
                    putExtra("beginTime", time);
//                    putExtra("endTime", endTimeMillis);
                    putExtra("description", "new event")
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                        // This will open the "Complete action with" dialog if the user doesn't have a default app set.
                        context.startActivity(this)
                    } else {
                        context.startActivity(Intent.createChooser(this, "select"))
                    }
                }
            }
        }

    }

}
