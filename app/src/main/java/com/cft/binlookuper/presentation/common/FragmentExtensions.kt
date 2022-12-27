package com.cft.binlookuper.presentation.common

import androidx.activity.ComponentActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment

fun Fragment.addMenu() {
    context?.let {
        if (it is ComponentActivity && this is MenuProvider) {
            it.addMenuProvider(this, viewLifecycleOwner)
        }
    }
}

fun Fragment.setActionBarTitle(title: String) {
    requireActivity().title = title
}

fun Fragment.setActionBarTitle(titleResId: Int) {
    setActionBarTitle(getString(titleResId))
}