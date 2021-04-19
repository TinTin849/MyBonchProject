package com.example.mybonchproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val _viewState = MutableLiveData<MessengerDataViewState>()
    val viewState: LiveData<MessengerDataViewState> get() = _viewState

    fun addingOfMes(mes: String){
        if (_viewState.value != null) {
            //Log.d("testing", "second mess")
            _viewState.value!!.workArr.add(mes)
            val numAns = _viewState.value!!.numOfAnswer
            val ansArr = _viewState.value!!.workArr
            _viewState.value = MessengerDataViewState(mes, numAns, ansArr)
            //Log.d("testing", "funOk")
        } else {
            val initArr: ArrayList<String> = ArrayList()
            initArr.add(mes)
            _viewState.value = MessengerDataViewState(mes, 0, initArr)
        }
    }

    fun numOfAnswerRealisation(){
        if (_viewState.value?.numOfAnswer == 9) {
            _viewState.value?.numOfAnswer = 0
        } else {
            _viewState.value?.numOfAnswer = _viewState.value?.numOfAnswer?.plus(1)!!
        }
        //Log.d("testing", "numOfAnswer ${_viewState.value!!.numOfAnswer}")
    }
}


