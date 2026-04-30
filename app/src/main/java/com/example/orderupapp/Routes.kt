package com.example.orderupapp

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {

    @Serializable
    data object Home : Routes()

    @Serializable
    data object Pedido : Routes()

    @Serializable
    data object Orden : Routes()

    @Serializable
    data class PedidoItem(val pedidoId: Int) : Routes()
}