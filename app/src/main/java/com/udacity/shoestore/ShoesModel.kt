package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesModel : ViewModel() {
    fun addShoe(shoe: Shoe) {
        _shoeList.postValue(_shoeList.value?.plus(shoe))
    }

    private val _shoeList = MutableLiveData<List<Shoe>>().also {
        it.value = listOf(
            Shoe(
                "Walking Shoes MW880",
                25.5,
                "New Balance",
                "Bought at October 2018. A Pair of very nice shoes!",
                emptyList()
            )
        )
    }
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList


}