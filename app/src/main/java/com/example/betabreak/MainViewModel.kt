package com.example.betabreak

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(){
    /*
    Class Name: MainViewModel
    Class Description: This class is a ViewModel class that is used to manage the data for the MainActivity class.
     */
    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    init {
        /*
        Function Name: init
        Function Description: This function is called when an instance of the MainViewModel
        class is created. It is used to initialize the ViewModel. First check if the app is ready
        and if yes, wait 3 seconds. Can add functionality to check if the app is ready.
         */
        viewModelScope.launch {
            delay(3000L) // Delay for 3 seconds
            _isReady.value = true
        }
    }
}