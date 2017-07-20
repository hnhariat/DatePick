package com.sun.toy.datepick

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sun.toy.datepick.base.OnClickDeliverer
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by 1100160 on 2017. 6. 26..
 */
class AdapterEvent : RecyclerView.Adapter<AdapterEvent.ViewHolder>() {

    var mList = ArrayList<Event>()
    var mOnClickDeliver: OnClickDeliverer? = null


    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_event, null, false) as View
        var viewHolder = ViewHolder(view)
        view.setOnClickListener(View.OnClickListener {
            mOnClickDeliver?.onClickDeliver(view)
        })
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var item = mList.get(position)
        holder.apply {
            holder?.txtTitle?.apply {
                text = item.name
            }
        }
        holder.apply {
            holder?.txtTime?.apply {
                var cal = Calendar.getInstance() as Calendar
                cal.timeInMillis = item.time
                text = DateFormat.getLongDateFormat(context).format(Date(cal.timeInMillis))
            }
        }
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView?
        var txtTime: TextView?

        init {
            txtTitle = itemView?.findViewById(R.id.txt_title) as TextView?
            txtTime = itemView?.findViewById(R.id.txt_time) as TextView?
        }
    }

    fun setOnClickDeliverer(l: OnClickDeliverer) {
        mOnClickDeliver = l
    }

    fun setList(list: ArrayList<Event>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    fun item(pos: Int): Event {
        return mList?.get(pos)
    }
}