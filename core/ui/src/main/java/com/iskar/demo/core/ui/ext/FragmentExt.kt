package com.iskar.demo.core.ui.ext

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), text, duration).show()
}