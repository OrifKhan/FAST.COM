package com.example.formegafon

import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {
@POST("megafon.tj/")
suspend  fun sendThis(@Body string: String)
}