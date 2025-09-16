package com.example.myapplication.model.abstract

import com.example.myapplication.model.*

abstract class RawStatus() {
    open var strength: Int = 0
    open var dexterity: Int = 0
    open var constitution: Int = 0
    open var intelligence: Int = 0
    open var wisdom: Int = 0
    open var charisma: Int = 0
}
