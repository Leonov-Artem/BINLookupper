package com.cft.data.model

sealed class State {
    object Loading : State()
    class Success(val data: Any) : State()
    object SpeedLimitExceeded : State()
    class Error(val message: String) : State()
}