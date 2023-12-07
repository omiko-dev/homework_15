package com.example.shemajamebeli__5.contacts.model

import retrofit2.Call
import retrofit2.http.GET

interface HttpService {
    @GET("744fa574-a483-43f6-a1d7-c65c87b5d9b2/")
    fun getData(): Call<List<ContactItem>>
}