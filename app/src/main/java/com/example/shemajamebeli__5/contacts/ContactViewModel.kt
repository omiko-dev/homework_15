package com.example.shemajamebeli__5.contacts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shemajamebeli__5.RetrofitClient
import com.example.shemajamebeli__5.contacts.model.ContactItem
import com.example.shemajamebeli__5.contacts.model.HttpService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactViewModel : ViewModel() {

    private val retrofit = RetrofitClient.getContactClient()
    private val contactApi = retrofit.create(HttpService::class.java)

    private var _contactSharedFlow = MutableSharedFlow<List<ContactItem>>()
    val contactSharedFlow = _contactSharedFlow.asSharedFlow()

    private var allContactItems: List<ContactItem> = emptyList()

    fun filterData(searchText: String) {
        viewModelScope.launch {
            val filterData = if (searchText.isNotEmpty()) {
                allContactItems.filter { it.owner.contains(searchText) }
            } else {
                allContactItems
            }

            _contactSharedFlow.emit(filterData)
        }
    }

    fun fetch() {
        contactApi.getData().enqueue(object : Callback<List<ContactItem>> {
            override fun onResponse(
                call: Call<List<ContactItem>>,
                response: Response<List<ContactItem>>
            ) {
                viewModelScope.launch {
                    val items = response.body()
                    if (response.isSuccessful) {
                        items?.let {
                            allContactItems = it
                            filterData("")
                        }
                    } else {
                        Log.e("Api", "Error: ${response.code()}")
                    }
                }
            }
            override fun onFailure(call: Call<List<ContactItem>>, t: Throwable) {
                Log.e("Api", "Error: ${t.message}")
            }
        })
    }
}