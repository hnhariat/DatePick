package com.sun.toy.datepick

/**
 * Created by 1100160 on 2017. 6. 26..
 */
class Event() {

    var time: Long = 0

    var name: String = ""

    constructor(time: Long, name: String) : this() {
        this.time = time;
        this.name = name;
    }
}
