package com.udacity.shoestore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeDetailModel : ViewModel() {

    val name = MutableLiveData("")

    val company = MutableLiveData("")

    val size = MutableLiveData("")

    val description = MutableLiveData("")
}