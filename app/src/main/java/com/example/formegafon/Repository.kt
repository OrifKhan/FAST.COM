package com.example.formegafon

import android.icu.util.Calendar
import com.example.formegafon.Data.Client
import com.example.formegafon.Data.Constant
import com.example.formegafon.Data.Constant.Companion.ID_API
import com.example.formegafon.Data.Constant.Companion.ID_INTERNET_SPEED
import com.example.formegafon.Data.Constant.Companion.ID_LATENCY
import com.example.formegafon.Data.Constant.Companion.ID_LOADED
import com.example.formegafon.Data.Constant.Companion.ID_SERVER
import com.example.formegafon.Data.Constant.Companion.ID_SPEED
import com.example.formegafon.Data.Constant.Companion.ID_USER

class Repository() {
    private val list = mutableListOf<Client>()
    private var clientCountry:  String?=null
    private var apiAddress:  String?=null
    private var unloaded:  String?=null
    private var loaded:  String?=null
    private var serverCountry:  String?=null
    private var yourSpeedInternet:  String?=null
    private var speed:  String?=null
    private var testData:  String?=null

    fun setClientList(info: MutableList<Map<String,String>>): List<Client> {
        info.forEach() {

            info.forEach(){

                    it[ID_USER]?.let { it1 -> clientCountry=it1 }
                    it[ID_API]?.let { it1 -> apiAddress=it1 }
                    it[ID_LATENCY]?.let { it1 -> unloaded=it1}
                    it[ID_LOADED]?.let { it1 -> loaded=it1 }
                    it[ID_SERVER]?.let { it1 -> serverCountry=it1 }
                    it[ID_INTERNET_SPEED]?.let { it1 -> yourSpeedInternet =it1}
                    it[ID_SPEED]?.let { it1 -> speed=it1 }


            }
            list.add(Client(clientCountry,apiAddress, unloaded, loaded,serverCountry,yourSpeedInternet,
        speed, Calendar.DAY_OF_WEEK_IN_MONTH.toString())



        /*Client(
                    clientCountry = it[Constant.ID_USER],
                    apiAddress = it[Constant.ID_API],
                    unloaded = it[Constant.ID_LATENCY],
                    loaded = it[Constant.ID_LOADED],
                    serverCountry = it[Constant.ID_SERVER],
                    yourSpeedInternet = it[ID_INTERNET_SPEED],
                    speed = it[Constant.ID_SPEED],
                    testData = android.icu.util.Calendar.DATE.toString()
                )*/
            )
        }
        return list
    }

    fun getList(): List<Client> {
        return list.toList()
    }
}


