package com.mountech.binner.entity

//На сайте предупреждали, что любое поле может оказаться null
interface BinInfo {
    val number: NumberInfo?
    val scheme: String?
    val type: String?
    val brand: String?
    val country: Country?
    val bank: Bank?
}