package com.mountech.binner.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    data object Request: Screen()

    @Serializable
    data object History: Screen()
}