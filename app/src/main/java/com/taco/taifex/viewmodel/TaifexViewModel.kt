package com.taco.taifex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taco.taifex.domain.repository.TaifexRepository
import com.taco.taifex.domain.server.Result
import com.taco.taifex.model.TaifexModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaifexViewModel(
    private val repo: TaifexRepository
) : ViewModel() {

    private var _taifexData = MutableLiveData<List<TaifexModel.TaifexData>>()
    val taifexData: LiveData<List<TaifexModel.TaifexData>> = _taifexData

    private var _errMsg = MutableLiveData<String>()
    val errMsg: LiveData<String> = _errMsg

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getTaifexData() {
        _loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            repo.fetchTaifexData().collect {
                when (it) {
                    is Result.Success -> {
                        _taifexData.postValue(it.data.taifexList)
                    }
                    is Result.Error -> {
                        _errMsg.postValue(it.exception.message)
                    }
                }
                _loading.postValue(false)
            }
        }
    }
}