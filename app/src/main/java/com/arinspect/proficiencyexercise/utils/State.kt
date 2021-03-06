package com.arinspect.proficiencyexercise.utils

/**
 * Created Date: 27-05-2020
 * Purpose: Class for managing the various states.
 */
open class State

data class LoadingState(val tag: Any? = null) : State()

data class SuccessState(val tag: Any? = null, val message: String = "") : State()

data class EmptyState(val tag: Any? = null) : State()

data class ErrorState(val tag: Any? = null, val throwable: Throwable) : State()

