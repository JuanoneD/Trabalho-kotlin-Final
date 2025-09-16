package com.example.myapplication.model


class Dice {
    companion object
    {
        fun rool1d10():Number
        {
            return (0..10).random() + 1
        }
        fun rool1d8():Number
        {
            return (0..8).random() + 1;
        }
        fun rool1d4():Number
        {
            return (0..4).random() + 1;
        }
        fun rool3d6():Number
        {
            return (0..6).random() + (0..6).random() + (0..6).random() + 3;
        }
        fun rool4d6DropLowest():Number
        {
            val d1 = (0..6).random() + 1;
            val d2 = (0..6).random() + 1;
            val d3 = (0..6).random() + 1;
            val d4 = (0..6).random() + 1;
            return d1 + d2 + d3 + d4 - minOf(d1, d2, d3, d4);
        }
    }
}