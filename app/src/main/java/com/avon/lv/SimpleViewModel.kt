package com.avon.lv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SimpleViewModel : ViewModel() {
//    val lastName = "Baek"
//    var name = "SeungHyun"
//    var likes = 0
//        private set

    private val _name = MutableLiveData("Ada")
    private val _lastName = MutableLiveData("Lovelace")
    private val _likes = MutableLiveData(0)

    val name: LiveData<String> = _name
    val lastName: LiveData<String> = _lastName
    val likes: LiveData<Int> = _likes


//    val popularity: Popularity
//        get() {
//            return when {
//                likes > 9 -> Popularity.STAR
//                likes > 4 -> Popularity.POPULAR
//                else -> Popularity.NORMAL
//            }
//        }

    val popularity: LiveData<Popularity> = Transformations.map(_likes) {
        when {
            it > 9 -> Popularity.STAR
            it > 4 -> Popularity.POPULAR
            else -> Popularity.NORMAL
        }
    }

    fun onLike() {
//        likes++
        _likes.value = (_likes.value ?: 0) + 1
        if (_likes.value!! > 10) {
            _likes.value = 0
        }
    }
}

enum class Popularity {
    NORMAL,
    POPULAR,
    STAR
}