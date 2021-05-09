package com.example.mybonchproject.helpersForWork

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessengerAppViewModel : ViewModel() {
    private val _viewState = MutableLiveData<MessengerAppDataClass>()
    val viewState: LiveData<MessengerAppDataClass> get() = _viewState

    fun addingOfMes(mes: String){
        if (_viewState.value != null) {
            _viewState.value!!.workArr.add(mes)
            val numAns = _viewState.value!!.numOfAnswer
            val ansArr = _viewState.value!!.workArr
            _viewState.value = MessengerAppDataClass(mes, numAns, ansArr)
        } else {
            val initArr: ArrayList<String> = ArrayList()
            initArr.add(mes)
            _viewState.value = MessengerAppDataClass(mes, 0, initArr)
        }
    }

    fun numOfAnswerRealisation(){
        if (_viewState.value?.numOfAnswer == 9) {
            _viewState.value?.numOfAnswer = 0
        } else {
            _viewState.value?.numOfAnswer = _viewState.value?.numOfAnswer?.plus(1)!!
        }
    }
}


