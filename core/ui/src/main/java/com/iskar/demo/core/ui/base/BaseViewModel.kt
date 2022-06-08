package com.iskar.demo.core.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iskar.demo.core.utils.loge
import kotlinx.coroutines.*
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

@OptIn(KoinApiExtension::class)
abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected val mainContext: CoroutineContext = Dispatchers.Main
    protected val defaultContext: CoroutineContext = Dispatchers.Default
    protected val ioContext: CoroutineContext = Dispatchers.IO

    val isLoading = MutableLiveData(false)

    protected val defaultErrorHandler = CoroutineExceptionHandler { _, throwable ->
        sendError(throwable.message)
        loge(throwable)
    }

    open val errorLiveData: LiveData<String> = MutableLiveData()

    protected fun launch(
        loadingLiveData: MutableLiveData<Boolean>? = isLoading,
        dispatcher: CoroutineContext = mainContext,
        scope: CoroutineScope = viewModelScope,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return scope.launch(dispatcher + defaultErrorHandler) {
            try {
                withContext(mainContext) {
                    loadingLiveData?.value = true
                }
                this.block()
            } finally {
                withContext(mainContext) {
                    loadingLiveData?.value = false
                }
            }
        }
    }

    open fun sendError(message: String?) = (errorLiveData as MutableLiveData).postValue(message)

}
