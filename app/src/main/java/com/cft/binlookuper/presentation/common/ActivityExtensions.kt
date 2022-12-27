package com.cft.binlookuper.presentation.common

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.showActionBarArrow() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
}

fun AppCompatActivity.hideActionBarArrow() {
    supportActionBar?.setDisplayHomeAsUpEnabled(false)
}