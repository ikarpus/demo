package com.iskar.demo.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.iskar.demo.core.ui.ext.showToast
import com.iskar.demo.core.ui.ext.subscribe
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(
    @LayoutRes layoutRes: Int,
    private val bindingFactory: (View) -> VB
) : Fragment(layoutRes) {

    lateinit var binding: VB

    protected val viewModel: VM by lazy {
        getViewModel(
            owner = { ViewModelOwner.from(this, this) },
            clazz = getViewModelKClass()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = super.onCreateView(inflater, container, savedInstanceState)?.also {
        binding = bindingFactory.invoke(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    @CallSuper
    open fun observeViewModel() {
        subscribe(viewModel.errorLiveData, ::propagateError)
    }

    private fun propagateError(message: String) = showToast(message)

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelKClass(): KClass<out VM> {
        val actualClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        return actualClass.kotlin
    }
}
