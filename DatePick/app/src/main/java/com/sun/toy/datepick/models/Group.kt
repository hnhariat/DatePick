package com.sun.toy.datepick.models

import io.realm.RealmObject
import io.realm.annotations.Required

/**
 * Created by 1100160 on 2017. 6. 29..
 */

class Group : RealmObject() {
    @Required
    private var name = ""
////    @Required
////    private var eventId = 0
//
    fun getName(): String {
        return name
    }
//
////    fun getEventId(): Int {
////        return eventId
////    }
//
    fun setName(name: String) {
        this.name = name
    }
//
////    fun setEvnetId(id: Int) {
////        this.eventId = id
////    }
}
