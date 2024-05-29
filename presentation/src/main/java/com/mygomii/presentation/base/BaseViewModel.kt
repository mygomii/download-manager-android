package com.mygomii.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {
    protected val viewModelScope = CoroutineScope(Dispatchers.Main)

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}